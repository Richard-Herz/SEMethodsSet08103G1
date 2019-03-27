package SEMethodsSet088103G1CW;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Tests
{
    static SQLConnection connection;

    @BeforeAll
    static void init()
    {
        connection = new SQLConnection();
    }


    @Test
    void printCountriesNull() {
        ArrayList<Country> arrC = new ArrayList<>();
        arrC.add(null);
        connection.displayCountry(arrC);
    }

    @Test
    void printCountryPropertiesNull() {
        Country c = new Country();
        c.Name = null;
        c.Code = null;
        c.Capital = 0;
        ArrayList<Country> arrC = new ArrayList<>();
        connection.displayCountry(arrC);
    }
    @Test
    void printCountryEmptyList() {
        ArrayList<Country> arrC = new ArrayList<>();
        connection.displayCountry(arrC);
    }
    @Test
    void printCountryAllProperties() {
        Country c = new Country();
        c.Name = "Great Britain";
        c.Code = "GB";
        c.Continent = "EUROPE";
        c.Population = 70000000;
        c.Region = "Some Region";
        c.headOfState = "Elizabeth II";
        c.Capital = 0;
        ArrayList<Country> arrC = new ArrayList<>();
        arrC.add(c);
        connection.displayCountry(arrC);
    }



}