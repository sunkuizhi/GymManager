package com.test.GymManager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.GymManager.entity.Reservation;

public interface IReservationService extends IService<Reservation> {
    boolean createReservation(Reservation reservation);
    boolean createReservation(Long memberId, Long courseId);
     boolean checkIn(Long reservationId);
}
