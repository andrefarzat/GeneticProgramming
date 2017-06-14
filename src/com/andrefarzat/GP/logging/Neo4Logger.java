package com.andrefarzat.GP.logging;

import com.andrefarzat.mendel.Population;
import com.andrefarzat.mendel.logging.MendelLogger;
import org.neo4j.driver.v1.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.neo4j.driver.v1.Values.parameters;


public class Neo4Logger implements MendelLogger {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private Driver driver;
    private Session session;

    private UUID id = UUID.randomUUID();

    public String getId() {
        return this.id.toString();
    }

    public Neo4Logger() {
        /*
        this.driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "h4rdP4ss" ) );
        this.session = driver.session();

        session.run( "CREATE (a:Person {name: {name}, title: {title}})",
                parameters( "name", "Arthur", "title", "King" ) );

        StatementResult result = session.run( "MATCH (a:Person) WHERE a.name = {name} " +
                        "RETURN a.name AS name, a.title AS title",
                parameters( "name", "Arthur" ) );
        while ( result.hasNext() )
        {
            Record record = result.next();
            System.out.println( record.get( "title" ).asString() + " " + record.get( "name" ).asString() );
        }

        session.close();
        driver.close();
        */
    }

    private void execute(String query, Object ...params) {
        this.driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "h4rdP4ss" ) );
        this.session = driver.session();

        session.run(query, parameters(params));

        session.close();
        driver.close();
    }

    @Override
    public void logStartTime() {
        this.startTime = LocalDateTime.now();
        String time = this.startTime.format(this.dateTimeFormat);

        String query = "CREATE (e:Execution {id: {id}, startTime: {startTime}})";
        this.execute(query, "id", this.getId(), "startTime", time);

        System.out.println("Start time: " + time);
    }

    @Override
    public void logEndTime() {
        this.endTime = LocalDateTime.now();
        String time = this.endTime.format(this.dateTimeFormat);

        System.out.println("Finish time: " + time);
        Duration interval = Duration.between(this.startTime, this.endTime);
        System.out.println(String.format("It took %s seconds", interval.getSeconds()));

        String query = "MATCH (e:Execution {id: {id}}) SET e.endTime = {endTime}, e.duration = {duration}";
        this.execute(query, "id", this.getId(), "endTime", time, "duration", interval.getSeconds());
    }

    @Override
    public void logPopulation(String label, Population population) {

    }
}
