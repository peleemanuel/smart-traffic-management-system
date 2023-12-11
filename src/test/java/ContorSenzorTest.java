import grupa51.ContorSenzor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ContorSenzorTest {
    @Test
    @DisplayName("Test pentru schimbareStare")
    public void testSchimbaStare() {
        //setup
        ContorSenzor senzor1 = new ContorSenzor(false, 0);

        //action
        senzor1.schimbaStare(true);

        //assertion
        assertEquals(true, senzor1.getEnable());


    }

    @Test
    @DisplayName("Test pentru setNumarMasini")
    public void testSetNumarMasini()
    {
        //setup
        ContorSenzor senzor1 = new ContorSenzor(false, 0);

        //action
        senzor1.setNumarMasini(10);
        int numarMasini=senzor1.getNumarMasini();

        //assertion
        assertEquals(10, numarMasini);
    }

    @Test
    @DisplayName("Test pentru getNumarMasini")
    public void testGetNumarMasini()
    {
        //setup
        ContorSenzor senzor = new ContorSenzor(true, 0);

        //action
        int numarMasini=senzor.getNumarMasini();

        //assertion
        assertEquals(0, numarMasini);
    }

    @Test
    @DisplayName("Test pentru getEnable")
    public void testGetEnable()
    {
        //setup
        ContorSenzor senzor1 = new ContorSenzor(false, 5);

        //action
        boolean enable=senzor1.getEnable();

        //assertion
        assertEquals(false, enable);
    }

    @Test
    @DisplayName("Test pentru  incrementNumarMasini")
    public void testIncrementNumarMasini()
    {
        //setup
        ContorSenzor senzor1 = new ContorSenzor(false, 0);

        ///aici se poate vedea cazul in care enable este fals iar numarul de masini nu se incrementeaza
        //action
        senzor1.incrementNumarMasini();
        int numarMasini=senzor1.getNumarMasini();

        //assertion
        assertEquals(0, numarMasini);

        ///aici se poate vedea cazul in care enable este true iar numarul de masini se incrementeaza
        //action
        senzor1.schimbaStare(true);
        senzor1.incrementNumarMasini();

        //assertion
        assertEquals(1, senzor1.getNumarMasini());
    }

    @Test
    @DisplayName("Test pentru  reseteazaContor")
    public void testReseteazaContor()
    {
        //setup
        ContorSenzor senzor1 = new ContorSenzor(false, 0);

        //action
        //am setat numarul de masini pe o valoare diferita pentru a putea observa daca reseteazaContor pune appoi valoarea pe 0
        senzor1.setNumarMasini(5);
        //assertEquals(5,senzor1.getNumarMasini());
        senzor1.reseteazaContor();
        int numarMasini= senzor1.getNumarMasini();

        //assertion
        assertEquals(0, numarMasini);
    }
}
