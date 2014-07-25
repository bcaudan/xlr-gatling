package simulations.xlrelease

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import akka.util.duration._
import bootstrap._
import scala.util.Random

object Util {
  def randomPick: (Seq[String]) => String = {
    arr => Random.shuffle(arr).head
  }
}
