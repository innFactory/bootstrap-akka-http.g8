package de.innfactory.bootstrap.http

import akka.http.scaladsl.server.Directive1
import akka.http.scaladsl.server.directives.{BasicDirectives, FutureDirectives, HeaderDirectives, RouteDirectives}
import de.innfactory.bootstrap.services.AuthService
import de.innfactory.bootstrap.utils.Configuration

trait SecurityDirectives extends Configuration {

  import BasicDirectives._
  import FutureDirectives._
  import HeaderDirectives._
  import RouteDirectives._

  def authenticate: Directive1[Map[String, AnyRef]] = {
    if(allowAll){
      provide(Map())
    }else {
      headerValueByName("Authorization").flatMap { token =>
        onSuccess(authService.authenticate(token)).flatMap {
          case Some(user) => provide(user)
          case None => reject
        }
      }
    }
  }

  protected val authService: AuthService

}
