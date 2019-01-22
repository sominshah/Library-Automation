package com.thinking.machines.library.server;
import java.util.*;
import com.thinking.machines.library.dl.*;
class ApplicationProcessor
{
public static String process(String request)
{
String splits[]=request.split(":");
String entity=splits[0];
String operation=splits[1];
if(entity.equals("AUTHOR"))
{
	AuthorDAOInterface authorDAOInterface=new AuthorDAO();
	if(operation.equals("ADD"))
	{
		try
		{
		AuthorInterface authorInterface=new Author();
		authorInterface.setName(splits[2]);
		authorDAOInterface.add(authorInterface);
		return "true:"+authorInterface.getCode();
		}catch(DAOException daoException)
		{
		return "false:"+daoException.getMessage();
		}

	}
	if(operation.equals("UPDATE"))
	{
		try
		{
		AuthorInterface authorInterface=new Author();
		authorInterface.setCode(Integer.parseInt(splits[2]));
		authorInterface.setName(splits[3]);
		authorDAOInterface.update(authorInterface);
		return "true";
		}catch(DAOException daoException)
		{
		return "false:"+daoException.getMessage();
		}
        }
	if(operation.equals("DELETE"))
	{
		try
		{
		authorDAOInterface.delete(Integer.parseInt(splits[2]));
		return "true";
		}catch(DAOException daoException)
		{
		return "false"+daoException.getMessage();
		}
	}
	if(operation.equals("GET_ALL_SORTED_BY"))
	{
		try
		{
		String sortOn=splits[2];
		LinkedList<AuthorInterface> authors;
			if(sortOn.equals("NAME"))
			{
		authors=authorDAOInterface.getAll(Author.Name);
			}
			else
			{
			authors=authorDAOInterface.getAll(Author.Code);
			}
		StringBuffer sb=new StringBuffer();
		sb.append("true:");
		boolean applyColon=false;
		for(AuthorInterface author:authors)
			{
			if(applyColon)sb.append(":");
			sb.append(String.valueOf(author.getCode()));
			sb.append(":");
			sb.append(author.getName());
			applyColon=true;
			}
		return sb.toString();
		}catch(DAOException daoException)
		{
		return "false"+daoException.getMessage();
		}
	}
	if(operation.equals("GET_ALL"))
	{
		try
		{
		LinkedList<AuthorInterface> authors;
		authors=authorDAOInterface.getAll();
		StringBuffer sb=new StringBuffer();
		sb.append("true:");
		boolean applyColon=false;
		for(AuthorInterface author:authors)
		{
		if(applyColon)sb.append(":");
		sb.append(String.valueOf(author.getCode()));
		sb.append(":");
		sb.append(author.getName());
		applyColon=true;
		}
		return sb.toString();
		}catch(DAOException daoException)
		{
		return "false:"+daoException.getMessage();
		}
	}
	if(operation.equals("GET_BY_CODE"))
	{
		try
		{
		AuthorInterface authorInterface;
		authorInterface=authorDAOInterface.getByCode(Integer.parseInt(splits[2]));
		return "true:"+authorInterface.getCode()+":"+authorInterface.getName();
		}catch(DAOException daoException)
		{
		return "false:"+daoException.getMessage();
		}
	}
	if(operation.equals("GET_BY_NAME"))
	{
		try
		{
		AuthorInterface authorInterface;
		authorInterface=authorDAOInterface.getByName(splits[2]);
		return "true:"+authorInterface.getCode()+":"+authorInterface.getName();
		}catch(DAOException daoException)
		{
		return "false:"+daoException.getMessage();
		}
	}
	try
	{
	return "true:"+authorDAOInterface.getCount();
	}catch(DAOException daoException)
	{
	return "false:"+daoException.getMessage();
	}
}
return " ";
}
}