# Ecommerce using microservices
## Commonly faced issues
### Set jdk to 21 to just this project(powershell)
```
$env:JAVA_HOME="C:\Users\dmahanka\Downloads\jdk-21.0.5_windows-x64_bin\jdk-21.0.5"
$env:PATH="$env:JAVA_HOME\bin;$env:PATH"
```
### If Lombok annotations don't work as expected
- remove it from pom.xml > build/plugins
- close the project, delete the `.idea` and `target` folders and reopen the project.

## Product Service
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
     

## Order Service
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
---
### Useful resources
* [YT Course content](https://www.youtube.com/watch?v=yn_stY3HCr8) and  [Blog](https://programmingtechie.com/articles/spring-boot-microservices-tutorial) 
