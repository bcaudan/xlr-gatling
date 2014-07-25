package simulations.xlrelease

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import akka.util.duration._
import bootstrap._

class XLReleaseSimulation extends Simulation {
  val factor: Int = 10

	val httpConf = httpConfig
		.baseURL("http://localhost:5516")
		.acceptCharsetHeader("utf-8")
    .authorizationHeader("Basic YWRtaW46YWRtaW4=")
		.acceptHeader("application/json")
		.disableFollowRedirect


	val scn = scenario("Scenario name")
    .repeat(factor) {
      exec(
        http("release")
          .post("/releases/")
          .header("Content-Type", "application/json")
          .body("{\"title\":\"gatling\",\"description\":null,\"owner\":{\"username\":\"admin\",\"fullName\":\"XL Release Administrator\"},\"scheduledStartDate\":\"2014-07-25T07:00:00.000Z\",\"dueDate\":\"2014-07-25T15:00:00.000Z\",\"plannedDuration\":null,\"variables\":[],\"tags\":[\"tutorial\"],\"flag\":{\"status\":\"OK\"},\"abortOnFailure\":false,\"templateId\":\"ReleaseTemplate_configure\"}")
          .check(status.is(200)))
    }
  setUp(scn.users(factor).ramp(factor).protocolConfig(httpConf))
}
