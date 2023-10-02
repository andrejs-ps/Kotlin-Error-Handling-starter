package m4_throwing._3throws;

import java.io.IOException;

public class JavaMain {

    public static void main(String[] args) {

        var service = new JobService();

        try {
            service.fetchJobById("5");
        } catch (IOException ex) {
            // handle
        }

    }

    static void doThing() throws IOException {
        throw new IOException();
    }

    static void doThing2()  {
        throw new RuntimeException();
    }
}
