package tests

object Helpers {
  def postgresTest(name: String = "default"): Map[String, String] = {
    Map(
      ("db." + name + ".driver") -> "org.postgresql.Driver",
      ("db." + name + ".url") -> ("jdbc:postgresql://localhost/easycards-test"),
      ("db." + name + ".user") -> ("easycards"),
      ("db." + name + ".password") -> ("easycards")
    )
  }
}
