package com.booking.service;


import com.booking.helper.WorkStageEnum;
import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ReservationRepository;
import com.booking.repositories.ServiceRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final PersonRepository personRepository;
    private final ServiceRepository serviceRepository;

    public ReservationService(ReservationRepository reservationRepository, PersonRepository personRepository, ServiceRepository serviceRepository) {
        this.reservationRepository = reservationRepository;
        this.personRepository = personRepository;
        this.serviceRepository = serviceRepository;
    }

    public Reservation createReservation(Reservation newReservation) {
        if (newReservation != null) {
         reservationRepository.getAll().add(newReservation);
         return reservationRepository.getAll().stream().filter(reservation -> reservation.getReservationId().equalsIgnoreCase(newReservation.getReservationId())).findFirst().orElse(null);
        } else {
            return null;
        }
    }


    public Customer getCustomerByCustomerId(String id) {
        return personRepository.getAll()
                .stream()
                .filter(person -> person instanceof Customer)
                .map(person -> (Customer) person)
                .filter(customer -> customer.getId().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }



    public Employee getEmployeeByEmployeeId(String id) {
        return personRepository.getAll()
                .stream()
                .filter(person -> person instanceof Employee)
                .map(person -> (Employee) person)
                .filter(employee -> employee.getId().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }

    public List<Service> getAllService() {
        return serviceRepository.getAll();
    }


    public Service getServiceByServiceId(String inputServiceId) {
        return serviceRepository.getAll()
                .stream()
                .filter(service -> service.getServiceId().equalsIgnoreCase(inputServiceId))
                .findFirst()
                .orElse(null);
    }

    public List<Reservation> getAllRecentReservation() {
        return  reservationRepository.getAll()
                .stream()
                .filter(reservation -> reservation.getWorkstage() == WorkStageEnum.PROCESS)
                .collect(Collectors.toList());
    }

    public Reservation getReservationByReservationId(String reservationId) {
        return  reservationRepository.getAll()
                .stream()
                .filter(reservation -> reservation.getReservationId().equalsIgnoreCase(reservationId))
                .findFirst().orElse(null);
    }

    public Reservation updateStatusWorkStage(Reservation reservationEditStatus, WorkStageEnum getStatusEdit) {
        reservationRepository.getAll().remove(reservationEditStatus);
        Reservation newUpdatedReservationEditStatus = reservationEditStatus.copy();
        newUpdatedReservationEditStatus.setWorkstage(getStatusEdit);
        reservationRepository.getAll().add(newUpdatedReservationEditStatus);
        Customer customer = getCustomerByCustomerId(newUpdatedReservationEditStatus.getCustomer().getId());
        customer.setWallet(customer.getWallet() - newUpdatedReservationEditStatus.getReservationPrice());
        return getReservationByReservationId(newUpdatedReservationEditStatus.getReservationId());
    }

    public List<Reservation> getAllHistoryReservation() {
       return reservationRepository.getAll()
                .stream()
                .filter(reservation -> reservation.getWorkstage() != WorkStageEnum.PROCESS)
                .collect(Collectors.toList());
    }
    private static ReservationService INSTANCE;

    public static ReservationService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReservationService(
                    ReservationRepository.getInstance(),
                    PersonRepository.getInstance(),
                    ServiceRepository.getInstance()
            );
        }
        return INSTANCE;
    }
}
