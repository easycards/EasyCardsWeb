import play.Project._
import sbt._
import Keys._

object ApplicationBuild extends Build {

  val appName = "EasyCards"
  val appVersion = "1.0"


  val appDependencies = Seq(
    jdbc,
    anorm,
    "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
  )
  val main = play.Project(appName, appVersion, appDependencies).settings(
    lessEntryPoints <<= baseDirectory(_ / "app" / "assets" / "stylesheets" / "bootstrap" ** "main.less")
  )
}
