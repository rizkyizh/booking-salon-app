package com.booking.componets;

public class Header {
    public static void printHeader(String title) {
            System.out.println("================================================================================");
            System.out.println(title.toUpperCase());
            System.out.println("================================================================================");
    }

    public static void printHeaderTable(String title, int longSize){
        System.out.printf("%"+ (longSize + title.length()) /2+"s\n", title.toUpperCase());
    }
}
