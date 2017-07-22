package $package$.services

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.github.swagger.akka._
import com.github.swagger.akka.model.Info
import $package$.http.routes.{AuthServiceRoute, DummyServiceRoute}
import io.swagger.models.ExternalDocs
import io.swagger.models.auth.{ApiKeyAuthDefinition, In}

import scala.reflect.runtime.{universe => ru}


class SwaggerDocService(address: String, port: Int, system: ActorSystem)
  extends SwaggerHttpService
    with HasActorSystem {
  override implicit val actorSystem: ActorSystem = system
  override implicit val materializer: ActorMaterializer = ActorMaterializer()
  override val apiTypes = Seq(
    ru.typeOf[AuthServiceRoute], ru.typeOf[DummyServiceRoute]
  )
  override val host = address + ":" + port
  override val info = Info(version = "1.0")
  override val externalDocs = Some(new ExternalDocs("More Info", "#noset"))
  override val basePath: String = "/v1/"
  override val securitySchemeDefinitions = Map("basicAuth" -> new  ApiKeyAuthDefinition("Authorization", In.HEADER))

}