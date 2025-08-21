
SET PortNodeAgent=%1
SET PortApplication=%2


REM  Start Listener
curl -X PUT localhost:%PortNodeAgent%/stub/execution/listener/port/%PortApplication%
