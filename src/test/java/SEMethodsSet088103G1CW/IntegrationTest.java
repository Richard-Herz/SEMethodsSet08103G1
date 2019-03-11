package SEMethodsSet088103G1CW;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest
{
    static MainWin mainwin;

    @BeforeAll
    static void init()
    {
        SQLConnection con = new SQLConnection();
        con.connect("localhost:33060");
    }

    @Test
    void testGetCountry()
    {
        SQLConnection con = new SQLConnection();
        Country c = con.getPopulation(103000);
        con.displayCountry(c);
        //Employee emp = app.getEmployee(255530);
        //assertEquals(emp.emp_no, 255530);
       // assertEquals(emp.first_name, "Ronghao");
       // assertEquals(emp.last_name, "Garigliano");
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