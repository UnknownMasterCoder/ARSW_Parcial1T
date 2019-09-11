package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import edu.eci.arsw.parallel.ParallelCalculation;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PrimesFinderTool {

	public static void main(String[] args) throws InterruptedException {
		            
            /*   //PUNTO 1 
            //(Descomentar esto y comentar los demás para probar solo esta parte)
            
            //ParallelCalculation me calcula todos los numeros primos
            //Tomando como parametro (el # de primos a calcular y el # de hilos)
            //En este caso 4 hilos, pero tambien se puede hacer con diferente # de hilos
            
            //System.out.println("Punto 1:\n");
            //ParallelCalculation punto1 = new ParallelCalculation(10000,4);
            //punto1.calculate();
            */
            
            
            
            
            //   PUNTO 2
            //(Descomentar esto y comentar los demás para probar solo esta parte)

            System.out.println("Punto 1:\n");
            ParallelCalculation punto1 = new ParallelCalculation(1000000,10);
            //Mientras que haya movimiento del mouse, Mientras la tarea no esté terminada
            //while(MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement() > 10000){
            while(!Thread.interrupted()){
                try {
                    //check every 10ms if the idle status (10 seconds without mouse
                    //activity) was reached. 
                    Thread.sleep(10);
                    if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement() > 10000){
                        System.out.println("\nIDLE CPU\n");                        
                        punto1.calculate();
                    }
                    else{
                        System.out.println("\nUser working again!\n");
                        //punto1.wait();
                        Thread.holdsLock(punto1);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                synchronized (punto1) {
                    punto1.notify();
                }
            }     
            
	}
	
}


