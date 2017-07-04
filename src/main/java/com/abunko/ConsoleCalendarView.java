package com.abunko;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Locale;

import static com.abunko.ConsoleColor.*;


/**
 * Created by Андрей on 03.07.2017.
 */
public class ConsoleCalendarView implements CalendarView {

    private final LocalDate nowDate;
    private final LocalDate date;
    private final Locale loc;
    private int numOfFirstDay;
    private int numDaysInMonth;

    ConsoleCalendarView(){
        nowDate = LocalDate.now();
        loc = Locale.getDefault();
        date = LocalDate.now();
        numDaysInMonth = date.lengthOfMonth();
        numOfFirstDay = DayOfWeek.from(date.with(TemporalAdjusters.firstDayOfMonth())).getValue();
    }

    ConsoleCalendarView(int month){
        nowDate = LocalDate.now();
        loc = Locale.getDefault();
        date = LocalDate.of(nowDate.getYear(), month, 1);
        numDaysInMonth = date.lengthOfMonth();
        numOfFirstDay = DayOfWeek.from(date.with(TemporalAdjusters.firstDayOfMonth())).getValue();
    }

    public void showCalendar(){
        printDayOfWeek();
        printNumberOfDaysDefault(numOfFirstDay, numDaysInMonth);
        System.out.println();
    }

    private void printDayOfWeek() {

        System.out.println(" Текущая дата " + YElOW + nowDate.format(DateTimeFormatter.ofPattern("yyyy.LL.dd ")) + RESET);
        System.out.println(" " +date.getMonth().getDisplayName(TextStyle.FULL, Locale.UK));

        Arrays.stream(DayOfWeek.values()).map(dayOfWeek -> dayOfWeek.equals(DayOfWeek.SUNDAY) ||
                dayOfWeek.equals(DayOfWeek.SATURDAY) ? " " + BLUE + dayOfWeek.getDisplayName(TextStyle.SHORT, loc) + RESET :
                " " + dayOfWeek.getDisplayName(TextStyle.SHORT, loc)).forEach(System.out::print);

        System.out.println();
    }

    private void printNumberOfDaysDefault(int numOfFirstDay, int numDaysInMonth) {

        int i;
        String s;
        String s1;

        for (i = 1; i < numOfFirstDay; i++) System.out.print("   ");

        for (int j = 1; j <= numDaysInMonth; j++) {
            if (j == numDaysInMonth && DayOfWeek.from(date.with(TemporalAdjusters.lastDayOfMonth())).equals(DayOfWeek.SATURDAY)){
                if (LocalDate.of(date.getYear(), date.getMonth(), j).equals(nowDate))
                    s = Integer.toString(j).length() < 2 ? YElOW + "  " + Integer.toString(j) + RESET : YElOW + " " + Integer.toString(j) + RESET;
                else
                    s = Integer.toString(j).length() < 2 ? BLUE + "  " + Integer.toString(j) + RESET : BLUE + " " + Integer.toString(j) + RESET;

                System.out.print(s);
            }
            else if (i == 7){
                if (LocalDate.of(date.getYear(), date.getMonth(), j).equals(nowDate)) {
                    s = Integer.toString(j).length() < 2 ? YElOW + "  " + Integer.toString(j) + RESET : YElOW + " " + Integer.toString(j) + RESET;
                }else s = Integer.toString(j).length() < 2 ? BLUE + "  " + Integer.toString(j) + RESET : BLUE + " " + Integer.toString(j) + RESET;

                System.out.println(s);
                i =0;
            }
            else if ((i + j) % 7 == 0) {
                if (LocalDate.of(date.getYear(), date.getMonth(), j).equals(nowDate)) {
                    s = Integer.toString(j).length() < 2 ? YElOW + "  " + Integer.toString(j) + RESET : YElOW + " " + Integer.toString(j) + RESET;
                    s1 = Integer.toString(++j).length() < 2 ? BLUE + "  " + Integer.toString(j) + RESET : BLUE + " " + Integer.toString(j) + RESET;
                } else if (LocalDate.of(date.getYear(), date.getMonth(), j + 1).equals(nowDate)) {
                    s = Integer.toString(j).length() < 2 ? BLUE + "  " + Integer.toString(j) + RESET : BLUE + " " + Integer.toString(j) + RESET;
                    s1 = Integer.toString(++j).length() < 2 ? YElOW + "  " + Integer.toString(j) + RESET : YElOW + " " + Integer.toString(j) + RESET;
                } else {
                    s = Integer.toString(j).length() < 2 ? BLUE + "  " + Integer.toString(j) + RESET : BLUE + " " + Integer.toString(j) + RESET;
                    s1 = Integer.toString(++j).length() < 2 ? BLUE + "  " + Integer.toString(j) + RESET : BLUE + " " + Integer.toString(j) + RESET;
                }

                System.out.print(s);
                System.out.println(s1);
            }
            else if (LocalDate.of(date.getYear(), date.getMonth(), j).equals(nowDate)) {
                System.out.print(Integer.toString(j).length() < 2 ? YElOW + "  " + Integer.toString(j) + RESET : YElOW + " " + Integer.toString(j) + RESET);
            }
            else
                System.out.printf("%3s", j);
        }
    }
}