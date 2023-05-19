# spring-restful-superheroes
Servicios RESTful con Spring

```
curl -v localhost:8080/heroes | jq  

curl -v localhost:8080/heroes/1 | jq

curl -v -X POST localhost:8080/heroes -H 'Content-type:application/json' -d '{"name": "Elongated Man Girl", "power":"Healing Factor", "descriptor"": "Justice"}' | jq

curl -v -X PUT localhost:8080/heroes/101 -H 'Content-type:application/json' -d '{"name": "Elongated Man", "power": "Healing Factor", "descriptor"": "Justice"}'  | jq

curl -X DELETE localhost:8080/heroes/100  | jq
```

