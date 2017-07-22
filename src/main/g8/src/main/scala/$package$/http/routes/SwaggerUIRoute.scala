package $package$.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives

import scala.concurrent.ExecutionContext

class SwaggerUIRoute()(implicit executionContext: ExecutionContext)
  extends Directives {


  def assets = pathPrefix("swagger") {
    getFromResourceDirectory("swagger") ~ pathSingleSlash(get(redirect("index.html", StatusCodes.PermanentRedirect)))
  }

  val route = assets

}


