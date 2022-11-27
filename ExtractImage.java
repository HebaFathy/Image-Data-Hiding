import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ExtractImage extends JFrame
{
 public ExtractImage(int[][] indeces) throws IOException
 {
 	ReadImageData r=new ReadImageData("Covered.txt"); 	
 	
 	int[][] extracted_red=new int[64][64];
    int[][] extracted_green=new int[64][64];
    int[][] extracted_blue=new int[64][64];
 	int h=0; 	
 	
	for(int i=0;i<64;i++)
 	{
 	  for(int j=0;j<64;j++)
 	  {
 	  	extracted_red[i][j]=r.red[indeces[h][0]][indeces[h][1]];
 	  	extracted_green[i][j]=r.red[indeces[h][0]][indeces[h][1]];
 	  	extracted_blue[i][j]=r.red[indeces[h][0]][indeces[h][1]];
 	  	h++;  	
      }
    }
    
 	Container c2=getContentPane();
 	Show ss=new Show(extracted_red,extracted_green,extracted_blue);
 	c2.add(ss);
 	setTitle("Extracted Image");	  
 	setSize(250,200);
 	setVisible(true);
 }	

 
//==============================================main Method============================================// 	
 /*public static void main(String args[])
 {
 	try{
 	    WaterMarking watermark=new WaterMarking();
 	}
 	catch(Exception ex)
 	{}
 }*/  
}