package libs;

import java.util.Random;
import libs.*;

public class GenerateKeys {

    private int p;
    private int q;
    private long n;

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public long getN() {
        return n;
    }

    public void setN(long n) {
        this.n = n;
    }

    public GenerateKeys() {
        this.p = 0;
        this.q = 0;
        this.n = 0;
    }

    public void get_p_q() {
        while(this.p == this.q) {
            this.q = this.get_Primenumber();
            this.p = this.get_Primenumber();
        }
    }

    //Get prime number
    public int get_Primenumber() {
        int num = 0;
        Random rand = new Random(); // generate a random number
        num = rand.nextInt(1000) + 1;
        while (!isPrime(num)) {
            num = rand.nextInt(1000) + 1;
        }
        return num;
    }

    //Generate RSA keys
    public void keyRSA() {
        this.n = this.q * this.p;
        long m = (this.p - 1)*(this.p - 1);
        boolean found = false;
        do {
            long e = Functions.getRandomNumber(1, m);
            if(Functions.GCD(m, e) == 1) {
                found = true;
            }
        } while(!found);
    }

    //Check prime number
    public static boolean isPrime(int inputNum){
        if (inputNum <= 3 || inputNum % 2 == 0)
            return inputNum == 2 || inputNum == 3; //this returns false if number is <=1 & true if number = 2 or 3
        int divisor = 3;
        while ((divisor <= Math.sqrt(inputNum)) && (inputNum % divisor != 0))
            divisor += 2; //iterates through all possible divisors
        return inputNum % divisor != 0; //returns true/false
    }
}
