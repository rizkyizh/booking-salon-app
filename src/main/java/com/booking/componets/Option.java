package com.booking.componets;

import com.booking.helper.Utils;

public class Option {

        private final String titleOption;

        public Option(String titleOption) {
            this.titleOption = titleOption;
        }

        public static void printOption(String title) {
            System.out.print("\n" + Utils.toTitleCase(title));
        }

        public String getTitleOption() {
            return titleOption;
        }
}
