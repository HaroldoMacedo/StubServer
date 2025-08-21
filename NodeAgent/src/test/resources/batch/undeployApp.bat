
@ECHO OFF

SET PortNodeAgent=%1
SET PortApplication=%2

REM Deploy application
curl -X DELETE localhost:%PortNodeAgent%/stub/execution/listener/port/%PortApplication%/application  -H "Content-Type: application/json"
