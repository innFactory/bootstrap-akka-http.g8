package de.innfactory.bootstrap

import akka.http.scaladsl.model.ContentTypes
import com.github.swagger.akka.CustomMediaTypes
import de.innfactory.bootstrap.services.SwaggerDocService
import de.innfactory.bootstrap.utils.Configuration
import akka.http.scaladsl.model.ContentTypes._
import akka.http.scaladsl.model.StatusCodes._
import org.scalatest.BeforeAndAfterAll

class MinimalSwaggerHttpServiceSpec
  extends BaseServiceTest with BeforeAndAfterAll with Configuration {

  override def afterAll: Unit = {
    super.afterAll()
    system.terminate()
  }


  trait Context {
    val swaggerDocService = new SwaggerDocService(httpHost, httpPort, system)
    val route = httpService.swaggerDocService.routes
    val uiroute = httpService.swaggerRouter.route
  }

  "The SwaggerHttpService" when {
    "accessing the root doc path" should {
      "return the basic set of api info" in new Context {
        Get(s"/${swaggerDocService.apiDocsPath}/swagger.json") ~> route ~> check {
          handled shouldBe true
          contentType shouldBe `application/json`
        }
      }
      "return the basic set of api info in yaml" in new Context {
        Get(s"/${swaggerDocService.apiDocsPath}/swagger.yaml") ~> route ~> check {
          handled shouldBe true
          contentType shouldBe CustomMediaTypes.`text/vnd.yaml`.toContentType
        }
      }
    }
    "accessing the swagger ui" should {
      "return the html ui" in new Context {
        Get(s"/swagger/index.html") ~> uiroute ~> check {
          handled shouldBe true
          status shouldBe OK
          contentType shouldBe `text/html(UTF-8)`
        }
      }
    }
  }

}