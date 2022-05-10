Demo Mongo Java Interactions

This is a demo api which uses Java Springboot and MongoDB using student log application.

Api end points:
@GetMapping
Get List of all Students from MongoDB
localhost:8080/api/v1/listallstudents

@PostMapping
Add one student
localhost:8080/api/v1/addstudent

Sampele Student Json:

{
"firstName": "Jane",
"lastName": "Dough",
"email": "jane-dough@gmail.com",
"age": 22,
"gender": "Female",
"address": {
"city": "Hopkins",
"zipcode": 12345,
"country": "USA"
},
"subjects": ["Maths", "Science", "English"],
"fees": 2000
}