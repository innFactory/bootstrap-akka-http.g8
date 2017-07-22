package de.innfactory.bootstrap

import akka.http.scaladsl.testkit.ScalatestRouteTest
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import de.innfactory.bootstrap.http.HttpService
import de.innfactory.bootstrap.services.{AuthService, DummyService}
import de.innfactory.bootstrap.utils.AutoValidate
import de.innfactory.bootstrap.utils.InMemoryPostgresStorage._
import org.scalatest.{Matchers, WordSpec}


trait BaseServiceTest extends WordSpec with Matchers with ScalatestRouteTest with FailFastCirceSupport {


  dbProcess.getProcessId

  val dummyService = new DummyService()
  val authService = new AuthService(new AutoValidate)
  val httpService = new HttpService(authService, dummyService)

}
