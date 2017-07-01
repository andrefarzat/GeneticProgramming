package com.andrefarzat.GP.logging;

import com.andrefarzat.GP.GP;
import com.andrefarzat.GP.Individual;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;


public class MongoLogger implements Logger {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection<Document> collection;

    public MongoLogger() {
        this.client = new MongoClient("localhost", 27017);
    }

    @Override
    public void logStart(GP gp) {
        this.db = this.client.getDatabase(gp.getId());
        this.collection = this.db.getCollection("individuals");

        this.startTime = LocalDateTime.now();
        String time = this.startTime.format(this.dateTimeFormat);

        Document execution = new Document("id", gp.getId())
            .append("startTime", time);

        this.db.getCollection("executions").insertOne(execution);

        gp.log("Start time: " + time);
    }

    @Override
    public void logEnd(GP gp) {
        this.endTime = LocalDateTime.now();
        String time = this.endTime.format(this.dateTimeFormat);

        System.out.println("Finish time: " + time);
        Duration interval = Duration.between(this.startTime, this.endTime);
        System.out.println(String.format("It took %s seconds", interval.getSeconds()));

        this.db.getCollection("executions").updateOne(
                eq("id", gp.getId()),
                combine(
                        set("endTime", time),
                        set("duration", interval.getSeconds()),
                        set("bestId", gp.getPopulation().get(0).getIndId())
                )
        );
    }

    @Override
    public void logPopulation(GP gp) {
        if (gp.getGenerationNumber() == 1) {
            for(Individual ind : gp.getPopulation().getAll()) {
                Document doc = new Document("id", ind.getIndId())
                    .append("fitness", ind.getFitness())
                    .append("tree", ind.getTree().toString())
                    .append("createdIn", 1);
                this.collection.insertOne(doc);
            }
        }
    }

    @Override
    public void logCrossover(GP gp, Individual father, Individual mother, Individual neo) {
        Document doc = new Document("id", neo.getIndId())
            .append("fitness", neo.getFitness())
            .append("tree", neo.getTree().toString())
            .append("createdIn", gp.getGenerationNumber())
            .append("operator", gp.crossoverOperator.getClass().getName())
            .append("fatherId", father.getIndId())
            .append("motherId", mother.getIndId());

        this.collection.insertOne(doc);
    }

    @Override
    public void logMutation(GP gp, Individual father, Individual neo) {
        Document doc = new Document("id", neo.getIndId())
                .append("fitness", neo.getFitness())
                .append("tree", neo.getTree().toString())
                .append("createdIn", gp.getGenerationNumber())
                .append("operator", gp.mutationOperator.getClass().getName())
                .append("fatherId", father.getId());

        this.collection.insertOne(doc);
    }
}
