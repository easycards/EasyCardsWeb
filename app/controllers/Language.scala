package controllers

import common.ControllerBase
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import play.api.Logger
import play.api.i18n.Lang
import play.api.mvc.Cookie
import service.UserRepository

object Language extends ControllerBase {
  protected val HOME_URL = "/"
  def changeLocale(locale:String) = Action { implicit request =>
    val referrer = request.headers.get(REFERER).getOrElse(HOME_URL)
    Redirect(referrer).withCookies(Cookie(LANG, locale))
  }
  def changeLocaleView = Action {
    implicit request =>
      val user = request.session.get("email").map {
        case (email) => UserRepository.findByEmail(email)
      }.getOrElse(None)
      Ok(views.html.Language.changeLocale(user))
  }
}
