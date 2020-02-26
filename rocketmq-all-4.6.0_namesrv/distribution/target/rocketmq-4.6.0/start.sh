echo "starting nameserver1"
nohup sh bin/mqnamesrv -c conf/namesrv1.properties &
echo "started nameserver1"
echo "starting nameserver2"
nohup sh bin/mqnamesrv -c conf/namesrv2.properties &
echo "started nameserver2"
