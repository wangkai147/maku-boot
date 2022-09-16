# 创建数据库

1. 创建maku_boot数据库，数据库编码为utf8mb4
2. 执行db/mysql/maku-boot.sql文件，初始化数据库脚本

# 安装并启动Redis

暂无

# 修改配置文件

修改application-dev.yml文件，配置MySQL、Redis账号信息

# 编译项目

在maven的maku-boot目录下，双击install

# 启动项目

在maku-server工程里面，运行ServerApplication.java，则可启动项目

# 接口文档

本项目使用了swagger，生成接口文档数据，接口文档地址：http://localhost:8080/doc.html

# 文件存储服务

文件存储，目录已经实现本地存储、阿里云OSS等

阿里云OSS

```yaml
storage:
  enabled: true
  config:
    type: aliyun
    domain: xxx
    prefix:
  aliyun:
    access-key-id: xxx
    access-key-secret: xxx
    bucket-name: xxx
    end-point: xxx
```

本地存储

```yaml
storage:
  enabled: true
  config:
    type: local
    domain: http://localhost:8080
    prefix:
  local:
    path: D://upload
```


