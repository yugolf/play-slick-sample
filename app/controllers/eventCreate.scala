package controllers.event

import play.api.mvc._
import models.db.Events
import models.db.common.Tables._
import models.form.event.{EventCreateForm, EventCreateForms}

object EventCreate extends Controller with EventCreateForm {

  def index = Action { implicit request =>
    Ok(views.html.event.eventCreate(eventCreateForm))
  }

  def create = Action { implicit request =>
    eventCreateForm.bindFromRequest.fold(
      hasErrors = { form =>
        Ok(views.html.event.eventCreate(form))
      },
      success = { form =>
        val event = EventRow(form.id, form.eventId, form.eventNm, form.eventDate, form.homepage)
        Events.insert(event)
        Redirect(controllers.event.routes.EventCreate.index)
          .flashing("success" -> "Registration was successful.")
      })
  }


}