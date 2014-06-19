package controllers.event

import play.api.mvc._
import models.db.Events
import models.db.common.Tables._
import models.form.event.{EventUpdateForm, EventUpdateForms}

object EventUpdate extends Controller with EventUpdateForm {

  def index(id: Int) = Action { implicit request =>
    val event = Events.filterById(id)
    val form = EventUpdateForms(event.id, event.eventId, event.eventNm, event.eventDate, event.homepage)

    Ok(views.html.event.eventUpdate(eventUpdateForm.fill(form), id))
  }

  def update(id :Int) = Action { implicit request =>
    eventUpdateForm.bindFromRequest.fold(
      hasErrors = { form =>
        Ok(views.html.event.eventUpdate(form, id))
      },
      success = { form =>
          val event = EventRow(form.id, form.eventId, form.eventNm, form.eventDate, form.homepage)
    Events.update(event)
        Redirect(controllers.event.routes.EventUpdate.index(id))
          .flashing("success" -> "Update was successful.")
      })
  }


}