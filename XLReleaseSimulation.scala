package simulations.xlrelease

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import akka.util.duration._
import bootstrap._

class XLReleaseSimulation extends Simulation {
  val factor: Int = 1

	val httpConf = {
    val localhost: String = "http://localhost:5516"
    val mathieu: String = "http://10.1.0.7:5516"
    httpConfig
      .baseURL(localhost)
      .authorizationHeader("Basic YWRtaW46YWRtaW4=")
      .acceptHeader("application/json")
      .disableFollowRedirect
  }

  setUp(WriteScenario.scn.users(factor).ramp(factor).protocolConfig(httpConf))
}
