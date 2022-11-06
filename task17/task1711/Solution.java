package com.javarush.task.task17.task1711;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        switch (args[0]){
            case "-c":
                synchronized (allPeople){
                    Date dateForC;
                    for (int i = 1; i <= args.length - 3; i = i + 3) {
                        dateForC = new  SimpleDateFormat("dd/MM/yyyy").parse(args[i + 2]);
                        if(args[i + 1].equals("м"))
                            allPeople.add(Person.createMale(args[i], dateForC));
                        else
                            allPeople.add(Person.createFemale(args[i], dateForC));
                        System.out.println(allPeople.size() - 1);
                    }
                }
                break;
            case "-u":
                synchronized (allPeople){
                    for (int i = 1; i <= args.length - 4; i = i + 4) {
                        Person person1 = allPeople.get(Integer.parseInt(args[i]));
                        person1.setName(args[i + 1]);
                        person1.setBirthDate(new  SimpleDateFormat("dd/MM/yyyy").parse(args[i + 3]));
                        if(args[i + 2].equals("м"))
                            person1.setSex(Sex.MALE);
                        else
                            person1.setSex(Sex.FEMALE);
                    }
                    System.out.println(allPeople.get(0));
                    System.out.println(allPeople.get(1));
                }
                break;
            case "-d":
                synchronized (allPeople){
                    for (int i = 1; i < args.length; i++) {
                        allPeople.get(Integer.parseInt(args[i])).deliteAllDates();
                    }
                }
                break;
            case "-i":
                synchronized (allPeople){
                    for (int i = 1; i < args.length; i++) {
                        Person person = allPeople.get(Integer.parseInt(args[i]));
                        String stringDate = convertDate(allPeople.get(Integer.parseInt(args[i])).getBirthDate());
                        if(person.getSex() == Sex.MALE)
                            System.out.println(person.getName() + " м " + stringDate);
                        else
                            System.out.println(person.getName() + " ж " + stringDate);
                    }
                }
        }
    }



    public static String convertDate(Date date){
        String newDate = "";
        String simpleDate = date.toString();
        String simpleMonth = null;
        if(date.getDate() / 10 == 0)
            simpleMonth = "0" + date.getDate() + "-" + simpleDate.split(" ")[1] + "-" + (date.getYear() + 1900);
        else
            simpleMonth = date.getDate() + "-" + simpleDate.split(" ")[1] + "-" + (date.getYear() + 1900);
        return simpleMonth;
    }


}
