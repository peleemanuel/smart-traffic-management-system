package proiect_fic.smarttrafficmanagementsystem.models;

import org.junit.jupiter.api.Test;
import proiect_fic.smarttrafficmanagementsystem.models.FlagSenzor;

import static org.junit.jupiter.api.Assertions.*;

public class FlagSenzorTest {

    @Test
    public void testInitialStatus() {
        FlagSenzor senzor = new FlagSenzor(true);
        assertTrue(senzor.getStatus(), "Initial status should be true");
    }

    @Test
    public void testSetStatus() {
        FlagSenzor senzor = new FlagSenzor(false);
        assertFalse(senzor.getStatus(), "Initial status should be false");

        senzor.setStatus(true);
        assertTrue(senzor.getStatus(), "Status should be true after setting to true");

        senzor.setStatus(false);
        assertFalse(senzor.getStatus(), "Status should be false after setting to false");
    }
}

