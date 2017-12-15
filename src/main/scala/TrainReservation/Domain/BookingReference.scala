package TrainReservation.Domain

case class BookingReference(val id: String) {
  override def toString: String = id
}

