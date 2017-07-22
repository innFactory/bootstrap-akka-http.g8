package de.innfactory.bootstrap.services

import de.innfactory.bootstrap.models.db.DummyRepository
import de.innfactory.bootstrap.utils.Persistence

import scala.concurrent.ExecutionContext

class DummyService()(implicit executionContext: ExecutionContext)  extends Persistence {
  val dummyRepository = new DummyRepository()

  def getAll = {
    executeOperation {
      dummyRepository.findAll()
    }
  }

  def getOne(id : Long) = {
    executeOperation {
      dummyRepository.find(Some(id), None)
    }
  }

}
