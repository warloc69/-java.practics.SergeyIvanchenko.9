rmdir /q/s classServer
mkdir classServer
javac.exe -Xlint -d classServer -sourcepath src -classpath log\log4j-1.2.16.jar src\TestServer.java
pause
cd classServer
rmic -v1.2 FileServerImpl
pause