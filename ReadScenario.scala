package simulations.xlrelease

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import akka.util.duration._
import bootstrap._

/**
 * - get tasks list
 * - get one random release
 * - get one random task
 * - get releases list
 */
object ReadScenario {

  val scn = scenario("Scenario Name")
    .exec(http("tasks list")
    .post("/tasks/search")
    .header("Content-Type", "application/json")
    .body( """{"active":true,"assignedToMe":true,"assignedToMyTeams":true,"assignedToOthers":true,"notAssigned":false,"filter":""}""")
    .check(status.is(200))
    .check(jsonPath("$[*].id").findAll.transform(Util.randomPick).saveAs("releaseId"))
  )
    .pause(1)
    .exec(http("get release")
    .get("/releases/${releaseId}")
    .check(status.is(200))
    .check(jsonPath("$.phases[*].tasks[*].id").findAll.transform(Util.randomPick).saveAs("taskId"))
  )
    .pause(351 milliseconds)
    .exec(http("python script definition")
    .get("/tasks/python-script-definitions")
    .check(status.is(200))
  )
    .pause(492 milliseconds)
    .exec(http("assignable teams")
    .get("/tasks/${taskId}/teams/assignable")
    .check(status.is(200))
  )
    .pause(236 milliseconds)
    .exec(http("comments")
    .get("/tasks/${taskId}/comments")
    .check(status.is(200))
  )
    .pause(825 milliseconds)
    .exec(http("configurations")
    .get("/configurations")
    .queryParam( """anonymized""", """true""")
    .check(status.is(200))
  )
    .pause(645 milliseconds)
    .exec(http("user names")
    .get("/users/names")
    .check(status.is(200))
  )
    .pause(762 milliseconds)
    .exec(http("releases list")
    .post("/releases/search")
    .header("Content-Type", "application/json")
    .body( """{"active":true,"planned":true,"completed":false,"onlyMine":false,"onlyFlagged":false,"filter":""}""")
    .check(status.is(200))
  )
}
