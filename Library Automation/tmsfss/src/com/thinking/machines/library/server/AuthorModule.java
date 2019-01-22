package com.thinking.machines.library.server;
import com.thinking.machines.library.dl.*;
import com.thinking.machines.nafServer.annotation.*;
import java.util.*;
@Path("/author")
public class AuthorModule
{
@Path("/add")
public void add(AuthorInterface authorInterface)throws DAOException
{
new AuthorDAO().add(authorInterface);
}
@Path("/update")
public void update(AuthorInterface authorInterface)throws DAOException
{
new AuthorDAO().update(authorInterface);
}
@Path("/delete")
public void delete(int code)throws DAOException
{
new AuthorDAO().delete(code);
}
@Path("/getAll")
public LinkedList<AuthorInterface> getAll() throws DAOException
{
return new AuthorDAO().getAll();
}
@Path("/getAllOrderBy")
public LinkedList<AuthorInterface> getAll(Author.ATTRIBUTE sortBy) throws DAOException
{
return new AuthorDAO().getAll(sortBy);
}
@Path("/getByCode")
public AuthorInterface getByCode(int code)throws DAOException
{
return new AuthorDAO().getByCode(code);
}
@Path("/getBYName")
public AuthorInterface getByName(String name)throws DAOException
{
return new AuthorDAO().getByName(name);
}
@Path("/getCount")
public long getCount() throws DAOException
{
return new AuthorDAO().getCount();
}
}