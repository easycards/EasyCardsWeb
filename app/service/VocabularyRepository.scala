package service

import common.VocabularyService
import models.{User, Vocabulary}
import play.api.db.DB
import anorm._
import play.api.Play.current
import anorm.SqlParser._
import anorm.~

object VocabularyRepository extends VocabularyService {
  val vocabularyParser = {
      get[String]("vocabulary.title") ~
      get[String]("vocabulary.sourceLanguage") ~
      get[String]("vocabulary.targetLanguage") ~
      get[Long]("vocabulary.id") ~
      get[Long]("vocabulary.userId") map {
      case title ~ source ~ target ~ id ~ userId =>
        Vocabulary(title, source, target, Option(id), Option(userId))
    }
  }

  def list: Seq[Vocabulary] = {
    DB.withConnection {
      implicit connection =>
        SQL(
          """
            select * from vocabulary
          """
        ).as(VocabularyRepository.vocabularyParser *)
    }
  }

  def create(vocabulary: Vocabulary): Long = {
    DB.withConnection {
      implicit connection =>
        SQL(
          """
            insert into vocabulary(title, sourceLanguage, targetLanguage, userId)
            values({title}, {sourceLanguage}, {targetLanguage}, {userId});
          """
        ).on(
          'title -> vocabulary.title,
          'sourceLanguage -> vocabulary.sourceLanguage,
          'targetLanguage -> vocabulary.targetLanguage,
          'userId -> vocabulary.userId
        ).executeUpdate
        SQL("select max(id) from vocabulary where userId = {userId};").on(
          'userId -> vocabulary.userId
        ).as(scalar[Long].single)
    }
  }
}
