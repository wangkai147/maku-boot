package net.maku.quartz.utils;

import net.maku.quartz.job.AbstractScheduleJob;
import org.quartz.DisallowConcurrentExecution;

/**
 * 禁止并发
 * <p>[@DisallowConcurrentExecution] :禁止并发执行多个相同定义的JobDetail, 这个注解是加在Job类上的, 但意思并不是不能同时执行多个Job,而是不能并发执行同一个Job Definition(由JobDetail定义), 但是可以同时执行多个不同的JobDetail。
 * <p>[@PersistJobDataAfterExecution]:加在Job上,表示当正常执行完Job后, JobDataMap中的数据应该被改动, 下次执行相同的job时, 会接受到已经更新的数据。
 */
@DisallowConcurrentExecution
public class ScheduleDisallowConcurrentExecution extends AbstractScheduleJob {

}
