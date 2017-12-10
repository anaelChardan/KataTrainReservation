package TrainReservation.Domain

case class ReferencedSeat(val seat: Seat, val bookingReference: Option[BookingReference]) {
  val isAvailable = bookingReference.isEmpty
}