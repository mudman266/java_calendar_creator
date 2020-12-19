// Exercise 13.4
// Display Calendars
// Josh Williams

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.lang.Math;

public class EX13_4 {

    static boolean debugging = false;

    // Create GregorianCalendar object
    static Calendar calendar;

    static String[] era = {"AD", "BC"};
    static String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
        "October", "November", "December"};

    /** Main Method **/
    public static void main(String[] args){

        if (args.length > 2) {
            System.out.print("Usage: EX13_4 [mm] [yyyy]");
            System.exit(0);
        }
        if (args.length > 0) {
            try {
                // Verify month
                if (Integer.parseInt(args[0]) < 1 || Integer.parseInt(args[0]) > 12) {
                    throw new IOException();
                }
                if (args.length == 2) {
                    // Verify year
                    Integer.parseInt(args[1]);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry. Values must be numerical.");
                if (debugging)
                    e.getStackTrace();
                System.exit(0);
            } catch (IOException e) {
                System.out.println("Invalid entry. Month must be 1-12.");
                if (debugging)
                    e.getStackTrace();
                System.exit(0);
            }
        }
        if (args.length == 2) {
            if (debugging) System.out.println("Arg length 2: " + args[0] + " and " + args[1]);
            calendar = new GregorianCalendar(Integer.parseInt(args[1]), (Integer.parseInt(args[0]) - 1), 1);
        }
        else if (args.length == 1) {
            if (debugging) System.out.println("Arg length 1: " + args[0]);
            Calendar forYear = new GregorianCalendar();
            calendar = new GregorianCalendar(forYear.get(Calendar.YEAR), (Integer.parseInt(args[0]) - 1), 1);
        } else {
            calendar = new GregorianCalendar();
        }

        // Print Calendar for the month of the year
        printMonth();
    }

    /** Print the month calendar */
    public static void printMonth() {
        // Print the headings of the calendar
        printMonthTitle();

        // Print the body of the calendar
        printMonthBody();
    }

    /** Print Month title */
    public static void printMonthTitle() {
        System.out.print("          " + months[calendar.get(Calendar.MONTH)] + " ");
        // Handle negative years (BC)
        if (calendar.get(Calendar.ERA) == GregorianCalendar.BC) {
            System.out.println(calendar.get(Calendar.YEAR) + "BC");
        } else {
            System.out.println(calendar.get(Calendar.YEAR));
        }
        System.out.println("---------------------------------");
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
    }

    /** Print month body */
    public static void printMonthBody() {
        // Pad space before the first day of the month
        int i;
        for (i = 1; i < calendar.get(Calendar.DAY_OF_WEEK); i++)
            System.out.print("    ");
        // Print each date
        for (i = 1; i <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            System.out.printf("%4d", i);
        // New line if we have 7 days already
            if ((i + (calendar.get(Calendar.DAY_OF_WEEK) - 1)) % 7 == 0)
                System.out.println();
        }
    }
}

