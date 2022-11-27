import java.awt.*;
import java.io.*;
import java.awt.event.*;

import javax.swing.*;

public class Show extends JPanel
{

 int red[][];
 int green[][];
 int blue[][];
 
 int width;
 int hieght;
 	
 
 public Show(int r_matrix[][],int g_matrix[][],int b_matrix[][])
 {
  
  red=r_matrix;
  green=g_matrix;
  blue=b_matrix;	
 	
 	
 }
 
 public void paintComponent(Graphics g)
 {
  
  super.paintComponent(g);
  for(int h=red.length-1;h>0;h--)
    for(int w=0;w<red[0].length;w++)
     {
      
      if(red[h][w]>-1&&green[h][w]>-1&&blue[h][w]>-1)
      {
      	g.setColor(new Color(red[h][w],green[h][w],blue[h][w]));
        g.drawLine(w,h,w,h);
      }  
     	
     }
 	
 	
 } 
	
}
