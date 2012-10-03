package controllers.common

import play.api.mvc._
import controllers.routes
import models.User
import service.UserRepository

trait Secured {

  private def username(request: RequestHeader) = request.session.get("email")

  private def onUnauthorized(request: RequestHeader) = Results.Redirect(routes.Security.loginView)

  def IsAuthenticated(f: => String => Request[AnyContent] => Result) = Security.Authenticated(username, onUnauthorized) { user =>
    Action(request => f(user)(request))
  }
  def withUser(f: User => Request[AnyContent] => Result) = IsAuthenticated {
    username => implicit request =>
      UserRepository.findByEmail(username).map { user =>
        f(user)(request)
      }.getOrElse(onUnauthorized(request))
  }

  def IsMemberOf(group: Long)(f: => String => Request[AnyContent] => Result) = IsAuthenticated { user => request =>
    Results.Forbidden
  }

  def IsOwnerOf(shop: Long)(f: => String => Request[AnyContent] => Result) = IsAuthenticated { user => request =>
    Results.Forbidden
  }

}
