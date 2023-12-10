import grupa51.Exemplu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExempluTest {
    @Test
    @DisplayName("Test la creare obiect")
    public void testNumber() {
        // setup
        Exemplu obiectExemplu = new Exemplu();

        // action
        int result = obiectExemplu.getNumber();

        // assertion
        assertEquals(4, result);

    }


}
