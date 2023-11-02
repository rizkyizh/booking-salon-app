package com.booking.service;

import java.util.List;
import java.util.Scanner;

import com.booking.componets.Header;
import com.booking.componets.Menu;
import com.booking.componets.Option;
import com.booking.models.Person;
import com.booking.repositories.PersonRepository;
import com.booking.helper.Utils;

public class MenuService {
    private static final List<Person> personList = PersonRepository.getInstance().getAll();
    private static final ReservationService reservationService = ReservationService.getInstance();

    public static void mainMenu() {
        Scanner inputTerminal = new Scanner(System.in);
        boolean isContinue = true;
        String inputUser;

        Option[] options = {
                new Option("Tampilkan Data"),
                new Option("Membuat Reservasi"),
                new Option("Finish/Cancal Reservasi"),
        };

        while (isContinue) {
            Utils.clearScreen();
            Header.printHeader("Selamat Datang di Aplikasi Pendataan Karyawan PT.Secret Semut 79");
            Menu.createMenu(options, true, false);
            Option.printOption("Masukkan Pilihan anda: ");
            inputUser = inputTerminal.next();

            switch (inputUser) {
                case "1":
                    MenuService.printSubMenuTampilkanData();
                    break;
                case "2":
                    PrintService.createNewReservasi(reservationService);
                    isContinue = Utils.yesOrNo();
                    break;
                case "3":
                    PrintService.finishCancelReservation(reservationService);
                    isContinue = Utils.yesOrNo();
                    break;
                case "0":
                    System.exit(0);
                    inputTerminal.close();
                    break;
                default:
                    Option.printOption("pilihan yang anda masukkan salah!!");
                    Option.printOption("Silahkan pilih [1/3]\n");
                    isContinue = Utils.yesOrNo();
            }

        }

    }

    private static void printSubMenuTampilkanData() {
        Scanner inputTerminal = new Scanner(System.in);
        boolean isContinue = true;
        String inputUser;

        Option[] options = {
                new Option("Tampilkan Recent Reservation"),
                new Option("Tampilkan Customer"),
                new Option("Tampilkan Employee"),
                new Option("Tampilkan History Reservation + Total Keuntungan"),
        };

        while (isContinue) {
            Utils.clearScreen();
            Header.printHeader("tampilkan data");
            Menu.createMenu(options, true, true);
            Option.printOption("Masukkan Pilihan anda: ");
            inputUser = inputTerminal.next();

            switch (inputUser) {
                case "1":
                    PrintService.showRecentReservation(reservationService);
                    isContinue = Utils.yesOrNo();
                    break;
                case "2":
                    PrintService.showAllCustomer(personList);
                    isContinue = Utils.yesOrNo();
                    break;
                case "3":
                    PrintService.showAllEmployee(personList);
                    isContinue = Utils.yesOrNo();
                    break;
                case "4":
                    PrintService.showHistoryReservation(reservationService);
                    isContinue = Utils.yesOrNo();
                    break;
                case "99":
                    return;
                case "0":
                    System.exit(0);
                    inputTerminal.close();
                    break;
                default:
                    Option.printOption("pilihan yang anda masukkan salah!!");
                    Option.printOption("Silahkan pilih [1/4]\n");
                    isContinue = Utils.yesOrNo();
            }

        }
    }


}

