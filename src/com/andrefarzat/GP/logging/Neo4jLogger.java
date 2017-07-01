package com.andrefarzat.GP.logging;

import com.andrefarzat.GP.GP;
import com.andrefarzat.GP.Individual;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.neo4j.driver.v1.*;
import static org.neo4j.driver.v1.Values.parameters;


public class Neo4jLogger implements Logger {
    private LocalDateTime startTime;
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

    private Driver driver;
    private Session session;


    public Neo4jLogger() {
        this.driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "h4rdP4ss" ) );
        this.session = driver.session();
    }

    private void execute(String query, Object ...params) {
        this.session.run(query, parameters(params));
    }

    @Override
    public void logStart(GP gp) {
        this.startTime = LocalDateTime.now();
        String time = this.startTime.format(this.dateTimeFormat);

        String query = "CREATE (e:Execution {id: {id}, startTime: {startTime}})";
        this.execute(query, "id", gp.getId(), "startTime", time);

        gp.log("Start time: " + time);
    }

    @Override
    public void logEnd(GP gp) {
        LocalDateTime endTime = LocalDateTime.now();
        String time = endTime.format(this.dateTimeFormat);

        System.out.println("Finish time: " + time);
        Duration interval = Duration.between(this.startTime, endTime);
        System.out.println(String.format("It took %s seconds", interval.getSeconds()));

        String query = "MATCH (e:Execution {id: {id}}) SET e.endTime = {endTime}, e.duration = {duration} ";
        query += "MATCH (best:Individual {id: {bestId}) ";
        query += "CREATE (e)-[:BEST_FOUND]->(best)";

        Object[] params = new Object[] {
            "id", gp.getId(),
            "endTime", time,
            "duration", interval.getSeconds(),
            "bestId", gp.getPopulation().get(0).getId()
        };

        this.execute(query, params);
    }

    @Override
    public void logPopulation(GP gp) {
        if (gp.getGenerationNumber() == 1) {
            String query = "MATCH (e:Execution {id: {executionId}}) "
                + "CREATE (neo:Individual {id: {id}, ind: {ind}, fitness: {fitness}, generation: {generation}})"
                + "CREATE (neo)-[:BELONGS_TO]->(e) ";

            for(Individual individual : gp.getPopulation().getAll()) {
                Object[] params = new Object[] {
                    "executionId", gp.getId(),
                    "id", individual.getId(),
                    "ind", individual.getIndId(),
                    "tree", individual.getTree().toString(),
                    "fitness", individual.getFitness(),
                    "generation", 1
                };

                this.execute(query, params);
            };
        }
    }

    @Override
    public void logCrossover(GP gp, Individual father, Individual mother, Individual neo) {
        String query = "MATCH (father:Individual {id: {fatherId}}) "
            + "MATCH (mother:Individual {id: {motherId}}) "
            + "CREATE (neo:Individual {id: {id}, ind: {ind}, fitness: {fitness}, generation: {generation}}) "
            + "MERGE (neo)-[:GENERATED_BY {operator: {operator}}]->(father) "
            + "MERGE (neo)-[:GENERATED_BY {operator: {operator}}]->(mother) ";

        Object[] params = new Object[] {
            "id", neo.getId(),
            "ind", neo.getIndId(),
            "fitness", neo.getFitness(),
            "tree", neo.getTree().toString(),
            "generation", gp.getGenerationNumber(),
            "operator", gp.crossoverOperator.getClass().getName(),
            "fatherId", father.getId(),
            "motherId", mother.getId(),
        };

        this.execute(query, params);
    }

    @Override
    public void logMutation(GP gp, Individual father, Individual neo) {
        String query = "MATCH (father:Individual {id: {fatherId}}) "
            + "CREATE (neo:Individual {id: {id}, ind: {ind}, fitness: {fitness}, generation: {generation}}) "
            + "MERGE (neo)-[:GENERATED_BY {operator: {operator}}]->(father) ";

        Object[] params = new Object[] {
            "id", neo.getId(),
            "ind", neo.getIndId(),
            "fitness", neo.getFitness(),
            "tree", neo.getTree().toString(),
            "generation", gp.getGenerationNumber(),
            "operator", gp.mutationOperator.getClass().getName(),
            "fatherId", father.getId(),
        };

        this.execute(query, params);
    }
}
