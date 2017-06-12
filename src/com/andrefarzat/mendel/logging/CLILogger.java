package com.andrefarzat.mendel.logging;


import com.andrefarzat.mendel.Population;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CLILogger implements MendelLogger {
    private LocalDateTime initialTime;
    private LocalDateTime finishTime;
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");



    @Override
    public void logInitialTime() {
        this.initialTime = LocalDateTime.now();
        String time = this.initialTime.format(this.dateTimeFormat);

        System.out.println("Initial time: " + time);
    }

    @Override
    public void logFinishTime() {
        this.finishTime = LocalDateTime.now();
        String time = this.finishTime.format(this.dateTimeFormat);

        System.out.println("Finish time: " + time);
        Duration interval = Duration.between(this.initialTime, this.finishTime);
        System.out.println(String.format("It took %s seconds", interval.getSeconds()));
    }

    @Override
    public void logPopulation(String label, Population population) {

    }
}
