package uxa

import io.gatling.core.Predef._ 
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation { 

  val httpConf = http 
    .baseURL("http://dev-uxanalytics.content-square.fr")
    .authorizationHeader("Basic YWRtaW46azBudDNudHNxdUByZQ==")
    .acceptHeader("application/json")

  val scn = scenario("BasicSimulation") 
    .exec(http("healthcheck")
    .get("/healthcheck"))
    .exec(http("dashboard")
    .get("/118/dashboard"))
    .pause(2)
    .exec(http("alerts")
    .get("/alert/118/pages?from=2015-03-20T00%3A00%3A00Z&to=2015-03-27T23%3A59%3A59Z&segment=-1&limit=3"))
    .exec(http("recordings")
    .get("/api/projects/118/live/recordings.json?limit=5"))
    .exec(http("ecommerce")
    .get("/api/websites/118/stats/ecommerce.json?from=2015-03-20T00%3A00%3A00Z&to=2015-03-27T23%3A59%3A59Z&segment=-1"))
    .exec(http("daily")
    .get("/api/websites/118/stats/ecommerce/daily.json?from=2015-03-20T00%3A00%3A00Z&to=2015-03-27T23%3A59%3A59Z&segment=-1"))

  setUp( 
    scn.inject(rampUsers(100) over (480 seconds))
  ).protocols(httpConf) 
}
