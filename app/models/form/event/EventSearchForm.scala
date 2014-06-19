package models.form.event

import play.api.data._
import play.api.data.Forms._
import java.sql.Date
import models.db.common.{ SearchCondition => cond }
import scala.annotation.meta.field

case class EventSearchForms(
  @(cond @field)("<=") id: Option[Int],
  @(cond @field)("startWith") eventId: Option[String],
  @(cond @field)("contains") eventNm: Option[String],
  @(cond @field)(">=") eventDate: Option[Date],
  @(cond @field)("endWith") homepage: Option[String])

trait EventSearchForm {

  val aaa = ""
  val eventSearchForm = Form(
    mapping(
      "id" -> optional(number(min = 0, max = 1000)),
      "eventId" -> optional(text(maxLength = 100)),
      "eventNm" -> optional(text(maxLength = 100)),
      "eventDate" -> optional(sqlDate),
      "homepage" -> optional(text(maxLength = 100)))(EventSearchForms.apply)(EventSearchForms.unapply))
}