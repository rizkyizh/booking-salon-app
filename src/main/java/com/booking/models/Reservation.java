package com.booking.models;

import java.util.List;

import com.booking.helper.WorkStageEnum;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private String reservationId;
    private Customer customer;
    private Employee employee;
    private List<Service> services;

    @Setter(AccessLevel.NONE)
    private double reservationPrice;

    private WorkStageEnum workstage;

    public Reservation(String reservationId, Customer customer, Employee employee, List<Service> services) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.employee = employee;
        this.services = services;
        this.reservationPrice = calculateReservationPrice();
        this.workstage = WorkStageEnum.PROCESS;
    }

    private double calculateReservationPrice(){
       double discountPercentage = customer.getMember().getMembershipNameEnum().getDISCOUNT();
       double totalBeforeDiscount = services.stream().mapToDouble(Service::getPrice).sum();
       return  totalBeforeDiscount - (totalBeforeDiscount * discountPercentage);
    }

    public Reservation copy(){
     return new Reservation(
             this.getReservationId(),
             this.getCustomer(),
             this.getEmployee(),
             this.getServices()
     );
    }
}
