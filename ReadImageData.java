import java.awt.*;
import java.io.*;
import java.awt.event.*;

import javax.swing.*;

public class ReadImageData extends JFrame
{
  
  int a=0;
  int b=0;
  int c=0;
  int d=0;
  
  int width=0;//width of picture
  int hieght=0;//hieght of picture
  int num=0;//number of bits per pixel 
  int count=0;
  int bytes_num=0;
  
  
  int red[][];
  int green[][];
  int blue[][];
  int Nmatrix[][]=new int[64*64][2];
  int m=0;
  
  
  public ReadImageData(String file)
  {  
  	 try
  	 {
  	
  	  DataInputStream input=new DataInputStream(new FileInputStream(file));
  	  input.skip(18);
  	  
  	  a=input.read();
	  b=input.read();
	  c=input.read();
	  d=input.read();           
	  width=(((d & 0xff) << 24) | ((c & 0xff) << 16) | ((b & 0xff) << 8) | (a & 0xff));
	
	  a=input.read();
	  b=input.read();
	  c=input.read();
	  d=input.read();      
	  hieght=(((d & 0xff) << 24) | ((c & 0xff) << 16)|  ((b & 0xff) << 8) | (a & 0xff));	  
	  input.skip(2);
      
      a=input.read();
      b=input.read();
      num=(((d & 0xff) << 24) | ((c & 0xff) << 16)|  ((b & 0xff) << 8) | (a & 0xff));       
	  input.close();
	  
	    
	  System.out.println("\nWidth  of Picture is : "+width);
      System.out.println("Height of Picture is : "+hieght);	
	  
	  //Check image width
	  bytes_num=(width*num)/8;	    
	  if(bytes_num%4!=0) 
      {	
        if((bytes_num+1)%4==0)
          count=1;
		else if ((bytes_num+2)%4==0) 
		  count=2;
		else if ((bytes_num+3)%4==0) 
		  count=3;
		else if ((bytes_num+4)%4==0) 
		  count=4;
	  }
	   
	  //Extract image colors	  	
	  input=new DataInputStream (new  FileInputStream(file)); 
	  input.skip(54);
	  
	  red=new int[hieght][width];
	  green=new int[hieght][width];	
	  blue=new int[hieght][width];
	  
	  for(int h=hieght-1;h>=0;h--)
	  { 
	   
	   for(int w=0;w<width;w++)
	   {
	   	m=m+3;
	   	blue[h][w]=input.read();
	   	green[h][w]=input.read();
	   	red[h][w]=input.read();	   	  
	   	
	    if(blue[h][w]<=-1)
           blue[h][w]=blue[w][h]*-1;   
        if(green[h][w]<=-1)
           green[h][w]=green[w][h]*-1;   
        if(red[h][w]<=-1)
           red[h][w]=red[w][h]*-1;           	   	
	   } 
	   
	   input.skip(count);
	   m=m+count;
	  }
       
      input.close(); 
	}//try
	
 catch(FileNotFoundException e)
 {
  System.out.println("File Not Found");	
 }
 catch(IOException e)
 {
  System.out.println("Error");		
 }	
 
 	
}

/*public int[][] extract_matrix(String file)
{

   try
   {	
    DataInputStream input2=new DataInputStream (new  FileInputStream(file)); 
	input2.skip(54+m);
	  System.out.println(m);
	
	
	for(int i=0;i<Nmatrix.length;i++)
     for(int j=0;j<Nmatrix[0].length;j++)          	
	  Nmatrix[i][j]=input2.read();
	  
	  

	
	input2.close();  
   }
   catch(Exception ex){ ex.printStackTrace(); ex.getMessage();}	
   
   return Nmatrix;
}*/	
	
	
	
	
}