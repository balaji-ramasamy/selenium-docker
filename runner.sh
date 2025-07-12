#!/bin/bash

#-------------------------------------------------
# This script expects the following env varaiables
#  HUB_HOST
#  BROWSER
#  TEST_SUITE
#  THREAD_COUNT
#-------------------------------------------------

# print the inputs
echo "-------------------------------------------------"
echo "HUB_HOST  :   ${HUB_HOST:-hub}"
echo "BROWSER   :   ${BROWSER:-chrome}"
echo "THREAD_COUNT  :   ${THREAD_COUNT:-1}"
echo "TEST_SUITE  :   ${TEST_SUITE}"
echo "------------------------------------------------"

# checking hub status
echo "checking hub status"
count=0
while [ "$( curl -s http://${HUB_HOST:-hub}:4444/status | jq -r .value.ready )" != "true" ]
do
  count=$((count+1))
  echo "Attempt: ${count}"
  if [ $count -ge 30 ]; then
    echo "**** HUB IS NOT READY WITHIN 30 secs ****"
    exit 1
  fi
  sleep 1
done

# at this point selenium grid should be up
echo "selenium grid is up and running. Running the test...!"

# start the java command
java -cp 'libs/*' \
     -Dselenium.grid.enabled=true \
     -Dselenium.grid.host="${HUB_HOST:-hub}" \
     -Dbrowser="${BROWSER:-chrome}" \
     org.testng.TestNG \
     -threadcount "${THREAD_COUNT:-1}" \
     test-suites/"${TEST_SUITE}"
