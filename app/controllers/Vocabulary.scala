package controllers

import common.{ControllerBase, Secured}
import play.api._
import data.{Forms, Form}
import data.Forms._
import i18n.Messages
import play.api.mvc.{Action, Controller}
import service.{VocabularyRepository, UserRepository}
import service.fake.{LanguageRepository}
import play.api.data.format.Formats._


object Vocabulary extends ControllerBase with Secured {
  val vocabularyForm = Form(
    mapping(
      "title" -> nonEmptyText,
      "sourceLanguage" -> nonEmptyText,
      "targetLanguage" -> nonEmptyText,
      "id" -> optional(of[Long]),
      "userId" -> optional(of[Long])
    )(models.Vocabulary.apply)(models.Vocabulary.unapply))

  def createView = withUser {
    user => implicit request =>
      Ok(views.html.Vocabulary.create(vocabularyForm, Option(user), LanguageRepository.getList))
  }

  def create = withUser {
    user => implicit request => {
      vocabularyForm.bindFromRequest.fold(
        errors => BadRequest(views.html.Vocabulary.create(errors, Option(user), LanguageRepository.getList)),
        vocabulary => {
          VocabularyRepository.create(vocabulary.copy(userId=Option(user.id)))
          Redirect(routes.Vocabulary.list)
        }
      )
    }
  }

  def get = Action {
    TODO
  }

  def remove = Action {
    TODO
  }

  def list = Action {
    implicit request => {
      val user = request.session.get("email").map {
        case (email) => UserRepository.findByEmail(email)
      }.getOrElse(None)
      Ok(views.html.Vocabulary.list(user, VocabularyRepository.list))
    }
  }

  def update = Action {
    TODO
  }
}
