package com.booking.componets;

public class Menu {
    public static void createMenu(Option[] options, boolean exitOption, boolean backToOption) {

            for (int i = 0; i < options.length; i++) {
                if (i < options.length - 1) {
                    System.out.println(i + 1 + ". " + options[i].getTitleOption());
                } else {
                    System.out.print(i + 1 + ". " + options[i].getTitleOption());

                    if (exitOption && backToOption) {
                        System.out.print("\n-------------------------------------");
                        System.out.print("\n99. Kembali");
                        System.out.print("\n0. Keluar");
                        System.out.print("\n-------------------------------------");
                    } else if (!exitOption && backToOption) {
                        System.out.print("\n-------------------------------------");
                        System.out.print("\n99. Kembali");
                        System.out.print("\n-------------------------------------");
                    } else if (exitOption) {
                        System.out.print("\n-------------------------------------");
                        System.out.print("\n0. Keluar");
                        System.out.print("\n-------------------------------------");
                    }
                }


            }


        }
}
