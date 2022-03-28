# Library System
Web application for librarians and library clients.

## Setup
You need a running Glassfish server for this application to work. </br>
To build this applcation use Maven in project's root directory:
```
mvn clean package
```
And then to deploy it to the Glassfish server (needs to be running), type in cmd:
```
path_to_glassfish/bin/asadmin deploy path_to_project_war_file
```

## Technologies
* Java Faces
* JPA
* CDI
* EJB
* JTA

## Features
* Users can register and then login to their accounts
* Ordinary users can list existing books with sorting and filtering
* On clicking an entry in table, more information on the book shows up
* Users with special permissions can add new books and mark existing books as borrowed
