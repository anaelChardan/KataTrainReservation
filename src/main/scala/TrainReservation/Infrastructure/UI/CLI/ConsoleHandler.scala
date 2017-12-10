package TrainReservation.Infrastructure.UI.CLI

trait ConsoleHandler {
  def help(): String
  def usage(): String
  def execute(entry: String): Either[String, String]
}
