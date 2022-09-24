package net.maku.quartz.job;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import net.maku.framework.common.utils.ExceptionUtils;
import net.maku.quartz.entity.ScheduleJobEntity;
import net.maku.quartz.entity.ScheduleJobLogEntity;
import net.maku.quartz.enums.ScheduleStatusEnum;
import net.maku.quartz.service.ScheduleJobLogService;
import net.maku.quartz.utils.ScheduleUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;
import java.util.Date;

@Slf4j
public abstract class AbstractScheduleJob extends QuartzJobBean {

    /**
     * ThreadLocal是一个线程内部的存储类，可以在指定线程内存储数据，数据存储以后，只有指定线程可以得到存储数据。<p>
     * ThreadLocal提供了线程内存储变量的能力，这些变量不同之处在于每一个线程读取的变量是对应的互相独立的。通过get和set方法就可以得到当前线程对应的值。
     */
    private static final ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    protected void executeInternal(JobExecutionContext context) {
        // 定时任务对象
        ScheduleJobEntity scheduleJob = new ScheduleJobEntity();
        // springframework提供的BeanUtils : 将 a 拷贝到 b 。 apache包提供的BeanUtils : 将 b拷贝到a
        BeanUtils.copyProperties(context.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY), scheduleJob);
        try {
            threadLocal.set(new Date());
            //执行任务
            doExecute(scheduleJob);
            //保存成功任务日志
//            saveLog(scheduleJob, null);
        } catch (Exception e) {
            log.error("任务执行失败，任务ID：{}", scheduleJob.getId(), e);
            //保存失败任务日志
//            saveLog(scheduleJob, e);
        }
    }

    /**
     * 执行spring bean方法
     */
    protected void doExecute(ScheduleJobEntity scheduleJob) throws Exception {
        log.info("准备执行任务，任务ID：{}", scheduleJob.getId());

        Object bean = SpringUtil.getBean(scheduleJob.getBeanName());
        Method method = bean.getClass().getDeclaredMethod(scheduleJob.getMethod(), String.class);
        method.invoke(bean, scheduleJob.getParams());

        log.info("任务执行完毕，任务ID：{}", scheduleJob.getId());
    }

    /**
     * 保存 log
     */
    protected void saveLog(ScheduleJobEntity scheduleJob, Exception e) {
        Date startTime = threadLocal.get();
        threadLocal.remove();

        // 执行总时长
        long times = System.currentTimeMillis() - startTime.getTime();

        // 保存执行记录
        ScheduleJobLogEntity log = new ScheduleJobLogEntity();
        log.setJobId(scheduleJob.getId());
        log.setJobName(scheduleJob.getJobName());
        log.setJobGroup(scheduleJob.getJobGroup());
        log.setBeanName(scheduleJob.getBeanName());
        log.setMethod(scheduleJob.getMethod());
        log.setParams(scheduleJob.getParams());
        log.setTimes(times);
        log.setCreateTime(new Date());

        if (e != null) {
            log.setStatus(ScheduleStatusEnum.PAUSE.getValue());
            String error = StringUtils.substring(ExceptionUtils.getExceptionMessage(e), 0, 2000);
            log.setError(error);
        } else {
            log.setStatus(ScheduleStatusEnum.NORMAL.getValue());
        }

        // 保存日志
        SpringUtil.getBean(ScheduleJobLogService.class).save(log);
    }

}
