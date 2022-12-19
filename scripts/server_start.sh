#!/bin/bash
exec >> /var/log/BatchBuild.log 2>&1
echo "********************************************************************************************************************"
echo $(date -u) "Starting to invoke server_start.sh"
chmod 755 /root/scripts/pac-batch-ec2-0.1.jar
sleep 5s
echo $(date -u) "Ending server_start.sh"