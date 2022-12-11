package bg.softuni.l07_springdataautomappingobjects.converter;

import java.time.LocalDate;

public class Birthday {

    private LocalDate date;

    public Birthday() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
