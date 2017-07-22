package $package$.services

import $package$.utils.Authentication

import scala.collection.JavaConverters._
import scala.concurrent.{ExecutionContext, Future, blocking}

class AuthService(auth : Authentication)(implicit executionContext: ExecutionContext) {

  def authenticate(accessToken: String): Future[Option[Map[String, AnyRef]]] = Future {
    blocking {
      val jwtCheck = auth.validateToken(accessToken)
      if (jwtCheck == null) {
        None
      } else {
        Some(jwtCheck.asScala.toMap)
      }
    }
  }

}
