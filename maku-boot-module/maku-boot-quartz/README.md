# 定时任务

## 1.基于@Scheduled注解的简单定时任务

## 2. 集成Quartz 定时框架实现定时

#### 概述

> Java编写的开源作业调度框架
> - 集成方便
> - 可集群部署也可单机运行
> - 可以通过JVM独立运行
> - 支持持久化
> - 支持多任务调度和管理

#### 组成

> Quartz由三部分组成
> - 任务：JobDetail
> - 触发器：Trigger [SimpleTrigger · CronTrigger]
> - 调度器：Scheduler

##### JobDetail

> JobDetail 由以下部分组成：
> - JobKey     :  Job的名字name和分组Group
> - JobClass
> - JobDataMap :  任务相关的数据
> - JobBuilder

###### JobDetail源码

```java
package org.quartz;

import java.io.Serializable;

public interface JobDetail extends Serializable, Cloneable {
    JobKey getKey();

    String getDescription();

    Class<? extends Job> getJobClass();

    JobDataMap getJobDataMap();

    boolean isDurable();

    boolean isPersistJobDataAfterExecution();

    boolean isConcurrentExectionDisallowed();

    boolean requestsRecovery();

    Object clone();

    JobBuilder getJobBuilder();
}
```

###### JobDetail示例

```java
public class JobDetailClass {
    private void jobDetailFun() {
        Map<String, String> jobData = new HashMap<>();
        String jobName = "schedulerJob";
        String jobGroup = "schedulerGroup";
        jobData.put("key00", "value00");
        JobDetail jobDetail = JobBuilder.newJob(SchedulerJob.class)
                .withIdentity(jobName, jobGroup)
                .usingJobData("key01", "value01")
                .usingJobData(jobData)
                .storeDurably()
                .build();
    }
}
```

##### Trigger

###### 描述：Trigger规定触发执行Job实现类，主要有SimpleTrigger和CronTrigger两个实现类。

> Trigger由以下部分组成：
> - TriggerKey（job的名字name和分组group）
> - JobDataMap（Trigger相关的数据，同JobDetail中JobDataMap，存相同key，若value不同，会覆盖前者。）
> - ScheduleBuilder

```java
public class TriggerClass {
    /**
     * ScheduleBuilder：有（CronScheduleBuilder、SimpleScheduleBuilder、CalendarIntervalScheduleBuilder、DailyTimeIntervalScheduleBuilder）常用前2种。
     */
    private void triggerFun() {
        //CronScheduleBuilder
        String triggerName2 = "schedulerJob2";
        String triggerGroup2 = "schedulerGroup2";
        String jobTime = "0 0 * * * ?";
        Trigger trigger2 = TriggerBuilder
                .newTrigger()
                .withIdentity(triggerName2, triggerGroup2)
                .startAt(DateBuilder.futureDate(1, IntervalUnit.SECOND))
                .withSchedule(CronScheduleBuilder.cronSchedule(jobTime))
                .startNow()
                .build();
        //SimpleScheduleBuilder
        String triggerName = "schedulerJob";
        String triggerGroup = "schedulerGroup";
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(triggerName, triggerGroup)
                .withSchedule(SimpleScheduleBuilder)
                .repeatSecondlyForever(1)
                .withIntervalInSeconds(0)
                .withRepeatCount(0).startNow()
                .build();
    }
}
```

##### Scheduler

###### 描述：调度器就是为了读取触发器Trigger从而触发定时任务JobDetail。可以通过SchedulerFactory进行创建调度器，分为StdSchedulerFactory（常用）和DirectSchedulerFactory两种。

> - StdSchedulerFactory使用一组属性（放在配置文件中）创建和初始化调度器，然后通过getScheduler()方法生成调度程序。
> - DirectSchedulerFactory不常用，容易硬编码。

```java

public class SchedulerClass {
    private void schedulerFun() {
        //建好jobDetail,trigger
        // ... ...
        //StdSchedulerFactory方式，用的多
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler schedulerStd = schedulerFactory.getScheduler();

        //DirectSchedulerFactory方式
        DirectSchedulerFactory directSchedulerFactory = DirectSchedulerFactory.getInstance();
        Scheduler schedulerDir = directSchedulerFactory.getScheduler();

        //执行调度
        schedulerStd.scheduleJob(jobDetail, trigger);
        schedulerStd.start();
    }
}

```

##### Cron表达式

###### 描述：定时任务离不开Cron表达式设置具体执行时间或执行周期，Cron表达式是一个字符串，一般有两种表达。

> - 秒 分 小时 日 月 星期 年
> - 秒 分 小时 日 月 星期

###### 其中，年份即为可选的，所以一般表达式为6-7个域，每个域以空格分开。其中的星期除了可以使用英文缩写，也可以用数字1-7 数字来表示，注意1表示的是星期日，7表示的星期六。)

