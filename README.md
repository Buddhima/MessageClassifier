Message Classifier
==================

Introduction
------------

Message Classifier project is to classify messages. This is implement on top of Akka (http://akka.io/) distributed application building tool with the aid of Play framework (http://www.playframework.com/) the high velocity web framework for Java and Scala. Classification is done currently using uClassify (http://uclassify.com/) online text classification services.

You can view the user guide from http://goo.gl/2RifYh

Prerequisites
-------------
Initially you need to clone this project.
Make sure you have added "java" and "javac" to your system Path variable.
Create **"lib"** folder inside "MessageClassifier" folder.
Add following jar files into "lib" folder

* log4j-1.2.15.jar (or later)

  http://logging.apache.org/log4j/1.2/download.html
  
* objectdb.jar

  http://www.objectdb.com/object/db/database/download


To run this project you can choose either of following methods:

* Using Play Framework Distribution (http://www.playframework.com/download)
* Using Typesafe Activator (http://typesafe.com/activator)


**Using Play Framework Distribution**

* Download Play framework distribution (http://www.playframework.com/download) and extract.
* Add "play" folder path to system Path variable.
* Go inside project folder using command prompt/terminal and execute "play" command, which will launch Play console.
* Execute "eclipse" to create Eclipse project out of this. (For other IDEs refer: http://www.playframework.com/documentation/2.2.x/IDE).
* Execute "run" command to start the server and goto "http://localhost:9000/" .


**Using Typesafe Activator**

* Download Typesafe Activator (http://typesafe.com/activator).
* Open this project folder location using "Open Existing Projects" option.
* You can use activator to inspect code, test, run and export as Eclipse project or IDEA module.


References
----------

1. Akka Documentation (2.3-M2)

   http://doc.akka.io/docs/akka/snapshot/java.html
   
2. Play Framework (2.2.x)

   http://www.playframework.com/

3. uClassify Text Classification Services

   http://www.uclassify.com/
   
4. Framework for uClassify services

   https://github.com/udy/UClassify

