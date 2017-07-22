package de.innfactory.bootstrap.utils
import java.util
import scala.collection.JavaConverters._

class AutoValidate extends Authentication {
  override def validateToken(token: String): util.Map[String, AnyRef] = Map[String, AnyRef]("testrun" -> "true").asJava
}
