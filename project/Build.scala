import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "zentasks"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "mysql" % "mysql-connector-java" % "5.1.18"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
    //This is a work around for testing  
    testOptions in Test ~= { args =>
	  for {
	    arg <- args
	    val ta: Tests.Argument = arg.asInstanceOf[Tests.Argument]
	    val newArg = if(ta.framework == Some(TestFrameworks.JUnit)) ta.copy(args = List.empty[String]) else ta
	  } yield newArg
	}      
  )

}
