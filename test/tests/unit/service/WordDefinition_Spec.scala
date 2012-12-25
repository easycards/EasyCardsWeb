package tests.unit.service

import org.specs2.mutable.{After, Specification}
import play.api.test.Helpers._
import service.{WordDefinitionRepository, VocabularyRepository}
import tests.Helpers._
import play.api.db.DB
import anorm._
import models.{WordDefinition, Vocabulary}
import play.api.test.FakeApplication
import play.api.Play.current

class WordDefinition_Spec extends Specification {
  override def is = args(sequential = true) ^ super.is

  "WordDefinition" should {
    "add new word to a dictionary" in new database {
      running(FakeApplication(additionalConfiguration = postgresTest())) {
        VocabularyRepository.create(new Vocabulary("Deutsch", "de", "ru", userId = Option(1)))
        val newId = WordDefinitionRepository.addWord(new WordDefinition("test", "тест", "de", "ru", 1))
        newId must equalTo(1)
      }
    }
    "update an existing word in a dictionary" in new database {
      running(FakeApplication(additionalConfiguration = postgresTest())) {
        VocabularyRepository.create(new Vocabulary("Deutsch", "de", "ru", userId = Option(1)))
        val newId = WordDefinitionRepository.addWord(new WordDefinition("test", "тест", "de", "ru", 1))
        val wordDefinition: WordDefinition = WordDefinitionRepository.find(newId)
        wordDefinition.copy(source = "test 2", target = "тест 2")
        WordDefinition.update(wordDefinition)
        val updatedWordDefinition: WordDefinition = WordDefinitionRepository.find(newId)
        updatedWordDefinition.source must equalTo("test 2")
      }
    }
  }
}
