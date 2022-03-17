# readingisgood
readingisgood
How to start:
From Intellij Maven tab , you must start install, 

Then , at project window target folder *.jar file will be created.
 
Now , we will create the docker image file from the Terminal screen of IntelliJ
	docker build -t springboot-mongodb:1.0 .
 
After building completed , 

	docker run -p 8080:8080 --name springboot-mongodb --link mongodb:mongo -d springboot-mongodb:1.0
Please check your mongo-DB image’s already started.
 
Tomcat started on port(s): 8080
