package net.maku.system.service;

import net.maku.framework.common.page.PageResult;
import net.maku.framework.common.service.BaseService;
import net.maku.system.entity.SysLogLoginEntity;
import net.maku.system.query.SysLogLoginQuery;
import net.maku.system.vo.SysLogLoginVO;

import java.io.IOException;
import java.util.Map;

/**
 * 登录日志
 *
 * @author wangkai
 */
public interface SysLogLoginService extends BaseService<SysLogLoginEntity> {

    /**
     * Page page result.
     *
     * @param query the query
     * @return the page result
     */
    PageResult<SysLogLoginVO> page(SysLogLoginQuery query);

    /**
     * 保存登录日志
     *
     * @param username  用户名
     * @param status    登录状态
     * @param operation 操作信息
     */
    void save(String username, Integer status, Integer operation);

    /**
     * 导出登录日志
     *
     * @return the map
     * @throws IOException the io exception
     */
    Map<String, String> export() throws IOException;
}