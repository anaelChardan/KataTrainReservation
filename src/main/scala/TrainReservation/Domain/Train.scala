package TrainReservation.Domain

case class Train(val trainId: TrainId, val coaches: List[Coach]) {
  private val capacity = coaches.map(_.capacity).sum
  private val reservedSeatsCount = coaches.map(_.reservedSeatsCount).sum
  //TODO: Is this rule well located? Is it better in an application service?
  private val saleableNumberOfSeats = Math.round(capacity * 70 / 100)

  //TODO: Does this can be there or in a Domain Service or in an Application Service?
  //TODO: Does a method getAvailableCoach makes sense?
  def canAcceptThosePeople(numberOfPeople: Int): Boolean = numberOfPeople + reservedSeatsCount <= saleableNumberOfSeats
}
