package de.innfactory.bootstrap.models

import com.byteslounge.slickrepo.meta.Entity
import io.swagger.annotations.ApiModel


@ApiModel("Dummy")
case class Dummy(override val id: Option[Long],
                       dummy: String) extends Entity[Dummy, Long] {
  override def withId(id: Long): Dummy = this.copy(id = Some(id))
}