#!/bin/bash
exec >> /var/log/BatchBuild.log 2>&1
echo "********************************************************************************************************************"
echo $(date -u) "Starting to invoke server_start.sh"
chmod 777 /root/scripts/pac-batch-ec2-version-replace.jar
sleep 5s
echo $(date -u) "Ending server_start.sh"