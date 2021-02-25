package libs;

public class Functions {

    public static long getRandomNumber(long min, long max) {
        return (long) ((Math.random() * (max - min)) + min);
    }

    public static long GCD(long m, long n) {
        if(n == 0) return m;
        return GCD(n, m % n);
    }

}
