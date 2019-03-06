package SEMethodsSet088103G1CW;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Tests
{
    static MainWin app;

    @BeforeAll
    static void init()
    {
        app = new MainWin();
    }

    @Test
    void printPopulationNull()
    {
        SQLConnection con = new SQLConnection();
        Country c = con.getPopulation(1);
        con.displayCountry(c);
    }


    @Test
    void printCityNull()
    {
        SQLConnection con = new SQLConnection();
        City c = con.getName("jlkdjbk");
        con.displayCity(c);
    }


    @Test
    void printLanguageNull()
    {
        SQLConnection con = new SQLConnection();
        Language l = con.getSpeakers("jlkdjbk");
        con.displayLanguage(l);
    }

    @Test
    void printCountry() {
        SQLConnection con = new SQLConnection();
        Country c = new Country();
        c.Code = "GB";
        c.Name = "Great Britain";
        c.Capital = 1;
        c.Population = 10;
        con.displayCountry(c);
    }
}