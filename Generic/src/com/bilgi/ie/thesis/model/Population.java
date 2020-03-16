package com.bilgi.ie.thesis.model;

/**
 * Population classı da birden çok Individual'lardan oluşuyor. Bunu da bir sınıftaki öğrenciler
 * olarak düşünebilirsiniz. 10 öğrenci varsa Population uzunluğu 10 oluyor ve her öğrenci de birer
 * Individual olduğu için aslında her birinin genleri var.
 * "fittestScore" dediğimiz şey ise bu Population içindeki Individual'ların fitness scorelarının
 * en büyüğü. Örneğin, 3. Individual'ın fitness score'u 5 olsun. Diğer Indivual'ların fitness score'u
 * ise 5'ten küçük olsun. fittestScore 5 olacak.
 */
public class Population {

    private int popSize;
    private Individual[] individuals;
    private int geneLength;
    private int fittestScore = 0;

    /**
     * @purpose Initialize population
     * @param popSize is the population size
     * @param geneLength is the number of genes an individual will have
     */
    public Population(int popSize, int geneLength) {
        super();
        this.popSize = popSize;
        this.geneLength = geneLength;
        this.individuals = new Individual[popSize];

        // Population için Individual'lar oluşturuluyır.
        for (int i = 0; i < popSize; i++) {
            individuals[i] = new Individual(geneLength);
        }
    }

    // Fitness score'u en yüksek olanı seçiyor ve sonrasında ise bu değeri yukarıdaki fittestScore
    // değeri olarak belirliyor.
    public Individual selectFittest() {
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (maxFit <= individuals[i].getFitness()) {
                maxFit = individuals[i].getFitness();
                maxFitIndex = i;
            }
        }
        // update fittest score
        fittestScore = individuals[maxFitIndex].getFitness();

        // try to return the fittest individual
        // Son olarak bu objenin kopyasını oluşturup, sonuç olarak bunu döndürüyor.
        try {
            return (Individual) individuals[maxFitIndex].clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Fitness score'u en yüksek ikinciyi buluyor.
    public Individual selectSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i].getFitness() > individuals[maxFit1].getFitness()) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (individuals[i].getFitness() > individuals[maxFit2].getFitness()) {
                maxFit2 = i;
            }
        }
        try {
            return (Individual) individuals[maxFit2].clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Fitness score'u en az olanın indexini döndürüyor. İleride bunu kullanacak.
    public int getLeastFittestIndex() {
        int minFitVal = Integer.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (minFitVal >= individuals[i].getFitness()) {
                minFitVal = individuals[i].getFitness();
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }

    // Fitness score'u en yüksek olanın indexini buluyor.
    public int getFittestIndex() {
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (maxFit <= individuals[i].getFitness()) {
                maxFit = individuals[i].getFitness();
                maxFitIndex = i;
            }
        }
        return maxFitIndex;
    }

    // Her bir Individual'ın fitness score'unu hesaplıyor. Bu sırada da en yüksek olanı buluyor.
    public void calculateEachFitnessOfIndividual() {
        for (int i = 0; i < individuals.length; i++) {
            individuals[i].calcFitness();
        }

        // En yüksek fitness değerini bulmak için.
        selectFittest();
    }


    // Getters and Setters
    public int getPopSize() {
        return popSize;
    }

    public void setPopSize(int popSize) {
        this.popSize = popSize;
    }

    public Individual[] getIndividuals() {
        return individuals;
    }

    public void setIndividuals(Individual[] individuals) {
        this.individuals = individuals;
    }

    public int getGeneLength() {
        return geneLength;
    }

    public void setGeneLength(int geneLength) {
        this.geneLength = geneLength;
    }


    public int getFittestScore() {
        return fittestScore;
    }

    public void setFittestScore(int fittestScore) {
        this.fittestScore = fittestScore;
    }

}