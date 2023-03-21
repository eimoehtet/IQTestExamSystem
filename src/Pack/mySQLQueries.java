package Pack;

import Pack.DBConnection;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.Stack;

public class mySQLQueries {
	static Connection con = null;
	static Statement stmt;
	static String query1, query2;
	ResultSet rs;

	DBConnection connect = new DBConnection();

	public mySQLQueries() {
		con = connect.GetMySQLConnection();
	}


		
	public String getAutoID(String field, String table, String prefix) {
		  if(table.equals("Question") || table.equals("Purchase"))
              return connect.getPrimaryKey1(field, table, prefix);
          else
              return connect.getPrimaryKey2(field, table, prefix);
	}

	public boolean isduplicate(String tbName, String[] data) {
		if (tbName.equals("Student"))
			query1 = "Select * From Student Where studentId='" + data[0] + "' Or nrcNo='" + data[1] + "' Or email='"
					+ data[2] + "' Or phone='" + data[3] + "'";
	        else if(tbName.equals("QuestionType"))
	            query1="Select * From QuestionType Where qtId='" + data[0] + "'";
	       
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query1);
			// rs=BR-0000001,Dell
			if (rs.next())
				return true;
			else
				return false;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "SQL Exception", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public boolean insertData(String tblName, String[] data) {
		
		if (tblName.equals("Student"))
			query1 = "Insert Into Student Values('" + data[0] + "','" + data[1] + "','" + data[2] + "','" + data[3]
					+ "','" + data[4] + "','" + data[5] + "')";
		 else if(tblName.equals("QuestionType"))
	            query1="Insert Into QuestionType Values('" + data[0] + "','" + data[1] + "')";
		 else if(tblName.equals("Question"))
	            query1="Insert Into Question Values('" + data[0] + "','" + data[1] + "','" + data[2] + "','" + data[3] + "','" + data[4] + "','" + data[5] + "','"+data[6]+"','"+data[7]+"')";
		try {
			stmt = con.createStatement();
			if (stmt.executeUpdate(query1) == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "SQL Exception", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

    public String[] getIDForChoice(String tbName)
    {
        Stack st=new Stack();
        try
        {
            if(tbName.equals("QuestionType"))
                rs=connect.SQLSelect("qtId", "QuestionType");
            if(tbName.equals("Merchandise"))
               rs=connect.SQLSelect("MerID", "Merchandise");
            if(tbName.equals("Supplier"))
                rs=connect.SQLSelect("SupplierID", "Supplier");
            if(tbName.equals("ItemDetail"))
                rs=connect.SQLSelect("ItemID", "ItemDetail");
            if(tbName.equals("Customer"))               rs=connect.SQLSelect("CustomerID", "Customer");



            int rowcount=0;
            while(rs.next())
            {
                rowcount++;
                st.add(rs.getString(1));
            }
            String[] temp=new String[rowcount];
            for(int i=rowcount-1;i>=0;i--)            {
                temp[i]=st.pop().toString();
            }
            return temp;
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
            return null;
       }
    }

    public String[] getNameForChoice(String tblName)
    {
        Stack st=new Stack();
        try
        {
            if(tblName.equals("QuestionType"))
                rs=connect.SQLSelect("qtName", "QuestionType");
            if(tblName.equals("Brand"))
                rs=connect.SQLSelect("BrandName", "Brand");
            if(tblName.equals("CustName"))
                rs=connect.SQLSelect("Name", "Customer");
            if(tblName.equals("CustAdd"))
                rs=connect.SQLSelect("Address", "Customer");
            if(tblName.equals("CustPh"))
                rs=connect.SQLSelect("PhoneNo", "Customer");
            if(tblName.equals("CustEmail"))
                rs=connect.SQLSelect("Email", "Customer");
            if(tblName.equals("SupplierName"))
                rs=connect.SQLSelect("Name", "Supplier");
            if(tblName.equals("SupplierAdd"))
                rs=connect.SQLSelect("Address", "Supplier");
            if(tblName.equals("SupplierPh"))
                rs=connect.SQLSelect("PhoneNo", "Supplier");
            if(tblName.equals("SupplierEmail"))
                rs=connect.SQLSelect("Email", "Supplier");


            int rowcount=0;
            while(rs.next())
            {
                rowcount++;
                st.add(rs.getString(1));
            }
            String[] temp=new String[rowcount];
            for(int i=rowcount-1;i>=0;i--)
            {
                temp[i]=st.pop().toString();
            }
            return temp;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }
    
    public String GetTypeID(String TypeName)
    {
        try
        {
            String TypeID;
            stmt=con.createStatement();
            query1="Select qtId From QuestionType Where qtName='"+ TypeName +"'";
            rs=stmt.executeQuery(query1);
            rs.next();
            TypeID=rs.getString(1);
            return TypeID;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;

    }

    public boolean updateRecord(String tblName,String id,String[] data)
    {
        if(tblName.equals("Student"))
            query1="Update Student Set studentName='"+ data[0] +"',nrcNo='"+ data[1] +"',email='"+ data[2] +"',phone='"+ data[3]+"',address='"+ data[4] +"' Where studentId='"+ id +"'";
        else if(tblName.equals("Customer"))
            query1="Update Customer Set Name='"+ data[0] +"',Address='"+ data[1] +"',PhoneNo='"+ data[2] +"',Email='"+ data[3] +"' Where CustomerID='"+ id +"'";
        else if(tblName.equals("itemdetail"))
            query1="Update ItemDetail Set itemname='" + data[0] + "',cursaleprice='" + data[1] + "',remark='" + data[2] + "' Where itemid='"+ id +"'";
        try
        {
            stmt=con.createStatement();
            int count=stmt.executeUpdate(query1);
            if(count==1)
                return true;
            else
                return false;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
//
//    public void deleteRecord(String tblName,String id)
//    {
//        int returnvalue=0;
//
//        if(tblName.equals("Supplier"))
//            query1="Delete From Supplier Where SupplierID='"+ id +"'";
//        else if(tblName.equals("Customer"))
//            query1="Delete From Customer Where CustomerID='"+ id +"'";
//
//        try
//        {
//            stmt=con.createStatement();
//            returnvalue=stmt.executeUpdate(query1);
//            if(returnvalue>0)
//                JOptionPane.showMessageDialog(null, "The record is deleted successfully in " + tblName + " table");
//        }
//        catch(Exception e)
//        {
//            JOptionPane.showMessageDialog(null, e.toString());
//        }
//
//    }
}
