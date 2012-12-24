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

  "WordDefinition" should {
    "add new word to a dictionary" in new database {
      running(FakeApplication(additionalConfiguration = postgresTest())) {

      }
    }
    "update an existing word in a dictionary" in new database {
      running(FakeApplication(additionalConfiguration = postgresTest())){

      }
    }
  }
}
