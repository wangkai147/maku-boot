package net.maku.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.maku.framework.common.dao.BaseDao;
import net.maku.system.entity.SysParamsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 参数管理
 *
 * @author wangkai
 */
@Mapper
public interface SysParamsDao extends BaseDao<SysParamsEntity> {

    default boolean isExist(String paramKey) {
        return this.exists(new QueryWrapper<SysParamsEntity>().eq("param_key" , paramKey));
    }
}