echo "starting nameserver1"
nohup sh rocketmq_namesrv/bin/mqnamesrv -c rocketmq_namesrv/conf/namesrv1.properties &
echo "started nameserver1"
echo "starting nameserver2"
nohup sh rocketmq_namesrv/bin/mqnamesrv -c rocketmq_namesrv/conf/namesrv2.properties &
echo "started nameserver2"
