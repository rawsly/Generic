package com.bilgi.ie.thesis.model;

import com.bilgi.ie.thesis.util.ConsoleColors;
import java.util.Arrays;
import java.util.Random;

/**
 * Individual classı gen uzunluğu kadar gen arrayi oluşturan bir class. Bunu bir insan olarak
 * düşünebilirsiniz. İnsanların içindeki gen sayısını bir arrayin içinde tutuyor. Bu array
 * 1 ve 0'lardan oluşuyor. Rastgele oluşturulan bir array. Arrayin adı da "genes"
 * "fitness" dediğimiz şeye ise arraydeki 1'lerin sayısı. Örneğin; [0,0,1,1,1,0,0,1]'den oluşan bir
 * arrayin fitness değeri 4. Çünkü 4 tane 1 var arrayde.
 */
public class Individual implements Cloneable{

    private int geneLength;
    private int fitness = 0;
    private int[] genes;

    public Individual(int geneLength) {

        // Individual objesini başlatıyor. Yani bir kişinin genlerini oluşturuyor.
        // Şu anda gen arrayi boş.
        this.geneLength = geneLength;
        this.genes = new int[geneLength];

        Random rn = new Random();

        // Loopda dönerek gen arrayini rastgele 1 ve 0'larla dolduruyor. Bunun için de rastgele
        // oluşturduğu bir sayının 2 ile bölümünden kalana bakıyor. Örneğin, rastgele oluşturduğu
        // sayı 14 ise, 14'ün 2 ile bölümünden kalan 0 olduğu için arraye 0'ı ekliyor.
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Math.abs(rn.nextInt() % 2);
        }

        // Başlangıçta fitness değeri 0.
        fitness = 0;
    }

    // Fitness değerini hesaplıyor. Basitçe, arrayde dolaşıp,
    // 1 görürse fitness değerini arttırıyor.
    public void calcFitness() {
        fitness = 0;
        for (int i = 0; i < genes.length; i++) {
            if (genes[i] == 1) {
                ++fitness;
            }
        }
    }

    // Bundan sonraki kısımlar Java ile alakalı kısımlar.
    // clone() metodu objeyi kopyalamak için kullanılıyor.
    // toString() ve toStringColor() metodları sonuçları konsola yazdırmak için.
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Individual individual = (Individual)super.clone();
        individual.genes = new int[geneLength];
        for(int i = 0; i < individual.genes.length; i++){
            individual.genes[i] = this.genes[i];
        }
        return individual;
    }

    @Override
    public String toString() {
        //without colors
        return "[genes=" + Arrays.toString(genes) + "]";
    }

    public String toStringColor() {
        //with colors
        String genesString = "[genes=[";
        int increment=0;
        for(int gene:genes) {
            //print gene
            if(gene == 0) genesString += ConsoleColors.BLACK_BOLD + ConsoleColors.RED_BACKGROUND_BRIGHT + gene + ConsoleColors.RESET;
            if(gene == 1) genesString += ConsoleColors.BLACK_BOLD + ConsoleColors.GREEN_BACKGROUND_BRIGHT + gene + ConsoleColors.RESET;
            //print comma
            if(increment<genes.length-1) genesString += ", ";

            increment++;
        }
        genesString += "]]";
        return genesString;
    }

    // Getters and Setters - Başka classlardan bu classın içindeki değerleri almak ve güncellemek
    // için getter ve setterlar kullanılıyor.
    public int getGeneLength() {
        return geneLength;
    }

    public void setGeneLength(int geneLength) {
        this.geneLength = geneLength;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int[] getGenes() {
        return genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

}