package net.maku.quartz.dao;

import net.maku.framework.common.dao.BaseDao;
import net.maku.quartz.entity.ScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 定时任务
*/
@Mapper
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {
	
}