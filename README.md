Example using Zookeeper for a Distributed Queue via Curator
============================================================


This example uses Netflix's Curator recipe for Distributed Queueing backed by Zookeeper.  To use the example, simply run a local zookeeper.  Then compile.  

This JUnit test uses the queue:
./src/test/java/com/github/boneill42/ZkDqTest.java

For details of the setup, look at the constructor in:
./src/main/java/com/github/boneill42/ZkDqQueuer.java

