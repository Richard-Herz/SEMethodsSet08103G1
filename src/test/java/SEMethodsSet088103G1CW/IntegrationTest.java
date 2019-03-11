package SEMethodsSet088103G1CW;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest
{
    static SQLConnection connection;

    @BeforeAll
    static void init()
    {
        SQLConnection connection = new SQLConnection();
        connection.connect("localhost:33060");
    }

    @Test
    void testGetCountry()
    {
        Country c = connection.getPopulation(1);
        connection.displayCountry(c);
        //Employee emp = app.getEmployee(255530);
        //assertEquals(emp.emp_no, 255530);
       // assertEquals(emp.first_name, "Ronghao");
       // assertEquals(emp.last_name, "Garigliano");
    }

    @Test
    void printPopulationNull()
    {
        Country c = connection.getPopulation(1);
        connection.displayCountry(c);
    }


    @Test
    void printCityNull()
    {
        City c = connection.getName("jlkdjbk");
        connection.displayCity(c);
    }


    @Test
    void printLanguageNull()
    {
        Language l = connection.getSpeakers("jlkdjbk");
        connection.displayLanguage(l);
    }

    @Test
    void printCountry() {
        Country c = new Country();
        c.Code = "GB";
        c.Name = "Great Britain";
        c.Capital = 1;
        c.Population = 10;
        connection.displayCountry(c);
    }
}