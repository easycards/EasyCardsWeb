package controllers.common
import play.api.mvc.{RequestHeader, Cookie, Action, Controller}
import play.api.data.Form
import play.api.data.Forms._
import play.api.Logger
import play.api.i18n.Lang

trait ControllerBase extends Controller {
  protected val LANG = "lang"
  override implicit def lang(implicit request: RequestHeader) = {

    request.cookies.get(LANG) match {

      case None => super.lang(request)
      case Some(cookie) => Lang(cookie.value)
    }
  }
}
