#!/bin/bash

# Ensure this script is executable: chmod +x load_test.sh

# Define the base URL for your application
BASE_URL="http://host.docker.internal:8080"

# Function to run Apache Bench (ab) via Docker
run_ab() {
  local endpoint=$1
  local requests=$2
  local concurrency=$3

  echo "Running load test for $BASE_URL$endpoint"
  docker run --platform linux/amd64 --rm jordi/ab -n "$requests" -c "$concurrency" "$BASE_URL$endpoint" | tee "results_${endpoint//\//_}.txt"
#  docker run --rm jordi/ab -n "$requests" -c "$concurrency" "$BASE_URL$endpoint"
  echo -e "\nResults saved to results_${endpoint//\//_}.txt\n"
}

# Load test for MongoDB endpoints
run_ab "/api/mongo/add" 1000 50
run_ab "/api/mongo/get" 1000 50

# Load test for Elasticsearch endpoints
run_ab "/api/elastic/health" 1000 20

# Load test for the root endpoint
run_ab "/" 100 10

echo "Load testing completed."