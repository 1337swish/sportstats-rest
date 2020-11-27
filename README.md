# REST API for sports 
Instructions
1. Clone repository.
2. Import "sportstats db" sql file under project resources, with e.g. mySQL Workbench.
3. Change username and password in DbConn.java file to your own.
4. Clean and build project.
5. Run project with embedded tomcat.
6. The endpoints can be reached with Postman on http://localhost:8080.

### Endpoints  
Use an endpoint by appending e.g. /sports to http://localhost:8080  
Use parameters by appending e.g. &id=1 to http://localhost:8080/sports?  
Parameters are delimited by &.  
Parameters in parantheses are optional, otherwise required.  
To reach an endpoint, use the url http://localhost:8080/endpoint?param=1&param=5 etc...  
To get a sport with id 12, use the url http://localhost:8080/sports?id=12  

#### @GET
/sports  
/leagues (id), (sportid)  
/arenas (id)  
/seasons (id), (leagueid)  
/teams (id), (arenaid), (seasonid), (sportid)  
/rounds (id), (seasonid)  
/matches (id), (roundid), (seasonid), (sportid), (overtime), (arenaid), (teamid), (date), (datefrom), (dateto)  
/seasonteams (seasonid), (teamid)  
/standings seasonid, (teamid), (home)  
  
#### @POST  
/sports name, win, winot, draw, loss, lossot  
/leagues name, sportid, country  
/arenas name, address, capacity  
/seasons leagueid, startyear, endyear, rounds, (autoaddrounds)  
/teams name, arenaid, sportid, createdyear  
/rounds seasonid, roundnumber  
/matches hometeamid, awayteamid, arenaid, roundid, seasonid, sportid, (date), (hometeamscore), (awayteamscore), (attendance), (overtime)  
/seasonteams teamid, seasonid  
  
#### @PUT  
/sports id, (name), (win), (winot), (draw), (loss), (lossot)  
/leagues id, (name), (country), (sportid)  
/arenas id, (name), (address), (capacity)  
/seasons id, (leagueid), (startyear), (endyear), (rounds)  
/teams id, (name), (arenaid), (sportid), (createdyear)  
/rounds id, (roundnumber), (seasonid)  
/matches id, (hometeamid), (awayteamid), (arenaid), (roundid), (seasonid), (sportid), (date), (hometeamscore), (awayteamscore), (attendance), (overtime)  
/seasonteams id, (seasonid), (teamid)  
  
#### @DELETE  
/sports id  
/leagues id  
/arenas id  
/seasons id  
/teams id  
/rounds id  
/matches id  
/seasonteams id  
