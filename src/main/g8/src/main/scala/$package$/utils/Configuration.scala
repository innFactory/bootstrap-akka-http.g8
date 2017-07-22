package $package$.utils

import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.duration.{FiniteDuration, MILLISECONDS}

trait Configuration {
  protected val config : Config = ConfigFactory.load()
  private val httpConfig = config.getConfig("http")
  private val databaseConfig = config.getConfig("database")
  private val authenticationConfig = config.getConfig("auth")

  val httpHost = httpConfig.getString("interface")
  val httpPort = httpConfig.getInt("port")
  val httpSelfTimeout = httpConfig.getDuration("self-timeout")

  val jdbcUrl = databaseConfig.getString("db.url")
  val dbUser = databaseConfig.getString("db.user")
  val dbPassword = databaseConfig.getString("db.password")

  val authCognito = authenticationConfig.getString("cognito")
  val allowAll = authenticationConfig.getBoolean("allow-all")

  private def getDuration(key: String) = FiniteDuration(config.getDuration(key, MILLISECONDS), MILLISECONDS)
}
