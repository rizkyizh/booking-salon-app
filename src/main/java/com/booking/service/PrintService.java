package com.booking.service;

import java.util.List;
import java.util.stream.Collectors;

import com.booking.componets.Header;
import com.booking.helper.WorkStageEnum;
import com.booking.models.*;
import com.booking.helper.Utils;

public class PrintService {

    public static String printServices(List<Service> serviceList) {
        StringBuilder result = new StringBuilder();
        for (Service service : serviceList) {
            result.append(service.getServiceName()).append(", ");
        }
        return result.toString();
    }

    public static void showRecentReservation(ReservationService reservationService) {

        List<Reservation> reservationList = reservationService.getAllRecentReservation();

        String lineTableBorder = "+------------------------------------------------------------------------------------------------------------------------------+";

        Header.printHeaderTable("Daftar recent reservation", lineTableBorder.length());
        System.out.println(lineTableBorder);
        System.out.printf("| %-4s | %-7s | %-15s | %-40s | %-15s | %-15s | %-10s |\n", "No.", "ID", "Nama Customer", "Service", "Total Biaya", "Pegawai", "Workstage");
        System.out.println(lineTableBorder);
        int num = 1;
        for (Reservation reservation : reservationList) {
            System.out.printf("| %-4s | %-7s | %-15s | %-40s | %-15s | %-15s | %-10s |\n",
                    num, reservation.getReservationId(),
                    reservation.getCustomer().getName(),
                    printServices(reservation.getServices()),
                    reservation.getReservationPrice(),
                    reservation.getEmployee().getName(),
                    reservation.getWorkstage());
            num++;
        }
        System.out.println(lineTableBorder);
    }


    public static void showAllCustomer(List<Person> personList) {

        List<Customer> customerList = personList.stream()
                .filter(person -> person instanceof Customer)
                .map(person -> (Customer) person)
                .collect(Collectors.toList());

        String lineTableBorder = "+------------------------------------------------------------------------------------+";

        Header.printHeaderTable("Daftar Customer", lineTableBorder.length());
        System.out.println(lineTableBorder);
        System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-15s | %-15s |\n", "No.", "ID", "Nama", "Alamat", "Membership", "Uang");
        System.out.println(lineTableBorder);
        int num = 1;
        for (Customer customer : customerList) {
            System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s |\n",
                    num,
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getMember().getMembershipNameEnum(),
                    Utils.formatCurrency(customer.getWallet()));
            num++;
        }
        System.out.println(lineTableBorder);



    }

    public static void showAllEmployee(List<Person> personList) {

        List<Employee> employeeList = personList
                .stream()
                .filter(person -> person instanceof Employee)
                .map(person -> (Employee) person)
                .collect(Collectors.toList());

        String lineTableBorder = "+-----------------------------------------------------------------+";

        Header.printHeaderTable("daftar employee", lineTableBorder.length());

        System.out.println(lineTableBorder);
        System.out.printf("| %-4s | %-6s | %-11s | %-15s | %-15s |\n", "No.", "ID", "Nama", "Alamat", "Pengalaman");
        System.out.println(lineTableBorder);
        int num = 1;
        for (Employee employee : employeeList) {
            System.out.printf("| %-4s | %-6s | %-11s | %-15s | %-15s |\n",
                    num,
                    employee.getId(),
                    employee.getName(),
                    employee.getAddress(),
                    employee.getExperience());
            num++;
        }
        System.out.println(lineTableBorder);

    }


    public static void showHistoryReservation(ReservationService reservationService) {

        List<Reservation> reservationList = reservationService.getAllHistoryReservation();
        String lineTableBorder = "+------------------------------------------------------------------------------------------------------------------------------+";

        Header.printHeaderTable("Daftar History reservation", lineTableBorder.length());

        System.out.println(lineTableBorder);
        System.out.printf("| %-4s | %-7s | %-15s | %-40s | %-15s | %-15s | %-10s |\n", "No.", "ID", "Nama Customer", "Service", "Total Biaya", "Pegawai", "Workstage");
        System.out.println(lineTableBorder);
        int num = 1;
        for (Reservation reservation : reservationList) {
            System.out.printf("| %-4s | %-7s | %-15s | %-40s | %-15s | %-15s | %-10s |\n",
                    num, reservation.getReservationId(),
                    reservation.getCustomer().getName(),
                    printServices(reservation.getServices()),
                    reservation.getReservationPrice(),
                    reservation.getEmployee().getName(),
                    reservation.getWorkstage());
            num++;
        }
        System.out.println(lineTableBorder);
        System.out.printf("| %-75s | %-46s |\n", "Total Keuntungan", reservationList.stream().mapToDouble(Reservation::getReservationPrice).sum());
        System.out.println(lineTableBorder);


    }

    public static void showAllService(ReservationService reservationService) {

        List<Service> servicesList = reservationService.getAllService();

        System.out.println("+---------------------------------------------------------+");
        System.out.printf("| %-4s | %-7s | %-20s | %-15s |\n", "No.", "ID", "Nama", "Harga");
        System.out.println("+---------------------------------------------------------+");
        int num = 1;
        for (Service service : servicesList) {
            System.out.printf("| %-4s | %-7s | %-20s | %-15s |\n",
                    num,
                    service.getServiceId(),
                    service.getServiceName(),
                    service.getPrice());
            num++;
        }
        System.out.println("+---------------------------------------------------------+");

    }

    public static void createNewReservasi(ReservationService reservationService) {

        Header.printHeader("Membuat Reservasi");


        String reservationId;
        Customer customer;
        Employee employee;
        List<Service> serviceList;

        reservationId = Utils.generateUniqueIDReservation();
        customer = ValidationService.inputAndValidationCustomerId(reservationService);
        employee = ValidationService.inputAndValidationEmployeeId(reservationService);
        serviceList = ValidationService.inputAndValidationServiceSelected(reservationService);

        Reservation newReservation = new Reservation(
                reservationId,
                customer.copy(),
                employee.copy(),
                serviceList
        );

        Reservation reservation = reservationService.createReservation(newReservation);

        if (reservation != null) {
            System.out.println("Booking Berhasil");
            System.out.print("Total Biaya Booking: ");
            System.out.println(reservation.getReservationPrice());
        }


    }

    public static void finishCancelReservation(ReservationService reservationService) {


        Header.printHeader("Finish/cancel status reservation");

        Reservation reservationEditStatus = ValidationService.inputAndvalidationReservation(reservationService);

        if (reservationEditStatus == null){
            return;
        }

        WorkStageEnum getStatusEdit = ValidationService.inputAndValidationWorkStage();

        if (getStatusEdit != null) {
            Reservation reservationStatusEdited = reservationService.updateStatusWorkStage(reservationEditStatus, getStatusEdit);
            if (reservationStatusEdited != null) {
                System.out.println("Reservasi dengan id " + reservationStatusEdited.getReservationId() + " sudah " + reservationStatusEdited.getWorkstage());
            } else {
                System.err.println("Opps terjadi kesalahan");
            }

        }

    }
}
