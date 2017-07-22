package de.innfactory.bootstrap

import org.scalatest.concurrent.ScalaFutures
import akka.http.scaladsl.model.ContentTypes._
import akka.http.scaladsl.model.StatusCodes._
import de.innfactory.bootstrap.models.Dummy
import io.circe.generic.auto._

class DummyServiceTest extends BaseServiceTest with ScalaFutures {

  trait Context {
    val route = httpService.dummyRouter.route
  }

  "Dummy service" should {

    "retrieve empty dummy list" in new Context {
      Get("/dummy/") ~> route ~> check {
        responseAs[Seq[String]].isEmpty should be(true)
      }
    }

    "retrieve no content for a specific search" in new Context {
      Get("/dummy/1")~> addHeader("Authorization", "test") ~> route ~> check {
        status shouldBe OK
        contentType shouldBe `application/json`
      }
    }

    "test the entity" in {
      val dummy = Dummy(Some(1), "Hello")
      dummy.id shouldBe Some(1)
      dummy.dummy shouldBe "Hello"
    }
  }

}
