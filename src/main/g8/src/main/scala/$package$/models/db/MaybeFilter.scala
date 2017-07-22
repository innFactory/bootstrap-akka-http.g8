package $package$.models.db

import slick.lifted.CanBeQueryCondition

import scala.language.higherKinds

case class MaybeFilter[X, Y, C[_]](val query: slick.lifted.Query[X, Y, C]) {
  def filter[T,R:CanBeQueryCondition](data: Option[T])(f: T => X => R) = {
    data.map(v => MaybeFilter(query.withFilter(f(v)))).getOrElse(this)
  }
}
