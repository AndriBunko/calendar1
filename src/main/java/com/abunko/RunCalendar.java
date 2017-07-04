package com.abunko;


public class RunCalendar {
    public static void main(String[] args) {
        CalendarView calendarView= new ConsoleCalendarView();
        calendarView.showCalendar();
        System.out.println();

        for (int i = 1; i <= 12; i++){
            new ConsoleCalendarView(i).showCalendar();
            System.out.println();
        }
    }
}
