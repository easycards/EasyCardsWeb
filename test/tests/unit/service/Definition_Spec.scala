package tests.unit.service

import org.specs2.mutable.{After, Specification}
import play.api.test.Helpers._
import service.{DefinitionRepository, VocabularyRepository}
import tests.Helpers._
import play.api.db.DB
import anorm._
import models.{Definition, Vocabulary}
import play.api.test.FakeApplication
import play.api.Play.current

class Definition_Spec extends Specification {
  override def is = args(sequential = true) ^ super.is

  "WordDefinition" should {
    "add new word to a dictionary" in new database {
      running(FakeApplication(additionalConfiguration = postgresTest())) {
        VocabularyRepository.create(new Vocabulary("Deutsch", "de", "ru", userId = Option(1)))
        val newId = DefinitionRepository.addWord(new Definition("test", "тест", "de", "ru", 1))
        newId must equalTo(1)
      }
    }
    "update an existing word in a dictionary" in new database {
      running(FakeApplication(additionalConfiguration = postgresTest())) {
        VocabularyRepository.create(new Vocabulary("Deutsch", "de", "ru", userId = Option(1)))
        val newId = DefinitionRepository.addWord(new Definition("test", "тест", "de", "ru", 1))
        val wordDefinition: Definition = DefinitionRepository.find(newId)
        wordDefinition.copy(source = "test 2", target = "тест 2")
        Definition.update(wordDefinition)
        val updatedWordDefinition: Definition = DefinitionRepository.find(newId)
        updatedWordDefinition.source must equalTo("test 2")
      }
    }
  }
}
