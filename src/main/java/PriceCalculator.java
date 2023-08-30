import models.Ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriceCalculator {

    public static double getAveragePrice(List<Ticket> tickets, String originName, String destinationName) {

        List<Ticket> thisRouteTickets = Util.getTicketsForSpecifiedRoute(tickets, originName, destinationName);
        int sum = thisRouteTickets.stream().mapToInt(ticket -> ticket.getPrice()).sum();

        return (double) sum / thisRouteTickets.size();
    }

    public static double getMedianPrice(List<Ticket> tickets, String originName, String destinationName) {

        List<Ticket> thisRouteTickets = Util.getTicketsForSpecifiedRoute(tickets, originName, destinationName);
        List<Integer> sortedPrices = thisRouteTickets.stream().map(ticket -> ticket.getPrice()).sorted().toList();

        if (sortedPrices.size() % 2 != 0) {
            return sortedPrices.get(sortedPrices.size() / 2);
        } else {
            double avg = (double) (sortedPrices.get((sortedPrices.size() / 2) - 1) + sortedPrices.get(sortedPrices.size() / 2)) / 2;
            return avg;
        }
    }

    public static void getDifferenceBetweenAverageAndMedianPrice(List<Ticket> tickets, String originName, String destinationName) {
        double average = getAveragePrice(tickets, originName, destinationName);
        double median = getMedianPrice(tickets, originName, destinationName);

        double difference = average - median;

        if (difference > 0) {
            System.out.println("Средняя цена больше медианной на " + difference);
        } else if (difference < 0) {
            System.out.println("Медианная цена больше средней на " + Math.abs(difference));
        } else {
            System.out.println("Средняя и медианная цены равны");
        }
    }
}
