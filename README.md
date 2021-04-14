# 2dPirateGame

##Installation steps
* Clone Project
* run `mvn clean install`
* run `docker build -t 2dpirategame .`
* run `docker run -d -p 8050:8057 2dpirategame`
* run `docker ps` to view the container status  
* Application now running on `PORT 8057`
* `/map` to input 2d map
* `/findPath?startXPosition=0&startYPosition=0&targetXPosition=3&targetYPosition=3` to get the path
* visit `http://localhost:8057/swagger-ui.html` to run test