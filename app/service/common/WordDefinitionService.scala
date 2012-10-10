package service.common

import models.WordDefinition

trait WordDefinitionService {
  def addWord(word: WordDefinition)

  def list(vocabularyId: Long): Seq[WordDefinition]
}
