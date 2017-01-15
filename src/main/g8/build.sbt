organization := "$package$"
name := "$name;format="lower,hyphen"$"
scalaVersion := "2.12.1"

enablePlugins(ScalaJSPlugin)
scalaJSModuleKind := ModuleKind.CommonJSModule

InputKey[Unit]("gcDeploy") := {
  val args = sbt.complete.DefaultParsers.spaceDelimited("gcDeploy <project-id> [<pubsub-topic>]").parsed

  val projectId = args.head
  val trigger = args.tail match {
    case Nil => "--trigger-http"
    case pubSubTopic :: Nil => s"--trigger-topic \$pubSubTopic"
  }

  val gcTarget = target.value / "gcloud"
  val function = gcTarget / "function.js"
  val functionName = "$name;format="camel"$"
  sbt.IO.copyFile((fastOptJS in Compile).value.data, function)
  s"gcloud beta functions deploy \$functionName --local-path \${gcTarget.getAbsolutePath} --stage-bucket \${name.value} \$trigger --project \$projectId --region us-central1"!
}
