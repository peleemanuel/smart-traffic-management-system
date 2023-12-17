package proiect_fic.smarttrafficmanagementsystem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import proiect_fic.smarttrafficmanagementsystem.models.Memorie;

import static org.junit.jupiter.api.Assertions.*;

public class MemorieTest {
    private Memorie memorie;
    private int[] debite;
    private int[] coef;

    @BeforeEach
    public void setUp() {
        debite = new int[]{10, 20, 30, 40};
        coef = new int[]{3, 4, 5, 6};
        memorie = new Memorie(debite, coef);
    }

    @DisplayName("Test GetTMIN")
    @Test
    public void testGetTMIN() {
        assertEquals(Memorie.getTMIN(), 20, "TMIN should be 20");
    }

    @DisplayName("Test GetTMAX")
    @Test
    public void testGetTMAX() {
        assertEquals(Memorie.getTMAX(), 40, "TMAX should be 40");
    }

    @DisplayName("Test GetTMED")
    @Test
    public void testGetTMED() {
        assertEquals(Memorie.getTMED(), 30, "TMED should be 30");
    }

    @DisplayName("Test GetTDIF")
    @Test
    public void testGetTDIF() {
        assertEquals(Memorie.getTDIF(), 20, "TDIF should be 20");
    }

    @DisplayName("Test GetKINIT")
    @Test
    public void testGetKINIT() {
        assertEquals(Memorie.getKINIT(), 1, "KINIT should be 1");
    }

    @Test
    @DisplayName("Test GetSetDebite")
    public void testGetSetDebite() {
        assertArrayEquals(debite, memorie.getDebite(), "Debite array should match initial values");

        int[] newDebite = new int[]{40, 50, 60, 70};
        memorie.setDebite(newDebite);
        assertArrayEquals(newDebite, memorie.getDebite(), "Debite array should match new values");
    }

    @Test
    @DisplayName("Test GetSetCoef")
    public void testGetSetCoef() {
        assertArrayEquals(coef, memorie.getCoef(), "Coef array should match initial values");

        int[] newCoef = new int[]{6, 7, 8, 9};
        memorie.setCoef(newCoef);
        assertArrayEquals(newCoef, memorie.getCoef(), "Coef array should match new values");
    }


}
