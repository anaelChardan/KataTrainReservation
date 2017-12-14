package TrainReservation.Domain

case class Coach(letter: String, seats: List[ReferencedSeat]) {
  private val saleableNumberOfSeats: Int = Math.round(capacity * 70 / 100);
  private val reservedSeats: List[ReferencedSeat] = seats.filter(_.bookingReference.isDefined)
  val reservedSeatsCount: Int = reservedSeats.size
  val freeSeats = seats.diff(reservedSeats).map(_.seat)
  val capacity: Int = seats.size;

  //TODO: Does this can be there or in a Domain Service or in an Application Service?
  def canAcceptThosePeople(numberOfPeople: Int): Boolean = reservedSeatsCount <= saleableNumberOfSeats && (reservedSeatsCount + numberOfPeople < capacity)
}
