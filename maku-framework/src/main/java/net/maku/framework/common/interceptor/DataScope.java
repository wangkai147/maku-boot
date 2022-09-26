package net.maku.framework.common.interceptor;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 数据范围
 *
 * @author wangkai
 */
@Data
@AllArgsConstructor
public class DataScope {
    private String sqlFilter;

}