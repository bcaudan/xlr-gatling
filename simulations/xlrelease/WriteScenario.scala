package simulations.xlrelease

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.
http.Predef._
import akka.util.duration._
import bootstrap._

object WriteScenario {

  val scn = scenario("Writing scenario")
    .exec(
      http("get releases")
        .post("/releases/search")
        .header("Content-Type", "application/json")
        .body( """{"active":true,"planned":true,"completed":false,"onlyMine":false,"onlyFlagged":false,"filter":""}""")
    )
    .pause(1)
    .exec(
      http("check permission")
        .get("/roles/permissions/global")
    )
    .pause(1)
    .exec(
      http("get templates")
        .get("/releases/templates")
        .queryParam( """filter""", """""")
    )
    .pause(1)
    .exec(
      http("get user's fullNames")
        .get("/users/names")
    )
    .pause(1)
    .exec(
      http("collect template variables")
        .get("/releases/ReleaseTemplate_sample/updatable-variables")
    )
    .pause(46 milliseconds)
    .exec(
      http("create release from template")
        .post("/releases")
        .header("Content-Type", "application/json")
        .fileBody("create_release.txt")
        .check(jsonPath("$.id").saveAs("releaseId")))
    .pause(17 milliseconds)
    .exec(
      http("get settings")
        .get("/reports/settings")
    )
    .pause(28 milliseconds)
    .exec(
      http("get python script defs")
        .get("/tasks/python-script-definitions")
    )
    .pause(28 milliseconds)
    .exec(
      http("get release")
        .get("/releases/${releaseId}")
    )
    .pause(1)
    .exec(
      http("start release")
        .post("/releases/${releaseId}/start")
        .header("Content-Type", "application/json")
    )
    .exec(
      http("refresh release after starting")
        .get("/releases/${releaseId}")
        .check(jsonPath("$.phases[0].tasks[0].id").saveAs("taskId"))
    )
    .pause(655 milliseconds)
    .exec(
      http("get full name")
        .get("/users/names")
    )
    .pause(12 milliseconds)
    .exec(
      http("get user assignated to a task")
        .get("/tasks/${taskId}/teams/assignable")
    )
    .exec(
      http("get configuration")
        .get("/configurations")
        .queryParam( """anonymized""", """true""")
    )
    .exec(
      http("get comments of task")
        .get("/tasks/${taskId}/comments")
    )
    .pause(58 milliseconds)
    .exec(
      http("get gate dependency of task")
        .get("/gates/${taskId}/dependency-target-candidates")
    )
    .pause(1)
    .exec(
      http("complete task")
        .post("/tasks/${taskId}/complete")
        .header("Content-Type", "application/json")
        .body("""{"text": "ss"}""")
    )
    .pause(33 milliseconds)
    .exec(
      http("refresh release")
        .get("/releases/${releaseId}")
    )
    .pause(1)
    .exec(
      http("abort release")
        .post("/releases/${releaseId}/abort")
        .header("Content-Type", "application/json")
    )
    .pause(39 milliseconds)
    .exec(
      http("refresh release")
        .get("/releases/${releaseId}")
    )
    .pause(1)
    .exec(
      http("get releases")
        .post("/releases/search")
        .header("Content-Type", "application/json")
        .body( """{"active":true,"planned":true,"completed":false,"onlyMine":false,"onlyFlagged":false,"filter":""}""")
    )
}
