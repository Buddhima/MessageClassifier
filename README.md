Message Classifier
==================

Introduction
------------

Message Classifier project is to classify messages. This is implement on top of Akka (http://akka.io/) distributed application building tool. Classification is done currently using uClassify (http://uclassify.com/) online text classification services.

Prerequisites
-------------
To run this project in your IDE, you need to add following JAR files to project dependencies:

* Akka 2.3-M2 distribution : lib/scala-library-2.10.2.jar & lib/akka/akka-actor_2.10-2.3-M2.jar

  http://downloads.typesafe.com/akka/akka-2.3-M2.zip

* commons-codec-1.4.jar (or later)

  http://commons.apache.org/codec/

* commons-lang-2.4.jar (or later)

  http://commons.apache.org/lang/

* log4j-1.2.15.jar (or later)

  http://logging.apache.org/log4j/1.2/download.html

* Cofig-1.0.0.jar (or later)

  http://repo.typesafe.com/typesafe/releases/com/typesafe/config/1.0.0/config-1.0.0.jar

* objectdb.jar

  http://www.objectdb.com/object/db/database/download

References
----------

1. Akka Documentation (2.3-M2)

   http://doc.akka.io/docs/akka/snapshot/java.html

2. uClassify Text Classification Services

   http://www.uclassify.com/
   
3. Framework for uClassify services

   https://github.com/udy/UClassify

