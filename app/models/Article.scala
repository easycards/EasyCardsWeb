package models

import org.joda.time.DateTime

case class Article(source:String,
                   target:String,
                   comment:String,
                   sourceLanguage:String,
                   targetLanguage:String){
  var difficulty:Int = 0
  var lastAnswered:Option[DateTime]=None
}
