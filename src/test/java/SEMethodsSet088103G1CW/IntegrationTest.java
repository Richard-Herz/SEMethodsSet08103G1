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
        con.connect("db");
    }

    @Test
    void testGetCountry()
    {
        //Employee emp = app.getEmployee(255530);
        //assertEquals(emp.emp_no, 255530);
       // assertEquals(emp.first_name, "Ronghao");
       // assertEquals(emp.last_name, "Garigliano");
    }
}