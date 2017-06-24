package com.andrefarzat.GP;


public class GP {
    protected static final int MAX_TRIES_TO_GENERATE_AN_INDIVIDUAL = 10;

    protected Population population;
    protected final int maxDepth = 2;
    protected final int populationSize = 1000;

    protected Individual generateIndividual() {
        Individual individual = new Individual();
        return individual;
    }

    public Population generateInitialPopulation() {
        Population population = new Population();

        for(int i = 0; i < this.populationSize; i++) {
            population.add(this.generateIndividual());
        }

        return population;
    }

    public void evaluate(Individual individual) {
        // TODO:
    }

    public void run() {
        // 1. Generate initial population
        this.population = this.generateInitialPopulation();

        do {
            // 2. Evaluate population
            for(Individual individual : population.individuals) {
                this.evaluate(individual);
            }

            // 3. Select
            //this.doSelection(population);

            // 4. Should stop ?
//            if (this.shouldStop(population)) {
//                break;
//            }

            // 5. Cross
//            this.doCrossover(population);

            // 6. Mutate
//            this.doMutation(population);

            // 7. We loop
        } while (true);
    }
}
