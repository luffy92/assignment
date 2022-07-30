# Assignment project

This project is about for user to list and record todo task list. The project is using Java as servlet and MYSQL as database.

## Authors

- [@luffy92](https://github.com/luffy92/assignment)


## Docker Compose

At the root of the project folder run the following command to start.

```sh
docker-compose up
```

**The MYSQL container might take some time to fully load up for first time**

Go to the following url, if display "Database connected", which mean database is fully up
```http
http://localhost:8080/
```

You may also manually run the container and network by following below steps.

### Manually start the mysql container
Goto the db folder and run the following command to build the mysql image
```sh
docker build -t my-mysql .
```

And then run the command to run the container
```sh
docker run -d -p 3306:3306 --name mysqlapp my-mysql
```

or

```sh
docker run -d -p 3306:3306 --name luffy92/assignment_mysql my-mysql
```

### Manually start the java container
Goto the app folder and run the following command to build the java image
```sh
docker build -t my-java .
```

And then run the command to run the container
```sh
docker run -d -p 8080:8080 --link mysqlapp:mysqlapp --name javaapp my-java
```

or

```sh
docker run -d -p 8080:8080 --link mysqlapp:mysqlapp --name luffy92/assignment_java my-java
```

### Create the network
First, create the bridge network
```sh
docker network create --driver bridge mynetwork 
```

Then, connect to containers
```sh
docker network connect mynetwork mysqlapp
```
```sh
docker network connect mynetwork javaapp
```

## API List ##

### List all todo items

```http
  GET http://localhost:8080/todoitem
```

#### Example
```sh
curl http://localhost:8080/todoitem
```



### Add a new todo item

```http
  POST http://localhost:8080/todoitem
```

| Header | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `string` | The task name |
| `description` | `string` | The task description |

#### Example
```sh
curl --header "name:Task A" --header "description:Purchase new laptop." -X POST http://localhost:8080/todoitem
```



### Delete a todo item

```http
  DELETE http://localhost:8080/todoitem
```

| Header | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. ID of item to delete|

#### Example
```sh
curl --header "id:6" -X DELETE http://localhost:8080/todoitem
```




### Update a todo item

```http
  PUT http://localhost:8080/todoitem
```

| Header | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. ID of item to update|
| `name` | `string` | The updated task name|
| `description` | `string` | The updated description|
| `completed` | `boolean` | **true** from completed, **false** for pending| 

#### Example
```sh
curl --header "id:6" --header "name:Task A.1" --header "description:Purchase new Desktop." -X PUT http://localhost:8080/todoitem
```



### List all completed todo items

```http
  GET http://localhost:8080/todoitem/complete
```

#### Example
```sh
curl http://localhost:8080/todoitem/complete
```




### Mark a todo item completed

```http
  PUT http://localhost:8080/todoitem/complete
```

| Header | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. ID of item to mark as completed|


#### Example
```sh
curl --header "id:6" -X PUT http://localhost:8080/todoitem/complete
```



### List all pending todo items

```http
  GET http://localhost:8080/todoitem/pending
```

#### Example
```sh
curl http://localhost:8080/todoitem/pending
```




### Mark a todo item pending

```http
  PUT http://localhost:8080/todoitem/pending
```

| Header | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. ID of item to mark as pending|

Example : 
```sh
curl --header "id:6" -X PUT http://localhost:8080/todoitem/pending
```

