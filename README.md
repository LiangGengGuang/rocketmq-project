# rocketmq-project
### sync： 同步双写
git clone后，需重新配置2m-2s-sync文件夹下：  
* broker-a-s.properties
* broker-a.properties
* broker-b-s.properties
* broker-b.properties

四个文件中的参数（以实际开发环境为准）
#### 一、sync： 同步双写快速启动/关闭命令：
1. nameserver
    * sh namesrv_shutdown.sh
    * sh namesrv_start.sh
2. broker
    * sh sync_shutdown.sh
    * sh sync_start.sh
