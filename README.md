# Assignment project

This project is about for user to list and record todo task list. The project is using Java as servlet and MYSQL as database.



## Docker Compose

At the root of the project folder run the following command to start.

```
docker-compose up
```

**The MYSQL container might take some time to fully load up for first time**


## API List ##

#### List all todo items

```http
  GET http://localhost:8080/todoitem
```

#### Add a new todo item

```http
  POST http://localhost:8080/todoitem
```

| Header | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `string` | The task name |
| `description` | `string` | The task description |


#### Delete a todo item

```http
  DELETE http://localhost:8080/todoitem
```

| Header | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. ID of item to delete|


#### Update a todo item

```http
  PUT http://localhost:8080/todoitem
```

| Header | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. ID of item to update|
| `name` | `string` | The updated task name|
| `description` | `string` | The updated description|
| `completed` | `boolean` | **true** from completed, **false** for pending| 


#### List all completed todo items

```http
  GET http://localhost:8080/todoitem/complete
```

#### Mark a todo item completed

```http
  PUT http://localhost:8080/todoitem/complete
```

| Header | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. ID of item to mark as completed|


#### List all pending todo items

```http
  GET http://localhost:8080/todoitem/pending
```

#### Mark a todo item pending

```http
  PUT http://localhost:8080/todoitem/pending
```

| Header | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. ID of item to mark as pending|