package com.example.ccdbookingapp;
// Christos Giapitzakis 3200034
// Dimitris Louridas 3200281
//Christos Katsaros 3210070

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

public class Accommodation implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    public class DateSpan implements Serializable {
        private static final long serialVersionUID = 6529685098267757691L;
        public Date start;
        public Date end;
    }
    private transient ArrayList<DateSpan> availableDates;
    private transient ArrayList<DateSpan> reservedDates;

    private String roomName;
    private int noOfPersons;
    private String area;
    private double stars;
    private int noOfReviews;
    private String roomImage;

    public Accommodation() {
        this.availableDates = new ArrayList<DateSpan>();
        this.reservedDates = new ArrayList<DateSpan>();
    }

    public Accommodation(String roomName, int noOfPersons, String area, double stars, int noOfReviews, String roomImage) {
        this.roomName = roomName;
        this.noOfPersons = noOfPersons;
        this.area = area;
        this.stars = stars;
        this.noOfReviews = noOfReviews;
        this.roomImage = roomImage;

        this.availableDates = new ArrayList<DateSpan>();
    }

    public void setRoomName(String roomName){
        this.roomName=roomName;
    }

    public void setNoOfPersons(int noOfPersons){
        this.noOfPersons=noOfPersons;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public int getnoOfReviews(){
        return noOfReviews;
    }

    public void setNoOfReviews(int noOfReviews) {
        this.noOfReviews = noOfReviews;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public double getStars(){
        return  stars;
    }

    public  String getArea(){
        return  area;
    }
    public String getRoomImage(){
        return roomImage;
    }
    public int getNoOfPersons() {
        return noOfPersons;
    }

    public String getRoomName() {
        return roomName;
    }

    public ArrayList<DateSpan> getAvailableDates() {
        return availableDates;
    }

    public ArrayList<DateSpan> getReservedDates() {
        return reservedDates;
    }

    public String toString(){
        return "Accommodation{" +
                "roomName='" + roomName + '\'' +
                ", noOfPersons=" + noOfPersons +
                ", area='" + area + '\'' +
                ", stars=" + stars +
                ", noOfReviews=" + noOfReviews +
                ", roomImage='" + roomImage + '\'' +
                '}';
    }

    public void addAvailableDates(Date start, Date end) {
        for (DateSpan avDate : availableDates) {
            if (avDate.start.compareTo(start) <= 0 && avDate.end.compareTo(start) >= 0) {
                if (avDate.end.compareTo(end) < 0) {
                    avDate.end = end;
                }
                return;
            } else if (avDate.start.compareTo(end) <= 0 && avDate.end.compareTo(end) >= 0) {
                if (avDate.start.compareTo(start) > 0) {
                    avDate.start = start;
                }
                return;
            }
        }

        DateSpan span = new DateSpan();
        span.start = start;
        span.end = end;
        availableDates.add(span);
    }

    public boolean isAvailable(Date start, Date end) {
        for (DateSpan avDate : availableDates) {
            if (avDate.start.compareTo(start) <= 0 && avDate.end.compareTo(end) >= 0) {
                return true;
            }
        }
        return false;
    }

    public void printAvailableDates() {
        System.out.println("Availability for " + roomName + ": ");
        for (DateSpan avDate : availableDates) {
            System.out.println("From: " + avDate.start + " To: " + avDate.end);
        }
    }

    public boolean addReservation(Date start, Date end) {
        for (DateSpan avDate : availableDates) {
            if (avDate.start.compareTo(start) <= 0 && avDate.end.compareTo(end) >= 0) {
                //remove dates from the available list
                if (avDate.start.before(start)) {
                    Date prevEnd = avDate.end;
                    avDate.end = start;

                    if (prevEnd.after(end)) {
                        DateSpan span = new DateSpan();
                        span.start = end;
                        span.end = prevEnd;
                        availableDates.add(span);
                    }
                } else {
                    if (avDate.end.equals(end)) {
                        availableDates.remove(avDate);
                    } else {
                        avDate.start = end;
                    }
                }

                //add date span to the reserved list
                DateSpan span = new DateSpan();
                span.start = start;
                span.end = end;
                reservedDates.add(span);

                return true;
            }
        }

        System.out.println("No availability found for the selected date range!");
        return false;

    }

    public void printReservedDates() {
        System.out.println("Reserved dates for " + roomName + ": " + reservedDates.size());
        for (DateSpan reDate : reservedDates) {
            System.out.println("From: " + reDate.start + " To: " + reDate.end);
        }
    }

}
