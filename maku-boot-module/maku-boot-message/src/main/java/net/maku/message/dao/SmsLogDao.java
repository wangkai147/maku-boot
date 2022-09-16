package net.maku.message.dao;

import net.maku.framework.common.dao.BaseDao;
import net.maku.message.entity.SmsLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 短信日志
*/
@Mapper
public interface SmsLogDao extends BaseDao<SmsLogEntity> {
	
}