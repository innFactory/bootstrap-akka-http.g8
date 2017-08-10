package $package$.http.routes

import javax.ws.rs.Path

import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import $package$.http.SecurityDirectives
import $package$.models.Dummy
import $package$.services.{AuthService, DummyService}
import io.circe.generic.auto._
import io.circe.syntax._
import io.swagger.annotations._

import scala.concurrent.ExecutionContext

@Path("dummy")
@Api(value = "/dummy", produces = "application/json")
class DummyServiceRoute(val authService: AuthService, val dummyService: DummyService)(implicit executionContext: ExecutionContext) extends FailFastCirceSupport with SecurityDirectives {

  import dummyService._

  val route = pathPrefix("dummy") {
    routeGetAll ~ routeGetOne
  }


  @ApiOperation(value = "Get all dummys", notes = "", nickname = "getDummys", httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "Array of Dummys", response = classOf[Array[Dummy]]),
    new ApiResponse(code = 403, message = "Auth failed"),
    new ApiResponse(code = 500, message = "Internal server error")
  ))
  def routeGetAll = pathEndOrSingleSlash {
      get {
        complete(getAll.map(_.asJson))
      }
    }
  


  @Path("/{dummyId}")
  @ApiOperation(value = "Get one dummy", notes = "", nickname = "getDummy", httpMethod = "GET")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "dummyId", value = "dummy id", required = true, dataType = "integer", paramType = "path")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "Dummy", response = classOf[Dummy]),
    new ApiResponse(code = 403, message = "Auth failed"),
    new ApiResponse(code = 500, message = "Internal server error")
  ))
  def routeGetOne =
    get {
      path(IntNumber) { dummyId =>
        authenticate { u =>
          complete {
            dummyService.getOne(dummyId).map(_.asJson)
          }
        }
      }
    }

}
