package SEMethodsSet088103G1CW;

import com.mysql.cj.jdbc.exceptions.NotUpdatable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;
import java.util.ArrayList;

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
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
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
    /**
     * Gets world populations
     * @return A list of countries and populations, or null if there is an error.
     */
    @RequestMapping("countryByPop")
    public ArrayList<Country> countryByPop()
    {
        try{
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Population, country.Continent, country.Capital "
                            + "FROM country "
                            + "ORDER BY country.Population DESC";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract country information
            ArrayList<Country> countries = new ArrayList<>();
            while (rset.next())
            {
                Country country = new Country();
                country.Code = rset.getString("country.Code");
                country.Name = rset.getString("country.Name");
                country.Population = rset.getInt("country.Population");
                country.Continent = rset.getString("country.Continent");
                country.Capital = rset.getInt("country.Capital");
                countries.add(country);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }


    //Test methods for unit testing
    @RequestMapping("/pop")
    public static Country getPopulation(@RequestParam(value="name", defaultValue="103000") int population)
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
    @RequestMapping("getname")
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
    @RequestMapping("/rep1")
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
    @RequestMapping("/rep2")
    public ArrayList<Country> getCountryReport2(@RequestParam(value="continent") String continent)
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
    @RequestMapping("/rep3")
    public ArrayList<Country> getCountryReport3(@RequestParam(value="region")String region)
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
    @RequestMapping("rep4")
    public ArrayList<Country> getCountryReport4(@RequestParam(value="limit") int limit)
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
                            + " LIMIT " + limit;
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
    @RequestMapping("rep5")
    public ArrayList<Country> getCountryReport5(@RequestParam(value="continent")String continent,@RequestParam(value="limit") int limit)
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
                            + " LIMIT " + limit;
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
    @RequestMapping("rep6")
    public ArrayList<Country> getCountryReport6(@RequestParam(value="region") String region,@RequestParam(value="limit") int limit)
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
                            + " LIMIT " + limit;
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
    @RequestMapping("cityRep1")
    public ArrayList<City> getCityReport1()
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District"
                            + " FROM city "
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
    @RequestMapping("cityRep2")
    public ArrayList<City> getCityReport2(@RequestParam(value = "code") String code)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District"
                            + " FROM city "
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
    @RequestMapping("cityRep3")
    public ArrayList<City> getCityReport3(@RequestParam(value= "district") String district)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM city "
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
    @RequestMapping("cityRep4")
    public ArrayList<City> getCityReport4(@RequestParam(value = "limit") int limit)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM city "
                            + " ORDER BY city.Population DESC"
                            + " LIMIT " + limit;
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
    @RequestMapping("cityRep5")
    public ArrayList<City> getCityReport5(@RequestParam(value = "code") String code ,@RequestParam(value ="limit") int limit)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM city "
                            + " WHERE city.CountryCode = '" + code + "'"
                            + " ORDER BY city.Population DESC"
                            + " LIMIT " + limit;
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
    @RequestMapping("cityRep6")
    public ArrayList<City> getCityReport6(@RequestParam(value = "district") String district,
                                          @RequestParam(value ="limit") int limit)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM city "
                            + " WHERE city.District = '" + district + "'"
                            + " ORDER BY city.Population DESC"
                            + " LIMIT " + limit;
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

    //City Report 7
    @RequestMapping("cityRep7")
    public ArrayList<City> getCityReport7()
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District"
                            + " FROM city"
                            + " LEFT JOIN country "
                            + " ON country.CountryCode = city.CountryCode "
                            + " ORDER BY city.Population DESC"
                            + " GROUP BY country.Continent";
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

    //City Report 8
    @RequestMapping("cityRep8")
    public ArrayList<City> getCityReport8()
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District"
                            + " FROM city"
                            + " LEFT JOIN country "
                            + " ON country.CountryCode = city.CountryCode "
                            + " ORDER BY city.Population DESC"
                            + " GROUP BY country.Region";
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

    //City Report 9
    @RequestMapping("cityRep9")
    public ArrayList<City> getCityReport9(@RequestParam(value = "limit") int limit)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM city"
                            + " LEFT JOIN country "
                            + " ON country.CountryCode = city.CountryCode "
                            + " ORDER BY city.Population DESC"
                            + " GROUP BY country.Continent"
                            + " LIMIT " + limit;
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

    //City Report 10
    @RequestMapping("cityRep10")
    public ArrayList<City> getCityReport10(@RequestParam(value = "limit") int limit)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Region, city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM city"
                            + " LEFT JOIN country"
                            + " ON country.Code = city.CountryCode "
                            + " GROUP BY country.Region, city.Name, city.CountryCode, city.District, city.Population"
                            + " ORDER BY city.Population DESC"
                            + " LIMIT " + limit;
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


    //Capital City Report 2
    @RequestMapping("capitalRep2")
    public ArrayList<City> getCapitalCityReport2()
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM city"
                            + " LEFT JOIN country"
                            + " ON country.CountryCode = city.CountryCode "
                            + " ORDER BY city.Population DESC"
                            + " GROUP BY country.Continent";
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

    //Capital City Report 3
    @RequestMapping("capitalRep3")
    public ArrayList<City> getCapitalCityReport3()
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM city"
                            + " LEFT JOIN country"
                            + " ON country.CountryCode = city.CountryCode "
                            + " ORDER BY city.Population DESC"
                            + " GROUP BY country.Region";
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
    //Capital City Report 4
    @RequestMapping("capitalRep4")
    public ArrayList<City> getCapitalCityReport4(@RequestParam(value = "limit") int limit)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM city"
                            + " LEFT JOIN country"
                            + " ON country.CountryCode = city.CountryCode "
                            + " ORDER BY city.Population DESC"
                            + " LIMIT " + limit
                            + " GROUP BY country.Region" ;
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

    //Capital City Report 5
    @RequestMapping("capitalRep5")
    public ArrayList<City> getCapitalCityReport3(@RequestParam(value = "limit") int limit)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM city "
                            + " LEFT JOIN country "
                            + " ON country.CountryCode = city.CountryCode "
                            + " ORDER BY city.Population DESC"
                            + " LIMIT " + limit
                            + " GROUP BY country.Continent";
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

    //Capital City Report 6
    @RequestMapping("capitalRep6")
    public ArrayList<City> getCapitalCityReport6(@RequestParam(value = "limit") int limit)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + " FROM city "
                            + " LEFT JOIN country "
                            + " ON country.CountryCode = city.CountryCode "
                            + " ORDER BY city.Population DESC"
                            + " LIMIT " + limit
                            + " GROUP BY country.Region";
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
    // End of Capital city reports

    //Start of Population Reports

    // End of Population Reports

    //Start of Language Reports
    
    //Langauge Report
    @RequestMapping("languageReport")
    public ArrayList<City> getLanguageReport(@RequestParam(value = "limit") int limit)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + " FROM city "
                            + " LEFT JOIN country "
                            + " ON country.CountryCode = city.CountryCode "
                            + " ORDER BY city.Population DESC"
                            + " LIMIT " + limit
                            + " GROUP BY country.Region";
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

    // End of Language Reports
}
