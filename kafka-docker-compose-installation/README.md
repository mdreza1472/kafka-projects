## Start Kafka using Docker Compose
docker compose -f docker-compose.yml up -d

## Move into Kafka container
docker ps      # Get the Kafka container ID or name
docker exec -it <kafka_container_id_or_name> /bin/sh

## Go to Kafka installation folder
cd /opt/kafka_<version>/bin

## Start Zookeeper (only if not already running from Docker Compose)
./zookeeper-server-start.sh ../config/zookeeper.properties

## Start Kafka Server (Broker)
./kafka-server-start.sh ../config/server.properties

## Create Kafka Topic
./kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic rezatechie

## Start Producer CLI
./kafka-console-producer.sh --topic rezatechie --bootstrap-server localhost:9092

## Start Consumer CLI
./kafka-console-consumer.sh --topic rezatechie --from-beginning --bootstrap-server localhost:9092