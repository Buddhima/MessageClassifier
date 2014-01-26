The Thrift server should be placed here. But for the convenience we have removed the server files and uploaded externally.
Follow following 2 major sections to send messages to Thrift server.

To run Thrift server
--------------------
* Download & extract files from: http://www.filedropper.com/thriftserver
* Install Ant and configure system Path variable to use "ant" commands
* Using cmd/terminal access inside "thriftServer" of directory
* Execute "ant"
* Execute "ant agentTestServer"

To enable sending messages
--------------------------
* Uncomment line in app/controllers/MainController.java - method "startFrontend" - last line
* Copy all jars inside "lib" directory of the downloaded server in to "lib" directory of this project
* Recompile and run using "play" commands
 
