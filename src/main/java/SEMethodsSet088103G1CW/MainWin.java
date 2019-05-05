package SEMethodsSet088103G1CW;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.sql.*;
import java.util.ArrayList;
@SpringBootApplication

public class MainWin
{
    private static SQLConnection connection = new SQLConnection();

    /**
     * Connection to MySQL database.
     */

    public static void main(String[] args) {

        if (args.length < 1) {
            connection.connect("localhost:33060");
        } else {
            connection.connect(args[0]);
        }
        SpringApplication.run(MainWin.class, args);
    }
}
