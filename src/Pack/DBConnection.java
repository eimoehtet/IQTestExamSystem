package Pack;

import java.sql.Connection;
import java.sql.*;
import java.util.*;

public class DBConnection
{
    public static Connection con=null;
    String url;

    public static void main(String args[])
    {
        GetMySQLConnection();
    }

    public static Connection GetMySQLConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examsystem?user=root&password=root");
            System.out.println("Connection with MySQL Successful");
        }
        catch(ClassNotFoundException cnfx)
        {
            cnfx.printStackTrace();
            System.out.println(cnfx);
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
            System.out.println(sqle);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
            System.out.println(exp);
        }
        return con;
    }

    public ResultSet SQLSelect(String field,String table)
    {
        ResultSet rs=null;
        try
        {
            Statement stmt=GetMySQLConnection().createStatement();
            rs=stmt.executeQuery("Select Distinct " + field + " From " + table);
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        return rs;
    }
  
    public String getPrimaryKey1(String field,String table,String prefix)
    {
        ResultSet rs=SQLSelect(field, table);
        int current;
        try
        {
            ArrayList result=new ArrayList();
            while(rs.next())
            {
                result.add(rs.getString(field));
            }
            if(result.size()>0)
            {
                current=Integer.parseInt(result.get(result.size()-1).toString().substring(2,10))+1;
                if(current>0 && current<=9)
                    return prefix + "0000000" + current;
                else if(current>9 && current<=99)
                    return prefix + "000000" + current;
                else if(current>99 && current<=999)
                    return prefix + "00000" + current;
                else if(current>999 && current<=9999)
                    return prefix + "0000" + current;
                else if(current>9999 && current<=99999)
                    return prefix + "000" + current;
                else if(current>99999 && current<=999999)
                    return prefix + "00" + current;
                else if(current>999999 && current<=9999999)
                    return prefix + "0" + current;
                else if(current>9999999 && current<=99999999)
                    return prefix + current;
            }
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        return prefix + "00000001";
    }
    public String getPrimaryKey2(String field,String table,String prefix)
    {
        ResultSet rs=SQLSelect(field, table);
        int current;
        try
        {
            ArrayList result=new ArrayList();
            //BR-0000001,BR-0000002,......,BR-0000006
            while(rs.next())
            {
                result.add(rs.getString(field));
            }
            if(result.size()>0)
            {
                current=Integer.parseInt(result.get(result.size()-1).toString().substring(3,10))+1;
                if(current>0 && current<=9)
                    return prefix + "000000" + current;
                //BR-0000007
                else if(current>9 && current<=99) 
                    return prefix + "00000" + current;
                else if(current>99 && current<=999)
                    return prefix + "0000" + current;
                else if(current>999 && current<=9999)
                    return prefix + "000" + current;
                else if(current>9999 && current<=99999)
                    return prefix + "00" + current;
                else if(current>99999 && current<=999999)
                    return prefix + "0" + current;
                else if(current>999999 && current<=9999999)
                    return prefix + current;                    
            }
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        return prefix + "0000001";
    }
    
}
