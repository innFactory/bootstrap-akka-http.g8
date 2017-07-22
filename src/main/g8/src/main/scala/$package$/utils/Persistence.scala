package $package$.utils

import slick.basic.DatabaseConfig
import slick.dbio.DBIO
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

trait Profile {
  val profile: JdbcProfile
}


trait DbModule extends Profile{
  val db: JdbcProfile#Backend#Database

  implicit def executeOperation[T](databaseOperation: DBIO[T]): Future[T] = {
    db.run(databaseOperation)
  }

}

trait PersistenceModule {
  implicit def executeOperation[T](databaseOperation: DBIO[T]): Future[T]
}


class Persistence() extends PersistenceModule with DbModule {
  private val dbConfig : DatabaseConfig[JdbcProfile]  = DatabaseConfig.forConfig("database")
  override implicit val profile: JdbcProfile = dbConfig.profile
  override implicit val db: JdbcProfile#Backend#Database = dbConfig.db
}