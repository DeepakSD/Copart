package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReadFromDataBase {
	

	 public static ArrayList<Integer> getYardNumber(String userVehicleType, int cId, int zipCode) throws SQLException{
		 	
	        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	        String USER = "SYSTEM";
	        String PASS = "ashwin";

	        ArrayList<Integer> yardList = new ArrayList<>();
	        try {
	              Class.forName("oracle.jdbc.driver.OracleDriver");
	              Connection con = DriverManager.getConnection(URL, USER, PASS);
	              
	              Statement stmt=con.createStatement();  
	              ResultSet rs=stmt.executeQuery("select * from auction");  
	              while(rs.next())  {
	            	  String vehicleType = rs.getString(1);
	            	  String seller = rs.getString(2);
	            	  String outOfState = rs.getString(3);
	            	  String yard = rs.getString(4);
	            	  
	            	  if(userVehicleType.equalsIgnoreCase(vehicleType) && cId == Integer.parseInt(seller) ){
	            		  if(Integer.parseInt(outOfState) == -1){
	            			  yardList.add(-1);
	            		  }else if(Integer.parseInt(outOfState) == 1){
	            			  if(yard.length() > 0 && yard != null){
	            				  yardList.add(Integer.parseInt(yard));
	            			  }else{
	            			  yardList.add(1);
	            			  }
	            		  }else{
	            			  yardList.add(Integer.parseInt(yard));
	            		  }
	            	  }
	              }  
	              con.close();  
	              
	            }
	              catch(ClassNotFoundException ex) {
	              System.out.println("Error: unable to load driver class!");
	               System.exit(1);
	        }
	        
	        return yardList;
	    }


}
