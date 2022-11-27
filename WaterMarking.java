import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class WaterMarking extends JFrame implements ActionListener
{
 Container c; 
 JPanel button=new JPanel(); 
 JPanel buttonhd=new JPanel(); 
 JButton ext=new JButton("Extract");
 JButton hd=new JButton("Hide");
 JFrame wa=new JFrame();	
 Container cw=wa.getContentPane();
      
 ReadImageData r1;
 ReadImageData r2;
 int indeces[][]=new int[64*64][2];
 
 public WaterMarking() throws IOException
 {
	  
	  c=getContentPane();
	  buttonhd.add(hd);	 
 	  r1=new ReadImageData("Cover.bmp");
 	  r2=new ReadImageData("w.bmp");
 	   	  
 	  Show s1=new Show(r1.red,r1.green,r1.blue); 	  
 	  Show s2=new Show(r2.red,r2.green,r2.blue);
 	   	  
 	  ext.addActionListener(this);	  
 	  hd.addActionListener(this);	  
 
 	  c.add(s1,BorderLayout.CENTER);
 	  c.add(buttonhd,BorderLayout.SOUTH);
 	  
 	 
 	  setTitle("Cover Images");	  
 	  setSize(512,512);
 	  setVisible(true);
 	  
 	  
 	    
      Show w=new Show(r2.red,r2.green,r2.blue);      
      wa.getContentPane().add(w);
      wa.getContentPane().add(button,BorderLayout.SOUTH);      
      wa.setTitle("Water Image");
      wa.setLocation(800,0);   
      wa.setSize(200,100);
      wa.setVisible(true);
 	   	  
 	  
 	  //hide(r1,r2);
 	  //ExtractImage ei=new ExtractImage(r1,indeces);
 }
 
 //==============================================Hide Function==========================================//
 
 public void hide(ReadImageData r1,ReadImageData r2) throws FileNotFoundException , IOException 
 {
 	
 	DataInputStream input=new DataInputStream(new FileInputStream ("Cover.bmp"));
 	DataOutputStream output=new DataOutputStream(new FileOutputStream("Heddin.bmp"));
 	
 	int flag=0;
 	int counter_width=0;
 	int counter_height=0;
 	int count_indeces=0;
 	
//////////////////////////////////////////////////////////////////////////////////////////////// 	
 	//copy image header
    for(int i=0;i<1078;i++)
    {  	
      output.write(input.read()); 
    }
 	 	
 	for(int i=0;i<r1.red.length;i+=8) 
 	 for(int j=0;j<r1.red[0].length;j+=8)
 	 { 
       flag=0;
       for(int x=i;x<i+8;x++)
       {
       	  
        for(int y=j;y<j+8;y++)
        {
           
           if(((int)Math.abs(r1.red[x][y]-r2.red[counter_height][counter_width])<=15)/*&&((int)Math.abs(r1.green[x][y]-r2.green[counter_height][counter_width])<=100)&&((int)Math.abs(r1.blue[x][y]-r2.blue[counter_height][counter_width])<=50)*/)
           {
      	  
 	         r1.red[x][y]=r2.red[counter_height][counter_width];

 	   	     indeces[count_indeces][0]=x;
 	   	     indeces[count_indeces][1]=y;
 	         flag=1;
 	         break;
 	         	         
 	       } 	      
 	      
 	      if(flag==0)  
 	      {
 	        r1.red[i+4][j+4]=r2.red[counter_height][counter_width];	  	
 	   	   
 	   	    indeces[count_indeces][0]=i+4;
 	   	    indeces[count_indeces][1]=j+4;
 	   	    break;
 	   	    	   	   
 	      } 	        
 	    }
 	    
 	   }
 	    	    
 	    count_indeces++; 	   	  
 	    counter_width++;
 	    if(counter_width==64)
 	    {
 	      counter_width=0;
 	      counter_height++;
 	    }   	  
     
     
      }
      
      
    for(int i=0;i<r1.red.length;i++) 
 	 for(int j=0;j<r1.red[0].length;j++)
      {
      	 output.writeByte(r1.red[i][j]);
 	   	 output.writeByte(r1.blue[i][j]); 	   	  	
 	   	 output.writeByte(r1.green[i][j]); 
 	  }	 
 	   	  

///////////////////////////////////////////////
///////////////////////////////////////////////
//put this cover_image in afile 
 /* try
  {  	
   DataInputStream input1=new DataInputStream(new FileInputStream("Cover.bmp"));
   DataOutputStream newimage=new DataOutputStream(new FileOutputStream("Covered.txt"));     
   
   for(int i=0;i<input1.available();i++)
     newimage.write(input1.read());

   for(int i=0;i<indeces.length;i++)
    for(int j=0;j<indeces[0].length;j++)
      newimage.write(indeces[i][j]);    

      
   input1.close();
   newimage.close();

  }
  catch(Exception ex)
  {  ex.printStackTrace(); ex.getMessage(); }*/
 
 ///////////////////////////////////////////////////////////////////////////////////////////  
   JFrame display=new JFrame();	
   Container container=display.getContentPane();     
   Show m=new Show(r1.red,r1.green,r1.blue);
   button.add(ext);
   container.add(m);
   container.add(button,BorderLayout.SOUTH);      
   display.setTitle("Watermarking");
   display.setLocation(500,100);   
   display.setSize(512,512);
   display.setVisible(true);
   
   
 }//end hide

//==============================================ActionPrerformed Method================================// 
public void actionPerformed(ActionEvent e)
{
	
 if(e.getSource()==hd)
 {
  
  try{
  	
  hide(r1,r2);
  setVisible(false);	
  wa.setVisible(false);
 }
 
  catch(Exception ec)
  {}
 }
 
 if(e.getSource()==ext)
 {
  try
  {
   ExtractImage ei=new ExtractImage(indeces);
  } catch(Exception ex){ ex.printStackTrace(); ex.getMessage();}
   
   setVisible(false);	
   wa.setVisible(false);	   
 }
 	
	
}	
//==============================================main Method============================================// 	
 public static void main(String args[])
 {
 	try{
 	    WaterMarking watermark=new WaterMarking();
 	}
 	catch(Exception ex)
 	{}
 }
}