package models

case class Vocabulary (
               title: String,
               sourceLanguage: String,
               targetLanguage: String,
               id: Option[Long] = None,
               userId:Option[Long] = None
           )
