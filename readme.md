Introduction:
todo-management-api is a stand alone SpringBoot Application that manages the TODO list of the user.

************************************
This API exposes the below methods
************************************
1. List all the available TODO Tasks
2. List all tasks which are due for a given date(in yyyy-mm-dd format)
3. List tasks due in give minutes(Example – tasks which are due in next 30 minutes)
4. Create a TODO Task.
5. Update a TODO Task.
6. Delete a TODO Task.

******************
Design of the DB Table. 
This is mapped to Todo.java through ORM.
******************
Column			Description
Id				Id of the TODO. Also the Primary Key
Name			Name of the Task
Description		Description of the Task
Date			Date on which the task is due(yyyy-MM-dd format)
Time			Time in which the task is due(HH:mm format)
Status			Status of the task. (Possible values are PENDING/COMPLETE)

******************
List of end points:
******************

HTTP Method		URL									Description
GET				localhost:8080/todos/				Retrieves the list of Tasks from the Database
GET				localhost:8080/todos/{yyyy-mm-dd}.  Retrieves the list of Tasks which are due for that day 
                Example - localhost:8080/todos/2018-04-19
GET				localhost:8080/todos/remind/{minutes}Retrieves the list of Tasks which are due in <x> minutes
POST			localhost:8080/todos/				 Creates a Todo Task and saves to the  Database
PUT				localhost:8080/todos/{id}			Updates the Task with the id and saves to the Database
DELETE			localhost:8080/todos/{id}			Deletes  the Task with the id from the Database


******************************
Steps to Run the Application:
******************************

The JAR file can be generated using maven.

To run the application - Goto the terminal (command line)  and type the below command:
java -jar <the location of the JAR file>

This will start the Springboot application and you are ready to fire requests to the above mentioned URL s.
It works with in-memory Apache Derby database for all the data operations.

********************************
Sample JSON for a POST Request 
********************************
	{
        "id": "1",
        "name": "Meeting with the HR Manager",
        "description": "Meeting with the HR Manager",
        "date": "2018-04-19",
        "time": "19:00"
    }
