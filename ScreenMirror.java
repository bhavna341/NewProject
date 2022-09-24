import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.List;

class ScreenMirror extends JFrame 
{

	
	JLabel l1;
	Process precord;
	//InputStream is;
	//BufferedImage bf,bi;
	//Graphics2D graphics;
	public ScreenMirror()
	{
		setLayout(new BorderLayout());

		

		l1=new JLabel();
		
		add(l1);
		
		 l1.setHorizontalAlignment(JLabel.CENTER);
		
		

		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(500,500);
		setResizable(false);
		setVisible(true);
		try
		{
			/*while(true)
			{
			System.out.println("abc");
			Process precord = Runtime.getRuntime().exec("adb exec-out screencap -p");
			InputStream is = precord.getInputStream();
			//Image image = ImageIO.read(is);
			BufferedImage bf = ImageIO.read(is);
			BufferedImage bi=new BufferedImage(200,600,bf.getType());
			Graphics graphics = bi.createGraphics();
			graphics.drawImage(bf, 0, 0,200,600, null);
			graphics.dispose();		
			System.out.println("xyz");	
			Thread.sleep(5000);
			l1.setIcon(new ImageIcon(bi));
			System.out.println("pqr");
			precord.destroy();
			}*/
			new AnswerWorker().execute();
			
			
		}
		catch (Exception e) 
		{
        		System.out.println(e);
    		}
	}
	class AnswerWorker extends SwingWorker<Integer,Integer>
	{
		BufferedImage bi;
		int i=0;
    		protected Integer doInBackground() throws Exception
    		{
			while(i<=50000)
			{
        		Process precord = Runtime.getRuntime().exec("adb exec-out screencap -p");
			InputStream is = precord.getInputStream();
			//Image image = ImageIO.read(is);
			BufferedImage bf = ImageIO.read(is);
			bi=new BufferedImage(200,400,BufferedImage.TYPE_3BYTE_BGR);
			Graphics graphics = bi.createGraphics();
			graphics.drawImage(bf, 0, 0,200,400, null);
			graphics.dispose();						
			
			publish(new Integer[]{i});			
        		//Thread.sleep(10);
			i++;
			}
			
        		return 42;
    		}
		protected void process(List<Integer> chunks) 
		{
      			System.out.println(chunks);
			l1.setIcon(new ImageIcon(bi));
			//l1.setText(i+"");
  		}
    		protected void done()
    		{
        		try
        		{
            			//JOptionPane.showMessageDialog(f, get());
        		}
        		catch (Exception e)
        		{
            		e.printStackTrace();
        	}
    	}
}
	
	
	
}


/*PackageManager packman = getPackageManager();
    			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    			String pack = intent.resolveActivity(packman).getPackageName();
			System.out.println(pack);*/


/*Process su = Runtime.getRuntime().exec("su");
    su.getOutputStream().write("input swipe 250 300 -800 300\n".getBytes());
    su.getOutputStream().write("exit\n".getBytes());
    su.waitFor(); */