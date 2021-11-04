package com.mulesoft;
import java.util.*;
import java.sql.*;
public class FavouriteMovies {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			//Creating Connection and Database
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:favouritemovies.db");
			if(conn!=null)
			{
				Statement stmt = conn.createStatement();
				//creating table and inserting records into it
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS MOVIES(Movie_Name text primary key,Actor text, Actress text,Director text,Year_of_Release int)");
				PreparedStatement pstmt = conn.prepareStatement("insert into Movies values(?,?,?,?,?)");
				System.out.println("Enter no. of records:");
				int n = sc.nextInt();
				if(n>0){
					while(n>0)
					{
						System.out.println("Enter Movie Name:");
						String name = sc.next();
						System.out.println("Enter Actor Name:");
						String Actor = sc.next();
						System.out.println("Enter Actress Name:");
						String Actress = sc.next();
						System.out.println("Enter Director Name:");
						String Dir = sc.next();
						System.out.println("Enter Year of the Movie Released:");
						int Year = sc.nextInt();
						pstmt.setString(1,name);
						pstmt.setString(2,Actor);
						pstmt.setString(3,Actress);
						pstmt.setString(4,Dir);
						pstmt.setInt(5,Year);
						n--;
					}
					int c = pstmt.executeUpdate();
					if(c==1){
						System.out.println("Records inserted Successfully");
					}
				}
				//Retrieving records from table
				PreparedStatement pstmtr = conn.prepareStatement("select * from movies");
				ResultSet rs = pstmtr.executeQuery();
				System.out.println("Movie_Name\t\tActor\t\tActress\t\tDirector\tYear_of_Release");
				System.out.println("_________________________________________________________________________________________");
				while(rs.next())
				{
					System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t"+rs.getInt(5));
				}
				//getting required column data from table
				System.out.println("Enter the actor name to get the movies:");
				String an = sc.next();
				PreparedStatement pstmta = conn.prepareStatement("select * from movies where Actor='"+an+"'");
				ResultSet rs1 = pstmta.executeQuery();
				System.out.println("Movie_Name\tActor\tActress\t\tDirector\tYear_of_Release\t");
				System.out.println("_________________________________________________________________________");
				while(rs1.next())
				{
					System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3)+"\t"+rs1.getString(4)+"\t"+rs1.getInt(5));
				}
			}
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}