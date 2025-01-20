# Highload Architecture Monitoring Project

This project is a part of the **Highload Software Architecture Course**. It implements a monitoring system with multiple services interacting through Docker containers. The system includes a Micronaut-based application, MongoDB, Elasticsearch, Telegraf, InfluxDB, Grafana, and Nginx.

### 1. Clone the Repository
### 2. Launch All Services

Start all services using Docker Compose:

		`docker-compose up -d`

This will start the following services:
- Micronaut application on http://localhost:8080
- InfluxDB on http://localhost:8086 (default credentials: admin / password)
- Grafana on http://localhost:3000 (default credentials: admin / admin)

### 3. Generate Load for Monitoring

Run the load testing script to simulate traffic:

      `./load_test.sh`

This script will generate load for the following API endpoints:
- GET /api/mongo/add
- GET /api/mongo/get
- GET /api/elastic/health
### 4. Access Grafana Dashboards

Visit Grafana at http://localhost:3000 to view metrics. The dashboard is pre-configured to visualize:
- Docker containers metrics
- Nginx performance
- System metrics (CPU, memory, disk, etc.)

### Project Structure

Below is the directory structure of the project:

		hsa13-hw2-highload-monitoring/
		├── app/                  # Micronaut application source code
		│   ├── src/              # Application code
		│   └─── pom.xml           # Maven configuration
		├── config/               # Configurations for services
		│   ├── grafana-provisioning/
		│   │   ├── dashboards/
		│   │   │   └── telegraf-dashboard.json   # Grafana dashboard definition
		│   │   ├── dashboards-config/
		│   │   │   └── telegraf-dashboard.yaml  # Dashboard provisioning config
		│   │   └── datasources/
		│   │       └── influxdb-datasource.yaml  # InfluxDB datasource config
		│   ├── elasticsearch.yml                 # Elasticsearch configuration
		│   ├── nginx.conf                        # Nginx configuration
		│   └── telegraf.conf                     # Telegraf configuration
		├── screenshots/           # Grafana dashboard screenshots
		│   ├── dashboard1.png
		│   ├── dashboard2.png
		│   ├── dashboard3.png
		│   └── dashboard4.png
		├── docker-compose.yml     # Docker Compose configuration
		├── load_test.sh           # Load testing script using Apache Benchmark
		├── .gitignore             # Git ignore file
		└── README.md              # Project documentation

### Screenshots

Below are examples of Grafana dashboards:
[![](https://github.com/kovamaru/hsa13-hw2-highload-monitoring/blob/main/screenshots/dashboard1.png)](https://github.com/kovamaru/hsa13-hw2-highload-monitoring/blob/main/screenshots/dashboard1.png)

[![](https://github.com/kovamaru/hsa13-hw2-highload-monitoring/blob/main/screenshots/dashboard2.png)](https://github.com/kovamaru/hsa13-hw2-highload-monitoring/blob/main/screenshots/dashboard2.png)

[![](https://github.com/kovamaru/hsa13-hw2-highload-monitoring/blob/main/screenshots/dashboard3.png)](https://github.com/kovamaru/hsa13-hw2-highload-monitoring/blob/main/screenshots/dashboard3.png)

[![](https://github.com/kovamaru/hsa13-hw2-highload-monitoring/blob/main/screenshots/dashboard4.png)](https://github.com/kovamaru/hsa13-hw2-highload-monitoring/blob/main/screenshots/dashboard4.png)
