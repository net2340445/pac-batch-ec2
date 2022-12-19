#!/bin/bash
exec >> /var/log/BatchBuild.log 2>&1
echo "********************************************************************************************************************"
echo $(date -u) "Starting to invoke sercer_validate.sh"
ls -lrta /root/scripts/pac-batch-ec2-0.1.jar
echo $(date -u) "Ending sercer_validate.sh"