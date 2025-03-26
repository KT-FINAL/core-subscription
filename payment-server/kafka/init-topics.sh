#!/bin/bash

# 내부 컨테이너에서 접근할 때는 kafka:29092
BOOTSTRAP_SERVER="kafka:29092"

echo "[INIT] Waiting for Kafka to be fully ready..."

RETRIES=30
until /usr/bin/kafka-topics --bootstrap-server $BOOTSTRAP_SERVER --list >/dev/null 2>&1 || [ $RETRIES -eq 0 ]; do
  echo "Waiting for Kafka to be ready..."
  sleep 2
  ((RETRIES--))
done

if [ $RETRIES -eq 0 ]; then
  echo "[ERROR] Kafka did not become ready in time."
  exit 1
fi

echo "[INIT] Kafka is ready. Creating topics..."

# 토픽 생성
/usr/bin/kafka-topics --create --if-not-exists --bootstrap-server $BOOTSTRAP_SERVER \
  --replication-factor 1 --partitions 1 --topic payment-completed

/usr/bin/kafka-topics --create --if-not-exists --bootstrap-server $BOOTSTRAP_SERVER \
  --replication-factor 1 --partitions 1 --topic subscription-failed

/usr/bin/kafka-topics --create --if-not-exists --bootstrap-server $BOOTSTRAP_SERVER \
  --replication-factor 1 --partitions 1 --topic member-notification-topic

echo "[INIT] Topic creation complete."
