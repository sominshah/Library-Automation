package com.thinking.machines.library.dl;
import com.thinking.machines.nafClient.*; 
import com.thinking.machines.nafCommon.*;
import java.util.*;
public class AuthorDAO implements AuthorDAOInterface
{
private String server="localhost";
private int portNumber=3000;
private TMNAFClient client=new TMNAFClient(server,portNumber);
public void add(AuthorInterface authorInterface)throws DAOException
{
try
{
client.process("/author/add",authorInterface);
}catch(ApplicationException serverException)
{
System.out.println(serverException);
throw new DAOException("Server not responding or not ready,contact adminastrator");
}
}
public void update(AuthorInterface authorInterface)throws DAOException
{
try
{
client=new TMNAFClient(server,portNumber);
client.process("/author/update",authorInterface);
}catch(ApplicationException serverException)
{
System.out.println(serverException);
throw new DAOException("Server not responding or not ready,contact administrator");
}
}
public void delete(int code)throws DAOException
{
try
{
client=new TMNAFClient(server,portNumber);
client.process("/author/delete",code);
}catch(ApplicationException serverException)
{
System.out.println(serverException);
throw new DAOException("Server not responding or not ready,contact administrator");
}
}
public LinkedList<AuthorInterface> getAll() throws DAOException
{
try
{
client=new TMNAFClient(server,portNumber);
return (LinkedList<AuthorInterface>)client.process("/author/getAll"); 
}
catch(ApplicationException serverException)
{
System.out.println(serverException);
throw new DAOException("Server not responding or not ready,contact Administrator.");
}
}
public LinkedList<AuthorInterface> getAll(Author.ATTRIBUTE sortBy) throws DAOException
{
try
{
client=new TMNAFClient(server,portNumber);
return (LinkedList<AuthorInterface>)client.process("/author/getAll",sortBy);
}catch(ApplicationException serverException)
{
System.out.println(serverException);
throw new DAOException("Server not responding or not ready,contact Administrator.");
}
}
public AuthorInterface getByCode(int code)throws DAOException
{
try
{
client=new TMNAFClient(server,portNumber);
return (AuthorInterface)client.process("/author/getByCode",code);
}
catch(ApplicationException serverException)
{
System.out.println(serverException);
throw new DAOException("Server not responding or not ready,contact Administrator.");
}
}
public AuthorInterface getByName(String name)throws DAOException
{
try
{
client=new TMNAFClient(server,portNumber);
return (AuthorInterface)client.process("/author/getBYName",name);
}
catch(ApplicationException serverException)
{
System.out.println(serverException);
throw new DAOException("Server not responding or not ready,contact Administrator.");
}
}
public long getCount() throws DAOException
{
try
{
client=new TMNAFClient(server,portNumber);
return (long)client.process("/author/getCount");
}catch(ApplicationException serverException)
{
System.out.println(serverException);
throw new DAOException("Server not responding or not ready,contact Administrator.");
}
}

}