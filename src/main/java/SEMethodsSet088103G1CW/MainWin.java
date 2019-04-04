package SEMethodsSet088103G1CW;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.sql.*;
import java.util.ArrayList;

public class MainWin
{
    private static SQLConnection connection = new SQLConnection();

    /**
     * Connection to MySQL database.
     */

    public static void main(String[] args)
    {
        //working connection and disconnect
        /*
        connection.connect("localhost:33060");

        ArrayList<Country> arrC = connection.getCountryReport1();
        connection.displayCountry(arrC);

        connection.disconnect();
        */

        // Connect to database

        //this is the endpoint we need for our program, no idea where to put it
        //35.242.135.235:3306
        if (args.length < 1)
        {
            connection.connect("localhost:33060");

        }
        else
        {
            connection.connect(args[0]);

        }


        //ArrayList<Country> arrC = connection.getCountryReport1();
        //connection.displayCountry(arrC);
        SpringApplication.run(MainWin.class, args);


        //test statement to see if print method works
        //Country country = connection.getPopulation(103000);


        //connection.displayCountry(country);




    }


}
