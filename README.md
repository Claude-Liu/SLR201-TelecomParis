# SLR201-TelecomParis

## philoServer project

The philosopher problem with the clients on local host and server on remote host.

1. the local version. We solve the classic philosopher problem by adding a 'waiter', who will distribute the forks to every philosopher: 
   1. give the philosopher both of two forks when they are all available.
   2. let the philosopher wait when not both of the two forks are available.

```
 # in local machine
  $ cd lab/src
  $ javac -d ../bin -cp ./ philo/*.java
  $ cd ../bin
  $ java -cp ./ philo.TestPhilo outputfile(optional)
```



2. the remote version. We use socket to communicate between different JVM.

```
  # in local machine
  $ cd lab/src
  $ javac -d ../bin -cp ./ philoServer/*.java
  $ cd ../bin
  $ java -cp ./ philoServer.TestClient host

  $ ssh user@host
  
  # in remote machine
  $ cd lab/src
  $ javac -d ../bin -cp ./ philoServer/*.java
  $ cd ../bin
  $ java -cp ./ philoServer.TestServer

```
