package net.maku.storage.properties;

import lombok.Data;

/**
 * 腾讯云存储配置项
 */
@Data
public class TencentStorageProperties {
    private String appId;
    private String region;
    private String endPoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
