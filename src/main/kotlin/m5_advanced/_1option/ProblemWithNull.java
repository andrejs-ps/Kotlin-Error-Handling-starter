package m5_advanced._1option;

public class ProblemWithNull {

    // Java code
    public static void main(String[] args) {

        String name = getUserNameById(123);

        if(name != null) { }

        System.out.println(name.charAt(0));
    }

    // alternatives: throw, default value (0), List.of() - but conventions get ignored
    private static String getUserNameById(int i) {
        return null;
    }
}
