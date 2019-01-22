package com.thinking.machines.library.dl;
import java.util.*;
import java.sql.*;
public class AuthorDAO implements AuthorDAOInterface 
{
public void add(AuthorInterface authorInterface)throws DAOException
{
String name=authorInterface.getName();
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select 1 as XYZ from author where name=?");
preparedStatement.setString(1,name);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Author :"+name+"Exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into author (name) Values(?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,name);
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
authorInterface.setCode(resultSet.getInt(1));
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
System.out.println(name);
System.out.println(authorInterface.getCode());
System.out.println(exception);
throw new DAOException(exception.getMessage());
}
}
public void update(AuthorInterface authorInterface)throws DAOException
{
String name=authorInterface.getName();
int code=authorInterface.getCode();
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select 1 as XYZ from author where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Author Code:"+code);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select 1 as XYZ from author where name=? and code<>?");
preparedStatement.setString(1,name);
preparedStatement.setInt(2,code);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Author :"+name+"exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("Update author set name=? where code=?");
preparedStatement.setString(1,name);
preparedStatement.setInt(2,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
System.out.println(exception);
throw new DAOException(exception.getMessage());
}
}
public void delete(int code)throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select 1 as XYZ from author where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Author Code:"+code);
}
resultSet.close();
preparedStatement.close();
//leter you need to check if book exists against this author if yes,throw exception
preparedStatement=connection.prepareStatement("delete from author where code=?");
preparedStatement.setInt(1,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
System.out.println(exception);
throw new DAOException(exception.getMessage());
}
}
public LinkedList<AuthorInterface> getAll() throws DAOException
{
try
{
Connection connection;
connection=DriverManager.getConnection("jdbc:derby:c:/somin/library1/javadb/library/library");
Statement statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select * from author order by code");
AuthorInterface authorInterface;
LinkedList<AuthorInterface> authors;
authors=new LinkedList<AuthorInterface>();
while(resultSet.next())
{
authorInterface=new Author();
authorInterface.setCode(resultSet.getInt("code"));
authorInterface.setName(resultSet.getString("name").trim());
authors.add(authorInterface);
}
resultSet.close();
statement.close();
connection.close();
if(authors.size()==0)
{
throw new DAOException("No Authors");
}
return authors;
}catch(SQLException sqlException)
{
System.out.print(sqlException);
throw new DAOException(sqlException.getMessage());
}
}
public LinkedList<AuthorInterface> getAll(Author.ATTRIBUTE sortBy) throws DAOException
{
try
{
Connection connection;
connection=DriverManager.getConnection("jdbc:derby:c:/somin/library1/javadb/library/library");
Statement statement=connection.createStatement();
ResultSet resultSet;
if(sortBy==Author.Name)
{
resultSet=statement.executeQuery("select * from author order by name");
}
else
{
resultSet=statement.executeQuery("select * from author order by code");
}
AuthorInterface authorInterface;
LinkedList<AuthorInterface> authors;
authors=new LinkedList<AuthorInterface>();
while(resultSet.next())
{
authorInterface=new Author();
authorInterface.setCode(resultSet.getInt("code"));
authorInterface.setName(resultSet.getString("name").trim());
authors.add(authorInterface);
}
resultSet.close();
statement.close();
connection.close();
if(authors.size()==0)
{
throw new DAOException("No Authors");
}
return authors;
}catch(SQLException sqlException)
{
System.out.print(sqlException);
throw new DAOException(sqlException.getMessage());
}
}
public AuthorInterface getByCode(int code)throws DAOException
{
try{
Connection connection;
connection=DriverManager.getConnection("jdbc:derby:c:/somin/library1/javadb/library/library");
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select code from author where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid author code : "+code);
}
AuthorInterface authorInterface=new Author();
authorInterface.setCode(code);
authorInterface.setName(resultSet.getString("name").trim());
resultSet.close();
preparedStatement.close();
connection.close();
return authorInterface;
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException(sqlException.getMessage());
}
}
public AuthorInterface getByName(String name)throws DAOException
{
try{
Connection connection;
connection=DriverManager.getConnection("jdbc:derby:c:/somin/library1/javadb/library/library");
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select code from author where name=?");
preparedStatement.setString(1,name);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid author name : "+name);
}
AuthorInterface authorInterface=new Author();
authorInterface.setCode(resultSet.getInt("code"));
authorInterface.setName(name);
resultSet.close();
preparedStatement.close();
connection.close();
return authorInterface;
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException(sqlException.getMessage());
}
}
public long getCount() throws DAOException
{
try
{
Connection connection;
connection=DriverManager.getConnection("jdbc:derby:c:/somin/library1/javadb/library/library");
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery("Select count(*) as cnt from author");
resultSet.next();
long count=resultSet.getLong("cnt");
resultSet.close();
statement.close();
connection.close();
return count;
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException(sqlException.getMessage());
}
}
}