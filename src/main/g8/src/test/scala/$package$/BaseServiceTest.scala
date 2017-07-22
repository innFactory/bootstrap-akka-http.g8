package $package$

import akka.http.scaladsl.testkit.ScalatestRouteTest
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import $package$.http.HttpService
import $package$.services.{AuthService, DummyService}
import $package$.utils.AutoValidate
import $package$.utils.InMemoryPostgresStorage._
import org.scalatest.{Matchers, WordSpec}


trait BaseServiceTest extends WordSpec with Matchers with ScalatestRouteTest with FailFastCirceSupport {


  dbProcess.getProcessId

  val dummyService = new DummyService()
  val authService = new AuthService(new AutoValidate)
  val httpService = new HttpService(authService, dummyService)

}
