package service.fake

import service.common.LanguageService

object LanguageRepository extends LanguageService {
  def getList = Seq("en"->"English","ru"->"Русский","de"->"Deutsch")
}
