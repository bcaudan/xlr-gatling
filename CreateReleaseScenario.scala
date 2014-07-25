package simulations.xlrelease

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import akka.util.duration._
import bootstrap._

object CreateReleaseScenario {

  val scn = scenario("Scenario name")
    .repeat(10) {
    exec(
      http("release")
        .post("/releases/")
        .header("Content-Type", "application/json")
        .body("{\"title\":\"gatling\",\"description\":null,\"owner\":{\"username\":\"admin\",\"fullName\":\"XL Release Administrator\"},\"scheduledStartDate\":\"2014-07-25T07:00:00.000Z\",\"dueDate\":\"2014-07-25T15:00:00.000Z\",\"plannedDuration\":null,\"variables\":[],\"tags\":[\"tutorial\"],\"flag\":{\"status\":\"OK\"},\"abortOnFailure\":false,\"templateId\":\"ReleaseTemplate_configure\"}")
        .check(status.is(200)))
  }
}
