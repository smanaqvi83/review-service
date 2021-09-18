
# review-service

# review-service
In this service we have exposed GET, POST, PUT and DELETE endpoints

 1. API to get all the added reviews 
	 /v1.0/review - GET
This API will return all the reviews that we have in the database 

    [{
		"id": 1,
		"productId": "BB5476",
		"reviewScore": 5
	},
	{
		"id": 2,
		"productId": "BB5476",
		"reviewScore": 4
	},
	{
		"id": 3,
		"productId": "BB5476",
		"reviewScore": 3
	}
]

 2. API to get the details for specific product - GET
	 

    {
		"averageReviewScore": 4,
		"numberOfReviews": 3
	}
	

 3. API to add new product in the database
  

    curl --location --request POST 'localhost:8081/v1.0/review' \
--header 'Authorization: Basic dGVzdDpwYXNzd29yZA==' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productId": "BB5476",
    "reviewScore": 5
}'
As this API will write on DB, so we have added the basic auth with this API, incase no or false Basic Auth is provided it will fail. 
There is also validation on review score as it is mandatory and its value can be between 1 and 5.

 4. API to Update existing Product
	 curl --location --request PUT 'localhost:8081/v1.0/review/2' \
--header 'Authorization: Basic dGVzdDpwYXNzd29yZA==' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productId": "BB5476Updated",
    "reviewScore": 2
}'
As this API will write on DB, so we have added the basic auth with this API, incase no or false Basic Auth is provided it will fail. 
There is also validation on review score as it is mandatory and its value can be between 1 and 
5 and ProductId is also mandatory.

 5. API to Delete existing product
	curl --location --request DELETE 'localhost:8081/v1.0/review/3' \

--header 'Authorization: Basic dGVzdDpwYXNzd29yZA==' \

--data-raw ''

As this API will write on DB, so we have added the basic auth with this API, incase no or false Basic Auth is provided it will fail. 

**Basic Auth**
For now we are using following credentials for Basic Auth and credentials can be modified in the application.properties file. 

 1. service.username=test  
 2. service.password=password 

Clone the repo locally and go in the directory and run the command following commands in the sequence

 1. **mvn clean install**
 2. **./mvnw spring-boot:run**

 **./mvnw spring-boot:run**, in case if we get the Permission Denied error in mac or linux we can run the following command **chmod +x mvnw** and then run the **./mvnw spring-boot:run** command again. 

By default this application will run on the **8081** port, which can be changed from **application.properties** file

