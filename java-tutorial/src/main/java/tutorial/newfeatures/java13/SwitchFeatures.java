package tutorial.newfeatures.java13;

import org.junit.jupiter.api.Test;

public class SwitchFeatures {

    /**
     * Switch Expression can now return a value.
     *
     * @param status
     * @return
     */
    public boolean statusIsOk(int status) {
        return switch (status) {
            case 1 -> true;
            case 2 -> false;
            default -> throw new IllegalArgumentException("Unexpected value: " + status);
        };
    }

    @Test
    public void testSwitch() {
        int status = 1;
        System.out.println(statusIsOk(status));
    }
    @Test
    public void testMultipleString() {
        String htmlBeforeJava13 = "<html>\n" +
                "    <body>\n" +
                "        <p>Hello, world</p>\n" +
                "    </body>\n" +
                "</html>\n";

        String htmlWithJava13 = """
              <html>
                  <body>
                      <p>Hello, world</p>
                  </body>
              </html>
              """;
        System.out.println(htmlBeforeJava13);
        System.out.println(htmlWithJava13);
    }
}
