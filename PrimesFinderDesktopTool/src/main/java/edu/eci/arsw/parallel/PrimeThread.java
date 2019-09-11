/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.parallel;

import edu.eci.arsw.primefinder.PrimeFinder;
import edu.eci.arsw.primefinder.PrimesResultSet;
import java.math.BigInteger;
import java.util.Collection;

/**
 *
 * @author 2099190
 */
public class PrimeThread extends Thread {

    private final BigInteger A;
    private final BigInteger B;
    private Collection<BigInteger> res;

    public PrimeThread(BigInteger in, BigInteger out) {
        this.A = in;
        this.B = out;
        this.res = null;
    }

    @Override
    public void run() {
        PrimesResultSet prs = new PrimesResultSet("john");
        PrimeFinder.findPrimes(A, B, prs);
        res = prs.getPrimes();
        
        System.out.println("PrimeThread, Divided Part of # found:\n" + res);
        
    }

    public Collection<BigInteger> getValue() {
        return res;
    }

}
