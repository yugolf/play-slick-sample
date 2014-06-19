package models.db

import models.db.common.Tables._
import models.db.common.Tables.profile.simple._
import models.db.common.AbstractDao
import scala.collection.immutable.List
import scala.slick.driver.H2Driver.simple._

abstract class AbstractEvents extends AbstractDao {
  /** create table */
  def createTable = database.withSession { implicit session: Session =>
    Event.ddl.create
  }

  /** drop table */
  def dropTable = database.withSession { implicit session: Session =>
    Event.ddl.drop
  }
  /** insert */
  def insert(e: EventRow) = database.withTransaction { implicit session: Session =>
    Event.insert(e)
  }

  /** update */
  def update(e: EventRow) = database.withTransaction { implicit session: Session =>
    Event.filter(t => t.id === e.id).update(e)
  }

  /** delete */
  def remove(id :Int) = database.withTransaction { implicit session: Session =>
    Event.filter(t => t.id === id).delete
  }

  /** find by id */
  def filterById(id :Int): EventRow = database.withTransaction { implicit session: Session =>
    Event.filter(t => t.id === id).first
  }

  /** find all data */
  def findAll(): List[EventRow] = database.withTransaction { implicit session: Session =>
    Event.sortBy(f => (f.id)).list
  }

  /** filter everything */
  def filter(form: Product): List[EventRow] = database.withTransaction { implicit session =>
    filter(form, form.getClass().getDeclaredFields().iterator, Event).list
  }

}