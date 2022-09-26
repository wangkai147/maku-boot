package net.maku.framework.security.mobile;

/**
 * 手机短信登录，验证码效验
 * @author wangkai
 */
public interface MobileVerifyCodeService {

    /**
     * 短信验证码效验
     * @param mobile 手机号
     * @param code 验证码
     * @return 是否通过
     */
    boolean verifyCode(String mobile, String code);
}
