package com.booking.helper;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Utils {
      public static String formatCurrency(double numerik) {
              DecimalFormat decimalFormat = new DecimalFormat("#,###,###,###.00");
              if (decimalFormat.format(numerik).equalsIgnoreCase(".00")) {
                  return "-";
              }
              return decimalFormat.format(numerik);
          }

          public static void clearScreen() {
              try {
                  if (System.getProperty("os.name").contains("Windows")) {
                      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                  } else {
                      System.out.println("\003\143");
                  }
              } catch (Exception ex) {
                  System.err.println("Tidak bisa clear Screen");
              }
          }

          public static boolean yesOrNo() {
              Scanner input = new Scanner(System.in);
              System.out.print("\napakah anda ingin melanjutkan [y/n]: ");
              String inputUser = input.next();
              while (!inputUser.equalsIgnoreCase("y") && !inputUser.equalsIgnoreCase("n")) {
                  System.out.println("Pilihan anda bukan [y/n]");
                  System.out.print("\napakah anda ingin melanjutkan [y/n]: ");
                  inputUser = input.next();
              }
              return inputUser.equalsIgnoreCase("y");
          }

    public static boolean yesOrNo(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print("\n"+ message+ " [y/n]: ");
        String inputUser = input.next();

        while (!inputUser.equalsIgnoreCase("y") && !inputUser.equalsIgnoreCase("n")) {
            System.out.println("Pilihan anda bukan [y/n]");
            System.out.print("\n"+ message+ " [y/n]: ");
            inputUser = input.next();
        }

        return inputUser.equalsIgnoreCase("y");
    }

          public static String toTitleCase(String title) {
              char firstChar = Character.toUpperCase(title.charAt(0));
              return title.toLowerCase().replace(title.charAt(0), firstChar);
          }

          private static int NUMBER_ID_TEMP;

    public static String generateUniqueIDReservation() {
        NUMBER_ID_TEMP++;
        return "Rsv-" + String.format("%02d", NUMBER_ID_TEMP);
    }
}
