package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        String param = args[0];
        switch (param) {
            case "-c":
                Date dateForC = new  SimpleDateFormat("dd/MM/yyyy").parse(args[3]);
                if(args[2].equals("м"))
                    allPeople.add(Person.createMale(args[1], dateForC));
                else
                    allPeople.add(Person.createFemale(args[1], dateForC));
                System.out.println(allPeople.size() - 1);
                break;
            case "-r":
                Person person = allPeople.get(Integer.parseInt(args[1]));
                String stringDate = convertDate(allPeople.get(Integer.parseInt(args[1])).getBirthDate());
                if(person.getSex() == Sex.MALE)
                    System.out.println(person.getName() + " м " + stringDate);
                else
                    System.out.println(person.getName() + " ж " + stringDate);
                break;
            case "-u":
                Person person1 = allPeople.get(Integer.parseInt(args[1]));
                person1.setName(args[2]);
                person1.setBirthDate(setNewDate(args[4]));
                if(args[3].equals("м"))
                    person1.setSex(Sex.MALE);
                else
                    person1.setSex(Sex.FEMALE);
                break;
            case "-d":
                allPeople.get(Integer.parseInt(args[1])).deliteAllDates();
                break;

        }

        //System.out.println(SimpleDateFormat.getDateInstance(SimpleDateFormat.DATE_FIELD, Locale.ENGLISH).format(allPeople.get(0).getBirthDate()));
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

    public static Date setNewDate(String stringDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd/MM/yyyy");
        Date newDate= format.parse(stringDate);
        return newDate;
    }
}
