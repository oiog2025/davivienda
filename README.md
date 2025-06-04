# Davivienda


# Getting Started Local
* Required Docker

```bash
docker build -t davivienda:1.0 .

docker run -d -p 8080:8080 --name davivienda-job:1.0
```
### Swagger

* [swagger-local](http://localhost:8080/davivienda/swagger-ui/index.html)

### Postman

* [download](https://drive.google.com/file/d/10PajJ0dNFYdEoDHsM_s7uas-mH1BQzZf/view)

## Endpoints de la API

| Method | Endpoint               | Description                      |
|--------|------------------------|----------------------------------|
| POST   | /davivienda/login      | Login                            |
| GET    | /davivienda/project    | Get all projects  |
| GET    | /davivienda/project/id | Get project by ID |
| POST   | /davivienda/project    | Creat a project required token   |
| PUT    | /davivienda/project    | Update projectrequired token     |
| DELETE | /davivienda/project/id | Delete project required token    |
| GET    | /davivienda/actuator/health   | Health check                     |


```

* /davivienda/login
```bash
curl --location 'localhost:8080/davivienda/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "admin@example.com",
    "password": "Admin123!"
}'
```