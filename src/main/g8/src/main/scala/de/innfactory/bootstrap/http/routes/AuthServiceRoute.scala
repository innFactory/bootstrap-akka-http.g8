package de.innfactory.bootstrap.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import de.innfactory.bootstrap.http.SecurityDirectives
import de.innfactory.bootstrap.services.AuthService

import scala.concurrent.ExecutionContext

class AuthServiceRoute(val authService: AuthService)(implicit executionContext: ExecutionContext) extends FailFastCirceSupport with SecurityDirectives {

  import StatusCodes._

  val route = pathPrefix("auth") {
     path("") {
        pathEndOrSingleSlash {
          get {
            complete(OK -> "Auth service available!")
          }
        }
      }
  }

}
