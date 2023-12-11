import grupa51.EmergencySenzor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmergencySenzorTest {
    private EmergencySenzor senzor;

    @BeforeEach
    void setUp() {
        senzor = new EmergencySenzor();
    }

    @Test
    void testInitialCondition() {
        assertFalse(senzor.getStatus(), "Sensor should not be in emergency state initially");
    }

    @Test
    void testSetStatusTrue() {
        senzor.setStatus(true);
        assertTrue(senzor.getStatus(), "Sensor status should be true after being set to true");
    }

    @Test
    void testSetStatusFalse() {
        senzor.setStatus(false);
        assertFalse(senzor.getStatus(), "Sensor status should be false after being set to false");
    }
}
