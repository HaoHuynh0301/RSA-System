package libs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Functions {

    public static long getRandomNumber(long min, long max) {
        return (long) ((Math.random() * (max - min)) + min);
    }

    public static long GCD(long m, long n) {
        if(n == 0) return m;
        return GCD(n, m % n);
    }

    public static String readFileAsString(String filename) {
        String text = new String("");
        try {
            text = new String(Files.readAllBytes(Paths.get(filename))); }
        catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

}
