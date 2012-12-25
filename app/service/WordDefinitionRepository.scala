package service

import common.WordDefinitionService
import models.WordDefinition
import play.api.db.DB
import anorm._
import models.WordDefinition
import anorm.SqlParser._
import models.WordDefinition
import play.api.Play.current
import anorm.~

object WordDefinitionRepository extends WordDefinitionService {
  def find(definitionId: Long): Option[WordDefinition] = ???

  def addWord(word: WordDefinition): Long = {
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

  def list(vocabularyId: Long):Seq[WordDefinition] = ???
}
