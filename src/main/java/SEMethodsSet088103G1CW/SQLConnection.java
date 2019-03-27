package SEMethodsSet088103G1CW;

import com.mysql.cj.jdbc.exceptions.NotUpdatable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class SQLConnection {


    /**
     * Connection to MySQL database.
     */
    private static Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public static void connect(String location)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(300);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "password");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public static void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    //Method for displaying country report information
    public void displayCountry(ArrayList<Country> arr) {
        for (Country country : arr) {
            if (country != null) {
                System.out.println(country.Code + " " + country.Name + " " + country.Continent + " " + country.Region + " " + country.Population +  + country.Capital + " " + "\n");
                if (country.Code == null && country.Capital == 0 && country.Population == 0 && country.Name == null && country.Continent == null && country.Region == null) {
                    System.out.println("Country is empty");
                }
            } else {
                System.out.println("Country is empty");
            }
        }
    }

    //Method for displaying city report information
    public void displayCity(ArrayList<City> arr) {
        for (City city : arr) {
            if (city != null) {
                System.out.println(city.CountryCode + " " + city.Name + " " + city.District + " " + city.Population + " " + "\n");
                if (city.CountryCode == null && city.Name == null && city.Population == 0 && city.District == null) {
                    System.out.println("City is empty");
                }
            } else {
                System.out.println("City is empty");
            }
        }
    }

    //Method for displaying Capital city report information
    public void displayCapitalCity(ArrayList<City> arr) {
        for (City city : arr) {
            if (city != null) {
                System.out.println(city.CountryCode + " " + city.Name +  " " + city.Population + " " + "\n");
                if (city.CountryCode == null && city.Name == null && city.Population == 0) {
                    System.out.println("Capital city is empty");
                }
            } else {
                System.out.println("Capital city is empty");
            }
        }
    }
    //Test methods for unit testing

    //test method to see if population is loaded in server
    public Country getPopulation(int population)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Capital, country.Population "
                            + "FROM country "
                            + "WHERE country.Population = '" + population + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Capital = rset.getInt("Capital");
                country.Population = rset.getInt("Population");
                return country;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
    }

    //test method to see if population is loaded in server
    public City getName(String name)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.CountryCode, city.Name, city.Population "
                            + "FROM city "
                            + "WHERE city.Name = '" + name + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City city = new City();
                city.CountryCode = rset.getString("CountryCode");
                city.Name = rset.getString("Name");
                city.Population = rset.getInt("Population");
                return city;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    public void displayCity(City city) {
        try {
            if (city != null) {
                System.out.println(city.Name + " " + city.Population + "\n");
            } else {
                System.out.println("City is empty");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Exception caught ");
        }

    }

    //test method to see if population is loaded in server
    public Language getSpeakers(String name)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(countryLanguage.percentage), countryLanguage.Language "
                            + "FROM countryLanguage "
                            + "WHERE countryLanguage.Language = '" + name + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Language language = new Language();
                language.percentage = rset.getFloat("Percentage");
                language.language = rset.getString("Language");
                return language;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Language details");
            return null;
        }
    }

    public void displayLanguage(Language langauage){
        if(langauage != null)
        {
            System.out.println(langauage.language + "\n");
        }
        else
        {
            System.out.println("Language is empty");
        }
    }
    //

    //All the Country Reports

    //Country Report 1
    public ArrayList<Country> getCountryReport1()
    {
        try
        {
            ArrayList<Country> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, country.Capital "
                            + "FROM country "
                            + " ORDER BY country.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.Region = rset.getString("Region");
                country.Population = rset.getInt("Population");
                country.Capital = rset.getInt("Capital");
                lst.add(country);
            }

            return lst;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
    }

    //country Report 2
    public ArrayList<Country> getCountryReport2(String continent)
    {
        try
        {
            ArrayList<Country> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, country.Capital "
                            + "FROM country "
                            + "WHERE country.Continent = '" + continent + "'"
                            + " ORDER BY country.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.Region = rset.getString("Region");
                country.Population = rset.getInt("Population");
                country.Capital = rset.getInt("Capital");
                lst.add(country);
            }

            return lst;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
    }

    //country Report 3
    public ArrayList<Country> getCountryReport3(String region)
    {
        try
        {
            ArrayList<Country> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, country.Capital "
                            + "FROM country "
                            + "WHERE country.Region = '" + region + "'"
                            + " ORDER BY country.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.Region = rset.getString("Region");
                country.Population = rset.getInt("Population");
                country.Capital = rset.getInt("Capital");
                lst.add(country);
            }

            return lst;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
    }

    //Country Report 4
    public ArrayList<Country> getCountryReport4(int limit)
    {
        try
        {
            ArrayList<Country> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, country.Capital "
                            + "FROM country "
                            + " ORDER BY country.Population DESC"
                            + " LIMIT '" + limit + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.Region = rset.getString("Region");
                country.Population = rset.getInt("Population");
                country.Capital = rset.getInt("Capital");
                lst.add(country);
            }

            return lst;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
    }

    //Country Report 5
    public ArrayList<Country> getCountryReport5(String continent, int limit)
    {
        try
        {
            ArrayList<Country> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, country.Capital "
                            + "FROM country "
                            + " WHERE country.Continent = '" + continent + "'"
                            + " ORDER BY country.Population DESC"
                            + " LIMIT '" + limit + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.Region = rset.getString("Region");
                country.Population = rset.getInt("Population");
                country.Capital = rset.getInt("Capital");
                lst.add(country);
            }

            return lst;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
    }

    //Country Report 6
    public ArrayList<Country> getCountryReport6(String region, int limit)
    {
        try
        {
            ArrayList<Country> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, country.Capital "
                            + "FROM country "
                            + " WHERE country.Region = '" + region + "'"
                            + " ORDER BY country.Population DESC"
                            + " LIMIT '" + limit + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.Region = rset.getString("Region");
                country.Population = rset.getInt("Population");
                country.Capital = rset.getInt("Capital");
                lst.add(country);
            }

            return lst;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
    }

    // End of Country Reports

    //All the City Reports

    //City Report 1
    public ArrayList<City> getCityReport1()
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.Country, country.District, country.Population "
                            + "FROM city "
                            + " ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                City city = new City();
                city.Name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
                city.District = rset.getString("District");
                city.Population = rset.getInt("Population");
                lst.add(city);
            }

            return lst;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    //City Report 2
    public ArrayList<City> getCityReport2(String code)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, country.District, country.Population"
                            + "FROM city "
                            + "JOIN country ON city WHERE countryCode = country.CountryCode"
                            + " WHERE city.CountryCode = '" + code + "'"
                            + " ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                City city = new City();
                city.Name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
                city.District = rset.getString("District");
                city.Population = rset.getInt("Population");
                lst.add(city);
            }

            return lst;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    //City Report 3
    public ArrayList<City> getCityReport3(String district)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, country.District, country.Population "
                            + "FROM city "
                            + " WHERE city.District = '" + district + "'"
                            + " ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                City city = new City();
                city.Name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
                city.District = rset.getString("District");
                city.Population = rset.getInt("Population");
                lst.add(city);
            }

            return lst;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    //City Report 4
    public ArrayList<City> getCityReport4(int limit)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, country.District, country.Population "
                            + "FROM city "
                            + " ORDER BY city.Population DESC"
                            + " LIMIT '" + limit + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                City city = new City();
                city.Name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
                city.District = rset.getString("District");
                city.Population = rset.getInt("Population");
                lst.add(city);
            }

            return lst;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    //City Report 5
    public ArrayList<City> getCityReport5(String code ,int limit)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, country.District, country.Population "
                            + "FROM city "
                            + " WHERE city.CountryCode = '" + code + "'"
                            + " ORDER BY city.Population DESC"
                            + " LIMIT '" + limit + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                City city = new City();
                city.Name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
                city.District = rset.getString("District");
                city.Population = rset.getInt("Population");
                lst.add(city);
            }

            return lst;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    //City Report 6
    public ArrayList<City> getCityReport6(String district,int limit)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, country.District, country.Population "
                            + "FROM city "
                            + " WHERE city.District = '" + district + "'"
                            + " ORDER BY city.Population DESC"
                            + " LIMIT '" + limit + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                City city = new City();
                city.Name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
                city.District = rset.getString("District");
                city.Population = rset.getInt("Population");
                lst.add(city);
            }

            return lst;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }
    // End of City Reports

    // Start of Capital city reports


    // End of Capital city reports

    //Start of Population Reports

    // End of Population Reports

    //Start of Language Reports
    
    // End of Language Reports
}
