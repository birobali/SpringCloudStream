# springcloudstream
Demonstrate Spring Cloud Stream functionality


Source application
Start the Source application with the following JVM arguments
-Dtwitter.consumerKey=
-Dtwitter.consumerSecret=
-Dtwitter.accessToken=
-Dtwitter.accessTokenSecret=

Processor application
Run the app in two instances with the following JVM arguments:
Instance 1:  -Dspring.cloud.stream.instanceIndex=0
Instance 2:  -Dspring.cloud.stream.instanceIndex=1