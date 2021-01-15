# ratelimiterdemo
A demo project for rate limiter of standalone server
Note: this rate limiter is only for demo purpose. And you need to implement a rate limiter with Redis for a cluster environment. 

## How to run it
Pre-requisite: OpenJDK 8, and JAVA_HOME had been set correctly
1. git clone https://github.com/xiangyucai/ratelimiterdemo.git
   or download the zip from github
2. go to <Local_Repositoy_Home>/RateLimiterStandaloneDemo, run with the command:
   (MacOS/Linux) ./mvnw spring-boot:run or (Windows) mvnw spring-boot:run
3. open browser and go to http://localhost:8080/order?userid=3&orderid=2
4. user with id 3 can send 1 request per minute according to the license, user with id 2 can send 10 requests per minute, and user with id 1 can send 100 requests per minute
5. if too many requests were sent by the user according to the license, the Too Many Request error would be returned
6. if no userid or orderid provided, error would be returned
