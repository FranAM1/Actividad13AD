package org.example;

public class Booking {
    private int location_number;
    private String client;
    private String agency;
    private double price;
    private String room;
    private String hotel;
    private String check_in;
    private int room_nights;

    @Override
    public String toString() {
        return "Booking {" + '\n' +
                "locationNumber = " + location_number + '\n' +
                "client = " + client + '\n' +
                "agency = " + agency + '\n' +
                "price = " + price + '\n' +
                "room = " + room + '\n' +
                "hotel = " + hotel + '\n' +
                "checkIn = " + check_in + '\n' +
                "roomNights = " + room_nights + '\n' +
                '}';
    }

    public int getLocation_number() {
        return location_number;
    }

    public void setLocation_number(int location_number) {
        this.location_number = location_number;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public int getRoom_nights() {
        return room_nights;
    }

    public void setRoom_nights(int room_nights) {
        this.room_nights = room_nights;
    }
}
