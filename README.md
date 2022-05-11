Demo Mongo Java Interactions

This is a demo api which uses Java Springboot and MongoDB using student log application.

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

Sample Student Json:
Please refer to the Sample.json file in the project.

Prerequisite:
Need a running mongodb which is already available.
If not available , 
please docker-compose file to spinup a mongodb running on docker. 
CMD > docker-compose -d -f docker.compose.yaml up 