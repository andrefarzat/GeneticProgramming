package com.andrefarzat.GP.logging;


import com.andrefarzat.GP.GP;
import com.andrefarzat.GP.Individual;

import java.io.File;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CSVLogger implements Logger {
    private PrintWriter writer;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public CSVLogger() {
        File file = new File("csv");
        if (!file.exists()) {
            file.mkdir();
        }

        if (!file.isDirectory()) {
            System.out.println("csv must be a folder");
            System.exit(1);
        }
    }

    private void insertLine(String line) {
        this.writer.println(line);
    }

    private void insertLine(String[] line) {
        this.insertLine(String.join(",", line));
    }

    @Override
    public void logStart(GP gp) {
        String filename = String.format("csv/%s.csv", gp.getId());

        try {
            this.writer = new PrintWriter(filename, "UTF-8");
        } catch(Exception e) {
            System.out.println("Error opening PrintWriter");
            System.exit(1);
        }

        this.startTime = LocalDateTime.now();
        String time = this.startTime.format(this.dateTimeFormat);

        gp.log("Start time: " + time);
    }

    @Override
    public void logEnd(GP gp) {
        this.endTime = LocalDateTime.now();
        String time = this.endTime.format(this.dateTimeFormat);

        System.out.println("Finish time: " + time);
        Duration interval = Duration.between(this.startTime, this.endTime);
        System.out.println(String.format("It took %s seconds", interval.getSeconds()));
    }

    @Override
    public void logPopulation(GP gp) {
        if (gp.getGenerationNumber() == 1) {
            for(Individual individual : gp.getPopulation().getAll()) {
                // id, createdIn, generation, fitness
                String[] line = new String[] {
                    Integer.toString(individual.getIndId()),
                    LocalDateTime.now().format(this.dateTimeFormat),
                    "1",
                    Double.toString(individual.getFitness()),
                };

                this.insertLine(line);
            };
        }
    }

    @Override
    public void logCrossover(GP gp, Individual father, Individual mother, Individual neo) {
        // id, createdIn, generation, fitness, operator, fatherId, motherId
        String[] line = new String[] {
                Integer.toString(neo.getIndId()),
                LocalDateTime.now().format(this.dateTimeFormat),
                Integer.toString(gp.getGenerationNumber()),
                Double.toString(neo.getFitness()),
                gp.crossoverOperator.getClass().getName(),
                Integer.toString(father.getIndId()),
                Integer.toString(mother.getIndId()),
        };

        this.insertLine(line);
    }

    @Override
    public void logMutation(GP gp, Individual father, Individual neo) {
        // id, createdIn, generation, fitness, operator, fatherId
        String[] line = new String[] {
                Integer.toString(neo.getIndId()),
                LocalDateTime.now().format(this.dateTimeFormat),
                Integer.toString(gp.getGenerationNumber()),
                Double.toString(neo.getFitness()),
                gp.mutationOperator.getClass().getName(),
                Integer.toString(father.getIndId()),
        };

        this.insertLine(line);
    }
}
