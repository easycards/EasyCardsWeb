package tests.unit.service

import org.specs2.mutable.Specification
import play.api.test.Helpers._
import play.api.test.FakeApplication
import models.Vocabulary
import service.VocabularyRepository

class VocabularyRepository_Tests extends Specification {
  override def is = args(sequential = true) ^ super.is

  "VocabularyRepository" should {
    "Create vocabulary" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        val vocabulary = new Vocabulary("Test", "en", "ru")
        VocabularyRepository.create(vocabulary.copy(userId = 1))
        val vocabularyId = VocabularyRepository.create(vocabulary.copy(userId = 1))
        vocabularyId must equalTo(2)
      }
    }
    "Return list of vocabularies" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        Seq(
          new Vocabulary("Test 1", "en", "ru"),
          new Vocabulary("Test 2", "de", "ru")
        ).foreach(VocabularyRepository.create)
        val vocabularies = VocabularyRepository.list
        vocabularies.size must equalTo(2)
      }
    }
  }
}
