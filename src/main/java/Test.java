import com.fasterxml.jackson.databind.ObjectMapper;
import models.JsonFile;
import models.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        List<Ticket> tickets;

        try {
            JsonFile jsonFile = mapper.readValue(new File("tickets.json"), JsonFile.class);
            tickets = jsonFile.getTickets();

            Util.printResult(tickets, "Владивосток", "Тель-Авив");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
