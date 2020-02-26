echo "starting broker-a"
nohup sh bin/mqbroker -c conf/2m-2s-sync/broker-a.properties &
echo "started broker-a"
echo "starting broker-a-s"
nohup sh bin/mqbroker -c conf/2m-2s-sync/broker-a-s.properties &
echo "started broker-a-s"
echo "starting broker-b"
nohup sh bin/mqbroker -c conf/2m-2s-sync/broker-b.properties &
echo "started broker-b"
echo "starting broker-b-s"
nohup sh bin/mqbroker -c conf/2m-2s-sync/broker-b-s.properties &
echo "started broker-b-s"
