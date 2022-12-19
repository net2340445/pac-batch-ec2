#!/bin/bash
exec > /var/log/BatchBuild.log 2>&1
echo "********************************************************************************************************************"
echo $(date -u) "Starting to invoke server_stop.sh"
rm -rf /root/scripts/pac-batch-ec2-version-replace.jar
sleep 5s
echo $(date -u) "Ending server_stop.sh"