package service

import anorm.SqlParser._
import anorm._
import common.UserService
import play.api.db.DB
import anorm.~
import models.User
import play.api.Play.current

object UserRepository extends UserService{
  val userParser = {
      get[String]("users.name") ~
      get[String]("users.email") ~
      get[String]("users.password") ~
      get[Long]("users.id") map {
      case name ~ email ~ password ~ id => User(name, email, password, id)
    }
  }
  def findByEmail(email:String) : Option[User] = {
    DB.withConnection {
      implicit connection =>
        SQL(
          """
            select * from users where email = {email}
          """
        ).on(
          'email -> email
        ).as(UserRepository.userParser.singleOpt)
    }
  }

  def signUp(user: User): User = {
    DB.withConnection {
      implicit connection =>
        SQL(
          """
            insert into users(email, name, password) values({email}, {name}, {password})
          """
        ).on(
          'email -> user.email,
          'name -> user.name,
          'password -> user.password
        ).executeUpdate()
        user
    }
  }

  def authenticate(email: String, password: String) = {
    DB.withConnection {
      implicit connection =>
        SQL(
          """
           select * from
            users
           where
            email = {email} and
            password = {password}
          """
        ).on(
          'email -> email,
          'password -> password
        ).as(UserRepository.userParser.singleOpt)
    }
  }
}
