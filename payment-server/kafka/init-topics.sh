#!/bin/bash

echo "[INIT] Waiting for Kafka to be fully ready..."

RETRIES=30
until kafka-topics --bootstrap-server kafka:9092 --list >/dev/null 2>&1 || [ $RETRIES -eq 0 ]; do
  echo "Waiting for Kafka to be ready..."
  sleep 2
  ((RETRIES--))
done

if [ $RETRIES -eq 0 ]; then
  echo "[ERROR] Kafka did not become ready in time."
  exit 1
fi

echo "[INIT] Kafka is ready. Creating topics..."

kafka-topics --create --if-not-exists --bootstrap-server kafka:9092 \
  --replication-factor 1 --partitions 3 --topic payment-completed

kafka-topics --create --if-not-exists --bootstrap-server kafka:9092 \
  --replication-factor 1 --partitions 3 --topic member-notification-topic

echo "[INIT] Topic creation complete."
