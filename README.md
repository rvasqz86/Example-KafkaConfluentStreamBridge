# ApiKafka Project Documentation

## Project Overview

ApiKafka is a Java-based application that demonstrates integration between Spring Boot, Apache Kafka, and Avro schemas. The project showcases how to implement message publishing, consumption, and transformation in a microservices architecture.

## Technical Stack

- **Java 21** - Programming language
- **Spring Framework** - Primary application framework
- **Apache Kafka** - Message broker
- **Avro** - Data serialization format
- **Confluent Schema Registry** - Schema management

## Modules

### Api Module

The Api module serves as the main entry point and provides:

- REST controllers for handling HTTP requests
- Kafka configuration for producers and consumers
- Annotation-based message publishing
- Service implementations

#### Key Components

- **RestApi**: Controller exposing REST endpoints
- **KafkaConfig**: Kafka configuration setup
- **DynamicTopicPublisher**: Component for publishing to Kafka topics
- **Consumers**: Kafka message consumers
- **PublishOnReturn**: Custom annotation for automatic Kafka publishing
- **AnnotationProcessor**: Processes custom annotations

### CustomerTransform Module

This module handles data transformation between different formats:

- **PersonToCustomerKafka**: Transforms person data to customer format
- **PurchaseToCustomerMapper**: Maps purchase data to customer model


## Getting Started

### Prerequisites

- Java 21
- Apache Kafka
- Confluent Schema Registry

### Running the Application

1. Start Kafka and Schema Registry
2. Build the project using Maven
3. Run the Api application

### Testing

A Test.http file is included for testing the REST endpoints.

## API Usage

The API exposes endpoints for submitting and retrieving data. The controllers handle these requests and publish events to Kafka as configured.

## Message Flow

1. Data is submitted through REST API
2. Events are published to Kafka topics
3. Consumers process these events
4. Data transformations are applied as needed

## Advanced Features

### Custom Annotations

The project includes a custom `@PublishOnReturn` annotation that automatically publishes method return values to Kafka.

### Dynamic Topic Publishing

The `DynamicTopicPublisher` component allows for publishing to dynamically determined topics.
