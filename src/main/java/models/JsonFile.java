package models;

import java.util.List;

public class JsonFile {
    private List<Ticket> tickets;

    public JsonFile() {
    }

    public JsonFile(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
