package TrainReservation.Application

import TrainReservation.Domain.{BookingReference, Seats, Train, TrainId}

trait TrainService {
  def trainData(trainId: TrainId): Train
  def reserveASeat(trainId: TrainId, bookingReference: BookingReference, seats: Seats)
  def reset(train: TrainId)
}
