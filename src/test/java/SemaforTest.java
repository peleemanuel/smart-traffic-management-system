import grupa51.ContorSenzor;
import grupa51.EmergencySenzor;
import grupa51.FlagSenzor;
import grupa51.Semafor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
public class SemaforTest {
    private Semafor semafor;
    private ContorSenzor[] contori;
    private FlagSenzor[] flaguri;
    private EmergencySenzor emergencySenzor;
    @BeforeEach
    void setUp() {
        // inițializare array ContorSenzor
         contori = new ContorSenzor[]{
                new ContorSenzor(false, 5),
                new ContorSenzor(true, 10),
                new ContorSenzor(true, 15)
        };

         //initializare array flaguri
        flaguri=new FlagSenzor[]{
             new FlagSenzor(true),
             new FlagSenzor(false),
             new FlagSenzor(false)
        };

        // inițializare EmergencySenzor
         emergencySenzor = new EmergencySenzor(false);

        // inițializare Semafor
        semafor = new Semafor(contori, flaguri, emergencySenzor, "Rosu");
    }

    @DisplayName("Test pentru checkEmergency")
    @Test
    void testCheckEmergency() {

        emergencySenzor.setStatus(true);
        assertTrue(semafor.checkEmergency());

        emergencySenzor.setStatus(false);
        assertFalse(semafor.checkEmergency());
    }

    @DisplayName("Test pentru getCuloareSemafor si setCuloareSemafor")
    @Test
    void testCuloareSemafor() {
        semafor.setCuloareSemafor("Verde");
        assertEquals("Verde", semafor.getCuloareSemafor());
    }

    @DisplayName("Test pentru enableContori")
    @Test
    void testEnableContori(){
        semafor.enableContori();
        for( ContorSenzor contori : contori){
            assertTrue(contori.getEnable());
        }
    }

    @DisplayName("Test pentru disableContori")
    @Test
    void testDisableContori(){
        semafor.disableContori();
        for ( ContorSenzor contori : contori ){
            assertFalse(contori.getEnable());
        }
    }

    @DisplayName("Test pentru checkFlags")
    @Test
    void testCheckFlags(){
        //setam unele flag urile ca fiind active, si unul inactiv
        flaguri[0].setStatus(true);
        flaguri[1].setStatus(false);
        flaguri[2].setStatus(true);

        assertEquals(2,semafor.checkFlags());
    }


}

