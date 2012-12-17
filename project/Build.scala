import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "EasyCards"
    val appVersion      = "1.0"


    val appDependencies = Seq(
      anorm,
      "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      lessEntryPoints <<= baseDirectory(_ / "app" / "assets" / "stylesheets" / "bootstrap" ** "main.less")

    )

}
