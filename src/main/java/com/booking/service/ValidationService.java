package com.booking.service;

import com.booking.helper.Utils;
import com.booking.helper.WorkStageEnum;
import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;

import java.util.*;

public class ValidationService {

    public static Customer inputAndValidationCustomerId(ReservationService reservationService) {
        Scanner input = new Scanner(System.in);
        Customer customerExist;

        PrintService.showAllCustomer(PersonRepository.getInstance().getAll());

        do {
            System.out.println("Masukkan Id Member");
            String inputCostumerId = input.nextLine();

            customerExist = reservationService.getCustomerByCustomerId(inputCostumerId);

            if (customerExist == null) {
                System.err.println("Customer yang dicari tidak tersedia");
                System.out.println("Silahkan input ulang");
            }
        } while (customerExist == null);

        return customerExist;
    }

    public static Employee inputAndValidationEmployeeId(ReservationService reservationService) {
        Scanner input = new Scanner(System.in);
        Employee employeeIsExist;

        PrintService.showAllEmployee(PersonRepository.getInstance().getAll());

        do {
            System.out.println("Masukkan Id Employee");
            String inputEmployeeId = input.nextLine();

            employeeIsExist = reservationService.getEmployeeByEmployeeId(inputEmployeeId);

            if (employeeIsExist == null) {
                System.err.println("Employee yang dicari tidak tersedia");
                System.out.println("Silahkan input ulang");
            }

        } while (employeeIsExist == null);

        return employeeIsExist;
    }

    public static List<Service> inputAndValidationServiceSelected(ReservationService reservationService) {
        Scanner input = new Scanner(System.in);
        Set<Service> serviceSet = new HashSet<>();

        PrintService.showAllService(reservationService);

        boolean isContinue = true;

        while (isContinue) {

            Service serviceIsExist;

            do {
                System.out.println("Silahkan Masukkan Service Id: ");
                String inputServiceId = input.nextLine();
                serviceIsExist = reservationService.getServiceByServiceId(inputServiceId);

                if (serviceIsExist != null) {

                    String finalServiceIsExist = serviceIsExist.getServiceId();
                    Service isSelected = serviceSet.stream()
                            .filter(service -> service.getServiceId().equalsIgnoreCase(finalServiceIsExist))
                            .findFirst().orElse(null);

                    if (isSelected != null) {
                        System.err.println("Service Sudah dipilih");

                    } else {
                        serviceSet.add(serviceIsExist);
                        isContinue = Utils.yesOrNo("Ingin pilih service yang lain");
                    }

                } else {
                    System.err.println("Service yang dicari tidak tersedia");
                }

            } while (serviceIsExist == null);

            if (serviceSet.size() == reservationService.getAllService().size()) {
                break;
            }

        }

        System.out.println(serviceSet.size());
        return new ArrayList<>(serviceSet);

    }

    public static Reservation inputAndvalidationReservation(ReservationService reservationService) {
        Scanner input =  new Scanner(System.in);
        Reservation reservationSelectedIsExist;

        PrintService.showRecentReservation(reservationService);

        do {
            System.out.println("Masukkan Reservation id: (input 0 untuk kembali ke menu)");
            String reservationId = input.nextLine();

            if (reservationId.equalsIgnoreCase("0")){
                return null;
            }

            reservationSelectedIsExist = reservationService.getReservationByReservationId(reservationId);

            if (reservationSelectedIsExist == null){
                System.err.println("Reservation yang dicari tidak tersedia");
            }else {
                if (reservationSelectedIsExist.getWorkstage() == WorkStageEnum.FINISH){
                    System.err.println("Reservation yang dicari sudah selesai");
                    reservationSelectedIsExist = null;
                } else if (reservationSelectedIsExist.getWorkstage() == WorkStageEnum.CANCELED) {
                    System.err.println("Reservation yang dicari sudah di cancel");
                    reservationSelectedIsExist = null;
                }
            }

        }while (reservationSelectedIsExist == null);


        return reservationSelectedIsExist;

    }

    public static WorkStageEnum inputAndValidationWorkStage() {
        Scanner input = new Scanner(System.in);

        boolean isInputValid = true;
        String status;

        do {
            System.out.println("Selesaikan Reservasi [finish/canceled] (input 0 untuk kembali ke menu): ");
            status = input.nextLine();

            if (status.equalsIgnoreCase("finish") || status.equalsIgnoreCase("canceled")){
                isInputValid = false;
            } else if (status.equalsIgnoreCase("0")) {
                return null;
            } else {
                System.err.println("silahkan input [finish/canceled]: ");
            }

        } while (isInputValid);

        return WorkStageEnum.valueOf(status.toUpperCase());
    }
}
