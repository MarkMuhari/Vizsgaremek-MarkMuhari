### Network létrehozása.
``` docker network create agency_network ```

### Docker mysql konténer létrehozása
``` docker run --name traveldb --network agency_network -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=travel -d -p 3307:3306 mysql:latest```

### Docker image létrehozása
```mvn clean package```
``` docker build -t travelapp .```

### Docker konténer létrehozása és indítása 
``` docker run --name travelapp --network agency_network -p 8080:8080 -d travelapp```
docker
## Alkalmazás újrabuildelelése új funckió fejlesztése után
1. ```mvn clean package```
2. ```docker build -t travelapp .```
3. le kell állítani a már futó konténert  
   ```docker stop travelapp```
4. ki kell törölni a leállított konténert  
   ```docker rm travelapp```
5. ```docker run --name travelapp --network agency_network -p 8080:8080 -d travelapp```