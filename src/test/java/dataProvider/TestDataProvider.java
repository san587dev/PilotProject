package dataProvider;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.Locale;

public class TestDataProvider {

    @DataProvider
    public static Object[][] dataSuiteA(Method m) {
        System.out.println("- this is the name of the Test " + m.getName().toUpperCase());

        Object data[][] = null;
        if (m.getName().toUpperCase().equals("TESTA")) {
            data = new Object[2][2];

            data[0][0] = "U1A";
            data[0][1] = "P1A";

            data[1][0] = "U2A";
            data[1][1] = "P2A";
        } else if (m.getName().toUpperCase().equals("TESTAA")) {
            System.out.println("-This is test for "+m.getName().toUpperCase());
            data = new Object[2][2];

            data[0][0] = "U1AA";
            data[0][1] = "P1AA";

            data[1][0] = "U2AA";
            data[1][1] = "p2AA";

        }

        return data;

    }

    @DataProvider
    public static Object[][] dataSuiteB(Method m) {
        System.out.println("-This is test for "+m.getName().toUpperCase());
        Object data[][] = null;
        if (m.getName().toUpperCase().equals("TESTB")) {
            data = new Object[2][2];

            data[0][0] = "U1B";
            data[0][1] = "P1B";

            data[1][0] = "U2B";
            data[1][1] = "P2B";

        } else if (m.getName().toUpperCase().equals("TESTBB")) {
            System.out.println("-This is test for "+m.getName().toUpperCase());
            data = new Object[2][2];

            data[0][0] = "U1BB";
            data[0][1] = "P1BB";

            data[1][0] = "U2BB";
            data[1][1] = "P2BB";
        }

        return data;
    }

    @DataProvider
    public static Object[][] dataSuiteC(Method m) {
        Object data[][] = null;
        if (m.getName().toUpperCase().equals("TESTC")) {
            System.out.println("-This is test for "+m.getName().toUpperCase());
            data = new Object[2][2];

            data[0][0] = "U1C";
            data[0][1] = "P1C";

            data[1][0] = "U2C";
            data[1][1] = "P2C";
        } else if (m.getName().toUpperCase().equals("TESTCC")) {
            System.out.println("-This is test for "+m.getName().toUpperCase());
            data = new Object[2][2];

            data[0][0] = "U1CC";
            data[0][1] = "P1CC";

            data[1][0] = "U2CC";
            data[1][1] = "P2CC";
        }
        return data;
    }
}
