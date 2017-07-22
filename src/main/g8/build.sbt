name := "bootstrap-akka-http"
organization := "de.innfactory"
version := "1.0.0"
scalaVersion := Version.Scala

libraryDependencies ++= {
  Seq(
    Library.swagger,
    Library.swaggerAkka,
    Library.akkaActor,
    Library.akkaHttp,
    Library.akkaHttpCors,
    Library.akkaHttpCirce,
    Library.akkaStream,
    Library.log4jCore,
    Library.slf4jLog4jBridge,
    Library.akkaLog4j,
    Library.slick,
    Library.slickHikaricp,
    Library.postgresql,
    Library.slickRepo,
    Library.flywaydb,
    Library.nimbusds,
    Library.circeCore,
    Library.circeGeneric,
    Library.circeParser,
    TestLibrary.akkaTestkit,
    TestLibrary.akkaHttpTestkit,
    TestLibrary.postgresqlEmbedded,
    TestLibrary.scalaTest
  )
}

Revolver.settings
enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)

dockerExposedPorts := Seq(8080)
dockerEntrypoint := Seq("bin/%s" format executableScriptName.value, "-Dconfig.resource=docker.conf")
