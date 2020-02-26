echo "starting nameserver1"
nohup sh rocketmq-all-4.6.1_namesrv/bin/mqnamesrv -c rocketmq-all-4.6.1_namesrv/conf/namesrv1.properties &
echo "started nameserver1"
echo "starting nameserver2"
nohup sh rocketmq-all-4.6.1_namesrv/bin/mqnamesrv -c rocketmq-all-4.6.1_namesrv/conf/namesrv2.properties &
echo "started nameserver2"
