package controllers.event

import play.api.mvc._
import models.db.Events
import models.db.common.Tables._
import models.form.event.{EventSearchForm, EventSearchForms}

object EventSearch extends Controller with EventSearchForm {

  def index = Action { implicit request =>
    Ok(views.html.event.eventSearch(eventSearchForm, null))
  }

  def search = Action { implicit request =>
    eventSearchForm.bindFromRequest.fold(
      hasErrors = { form =>
        Ok(views.html.event.eventSearch(form, null))
      },
      success = { form =>
        //val events = Events.find(form.id, form.eventId, form.eventNm, form.eventDate, form.homepage)
        //val events = Events.findAll
        val events = Events.filter(form)
        Ok(views.html.event.eventSearch(eventSearchForm.bindFromRequest, events))
      })
  }

  def delete(id :Int) = Action { implicit request =>
    Events.remove(id)
    Redirect(controllers.event.routes.EventSearch.index)
      .flashing("success" -> "Delete was successful.")
  }


}