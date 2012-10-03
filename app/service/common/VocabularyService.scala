package service.common

import models.Vocabulary

trait VocabularyService {

    def list:Seq[Vocabulary]

    def create(vocabulary:Vocabulary):Long
}
