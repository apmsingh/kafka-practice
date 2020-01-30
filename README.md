# kafka-practice

To Start a kafka server:

#For Windows go to kafka directory:
1. Go to .\bin\windows\ directory
2. Open a command line
3. To start zookeeper execute the command "zookeeper-server-start.bat ../../config/zookeeper.properties"
4. To start a kafka broker execute the command "kafka-server-start.bat ../../config/server.properties"


# To create a topic in kafka using cmd
1. Go to .\bin\windows\ directory
2. Open a command line
3. Execute this command "kafka-topics.bat --zookeeper localhost:2181 --create --topic mytopic --partitions 2 --replication-factor 1"
4. To get details about topic "kafka-topics.bat --zookeeper localhost:2181 --describe --topic mytopic"
4. to delete the topic "kafka-topics.bat --zookeeper localhost:2181 --delete --topic mytopic"

# To start the producer console
1. Go to .\bin\windows\ directory
2. Open a command line
3. Execute this command "kafka-console-producer.bat --broker-list localhost:9092 --topic mytopic"

# To start the consumer console
1. Go to .\bin\windows\ directory
2. Open a command line
3. Execute this command "kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic mytopic"


# To start the more broker in same system.
1. Make a copy of server.properties file and make the below changes mentioned.
2. broker.id = <new-id>
3. broker port = <new-port>
4. Change the log directory