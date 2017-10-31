//import org.junit.Before;
//import org.junit.Test;
//import validation.ResultValidator;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//
//import static org.junit.Assert.assertEquals;
//
//public class MainAppTest {
//
//    ResultValidator app;
//    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//
//    @Before
//    public void setUp() {
//        System.setOut(new PrintStream(outContent));
//        app = new ResultValidator();
//    }
//
//    @Test
//    public void testCaseUserInputExample1() {
//        System.setIn(new ByteArrayInputStream((
//                "1 2 3\n" +
//                "1 3 5\n" +
//                "2 3 4"
//        ).getBytes()));
//
//        app.main(null);
//
//        assertEquals("" +
//                "1 2 3\n" +
//                        "1 3 5\n" +
//                        "2 3 4\n",
//                outContent.toString());
//    }
//
//    @Test
//    public void testCaseUserInputExample2() {
//        System.setIn(new ByteArrayInputStream(("" +
//                "3 \n " +
//                "0 3 5\n " +
//                "3 0 4\n" +
//                "5 4 0\n"
//        ).getBytes()));
//
//        app.main(null);
//
//        assertEquals("" +
//                        "1 2 3\n" +
//                        "1 3 5\n" +
//                        "2 3 4",
//                outContent.toString());
//    }
//
//}
