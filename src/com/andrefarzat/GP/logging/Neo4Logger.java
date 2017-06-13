package com.andrefarzat.GP.logging;

import com.andrefarzat.mendel.Population;
import com.andrefarzat.mendel.logging.MendelLogger;
import org.neo4j.driver.v1.*;
import static org.neo4j.driver.v1.Values.parameters;


public class Neo4Logger implements MendelLogger {
    private Driver driver;
    private Session session;

    public Neo4Logger() {
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
    }

    @Override
    public void logInitialTime() {

    }

    @Override
    public void logFinishTime() {

    }

    @Override
    public void logPopulation(String label, Population population) {

    }
}
