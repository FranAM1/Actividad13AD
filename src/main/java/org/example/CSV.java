package org.example;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class CSV {

    public static void bookingsToCSV(List<Booking> bookings, String ruta) {
        try {
            File file = new File(ruta);
            FileWriter writer = new FileWriter(file);

            for (Booking booking : bookings) {
                writer.write(booking.getLocation_number() + "," +
                        booking.getPrice() + "," +
                        booking.getRoom_nights() + "," +
                        booking.getRoom() + "," +
                        booking.getAgency() + "," +
                        booking.getClient() + "," +
                        booking.getHotel() + "," +
                        booking.getCheck_in() + "\n");
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Error al generar el archivo CSV: " + e);
        }
    }
}
