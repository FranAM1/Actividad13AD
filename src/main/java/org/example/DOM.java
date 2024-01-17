package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class DOM {

    public static void bookingsToXML(List<Booking> bookings, String ruta){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();

            // Elemento raíz
            Element rootElement = doc.createElement("bookings");
            doc.appendChild(rootElement);

            // Agregar objetos a la lista
            for (Booking objeto : bookings) {
                Element booking = doc.createElement("booking");

                Element locationNumber = doc.createElement("location_number");
                locationNumber.appendChild(doc.createTextNode(String.valueOf(objeto.getLocation_number())));
                booking.appendChild(locationNumber);

                Element client = doc.createElement("client");
                client.appendChild(doc.createTextNode(objeto.getClient()));
                booking.appendChild(client);

                Element agency = doc.createElement("agency");
                agency.appendChild(doc.createTextNode(objeto.getAgency()));
                booking.appendChild(agency);

                Element price = doc.createElement("price");
                price.appendChild(doc.createTextNode(String.valueOf(objeto.getPrice())));
                booking.appendChild(price);

                Element room = doc.createElement("room");
                room.appendChild(doc.createTextNode(objeto.getRoom()));
                booking.appendChild(room);

                Element hotel = doc.createElement("hotel");
                hotel.appendChild(doc.createTextNode(objeto.getHotel()));
                booking.appendChild(hotel);

                Element checkIn = doc.createElement("check_in");
                checkIn.appendChild(doc.createTextNode(objeto.getCheck_in()));
                booking.appendChild(checkIn);

                Element roomNights = doc.createElement("room_nights");
                roomNights.appendChild(doc.createTextNode(String.valueOf(objeto.getRoom_nights())));
                booking.appendChild(roomNights);

                rootElement.appendChild(booking);
            }

            // Transformar el documento a un archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(ruta));

            transformer.transform(source, result);

            System.out.println("Archivo XML generado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
