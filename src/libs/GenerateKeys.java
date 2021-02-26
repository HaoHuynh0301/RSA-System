package libs;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import libs.*;

public class GenerateKeys {
    private final static BigInteger one      = new BigInteger("1");
    private final static SecureRandom random = new SecureRandom();

    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger e;
    private BigInteger d;
    private BigInteger phi;

    public GenerateKeys(int N) {

        //Get Randome two big prime numbers (p,q)
        this.p = BigInteger.probablePrime(N/2, random);
        this.q = BigInteger.probablePrime(N/2, random);

        //phi = (p - 1)*(q - 1)
        this.phi = (this.p.subtract(one)).multiply(this.q.subtract(one));

        //n = q*p
        this.n = this.p.multiply(this.q);

        //Get random number e from 1 to phi so that gcd(e, phi) = 1
        this.e = BigInteger.probablePrime(N / 2, random);

        while (this.phi.gcd(this.e).compareTo(one) > 0 && this.e.compareTo(this.phi) < 0)
        {
            this.e.add(one);
        }
        this.d = this.e.modInverse(phi);
    }

    public GenerateKeys(BigInteger P, BigInteger Q, int N) {
        this.q = Q;
        this.p = P;
        this.phi = (this.p.subtract(one)).multiply(this.q.subtract(one));
        this.n = this.p.multiply(this.q);
        this.e = BigInteger.probablePrime(N / 2, random);
        while (this.phi.gcd(this.e).compareTo(one) > 0 && this.e.compareTo(this.phi) < 0)
        {
            this.e.add(this.one);
        }
        this.d = this.e.modInverse(phi);
    }

    // Encrypt the given plaintext message.Use public key decryp
    public synchronized String encrypt(String message) {
        return (new BigInteger(message.getBytes())).modPow(e, n).toString();
    }

    // Decrypt the given ciphertext message.Use private key decrypt
    public synchronized String decrypt(String message) {
        return new String((new BigInteger(message)).modPow(d, n).toByteArray());
    }

    // Decrypt the given ciphertext message.Use private key decrypt

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getE() {
        return this.e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }

    public BigInteger getD() {
        return this.d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }
    
}
