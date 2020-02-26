echo "starting nameserver1"
nohup sh rocketmq-all-4.6.0_namesrv/distribution/target/rocketmq-4.6.0/bin/mqnamesrv -c rocketmq-all-4.6.0_namesrv/distribution/target/rocketmq-4.6.0/conf/namesrv1.properties &
echo "started nameserver1"
echo "starting nameserver2"
nohup sh rocketmq-all-4.6.0_namesrv/distribution/target/rocketmq-4.6.0/bin/mqnamesrv -c rocketmq-all-4.6.0_namesrv/distribution/target/rocketmq-4.6.0/conf/namesrv2.properties &
echo "started nameserver2"
