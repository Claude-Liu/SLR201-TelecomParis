# SLR201-TelecomParis

## philoServer project

The philosopher problem with the clients on localhost and server on remote host.

```
  # in local machine
  $ cd lab/src
  $ javac -d ../bin -cp ./ philoServer/*.java
  $ cd ../bin
  $ java -cp ./ philoServer.TestClient host

  # in remote machine
  $ ssh user@host
  $ cd lab/src
  $ javac -d ../bin -cp ./ philoServer/*.java
  $ cd ../bin
  $ java -cp ./ philoServer.TestServer

```
