import models.Ticket;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Duration;
import java.util.*;

public class DurationCalculator {

    public static LocalTime getMinFlightDuration(List<Ticket> tickets) {

        int minDuration = Integer.MAX_VALUE;

        for(Ticket ticket : tickets) {
            int flightDuration = getDurationInMinutes(ticket);

            if (flightDuration < minDuration)
                minDuration = flightDuration;
        }

        if (minDuration != Integer.MAX_VALUE) {
            return convertMinutesToLocalTime(minDuration);
        } else {
            return LocalTime.of(0, 0);
        }
    }

    public static int getDurationInMinutes(Ticket ticket) {

        LocalDateTime departureDateTime = LocalDateTime.of(ticket.getDepartureDate(), ticket.getDepartureTime());
        LocalDateTime arrivalDateTime = LocalDateTime.of(ticket.getArrivalDate(), ticket.getArrivalTime());
        int duration = (int) Duration.between(departureDateTime, arrivalDateTime).toMinutes();

        return duration;
    }

    public static LocalTime convertMinutesToLocalTime(int minutes) {
        int hoursPart = minutes / 60;
        int minutesPart = minutes % 60;

        return LocalTime.of(hoursPart, minutesPart);
    }


    public static Map<String, LocalTime> getMinDurationForRoutePerCarrier(List<Ticket> tickets, String originName, String destinationName) {

        List<Ticket> thisRouteTickets = Util.getTicketsForSpecifiedRoute(tickets, originName, destinationName);
        Set<String> carriers = Util.getCarriersSet(thisRouteTickets);

        Map<String, LocalTime> minDurationsPerCarrier = new HashMap<>();

        for (String carrier : carriers) {
            List<Ticket> thisCarrierTickets = Util.getTicketsForSpecifiedCarrier(thisRouteTickets, carrier);
            minDurationsPerCarrier.put(carrier, getMinFlightDuration(thisCarrierTickets));
        }

        return minDurationsPerCarrier;
    }

}
