package service.common

import models.WordDefinition

trait WordDefinitionService {
  def addWord(word: WordDefinition): Long

  def list(vocabularyId: Long): Seq[WordDefinition]
}
