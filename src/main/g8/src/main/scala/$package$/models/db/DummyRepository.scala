package $package$.models.db

import com.byteslounge.slickrepo.meta.Keyed
import com.byteslounge.slickrepo.repository.Repository
import $package$.models.Dummy
import slick.ast.BaseTypedType
import slick.jdbc.JdbcProfile

class DummyRepository()(implicit override val driver: JdbcProfile) extends Repository[Dummy, Long](driver) {

  import driver.api._

  val pkType = implicitly[BaseTypedType[Long]]
  val tableQuery = TableQuery[Dummys]
  type TableType = Dummys


  class Dummys(tag: slick.lifted.Tag) extends Table[Dummy](tag, "dummy") with Keyed[Long] {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def dummy = column[String]("dummy")

    def * = (id.?, dummy) <> ((Dummy.apply _).tupled, Dummy.unapply)
  }


  def find(id: Option[Long],
           dummy: Option[String]
          ): DBIO[Seq[Dummy]] = {

    MaybeFilter(tableQuery)
      .filter(id)(v => d => d.id === v)
      .filter(dummy)(v => d => d.dummy === v)
      .query
      .sortBy(table => table.column[Long]("id").asc)
      .result
  }

}
