# Ecommerce using microservices
## Tech stack
* `REST Client`: Sync-calls between services
* `WireMock`: Mock testing intercommunicating services.
* `KeyCloak`: Securing API Gateway
* `Spring Cloud Circuit Breaker`: `Resilience4j` implementation to achieve circuit breaker pattern.
  If a service keeps failing, the circuit breaker "opens" and stops further calls for a while, giving the system a chance to recover.
* `Kafka` with `Zookeeper`
* `mailtrap.io` for sending order notification mails order-placed topic consumer. Login to mailtrap, check sent mails in: Inboxes > spring-shop-inbox.


### How it works
* `Order` Service talks to `Inventory` Service synchronously: before placing an order, the service class checks inventory availability (waits for response from inventory service). We use `REST Client` to make the inter calls.
---
### Keycloak : Generate access token
* keycloak admin console is at:  http://localhost:8181/admin/master/console/#/spring-microservices-security-realm admin/admin
* client ID, secret from: Clients -> <spring-microservices-security-realm> -> Client details -> Credentials
* Access Token URL: Realm settings > General > End points >
  OpenID Endpoint Configuration > token_endpoint's URL
* In postman, choose OAuth 2.0 as Authorization > Grant Type: Client Credentials, Access token URL, client ID, client secret from above
---
### Kafka
* Kafka UI: http://localhost:8086/
  * Create Kafka cluster: cluster name=localhost, bootstrap servers=broker port=29092. Validate > Submit
  * in topics, you will see all the messages sent to order-service topic.

---
## End Points
### API Gateway
* API gateway port configured to 9000. All services go via 9000. Examples: 
  * Product service: http://localhost:9000/api/product
  * Order service: http://localhost:9000/api/order
  * Inventory service: http://localhost:9000/api/inventory?skuCode=iphone_15&quantity=10
  * Aggregated microservices' swagger: http://localhost:9000/swagger-ui/index.html
  * Actuator: http://localhost:9000/actuator/health
### Product Service
1. Start Mongo db: from product-service\ run
```docker-compose up -d```
2. Sample REST calls:
   * GET: http://localhost:8080/api/product 
   * POST: http://localhost:8080/api/product
     body:
     ```
     {
        "name": "Yoga Mat",
        "description": "Non-slip yoga mat for exercise",
        "skuCode": "FIT-YM05",
        "price": "39"
     }
     ```
     

### Order Service
1. Start MySQL db: from order-service\ run
   ```docker-compose up -d```
2. Sample REST calls:
    * GET: http://localhost:8081/api/order
    * POST: http://localhost:8081/api/order
      body: 
   ```
   {
       "orderNumber": "iPhone12",
       "skuCode": "Best camera",
       "price": "699",
       "quantity": 2
   }
   ```
### Inventory Service
1. Start MySQL db: from order-service\ run
   ```docker-compose up -d```
2. Sample REST calls:
    * GET: http://localhost:8082/api/inventory?skuCode=iphone_15&quantity=10
   

---
## Commonly faced issues
### Set jdk to 21 to just this project(powershell)
```
$env:JAVA_HOME="C:\Users\dmahanka\Downloads\jdk-21.0.5_windows-x64_bin\jdk-21.0.5"
$env:PATH="$env:JAVA_HOME\bin;$env:PATH"
```
### If Lombok annotations don't work as expected
- remove it from pom.xml > build/plugins
- close the project, delete the `.idea` and `target` folders and reopen the project.

---
### Resources
* [YT Course content](https://www.youtube.com/watch?v=yn_stY3HCr8) and  [Blog](https://programmingtechie.com/articles/spring-boot-microservices-tutorial) 


---
### ToDos
* fix: swagger doesn't work for order-service.
* Cascade update/delete inventory and product.
* UI-Place Order: Pass logged-in user email in the payload to later use in kafka messages.