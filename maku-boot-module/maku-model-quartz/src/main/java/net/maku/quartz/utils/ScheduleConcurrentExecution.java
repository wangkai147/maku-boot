package net.maku.quartz.utils;

import net.maku.quartz.job.AbstractScheduleJob;

/**
 * 允许并发（不会等待上一次任务执行完毕，只要时间到就会执行）
 *
 */
public class ScheduleConcurrentExecution extends AbstractScheduleJob {

}
