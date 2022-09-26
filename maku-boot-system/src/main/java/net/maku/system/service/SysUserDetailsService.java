package net.maku.system.service;

import net.maku.system.entity.SysUserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface SysUserDetailsService {

    /**
     * 获取 UserDetails 对象
     *
     * @param userEntity 用户信息
     * @return UserDetails对象
     */
    UserDetails getUserDetails(SysUserEntity userEntity);
}
