package controllers

import common.{Secured, ControllerBase}

object WordDefinition extends ControllerBase with Secured {
  def list(vocabularyId: Long) = withUser {
    user => implicit request =>
      Ok(views.html.WordDefinition.list(Option(user)))
  }
}
