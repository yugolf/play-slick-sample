package models.db.common

import models.db.common.Tables.profile.simple._
import play.api.db.DB
import play.api.Play.current
import scala.slick.driver.H2Driver.simple._
import java.lang.reflect.Field
import scala.slick.lifted.Query
import play.Logger

abstract class AbstractDao {

  /** データベースコネクション */
  val database = Database.forDataSource(DB.getDataSource())

  def qfilter[A, B, T](q: Query[A, B, Seq], colname: String, value: T, condition: String) = {

    Logger.debug("value:" + value + ":" +value.getClass)
    q.filter { row =>
      val field = row.getClass().getDeclaredField(colname)
      field.setAccessible(true)
      val gotField = field.get(row)
      field.setAccessible(false)

      Logger.debug("gotField:"+gotField)

      value match {
        case Some(x: String) =>
          condmatch(condition, gotField.asInstanceOf[Column[String]], x.asInstanceOf[String])
        case x: String =>
          condmatch(condition, gotField.asInstanceOf[Column[String]], x.asInstanceOf[String])
        case Some(x: Int) =>
          condmatch(condition, gotField.asInstanceOf[Column[Int]], x.asInstanceOf[Int])
        case x: Int =>
          condmatch(condition, gotField.asInstanceOf[Column[Int]], x.asInstanceOf[Int])
        case Some(x: Boolean) =>
          condmatch(condition, gotField.asInstanceOf[Column[Boolean]], x.asInstanceOf[Boolean])
        case x: Boolean =>
          condmatch(condition, gotField.asInstanceOf[Column[Boolean]], x.asInstanceOf[Boolean])
        case Some(x: Byte) =>
          condmatch(condition, gotField.asInstanceOf[Column[Byte]], x.asInstanceOf[Byte])
        case x: Byte =>
          condmatch(condition, gotField.asInstanceOf[Column[Byte]], x.asInstanceOf[Byte])
        case Some(x: Short) =>
          condmatch(condition, gotField.asInstanceOf[Column[Short]], x.asInstanceOf[Short])
        case x: Short =>
          condmatch(condition, gotField.asInstanceOf[Column[Short]], x.asInstanceOf[Short])
        case Some(x: Long) =>
          condmatch(condition, gotField.asInstanceOf[Column[Long]], x.asInstanceOf[Long])
        case x: Long =>
          condmatch(condition, gotField.asInstanceOf[Column[Long]], x.asInstanceOf[Long])
        case Some(x: BigDecimal) =>
          condmatch(condition, gotField.asInstanceOf[Column[BigDecimal]], x.asInstanceOf[BigDecimal])
        case x: BigDecimal =>
          condmatch(condition, gotField.asInstanceOf[Column[BigDecimal]], x.asInstanceOf[BigDecimal])
        case Some(x: Double) =>
          condmatch(condition, gotField.asInstanceOf[Column[Double]], x.asInstanceOf[Double])
        case x: Double =>
          condmatch(condition, gotField.asInstanceOf[Column[Double]], x.asInstanceOf[Double])
        case Some(x: Float) =>
          condmatch(condition, gotField.asInstanceOf[Column[Float]], x.asInstanceOf[Float])
        case x: Float =>
          condmatch(condition, gotField.asInstanceOf[Column[Float]], x.asInstanceOf[Float])
        case Some(x: java.sql.Date) =>
          condmatch(condition, gotField.asInstanceOf[Column[java.sql.Date]], x.asInstanceOf[java.sql.Date])
        case x: java.sql.Date =>
          condmatch(condition, gotField.asInstanceOf[Column[java.sql.Date]], x.asInstanceOf[java.sql.Date])
        case Some(x: java.sql.Timestamp) =>
          condmatch(condition, gotField.asInstanceOf[Column[java.sql.Timestamp]], x.asInstanceOf[java.sql.Timestamp])
        case x: java.sql.Timestamp =>
          condmatch(condition, gotField.asInstanceOf[Column[java.sql.Timestamp]], x.asInstanceOf[java.sql.Timestamp])
        case _ =>
          new LiteralColumn(true)
      }
    }

  }
  def condmatch(condition: String, col: Column[String], value: String) =
    condition match {
      case "=" | "==" | "===" => col === value
      case "!=" | "<>" => col =!= value
      case "<" => col < value
      case "<=" => col <= value
      case ">" => col > value
      case ">=" => col >= value
      case "contains" | "like" => col like s"%$value%"
      case "startWith" | "startwith" => col like s"$value%"
      case "endWith" | "endwith" => col like s"%$value"
      case _ => col === value
    }

  def condmatch(condition: String, col: Column[Int], value: Int) =
    condition match {
      case "=" | "==" | "===" => col === value
      case "!=" | "<>" => col =!= value
      case "<" => col < value
      case "<=" => col <= value
      case ">" => col > value
      case ">=" => col >= value
      case _ => col === value
    }

  def condmatch(condition: String, col: Column[Boolean], value: Boolean) =
    condition match {
      case "=" | "==" | "===" => col === value
      case "!=" | "<>" => col =!= value
      case "<" => col < value
      case "<=" => col <= value
      case ">" => col > value
      case ">=" => col >= value
      case _ => col === value
    }
  def condmatch(condition: String, col: Column[Byte], value: Byte) =
    condition match {
      case "=" | "==" | "===" => col === value
      case "!=" | "<>" => col =!= value
      case "<" => col < value
      case "<=" => col <= value
      case ">" => col > value
      case ">=" => col >= value
      case _ => col === value
    }
  def condmatch(condition: String, col: Column[Short], value: Short) =
    condition match {
      case "=" | "==" | "===" => col === value
      case "!=" | "<>" => col =!= value
      case "<" => col < value
      case "<=" => col <= value
      case ">" => col > value
      case ">=" => col >= value
      case _ => col === value
    }
  def condmatch(condition: String, col: Column[Long], value: Long) =
    condition match {
      case "=" | "==" | "===" => col === value
      case "!=" | "<>" => col =!= value
      case "<" => col < value
      case "<=" => col <= value
      case ">" => col > value
      case ">=" => col >= value
      case _ => col === value
    }
  def condmatch(condition: String, col: Column[BigDecimal], value: BigDecimal) =
    condition match {
      case "=" | "==" | "===" => col === value
      case "!=" | "<>" => col =!= value
      case "<" => col < value
      case "<=" => col <= value
      case ">" => col > value
      case ">=" => col >= value
      case _ => col === value
    }
  def condmatch(condition: String, col: Column[Double], value: Double) =
    condition match {
      case "=" | "==" | "===" => col === value
      case "!=" | "<>" => col =!= value
      case "<" => col < value
      case "<=" => col <= value
      case ">" => col > value
      case ">=" => col >= value
      case _ => col === value
    }
  def condmatch(condition: String, col: Column[Float], value: Float) =
    condition match {
      case "=" | "==" | "===" => col === value
      case "!=" | "<>" => col =!= value
      case "<" => col < value
      case "<=" => col <= value
      case ">" => col > value
      case ">=" => col >= value
      case _ => col === value
    }
  def condmatch(condition: String, col: Column[java.sql.Date], value: java.sql.Date) =
    condition match {
      case "=" | "==" | "===" => col === value
      case "!=" | "<>" => col =!= value
      case "<" => col < value
      case "<=" => col <= value
      case ">" => col > value
      case ">=" => col >= value
      case _ => col === value
    }
  def condmatch(condition: String, col: Column[java.sql.Timestamp], value: java.sql.Timestamp) =
    condition match {
      case "=" | "==" | "===" => col === value
      case "!=" | "<>" => col =!= value
      case "<" => col < value
      case "<=" => col <= value
      case ">" => col > value
      case ">=" => col >= value
      case _ => col === value
    }

  def getValue[T](c: T) = c.asInstanceOf[T]

  def filter[A, B](form: Product, iterator: Iterator[Field], q: Query[A, B, Seq]): Query[A, B, Seq] = {
    if (!iterator.hasNext) q
    else {
      val field = iterator.next
      field.setAccessible(true)
      val colname = field.getName
      val value = getValue(field.get(form))
      val condition = field.getAnnotation(classOf[SearchCondition])
      field.setAccessible(false)
      if (value == null || value == None || value == "" || condition == null) filter(form, iterator, q)
      else filter(form, iterator, qfilter(q, colname, value, condition.value))
    }
  }
  def filter[A, B](iterator: Iterator[(String, String)], q: Query[A, B, Seq]): Query[A, B, Seq] = {
    if (!iterator.hasNext) q else {
      val (key, value) = iterator.next
      if (value.isEmpty) q else {
        val colname = key.indexOf("_") match {
          case x if (x > 0) => key.substring(0, x)
          case _ => key
        }
        val condition = key.lastIndexOf("_") match {
          case x if (x > 0) => key.substring(x + 1)
          case _ => ""
        }
        filter(iterator, qfilter(q, colname, value, condition))
      }
    }
  }
}
