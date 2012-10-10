package controllers

import common.{Secured, ControllerBase}
import play.api.data.Form
import play.api.data.Forms._
import service.fake.{WordDefinitionRepository, LanguageRepository}
import play.api.data.format.Formats._
object WordDefinition extends ControllerBase with Secured {
  val wordDefinitionForm = Form(
    mapping(
      "source" -> nonEmptyText,
      "target" -> nonEmptyText,
      "sourceLanguage" -> nonEmptyText,
      "targetLanguage" -> nonEmptyText,
      "vocabularyId" -> of[Long]
    )(models.WordDefinition.apply)(models.WordDefinition.unapply))
  def list(vocabularyId: Long) = withUser {
    user => implicit request =>
      Ok(views.html.WordDefinition.list(Option(user), vocabularyId))
  }
  def addWordView(vocabularyId: Long) = withUser {
    user => implicit request =>
      Ok(views.html.WordDefinition.add(wordDefinitionForm,
        Option(user),
        LanguageRepository.getList,
        vocabularyId))
  }
  def addWord(vocabularyId: Long) = withUser {
    user => implicit request => {
      wordDefinitionForm.bindFromRequest.fold(
        formWithErrors => BadRequest(views.html.WordDefinition.add(formWithErrors, Option(user), LanguageRepository.getList, vocabularyId)),
        wordDefinition => {
          WordDefinitionRepository.addWord(wordDefinition)
          Redirect(routes.WordDefinition.list(vocabularyId))
        }
      )
    }
  }
}
