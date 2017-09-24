
set ROOT_PATH=%~dp0
Set WEB_DRIVER_PATH=%ROOT_PATH%%WEB_DRIVER_RELATIVE_PATH%
Set STANDALONE_SERVER_PATH=%ROOT_PATH%%SELENIUM_SERVER_RELATIVE_PATH%

Set PATH=%WEB_DRIVER_PATH%;%PATH%

start "HUB" "%JAVA_EXE_PATH%" -jar "%STANDALONE_SERVER_PATH%" -role hub -maxSession 20 -browserTimeout 240 -timeout 30

start "NODE" "%JAVA_EXE_PATH%" -jar "%STANDALONE_SERVER_PATH%" -role node -hub http://localhost:4444/grid/register -browser "browserName=firefox,maxInstances=5"  -browser "browserName=chrome,maxInstances=5"  -browser "browserName=safari,maxInstances=5"  -browser "browserName=internet explorer,maxInstances=5" -browser "browserName=opera,maxInstances=5"

start iexplore http://localhost:4444/grid/console


::  ************Comments Section************
::  There are additional hub startup flags, like below:
:: -maxSession 20
:: -browserTimeout 240
:: -remoteControlPollingIntervalInSeconds 180
:: -sessionMaxIdleTimeInSeconds 240
:: -newSessionMaxWaitTimeInSeconds 250
:: -timeout 30
