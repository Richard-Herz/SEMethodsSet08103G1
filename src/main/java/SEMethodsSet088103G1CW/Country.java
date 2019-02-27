package SEMethodsSet088103G1CW;

import java.sql.*;

public class Country {

    private Connection con = null;

    public String Code;

    public String Name;

    public String Continent;

    public String Region;

    public float surfaceArea;

    public int indepYear;

    public int Population;

    public float lifeExpectancy;

    public float GNP;

    public float gnpOld;

    public String localName;

    public String governmentForm;

    public String headOfState;

    public String Capital;

    public String code2;

    public Country getName(String cName)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Capital, Population"
                            + "FROM country "
                            + "WHERE Name = " + cName;
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
}
