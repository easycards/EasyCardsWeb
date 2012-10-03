package controllers

import common.ControllerBase
import play.api._
import play.api.mvc._
import service.UserRepository

object Application extends ControllerBase {
  def index = Action {
    implicit request => {
      val user = request.session.get("email").map {
        case (email) => UserRepository.findByEmail(email)
      }.getOrElse(None)
      Ok(views.html.index("Your new application is ready.", user))
    }
  }
}