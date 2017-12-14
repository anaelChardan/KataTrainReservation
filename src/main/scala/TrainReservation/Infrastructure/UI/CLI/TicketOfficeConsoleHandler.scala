package TrainReservation.Infrastructure.UI.CLI

import TrainReservation.Application.{ReservationRequest, TicketOffice}
import TrainReservation.Infrastructure.WebApi.{RestBookingReferenceProvider, RestTrainService}

class TicketOfficeConsoleHandler extends ConsoleHandler {
  override def help(): String = ":make_reservation [train_id] [number_of_seats]"
  override def usage(): String = ":make_reservation"

  override def execute(entry: String): Either[String, String] = {
    val pattern = ":make_reservation (([0-9]|[A-Za-z])+) ([0-9]+)".r

    entry match {
      case pattern(trainId, numberOfSeats) => {
        val reservationRequest = ReservationRequest(trainId, numberOfSeats.toInt)
        val ticketOffice: TicketOffice = new TicketOffice(new RestTrainService, new RestBookingReferenceProvider)

        ticketOffice.makeReservation(reservationRequest) match {
          case Some(reservation) =>
            val seats = reservation.seats.map(seat => s"\42${seat.seatNumber}${seat.coach}\42")

            Left(s"{\42train_id\42:\42${trainId}\42, \42booking_reference\42:\42${reservation.bookingReference}\42, \42seats\42:[${seats.mkString(",")}]}")

          case None =>
            Left(s"{\42train_id\42:\42${trainId}\42, \42booking_reference\42:\42, \42seats\42:[]}")
          }
      }

      case _ => Right(s"Cannot parse the entry ${entry}")
    }
  }
}
