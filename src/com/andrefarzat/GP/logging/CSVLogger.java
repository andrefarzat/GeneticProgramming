package com.andrefarzat.GP.logging;


import com.andrefarzat.GP.GP;
import com.andrefarzat.GP.Individual;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CSVLogger implements Logger {
    private PrintWriter writer;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

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

    private PrintWriter getPrintWriter() {
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter("csv/executions.csv", true));
            PrintWriter writer = new PrintWriter(file, true);
            return writer;
        } catch(Exception e) {
            System.out.println("Error opening PrintWriter for executions");
        }

        System.exit(1);
        return null;
    }

    private void insertLine(String line) {
        this.writer.println(line);
    }

    private void insertLine(String[] line) {
        this.insertLine(String.join(",", line));
    }

    @Override
    public void logStart(GP gp) {
        this.startTime = LocalDateTime.now();
        String time = this.startTime.format(this.dateTimeFormat);

        PrintWriter writer = this.getPrintWriter();
        writer.append(gp.getId() + "," + time + ",");
        writer.close();

        gp.log("Start time: " + time);

        try {
            String filename = String.format("csv/%s.csv", gp.getId());
            this.writer = new PrintWriter(filename, "UTF-8");
        } catch(Exception e) {
            System.out.println("Error opening PrintWriter for individuals");
            System.exit(1);
        }
    }

    @Override
    public void logEnd(GP gp) {
        this.endTime = LocalDateTime.now();
        String time = this.endTime.format(this.dateTimeFormat);

        PrintWriter writer = this.getPrintWriter();
        writer.append(time + "," + gp.getPopulation().get(0).getIndId() + "\n");
        writer.close();

        System.out.println("Finish time: " + time);
        Duration interval = Duration.between(this.startTime, this.endTime);
        System.out.println(String.format("It took %s seconds", interval.getSeconds()));
    }

    @Override
    public void logPopulation(GP gp) {
        if (gp.getGenerationNumber() == 1) {
            for(Individual individual : gp.getPopulation().getAll()) {
                // id, generation, fitness, value
                String[] line = new String[] {
                    Integer.toString(individual.getIndId()),
                    "1",
                    Double.toString(individual.getFitness()),
                    individual.getTree().toString(),
                };

                this.insertLine(line);
            };
        }
    }

    @Override
    public void logCrossover(GP gp, Individual father, Individual mother, Individual neo) {
        // id, generation, fitness, value, operator, fatherId, motherId
        String[] line = new String[] {
                Integer.toString(neo.getIndId()),
                Integer.toString(gp.getGenerationNumber()),
                Double.toString(neo.getFitness()),
                neo.getTree().toString(),
                gp.crossoverOperator.getClass().getName(),
                Integer.toString(father.getIndId()),
                Integer.toString(mother.getIndId()),
        };

        this.insertLine(line);
    }

    @Override
    public void logMutation(GP gp, Individual father, Individual neo) {
        // id, generation, fitness, operator, fatherId
        String[] line = new String[] {
                Integer.toString(neo.getIndId()),
                Integer.toString(gp.getGenerationNumber()),
                Double.toString(neo.getFitness()),
                neo.getTree().toString(),
                gp.mutationOperator.getClass().getName(),
                Integer.toString(father.getIndId()),
        };

        this.insertLine(line);
    }
}
