package service.common

import models.Definition

trait DefinitionService {
  def addWord(word: Definition): Long

  def list(vocabularyId: Long): Seq[Definition]
}
