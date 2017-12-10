package TrainReservation.Infrastructure.WebApi

import TrainReservation.Domain.{BookingReference, BookingReferenceProvider}

class RestBookingReferenceProvider extends BookingReferenceProvider {
  override def get() = BookingReference(scala.io.Source.fromURL("http://localhost:8082/booking_reference").mkString)
}
