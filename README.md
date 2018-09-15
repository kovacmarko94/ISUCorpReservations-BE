# Docker config 
docker run --name IsuCorpReservations --net=host -e MYSQL_ROOT_PASSWORD=root -d mysql:latest
docker exec -it IsuCorpReservation /bin/bash

# Rest api documentation  
http://localhost:8080/swagger-ui.html
