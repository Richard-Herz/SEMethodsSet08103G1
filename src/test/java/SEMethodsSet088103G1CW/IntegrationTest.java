package SEMethodsSet088103G1CW;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


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
    /*
    @Test
    void testGetCountry()
    {
        System.out.println("This test works");
        SQLConnection connection = new SQLConnection();
        Country c = connection.getPopulation(103000);
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
    */
    @Test
    void printCountry() {
        connection.getCityReport1();
    }
}