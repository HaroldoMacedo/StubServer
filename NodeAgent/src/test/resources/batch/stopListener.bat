
SET PortNodeAgent=%1
SET PortApplication=%2


REM  Stop Listener
curl -X DELETE localhost:%PortNodeAgent%/stub/execution/listener/port/%PortApplication%
