package service.fake

import service.common.DefinitionService
import models.Definition

object DefinitionRepository extends DefinitionService {
  def addWord(word: Definition): Long = ???

  def list(vocabularyId: Long):Seq[Definition] = ???
}
