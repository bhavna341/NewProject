import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.sql.*;

class Splash_Screen extends JWindow
{

	JLabel lbl_img,lbl_title;
	JProgressBar pb;

	public Splash_Screen()
	{
		Container c=getContentPane();
		c.setLayout(new BorderLayout());
	
		lbl_img=new JLabel();
		lbl_title=new JLabel("Mobile Control",JLabel.CENTER);
		lbl_title.setFont(new Font("Serif", Font.BOLD, 40));
		pb=new JProgressBar(0,100);
		pb.setStringPainted(true);

		

		c.add(lbl_title,"North");
		c.add(lbl_img,"Center");
		c.add(pb,"South");
		
		getRootPane().setBorder(new EtchedBorder(EtchedBorder.RAISED));	
		
		setSize(350,300);
		setLocationRelativeTo(null);//to display the frame on center screen
		setVisible(true);
		
		for(int i=0;i<=100;i=i+5)
		{
			if(i<50)
			{
				pb.setString("Loading...   ");
			}			
			else if(i>50 && i<=80)
			{
				pb.setString("Initializing...  ");
			}
			else
			{
				pb.setString("Opening...  ");
			}
			pb.setValue(i);
			try
			{
				Thread.sleep(500);	
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		
		new MainPage();
		dispose();
	}
	public static void main(String args[])
	{
		new Splash_Screen();
	}
}	
		
		