package controllers

import common.ControllerBase
import play.api.mvc.{Action, Controller}
import play.api.data.Form
import play.api.data.Forms._
import service.UserRepository
import models.User


object Security extends ControllerBase {
  val loginForm = Form(
    tuple(
      "email" -> nonEmptyText,
      "password" -> nonEmptyText
    ) verifying("Invalid email or password", result =>
      result match {
        case (email, password) =>
          UserRepository.authenticate(email, password).isDefined
      })
  )
  val signUpForm = Form(
    tuple(
      "name" -> nonEmptyText,
      "email" -> nonEmptyText,
      "password" -> nonEmptyText
    ) verifying("Invalid email or password", result =>
      result match {
        case (name, email, password) =>
          UserRepository.signUp(User(name, email, password, 0))
          UserRepository.authenticate(email, password).isDefined
      })
  )

  def signUpView = Action {
    implicit request =>
      Ok(views.html.Security.signUp(signUpForm))
  }

  def signUp = Action {
    implicit request =>
      signUpForm.bindFromRequest.fold(
        formWithErrors => BadRequest(views.html.Security.signUp(formWithErrors)),
        user => {
          val (name, email, password) = user
          Redirect(routes.Application.index).withSession("email" -> email)
        }
      )
  }

  def loginView = Action {
    implicit request =>
      Ok(views.html.Security.login(loginForm))
  }

  def login = Action {
    implicit request =>
      loginForm.bindFromRequest.fold(
        formWithErrors => BadRequest(views.html.Security.login(formWithErrors)),
        user => {
          val (email, password) = user
          Redirect(routes.Application.index).withSession("email" -> email)
        }
      )
  }

  def logout = Action {
    Redirect(routes.Application.index).withNewSession.flashing(
      "success" -> "You've been logged out"
    )
  }
}
