import java.io.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;

import java.sql.*;

import java.io.IOException;
import java.io.*;



public class  AjaxUtility {
    StringBuffer sb = new StringBuffer();
    boolean namesAdded = false;
    static Connection conn = null;
    static String message;
    public static String getConnection()
    {

        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","xwwxgg144");
            message="Successfull";
            return message;
        }
        catch(SQLException e)
        {
            message="unsuccessful";
            return message;
        }
        catch(Exception e)
        {
            message="unsuccessful";
            return message;
        }
    }

    public  StringBuffer readdata(String searchId)
    {
        HashMap<String,Product> data;
        data=getData();

        Iterator it = data.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pi = (Map.Entry)it.next();
            if(pi!=null)
            {
                Product p=(Product)pi.getValue();
                if (p.getName().toLowerCase().startsWith(searchId))
                {
                    sb.append("<product>");
                    sb.append("<id>" + p.getId() + "</id>");
                    sb.append("<productName>" + p.getName() + "</productName>");
                    sb.append("</product>");
                }
            }
        }

        return sb;
    }

    public static HashMap<String,Product> getData()
    {
        HashMap<String,Product> hm=new HashMap<String,Product>();
        try
        {
            getConnection();

            String selectproduct="select * from  Productdetails";
            PreparedStatement pst = conn.prepareStatement(selectproduct);
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {	Product p = new Product(rs.getString("ProductType"),rs.getString("Id"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getString("ManufacturerRebate"),rs.getInt("inventory"));
                hm.put(rs.getString("Id"), p);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return hm;
    }
    public static void storeData(HashMap<String,Product> productdata)
    {
        try
        {

            getConnection();

            String insertIntoProductQuery = "INSERT INTO product(productType,productId,productName,productPrice,retailer,productCondition,discount) "
                    + "VALUES (?,?,?,?,?,?,?);";
            for(Map.Entry<String, Product> entry : productdata.entrySet())
            {

                PreparedStatement pst = conn.prepareStatement(insertIntoProductQuery);
                //set the parameter for each column and execute the prepared statement
                pst.setString(1,entry.getValue().getType());
                pst.setString(2,entry.getValue().getId());
                pst.setString(3,entry.getValue().getName());
                pst.setDouble(4,entry.getValue().getPrice());
                pst.setString(5,entry.getValue().getRetailer());
                pst.setString(6,entry.getValue().getCondition());
                pst.setDouble(7,entry.getValue().getDiscount());
                pst.execute();
            }
        }
        catch(Exception e)
        {

        }
    }

}
