package proiect_fic.smarttrafficmanagementsystem.models;

import proiect_fic.smarttrafficmanagementsystem.models.EmergencySenzor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmergencySenzorTest {
    private EmergencySenzor senzor;

    @BeforeEach
    void setUp() {
        // Inițializăm senzorul cu starea inițială false (nu în stare de urgență)
        senzor = new EmergencySenzor(false);
    }

    @Test
    void testInitialCondition() {
        // Verificăm condiția inițială, care ar trebui să fie false
        assertFalse(senzor.getStatus(), "Sensor should not be in emergency state initially");
    }

    @Test
    void testSetStatusTrue() {
        // Setăm starea senzorului la true și verificăm
        senzor.setStatus(true);
        assertTrue(senzor.getStatus(), "Sensor status should be true after being set to true");
    }

    @Test
    void testSetStatusFalse() {
        // Setăm starea senzorului la false și verificăm
        senzor.setStatus(false);
        assertFalse(senzor.getStatus(), "Sensor status should be false after being set to false");
    }
}
