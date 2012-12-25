package tests.unit.service

import org.specs2.mutable.{After, Specification}
import play.api.test.Helpers._
import service.VocabularyRepository
import tests.Helpers._
import play.api.db.DB
import anorm._
import models.Vocabulary
import play.api.test.FakeApplication
import play.api.Play.current

class VocabularyRepository_Spec extends Specification {
  override def is = args(sequential = true) ^ super.is
  "VocabularyRepository" should {
    "Create vocabulary" in new database {
      running(FakeApplication(additionalConfiguration = postgresTest())) {
        val vocabulary = new Vocabulary("Test", "en", "ru")
        VocabularyRepository.create(vocabulary.copy(userId = Option(1)))
        val vocabularyId = VocabularyRepository.create(vocabulary.copy(userId = Option(1)))
        vocabularyId must equalTo(2)
      }
    }
    "Return list of vocabularies" in new database {
      running(FakeApplication(additionalConfiguration = postgresTest())) {
        Seq(
          new Vocabulary("Test 1", "en", "ru", userId = Option(1)),
          new Vocabulary("Test 2", "de", "ru", userId = Option(1))
        ).foreach(VocabularyRepository.create)
        val vocabularies = VocabularyRepository.list
        vocabularies.size must equalTo(2)
      }
    }
  }
}

trait database extends After{
  def after = {
    running(FakeApplication(additionalConfiguration = postgresTest())){
      DB.withConnection {
        implicit connection =>
          SQL(
            """
            delete from vocabulary;
            ALTER SEQUENCE vocabulary_id_seq RESTART WITH 1;

            delete from word_definition;
            ALTER SEQUENCE word_definition_id_seq RESTART WITH 1;
            """
          ).executeUpdate()
      }
    }
  }
}
