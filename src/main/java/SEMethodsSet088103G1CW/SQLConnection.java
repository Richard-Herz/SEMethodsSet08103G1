package SEMethodsSet088103G1CW;

import com.mysql.cj.jdbc.exceptions.NotUpdatable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.interceptor.CacheableOperation;
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
                    "SELECT country.Code, country.Name, country.Population, country.Continent, city.Name "
                            + "FROM country "
                            + "JOIN city ON "
                            + "country.Code=city.CountryCode "
                            + "WHERE city.ID = country.Capital "
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
                country.Capital = rset.getString("city.Name");
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
                country.Capital = rset.getString("Capital");
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
                language.lanPercentage = rset.getFloat("Percentage");
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
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                            + "FROM country "
                            + "JOIN city ON "
                            + "country.Code=city.CountryCode "
                            + "WHERE city.ID = country.Capital "
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
                country.Capital = rset.getString("city.Name");
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
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                            + "FROM country "
                            + "JOIN city ON "
                            + "country.Code=city.CountryCode "
                            + "WHERE city.ID = country.Capital "
                            + "AND country.Continent = '" + continent + "'"
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
                country.Capital = rset.getString("city.Name");
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
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                            + "FROM country "
                            + "JOIN city ON "
                            + "country.Code=city.CountryCode "
                            + "WHERE city.ID = country.Capital "
                            + "AND country.Region = '" + region + "'"
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
                country.Capital = rset.getString("city.Name");
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
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                            + "FROM country "
                            + "JOIN city ON "
                            + "country.Code=city.CountryCode "
                            + "WHERE city.ID = country.Capital "
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
                country.Capital = rset.getString("city.Name");
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
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                            + "FROM country "
                            + "JOIN city ON "
                            + "country.Code=city.CountryCode "
                            + "WHERE city.ID = country.Capital "
                            + "AND country.Continent = '" + continent + "'"
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
                country.Capital = rset.getString("city.Name");
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
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                            + "FROM country "
                            + "JOIN city ON "
                            + "country.Code=city.CountryCode "
                            + "WHERE city.ID = country.Capital "
                            + "AND country.Region = '" + region + "'"
                            + " ORDER BY country.Population DESC"
                            + " LIMIT " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                Country country = new Country();
                country.Code = rset.getString("country.Code");
                country.Name = rset.getString("country.Name");
                country.Continent = rset.getString("country.Continent");
                country.Region = rset.getString("country.Region");
                country.Population = rset.getInt("country.Population");
                country.Capital = rset.getString("city.Name");
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
                    "SELECT city.Name, city.CountryCode, city.District, city.Population"
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
                    "SELECT city.Name, city.CountryCode, city.District, city.Population"
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
    public ArrayList<City> getCityReport7(@RequestParam(value = "continent") String continent)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Continent, city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM country"
                            + " JOIN city ON"
                            + " country.Code = city.CountryCode "
                            + " WHERE country.Continent = '" + continent + "'"
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

    //City Report 8
    @RequestMapping("cityRep8")
    public ArrayList<City> getCityReport8(@RequestParam(value = "region") String region)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Region, city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM country"
                            + " JOIN city ON"
                            + " country.Code = city.CountryCode "
                            + " WHERE country.Region = '" + region + "'"
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

    //City Report 9
    @RequestMapping("cityRep9")
    public ArrayList<City> getCityReport9(@RequestParam(value = "continent") String continent,
                                          @RequestParam(value = "limit") int limit)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Continent, city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM country"
                            + " JOIN city ON"
                            + " country.Code = city.CountryCode "
                            + " WHERE country.Continent = '" + continent + "'"
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
    public ArrayList<City> getCityReport10(@RequestParam(value = "region") String region,
                                           @RequestParam(value = "limit") int limit)
    {
        try
        {
            ArrayList<City> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Region, city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM country"
                            + " JOIN city ON"
                            + " country.Code = city.CountryCode "
                            + " WHERE country.Region = '" + region + "'"
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

    @RequestMapping("capitalRep1")
    public ArrayList<CapitalCity> getCapitalRep1(@RequestParam(value = "region") String region,
                                           @RequestParam(value = "limit") int limit)
    {
        try
        {
            ArrayList<CapitalCity> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name, country.Region, city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM country"
                            + " JOIN city ON"
                            + " country.Code = city.CountryCode "
                            + " WHERE city.ID = country.Capital"
                            + " AND country.Region = '" + region + "'"
                            + " ORDER BY city.Population DESC"
                            + " LIMIT " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                CapitalCity city = new CapitalCity();
                city.Name = rset.getString("city.Name");
                city.CountryName = rset.getString("country.Name");
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
    //Capital City Report 2
    @RequestMapping("capitalRep2")
    public ArrayList<CapitalCity> getCapitalCityReport2()
    {
        try
        {
            ArrayList<CapitalCity> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population"
                            + " FROM city"
                            + " JOIN country"
                            + " ON country.Code = city.CountryCode "
                            + " WHERE city.ID = country.Capital"
                            + " ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                CapitalCity capital = new CapitalCity();
                capital.Name = rset.getString("Name");
                capital.CountryName = rset.getString("country.Name");
                capital.Population = rset.getInt("Population");
                lst.add(capital);
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
    public ArrayList<CapitalCity> getCapitalCityReport3(@RequestParam(value = "continent") String continent)
    {
        try
        {
            ArrayList<CapitalCity> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Continent, city.Name, city.CountryCode, city.District, city.Population, country.Name"
                            + " FROM country"
                            + " JOIN city ON"
                            + " country.Code = city.CountryCode "
                            + " WHERE city.ID = country.Capital"
                            + " AND country.Continent = '" + continent + "'"
                            + " ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                CapitalCity capital = new CapitalCity();
                capital.Name = rset.getString("city.Name");
                capital.CountryName = rset.getString("country.Name");
                capital.Population = rset.getInt("Population");
                lst.add(capital);
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
    public ArrayList<CapitalCity> getCapitalCityReport4(@RequestParam(value = "region") String region)
    {
        try
        {
            ArrayList<CapitalCity> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name, country.Region, city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM country"
                            + " JOIN city ON"
                            + " country.Code = city.CountryCode "
                            + " WHERE city.ID = country.Capital"
                            + " AND country.Region = '" + region + "'"
                            + " ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                CapitalCity capital = new CapitalCity();
                capital.Name = rset.getString("city.Name");
                capital.CountryName = rset.getString("country.Name");
                capital.Population = rset.getInt("Population");
                lst.add(capital);
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
    public ArrayList<CapitalCity> getCapitalCityReport3(@RequestParam(value = "continent") String continent,
                                                        @RequestParam(value = "limit") int limit)
    {
        try
        {
            ArrayList<CapitalCity> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name, country.Continent, city.Name, city.CountryCode, city.District, city.Population"
                            + " FROM country"
                            + " JOIN city ON"
                            + " country.Code = city.CountryCode "
                            + " WHERE city.ID = country.Capital"
                            + " AND country.Continent = '" + continent + "'"
                            + " LIMIT " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                CapitalCity capital = new CapitalCity();
                capital.Name = rset.getString("city.Name");
                capital.CountryName = rset.getString("country.Name");
                capital.Population = rset.getInt("Population");
                lst.add(capital);
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
    public ArrayList<CapitalCity> getCapitalCityReport6(@RequestParam(value = "limit") int limit)
    {
        try
        {
            ArrayList<CapitalCity> lst = new ArrayList<>();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            + " FROM city "
                            + " JOIN country "
                            + " ON country.Code = city.CountryCode "
                            + " WHERE city.ID = country.Capital"
                            + " ORDER BY city.Population DESC"
                            + " LIMIT " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                CapitalCity capital = new CapitalCity();
                capital.Name = rset.getString("Name");
                capital.CountryName = rset.getString("country.Name");
                capital.Population = rset.getInt("Population");
                lst.add(capital);
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

    @RequestMapping("popRep1")
    public ArrayList<PopulationContinent> getPopReport1(@RequestParam(value = "continent") String continent)
    {
        try
        {
            ArrayList<PopulationContinent> lst = new ArrayList<>();

            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // WHERE country.Code =city.CountryCode AND country.Name = '" + "China" + "'
            // WHERE country.Continent = '" + continent + "'
            String strSelect =
                    "SELECT SUM(city.Population) as cityPop, country.Population as totalPop, country.Name"
                            + " FROM city "
                            + " JOIN country "
                            + " ON country.Code = city.CountryCode"
                            + " WHERE country.Code =city.CountryCode"
                            + " AND country.Code = '" + continent + "'"
                            + " AND city.CountryCode = '" + continent + "'";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                PopulationContinent pop = new PopulationContinent();
                pop.populationCity = rset.getLong("cityPop");
                //pop.populationNotCity = rset.getInt("noneCity");
                pop.populationNotCity =rset.getLong("noneCity");
                lst.add(pop);
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

    @RequestMapping("popRep3")
    public ArrayList<PopulationRegion> getPopReport3(@RequestParam(value = "region") String region)
    {
        try
        {
            ArrayList<PopulationRegion> lst = new ArrayList<>();

            Statement stmt = con.createStatement();


            String strSelect =
                    "SELECT SUM(city.Population) as cityPop, SUM(country.Population), country.Region"
                            + " FROM city "
                            + " INNER JOIN country "
                            + " ON country.Code = city.CountryCode"
                            + " WHERE country.Code =city.CountryCode"
                            + " AND city.CountryCode =(select city.CountryCode from country join city on city.CountryCode=country.Code where city.CountryCode = coutnry.Code)"
                            + " AND country.Region = '" + region + "'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                PopulationRegion pop = new PopulationRegion();
                pop.RegionName = rset.getString("country.Region");
                pop.populationCity = rset.getFloat("cityPop");
                pop.populationTotal = rset.getFloat("totalPop");
                //pop.populationTotal = 123123123;

                pop.populationNotCity = pop.populationTotal - pop.populationCity;
                pop.populationCityPercentage= (pop.populationCity/pop.populationTotal*100);
                pop.populationNotCityPercentage = (100 - pop.populationCityPercentage);
                lst.add(pop);
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

    @RequestMapping("popRep2")
    public ArrayList<PopulationCountry> getPopReport2(@RequestParam(value = "code") String country)
    {
        try
        {
            ArrayList<PopulationCountry> lst = new ArrayList<>();

            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // WHERE country.Code =city.CountryCode AND country.Name = '" + "China" + "'
            // WHERE country.Continent = '" + continent + "'
            String strSelect =
                    "SELECT SUM(city.Population) as cityPop, country.Population as totalPop, country.Name"
                            + " FROM city "
                            + " JOIN country "
                            + " ON country.Code = city.CountryCode"
                            + " WHERE country.Code =city.CountryCode"
                            + " AND country.Code = '" + country + "'"
                            + " AND city.CountryCode = '" + country + "'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                PopulationCountry pop = new PopulationCountry();
                pop.CountryName = rset.getString("country.Name");
                pop.populationCity = rset.getFloat("cityPop");
                pop.populationTotal = rset.getFloat("totalPop");
                pop.populationNotCity = pop.populationTotal - pop.populationCity;
                pop.populationCityPercentage= (pop.populationCity/pop.populationTotal*100);
                pop.populationNotCityPercentage = (100 - pop.populationCityPercentage);
                lst.add(pop);
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
    // End of Population Reports

    //Organization reports
    @RequestMapping("worldPop")
    public WorldPop worldPop()
    {
        try{
            WorldPop totalpop= new WorldPop();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(country.Population) as WorldPop"
                            + " FROM country ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                totalpop.Population = rset.getFloat("WorldPop");

            }

            return totalpop;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    @RequestMapping("continentPop")
    public WorldPop continentPop(@RequestParam(value = "continent") String continent)
    {
        try{
            WorldPop totalpop= new WorldPop();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(country.Population) as continentPop"
                            + " FROM country "
                            + "WHERE country.Continent = '" + continent + "'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                totalpop.Population = rset.getFloat("continentPop");

            }

            return totalpop;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    @RequestMapping("regionPop")
    public WorldPop regionPop(@RequestParam(value = "region") String region)
    {
        try{
            WorldPop totalpop= new WorldPop();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(country.Population) as regionPop"
                            + " FROM country "
                            + "WHERE country.Region = '" + region + "'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                totalpop.Population = rset.getFloat("regionPop");

            }

            return totalpop;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    @RequestMapping("countryPop")
    public WorldPop countryPop(@RequestParam(value = "country") String country)
    {
        try{
            WorldPop totalpop= new WorldPop();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Population"
                            + " FROM country "
                            + "WHERE country.Name = '" + country + "'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                totalpop.Population = rset.getFloat("country.Population");

            }

            return totalpop;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    @RequestMapping("districtPop")
    public WorldPop districtPop(@RequestParam(value = "district") String district)
    {
        try{
            WorldPop totalpop= new WorldPop();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(city.Population) as districtPop"
                            + " FROM city "
                            + " WHERE city.District = '" + district + "'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                totalpop.Population = rset.getFloat("districtPop");

            }

            return totalpop;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    @RequestMapping("cityPop")
    public WorldPop cityPop(@RequestParam(value = "city") String city)
    {
        try{
            WorldPop totalpop= new WorldPop();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Population"
                            + " FROM city "
                            + " WHERE city.Name = '" + city + "'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                totalpop.Population = rset.getFloat("city.Population");

            }

            return totalpop;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }
}
