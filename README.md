# GreenwichEnterpiseWebDevelopment
Technologies we use:

Java 8

Angular

TypeScript

HTML / CSS

MongoDB


To use the system, you need:

Setup MongoDB Environment, JDK1.8, Angular CLI, and Node Module

After setting up MongoDb, you need to enter the following syntax in turn:

show databases

use backend_project 

db.roles.insertMany ([

{name: "ROLE_USER"},

{name: "ROLE_MM"},

{name: "ROLE_ADMIN"},

{name: "ROLE_MC"},

{name: "ROLE_GUEST"},
])


run 2 front-end and back-end projects under the respective IDE.

Open http: // localhost: 4200 / admin / account


Create an account as Admin with the following form:

"codeUser": "ADMIN-1",

"name": "ADMIN",

"email": "admin@gmail.com",

"dob": "20-1-2000",

"address": "VIETNAM",

"phoneNumber": "0902913136",

"nam": "2021",

"username": "admin",

"password": "admin123",

"retypePassword":"admin123",

"roles": ["Admin"],

"faculty": "IT",



Then run http: // localhost: 4200 / login again and log in. Continuing the next functions of the project.
