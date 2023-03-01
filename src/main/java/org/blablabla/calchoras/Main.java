package org.blablabla.calchoras;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String... args){
        String[] periodos = {"07:11","10:34","11:57","12:24","13:24", "16:00"};

        List<Date> horas = new ArrayList<>();
        for(String periodo: periodos){
            SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
            try {
                horas.add(hourFormat.parse(periodo));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        horas.sort(Comparator.comparingLong(Date::getTime));
        Date lastHour = null;
        long totalPeriod = 0l;
        for(Date hora: horas){
            System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(hora));
            if(lastHour!=null){
                long passedTime = (hora.getTime() - lastHour.getTime());
                totalPeriod = totalPeriod+passedTime;
                lastHour = null;
                System.out.println("\t+ "+toHourString(passedTime));
                continue;
            }
            lastHour = hora;
        }
        System.out.println("Total: "+toHourString(totalPeriod));


    }

    public static String toHourString(Long time){
        double segundos = (double) time / 1000d;
        double segundosRestantes = segundos % 60d;
        double minutos = (segundos-segundosRestantes) / 60d;
        double horasRestantes = (minutos-segundosRestantes) % 60d;
        double horas = (minutos-horasRestantes) / 60d;
        return (int)horas+"h "+(int)horasRestantes+"m";
    }
}