package $package$

import akka.http.scaladsl.testkit.ScalatestRouteTest
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import de.innfactory.akka.AuthService
import de.innfactory.akka.jwt.AutoValidator
import $package$.http.HttpService
import $package$.services.DummyService
import $package$.utils.Persistence
import $package$.utils.InMemoryPostgresStorage._
import org.scalatest.{Matchers, WordSpec}


trait BaseServiceTest extends WordSpec with Matchers with ScalatestRouteTest with FailFastCirceSupport {


  dbProcess.getProcessId

  implicit val persistence = new Persistence
  val dummyService = new DummyService()
  val jwtValidator = new AutoValidator
  val authService = new AuthService(jwtValidator)
  val httpService = new HttpService(authService, dummyService)

}
