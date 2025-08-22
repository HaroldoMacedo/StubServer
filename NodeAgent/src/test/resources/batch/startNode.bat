
SET NodePort=%1
SET SpringBootRoot=%HOMEPATH%\Downloads\SpringBoot\*

java -cp NodeAgent.jar;%SpringBootRoot% haroldo.stub.api.agent.Main %NodePort%

