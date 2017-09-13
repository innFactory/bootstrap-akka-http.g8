package $package$.services

import $package$.models.Dummy
import $package$.models.db.DummyRepository
import $package$.utils.Persistence

import scala.concurrent.{ExecutionContext, Future}

class DummyService()(implicit executionContext: ExecutionContext, persistence: Persistence) {
  import persistence._
  val dummyRepository = new DummyRepository()

  def getAll : Future[Seq[Dummy]] = {
      dummyRepository.findAll()
  }

  def getOne(id : Long) : Future[Seq[Dummy]] = {
    executeOperation {
      dummyRepository.find(Some(id), None)
    } //FIXME because return datatyp is given you can remove executeOperation. Its used implictly from persistence class
  }

}
