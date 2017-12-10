package TrainReservation.Infrastructure.UI.CLI

class TicketOfficeConsoleHandler extends ConsoleHandler {
  override def help(): String = ":make_reservation [train_id] [number_of_seats]"
  override def usage(): String = ":make_reservation"

  override def execute(entry: String): Either[String, String] = ???
}
