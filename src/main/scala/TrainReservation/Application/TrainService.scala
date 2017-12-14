package TrainReservation.Application

import TrainReservation.Domain._

//PROVIDED IN PROJECT
trait TrainService {
  def trainData(trainId: TrainId): Train
  def reserveASeat(trainId: TrainId, bookingReference: BookingReference, seats: List[Seat])
  def reset(train: TrainId)
}
