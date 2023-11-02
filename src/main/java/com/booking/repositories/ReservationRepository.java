package com.booking.repositories;

import com.booking.models.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {
    List<Reservation> reservationList;

    private ReservationRepository(){
        initialReservationRepo();
    }

    private void initialReservationRepo(){
        reservationList = new ArrayList<>();
    }

    public List<Reservation> getAll(){
        return reservationList;
    }

    private static ReservationRepository INSTANCE;

    public static ReservationRepository getInstance() {
        if (INSTANCE == null){
            INSTANCE = new ReservationRepository();
        }
        return  INSTANCE;
    }
}
