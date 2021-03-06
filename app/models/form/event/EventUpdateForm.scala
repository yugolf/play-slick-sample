package models.form.event

import play.api.data._
import play.api.data.Forms._
import java.sql.Date

case class EventUpdateForms(
   id: Int,
   eventId: String,
   eventNm: String,
   eventDate: Option[Date],
   homepage: Option[String])

trait EventUpdateForm {

  val eventUpdateForm = Form(
    mapping(
      "id" -> number(min = 0, max = 1000),
      "eventId" -> nonEmptyText(maxLength = 100),
      "eventNm" -> nonEmptyText(maxLength = 100),
      "eventDate" -> optional(sqlDate),
      "homepage" -> optional(text(maxLength = 100))
      )(EventUpdateForms.apply)(EventUpdateForms.unapply))
}