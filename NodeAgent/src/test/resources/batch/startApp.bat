

SET PortNodeAgent=%1
SET Application=%2

REM Start application
curl -X PUT localhost:%PortNodeAgent%/stub/execution/application/%Application%

