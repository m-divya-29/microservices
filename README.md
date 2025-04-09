# Ecommerce using microservices
## Tech stack
* `Spring Cloud Open Feign`: Sync-calls between services
* `WireMock`: Mock testing intercommunicating services.
* `KeyCloak`: Securing API Gateway
### How it works
* `Order` Service talks to `Inventory` Service synchronously: before placing an order, the service class checks inventory availability (waits for response from inventory service). We use `Spring cloud openfeign` to make the inter calls.
---
### Keycloak : Generate access token
* keycloak admin console is at:  http://localhost:8181/admin/master/console/#/spring-microservices-security-realm admin/admin
* client ID, secret from: Clients -> <spring-microservices-security-realm> -> Client details -> Credentials
* Access Token URL: Realm settings > General > End points >
  OpenID Endpoint Configuration > token_endpoint's URL
* In postman, choose OAuth 2.0 as Authorization > Grant Type: Client Credentials, Access token URL, client ID, client secret from above
---
## End Points
### API Gateway
* API gateway port configured to 9000. All services go via 9000. Examples: 
  * Product service: http://localhost:9000/api/product
  * Order service: http://localhost:9000/api/order
  * Inventory service: http://localhost:9000/api/inventory?skuCode=iphone_15&quantity=10
### Product Service
1. Start Mongo db: from product-service\ run
```docker-compose up -d```
2. Sample REST calls:
   * GET: http://localhost:8080/api/product 
   * POST: http://localhost:8080/api/product
     body:
     ```
     {
        "name": "iPhone12",
        "description": "Another best phone ever"
        "price": "699"
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
