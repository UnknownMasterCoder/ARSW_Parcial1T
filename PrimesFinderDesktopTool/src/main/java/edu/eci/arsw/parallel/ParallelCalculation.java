/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.parallel;

import java.math.BigInteger;

/**
 *
 * @author 2099190
 */
public class ParallelCalculation {
    
    private final int primesNumber;
    private final int threadsNumber;// = 4;
    private final int[] threadsArray;
    //private double width;

    public ParallelCalculation(int primesNumber, int N) {
        this.primesNumber = primesNumber;
        this.threadsNumber = N;
        this.threadsArray = new int[N];
        
        //Calcular tamaño de cada hilo
        int div = primesNumber / N;
        int mod = primesNumber % N;
        int suma = div;
        for(int i = 0; i < N ; i++){
            if(i+1 == N){
                this.threadsArray[i]=suma+mod;
            }else{
                this.threadsArray[i]=suma;
            }
        }
    }
    
    public String calculate() throws InterruptedException{
        
        PrimeThread threads[] = new PrimeThread[threadsNumber];
        BigInteger width = BigInteger.valueOf(primesNumber).divide(BigInteger.valueOf(threadsNumber));
        
        //Calcular tamaño de cada hilo
        for (int i=0; i < threadsNumber; i++){
            BigInteger start = BigInteger.valueOf(i);
            BigInteger end = BigInteger.valueOf(i+1);
            
            //BigInteger start =  i * primesNumber/threadsNumber;
            //BigInteger end = (i+1) * (primesNumber/threadsNumber);
            
            start = start.multiply(width);
            end = end.multiply(width);
            
            threads[i] = new PrimeThread(start, end);
            threads[i].start();            
        }
        
        //Join all the threads       
        String res = "";
        for (int i = 0; i < threadsNumber; i++) {
            threads[i].join();
            res += threads[i].getValue();
        }
       
        System.out.println("\nJoined Threads (Full Prime Numbers): \n"+res);
        return res;
    }   
    
    
}
