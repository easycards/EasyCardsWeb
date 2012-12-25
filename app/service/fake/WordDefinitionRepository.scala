package service.fake

import service.common.WordDefinitionService
import models.WordDefinition

object WordDefinitionRepository extends WordDefinitionService {
  def addWord(word: WordDefinition): Long = ???

  def list(vocabularyId: Long):Seq[WordDefinition] = ???
}
