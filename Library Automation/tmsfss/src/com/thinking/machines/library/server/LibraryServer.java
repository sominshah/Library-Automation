package com.thinking.machines.library.server;
import com.thinking.machines.nafServer.*;
import com.thinking.machines.nafCommon.*;
public class LibraryServer
{

public static void main(String gg[])
{
int portNumber=3000;
TMNAFServer tmServerApplication=new TMNAFServer();
tmServerApplication.startServer(3000);
}
}