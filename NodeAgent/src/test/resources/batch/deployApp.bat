
@ECHO OFF

SET PortNodeAgent=%1
SET PortApplication=%2
SET JsonFile=%3

REM Deploy application
curl -d @%JsonFile% -X PUT localhost:%PortNodeAgent%/stub/execution/listener/port/%PortApplication%/application  -H "Content-Type: application/json"
