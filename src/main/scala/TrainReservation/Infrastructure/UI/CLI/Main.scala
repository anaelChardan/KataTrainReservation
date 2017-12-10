package TrainReservation.Infrastructure.UI.CLI

import TrainReservation.Domain.TrainId
import TrainReservation.Infrastructure.WebApi.RestTrainService

import scala.io.StdIn

object Main extends App {
  var handlers: List[ConsoleHandler] = List.empty

  override def main(args: Array[String]): Unit = {
    Console.println("--- Train TrainReservation.Domain.Reservation Kata ---")

    handlers = List(new TicketOfficeConsoleHandler);

    usage()

    while (true) {
      var entry: String = StdIn.readLine("> ")

      if (entry == ":q") {
        Console.println("\n Bye!")
        sys.exit()
      }

      handlers.collectFirst {
        case handler if handler.usage().startsWith(entry) => handler
      }  match {
        case Some(handler) => {
          handler.execute(entry) match {
            case Left(res: String) => Console.println(s"Success: ${res}")
            case Right(err: String) => {
              Console.println(s"Error!! ${err}")
              usage()
            }
          }
        }
        case None => usage()
      }
    }
  }

  def usage(): Unit = {
    Console.println()
    Console.println("Usage: \n")
    Console.println(":q - Quit")
    handlers.map(_.help()).foreach(Console.println(_))
    Console.println()
  }
}
