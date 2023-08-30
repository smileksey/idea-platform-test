import models.Ticket;

import java.time.LocalTime;
import java.util.*;

public class Util {

    public static Set<String> getCarriersSet(List<Ticket> tickets) {
        Set<String> carriers = new HashSet<>();

        for(Ticket ticket : tickets) {
            carriers.add(ticket.getCarrier());
        }
        return carriers;
    }

    public static List<Ticket> getTicketsForSpecifiedRoute(List<Ticket> tickets, String originName, String destinationName) {

        List<Ticket> thisRouteTickets = new ArrayList<>();

        for(Ticket ticket : tickets) {
            if ((ticket.getOriginName().equals(originName) && ticket.getDestinationName().equals(destinationName))
                    || (ticket.getOriginName().equals(destinationName) && ticket.getDestinationName().equals(originName))) {

                thisRouteTickets.add(ticket);
            }
        }
        return thisRouteTickets;
    }

    public static List<Ticket> getTicketsForSpecifiedCarrier(List<Ticket> tickets, String carrier) {
        return tickets.stream().filter(ticket -> ticket.getCarrier().equals(carrier)).toList();
    }

    public static void printResult(List<Ticket> tickets, String originName, String destinationName) {

        Map<String, LocalTime> minDurationPerCarrier = DurationCalculator.getMinDurationForRoutePerCarrier(tickets, originName, destinationName);
        double averagePrice = PriceCalculator.getAveragePrice(tickets, originName, destinationName);
        double medianPrice = PriceCalculator.getMedianPrice(tickets, originName, destinationName);
        double difference = averagePrice - medianPrice;

        System.out.println("Минимальное время полета по направлению *" + originName + " - " + destinationName + "* для авиаперевозчиков:");

        for (Map.Entry<String, LocalTime> entry : minDurationPerCarrier.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getHour() + " ч " + entry.getValue().getMinute() + " мин");
        }

        System.out.println("***");

        System.out.println("Средняя цена для данного направления: " + averagePrice);
        System.out.println("Медианная цена для данного направления: " + medianPrice);

        if (difference > 0) {
            System.out.println("Средняя цена больше медианной на " + difference);
        } else if (difference < 0) {
            System.out.println("Медианная цена больше средней на " + Math.abs(difference));
        } else {
            System.out.println("Средняя и медианная цены равны");
        }
    }
}
