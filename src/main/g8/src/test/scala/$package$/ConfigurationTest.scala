package $package$

import com.typesafe.config.ConfigFactory
import $package$.utils.Configuration
import org.scalatest.{Matchers, WordSpec}

class ConfigurationTest extends WordSpec with Matchers with Configuration {
   "Config" should {
    "should load all values from configuration file for test" in {
      httpHost.isEmpty shouldBe false
      httpPort should (be > (0) and be <= (65535))
      httpSelfTimeout.getSeconds.toInt should (be < (60))
      jdbcUrl.isEmpty shouldBe false
      jdbcUrl shouldBe "jdbc:postgresql://localhost:25535/database-name"
      dbUser.isEmpty shouldBe false
      dbPassword.isEmpty shouldBe false
      authCognito.startsWith("http") shouldBe true
      allowAll shouldBe false
    }
  }

}
