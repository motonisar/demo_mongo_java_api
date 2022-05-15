Demo Mongo Java Interactions

This is a demo api which uses Java Springboot and MongoDB to build student log application.
You can call the different urls to add, get and delete students into mongo db. 
Api end points:

@GetMapping
Get List of all Students from MongoDB
localhost:8080/api/v1/listallstudents

@GetMapping
get one Student
localhost:8080/api/v1/getstudent/{email}

@PostMapping
Add one student
localhost:8080/api/v1/addstudent

@DeleteMapping
Delete a Student by id
localhost:8080/api/v1/deletestudent/{email}

Sample Student Json:
Please refer to the Sample.json file in the project.

Prerequisite:
Need a running mongodb which is already available.
If not available , 
please use   docker-compose file to spinup a mongodb running on docker. 
CMD > docker-compose -f docker-compose.yaml up --detach 