package classes.random_array_generator;

import java.util.Random;

public class RandomArrayGenerator {

    public int[] generateRandomArray(int arraySize){
        int maxValue = 100; //Valor max pro random
        Random random = new Random();
        int[] randomArray = new int[arraySize];

        for (int i = 0; i < randomArray.length; i++){
            randomArray[i] = random.nextInt(maxValue);
        }

        return randomArray;
    }
    
}
