package service

import common.DefinitionService
import models.Definition
import play.api.db.DB
import anorm._
import models.Definition
import anorm.SqlParser._
import models.Definition
import play.api.Play.current
import anorm.~

object DefinitionRepository extends DefinitionService {
  def find(definitionId: Long): Option[Definition] = ???

  def addWord(word: Definition): Long = {
    DB.withConnection {
      implicit connection =>
        SQL(
          """
            insert into definition(source, target, sourceLanguage, targetLanguage, vocabularyId)
            values({source}, {target}, {sourceLanguage}, {targetLanguage}, {vocabularyId})
            returning ID;
          """
        ).on(
          'source -> word.source,
          'target -> word.target,
          'sourceLanguage -> word.sourceLanguage,
          'targetLanguage -> word.targetLanguage,
          'vocabularyId -> word.vocabularyId
        ).as(scalar[Long].single)
    }
  }

  def list(vocabularyId: Long):Seq[Definition] = ???
}
