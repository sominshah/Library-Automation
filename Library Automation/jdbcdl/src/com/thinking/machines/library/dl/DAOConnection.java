package com.thinking.machines.library.dl;
import java.io.*;
import java.sql.*;
public class DAOConnection
{
private String connectionString=" ";
DAOConnection()
{
try
{
String connectionString=" ";
File dataBaseConfigurationFile=new File("dataBaseConfigurationFile.cnfg");
if(dataBaseConfigurationFile.exists()==false)
{
throw new DataBaseConfigurationException("Data Base Configuration File not Exists");
}
RandomAccessFile randomAccessFilePointer=new RandomAccessFile(dataBaseConfigurationFile,"rw");
connectionString=randomAccessFilePointer.readLine();
if(randomAccessFilePointer.length()==0)
{
throw new DataBaseConfigurationException("Data Base Configuration File is Empty");
}
this.connectionString=connectionString;
if(this.connectionString==" ")
{
throw new DataBaseConfigurationException("Data Base Configuration is not Stablished.The Data Base Configuration File May Be Empty.");
}
randomAccessFilePointer.close();
}catch(Exception e)
{
System.out.println(e);
}

}
public static Connection getConnection()
{
Connection connection=null;
try
{
DAOConnection daoConnection=new DAOConnection();
connection=DriverManager.getConnection(daoConnection.connectionString);
return connection;
}catch(SQLException sqle)
{
System.out.print(sqle);
}
return connection;
}
}

