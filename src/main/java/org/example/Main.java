package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        BBDDSesion sesion=new BBDDSesion();
        sesion.initSession();
        sesion.setRequiereCommit();
        String opcion = "";
        boolean salir = false;

        while(!salir){
            do{
                options();
                opcion = main.sc.nextLine();
                System.out.println();
            }while(!isNumber(opcion));

            switch (opcion) {
                case "1":
                    System.out.println("Introduce la ruta del fichero XML");
                    String ruta = main.sc.nextLine();

                    try{
                        sesion.getSession().insert("org.example.BookingMapper.loadXML", ruta);
                        System.out.println("Fichero XML cargado correctamente");
                        sesion.executeCommit();
                    }catch (Exception e){
                        System.out.println("Error al cargar el fichero XML: "+e);
                    }

                    sesion.executeCommit();
                    break;
                case "2":

                    break;
                case "3":
                    try{
                        sesion.getSession().delete("org.example.BookingMapper.deleteData");
                        System.out.println("Datos eliminados correctamente");
                        sesion.executeCommit();
                    }catch (Exception e){
                        System.out.println("Error al eliminar los datos: "+e);
                    }
                    break;
                case "4":
                    Booking booking = new Booking();

                    System.out.println("Introduce el nombre del cliente");
                    booking.setClient(main.sc.nextLine());

                    System.out.println("Introduce el nombre de la agencia");
                    booking.setAgency(main.sc.nextLine());

                    String precio = "";
                    do {
                        System.out.println("Introduce el precio");
                        precio = main.sc.nextLine();
                    }while (!checkPrice(precio));
                    booking.setPrice(Double.parseDouble(precio));

                    System.out.println("Introduce el tipo de habitación");
                    booking.setRoom(main.sc.nextLine());

                    System.out.println("Introduce el nombre del hotel");
                    booking.setHotel(main.sc.nextLine());

                    String checkIn = "";
                    do {
                        System.out.println("Introduce la fecha de entrada");
                        checkIn = main.sc.nextLine();
                    }while (!checkDate(checkIn));
                    booking.setCheck_in(checkIn);

                    String roomNights = "";
                    do {
                        System.out.println("Introduce el número de noches");
                        roomNights = main.sc.nextLine();
                    }while (!isNumber(roomNights));
                    booking.setRoom_nights(Integer.parseInt(roomNights));

                    try{
                        sesion.getSession().insert("org.example.BookingMapper.insertBooking", booking);
                        System.out.println("Reserva añadida correctamente");
                        sesion.executeCommit();
                    }catch (Exception e){
                        System.out.println("Error al añadir la reserva: "+e);
                    }
                    break;
                case "5":
                    String localizador = "";
                    do {
                        System.out.println("Introduce el número de localizador de la reserva que quieres eliminar");
                        localizador = main.sc.nextLine();
                    }while (!isNumber(localizador));

                    try{
                        sesion.getSession().delete("org.example.BookingMapper.deleteBooking", localizador);
                        System.out.println("Reserva eliminada correctamente");
                        sesion.executeCommit();
                    }catch (Exception e){
                        System.out.println("Error al eliminar la reserva: "+e);
                    }

                    break;
                case "6":
                    String localizadorModificar = "";
                    do {
                        System.out.println("Introduce el número de localizador de la reserva que quieres modificar");
                        localizadorModificar = main.sc.nextLine();
                    }while (!isNumber(localizadorModificar));

                    Booking bookingInfo = sesion.getSession().selectOne("org.example.BookingMapper.selectBooking", localizadorModificar);
                    System.out.println(bookingInfo);

                    Booking newBooking = new Booking();
                    newBooking.setLocation_number(Integer.parseInt(localizadorModificar));

                    System.out.println("Introduce el nombre del cliente");
                    newBooking.setClient(main.sc.nextLine());

                    System.out.println("Introduce el nombre de la agencia");
                    newBooking.setAgency(main.sc.nextLine());

                    String precio2 = "";
                    do {
                        System.out.println("Introduce el precio");
                        precio2 = main.sc.nextLine();
                    }while (!checkPrice(precio2));
                    newBooking.setPrice(Double.parseDouble(precio2));

                    System.out.println("Introduce el tipo de habitación");
                    newBooking.setRoom(main.sc.nextLine());

                    System.out.println("Introduce el nombre del hotel");
                    newBooking.setHotel(main.sc.nextLine());

                    String checkIn2 = "";
                    do {
                        System.out.println("Introduce la fecha de entrada");
                        checkIn2 = main.sc.nextLine();
                    }while (!checkDate(checkIn2));
                    newBooking.setCheck_in(checkIn2);

                    String roomNights2 = "";
                    do {
                        System.out.println("Introduce el número de noches");
                        roomNights2 = main.sc.nextLine();
                    }while (!isNumber(roomNights2));
                    newBooking.setRoom_nights(Integer.parseInt(roomNights2));

                    try{
                        sesion.getSession().update("org.example.BookingMapper.updateBooking", newBooking);
                        System.out.println("Reserva modificada correctamente");
                        sesion.executeCommit();
                    }catch (Exception e){
                        System.out.println("Error al modificar la reserva: "+e);
                    }
                    break;
                case "7":
                    salir = true;
                    break;
            }
        }

        sesion.closeSession();
    }

    public static boolean isNumber(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            System.out.println("Introduce un número valido");
            return false;
        }
    }

    public static boolean checkPrice(String price){
        try{
            Double.parseDouble(price);
            return true;
        }catch (NumberFormatException nfe){
            System.out.println("Introduce un número decimal valido");
            return false;
        }
    }

    public static boolean checkDate(String date){
        return date.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})");
    }

    public static void options(){
        System.out.println("1 - Cargar base de datos desde un fichero XML");
        System.out.println("2 - Descargar la base de datos en un fichero XML");
        System.out.println("3 - Eliminar todos los datos de la base de datos");
        System.out.println("4 - Añadir reserva");
        System.out.println("5 - Eliminar una reserva");
        System.out.println("6 - Modificar una reserva");
        System.out.println("7 - Salir");
    }
}