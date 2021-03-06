package $package$.http

import akka.actor.{Actor, ActorLogging, ActorSystem}
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.server.Directives._
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors
import ch.megard.akka.http.cors.scaladsl.settings.CorsSettings
import de.innfactory.akka.AuthService
import $package$.http.routes.{DummyServiceRoute, SwaggerUIRoute}
import $package$.services.{DummyService, SwaggerDocService}
import $package$.utils.Configuration

import scala.concurrent.ExecutionContext

class HttpService(authService: AuthService,
                  dummyService: DummyService
                 )(implicit executionContext: ExecutionContext, actorSystem: ActorSystem) extends Configuration{

  val dummyRouter = new DummyServiceRoute(authService, dummyService)

  val swaggerRouter = new SwaggerUIRoute()
  val swaggerDocService = new SwaggerDocService(httpHost, httpPort, actorSystem)

  val settings = CorsSettings.defaultSettings.copy(allowedMethods = List(GET, POST, PUT, HEAD, OPTIONS, DELETE))

  // \$COVERAGE-OFF\$Routes are tested seperatly
  val routes =
    pathPrefix("v1") {
      cors(settings) {
        swaggerDocService.routes ~
        swaggerRouter.route ~
        dummyRouter.route
      }
    }
  // \$COVERAGE-ON\$

}
