echo "starting broker-a"
nohup sh rocketmq-all-4.6.1_2m-2s-sync/bin/mqbroker -c rocketmq-all-4.6.1_2m-2s-sync/conf/2m-2s-sync/broker-a.properties &
echo "started broker-a"
echo "starting broker-a-s"
nohup sh rocketmq-all-4.6.1_2m-2s-sync/bin/mqbroker -c rocketmq-all-4.6.1_2m-2s-sync/conf/2m-2s-sync/broker-a-s.properties &
echo "started broker-a-s"
echo "starting broker-b"
nohup sh rocketmq-all-4.6.1_2m-2s-sync/bin/mqbroker -c rocketmq-all-4.6.1_2m-2s-sync/conf/2m-2s-sync/broker-b.properties &
echo "starte broker-b"
echo "starting broker-b-s"
nohup sh rocketmq-all-4.6.1_2m-2s-sync/bin/mqbroker -c rocketmq-all-4.6.1_2m-2s-sync/conf/2m-2s-sync/broker-b-s.properties &
echo "started broker-b-s"
