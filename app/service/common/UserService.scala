package service.common

import models.User

trait UserService {
  def authenticate(email: String, password: String):Option[User]
  def findByEmail(email:String) : Option[User]
  def signUp(user: User): User
}

