package Connectors.Main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.reflect.Field;

public class Main {

	public static Connection connObj;
//	public static String JDBC_URL = "jdbc:sqlserver://localhost:1434;databaseName=TestDBs;integratedSecurity=true;";
	public static void main(String[] args) throws ClassNotFoundException {
		String ClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		//String IPAddr = "DESKTOP-LHLA0PA\\MSSQLSERVER01";
		String IPAddr = "DESKTOP-LHLA0PA\\SQLEXPRESS;";
		String DBName="Performance";
		String DB_URL = "jdbc:sqlserver://" + IPAddr + "DatabaseName=" + DBName + ";integratedSecurity=true" ;
		//Main connServer = new Main();
		dbConnect (DB_URL,ClassName);		
	}

	public static void dbConnect(String db_connect_string, String className) throws ClassNotFoundException
	{
		java.sql.Connection connection = null;
		System.out.println("Connecting to a selected database...");
		String libpath = System.getProperty("java.library.path");
		libpath = "C:\\Users\\soham\\Downloads\\sqljdbc_8.2\\enu\\auth\\x64\\;" +libpath;
		System.out.println("SQL server path"+libpath);
		System.setProperty("java.library.path",libpath);
		Field sysPathsField;
		try {
			sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
			sysPathsField.setAccessible(true);
			sysPathsField.set(null, null);
		} catch (NoSuchFieldException | SecurityException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			Class.forName(className);
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Native code library failed to load.\n" + e);
			System.exit(1);
		}
		try{
			connection = DriverManager.getConnection(db_connect_string);
			System.out.println("connected");
			Statement statement = connection.createStatement();
			String queryString = "select * from FuckTable ";
			
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	} // 

}
// ;instanceName=DESKTOP-LHLA0PA\\MSSQLSERVER01;
