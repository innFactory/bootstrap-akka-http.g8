package $package$

import akka.http.scaladsl.model.StatusCodes._

class AuthServiceTest extends BaseServiceTest {

  trait Context {
    val route = httpService.authRouter.route
  }

  "Auth service" should {
    "should run" in new Context {
      Get(s"/auth/") ~> route ~> check {
        status shouldBe OK
      }
    }
  }

}
