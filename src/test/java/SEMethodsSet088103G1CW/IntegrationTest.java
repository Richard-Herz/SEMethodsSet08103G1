package SEMethodsSet088103G1CW;

import com.mysql.cj.log.NullLogger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest
{
    static SQLConnection con = new SQLConnection();
    @BeforeAll
    static void init()
    {

        con.connect("localhost:33060");
    }

    @Test
    void testGetCity()
    {
        City c = con.getName("London");
        con.displayCity(c);
    }
    @Test
    void testGetCityEmptyStr()
    {
        City c = con.getName("");
        con.displayCity(c);
    }

    @Test
    void testGetCityNULL()
    {
        City c = con.getName(null);
        con.displayCity(c);
    }
    @Test
    void testGetCityWrongStr()
    {
        City c = con.getName("RandomString");
        con.displayCity(c);
    }

}