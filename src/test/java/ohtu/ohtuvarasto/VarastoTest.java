package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto, varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(10, 5);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);

        varasto2.lisaaVarastoon(2);
        assertEquals(7, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);

        varasto2.lisaaVarastoon(3);
        assertEquals(2, varasto2.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);

        double saatuMaara2 = varasto2.otaVarastosta(3);

        assertEquals(3, saatuMaara2, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);

        varasto2.otaVarastosta(1);
        assertEquals(6, varasto2.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ylimääräinenHukkaan() {
        varasto.lisaaVarastoon(11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otetaanVainVarastossaOlevaMaara() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(6);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tilaaOnVahintaan0() {
        Varasto varasto3 = new Varasto(-1.3);
        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVarastonOikeallaMaaralla() {
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void maaraaOnVahintaan0() {
        Varasto varasto3 = new Varasto(10, -1.5);
        assertEquals(0, varasto3.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void maaraaOnEnintaanTilavuus() {
        Varasto varasto3 = new Varasto(5, 6);
        assertEquals(5, varasto3.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alle0MaaraaEiLisata() {
        varasto2.lisaaVarastoon(-2.5);
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alle0MaaraaEiPoisteta() {
        varasto2.otaVarastosta(-3.4);
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringPalauttaaOikeanStringin() {
        assertTrue(varasto2.toString().contains("saldo = "));
        assertTrue(varasto2.toString().contains(" vielä tilaa 5"));
    }

}
