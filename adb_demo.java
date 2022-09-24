import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.filechooser.*;
import javax.imageio.*;

class adb_demo extends JFrame implements ActionListener
{

	JButton btn_cameraopen,btn_camerafocus,btn_cameraclick,btn_screenshot,btn_woff,btn_won,btn_mdoff,btn_mdon,btn_bloff,btn_blon;
	JButton btn_off,btn_restart,btn_startrecord,btn_stoprecord,btn_home,btn_back,btn_sm,btn_push,btn_pull;
	JLabel l1;
	BufferedReader in =null;
	JPanel p1;
	String line=null;
	int i=1,j=1;
	Process precord;
	InputStream is;
	public adb_demo(String d)
	{
		setLayout(new BorderLayout());
		
		p1=new JPanel(new GridLayout(5,4));
		l1=new JLabel();
		btn_cameraopen=new JButton("Start Camera");
		btn_camerafocus=new JButton("Focus");
		btn_cameraclick=new JButton("Click");
		btn_screenshot=new JButton("Take Screen Shot");
		btn_woff=new JButton("Wifi Off");
		btn_won=new JButton("Wifi On");
		btn_mdoff=new JButton("Mobile Data Off");
		btn_mdon=new JButton("Mobile Data On");	
		btn_bloff=new JButton("Bluetooth Off");
		btn_blon=new JButton("Bluetooth On");
		btn_off=new JButton("Power Off");
		btn_restart=new JButton("Restart");
		btn_startrecord=new JButton("Start Recording");
		btn_stoprecord=new JButton("Stop Recording");
		btn_home=new JButton("Home");
		btn_back=new JButton("Back");
		btn_sm=new JButton("Screen Mirror");
		btn_push=new JButton("Push");
		btn_pull=new JButton("Pull");

		p1.add(btn_cameraopen);
		p1.add(btn_camerafocus);
		p1.add(btn_cameraclick);
		p1.add(btn_screenshot);
		p1.add(btn_woff);
		p1.add(btn_won);
		p1.add(btn_mdoff);
		p1.add(btn_mdon);
		p1.add(btn_bloff);
		p1.add(btn_blon);	
		p1.add(btn_off);
		p1.add(btn_restart);
		p1.add(btn_startrecord);
		p1.add(btn_stoprecord);
		p1.add(btn_home);
		p1.add(btn_back);
		p1.add(btn_sm);
		p1.add(btn_push);
		p1.add(btn_pull);

		
		btn_cameraopen.addActionListener(this);
		btn_camerafocus.addActionListener(this);
		btn_cameraclick.addActionListener(this);
		btn_screenshot.addActionListener(this);
		btn_woff.addActionListener(this);
		btn_won.addActionListener(this);
		btn_mdoff.addActionListener(this);
		btn_mdon.addActionListener(this);
		btn_bloff.addActionListener(this);
		btn_blon.addActionListener(this);
		btn_off.addActionListener(this);
		btn_restart.addActionListener(this);
		btn_startrecord.addActionListener(this);
		btn_stoprecord.addActionListener(this);	
		btn_home.addActionListener(this);
		btn_back.addActionListener(this);
		btn_sm.addActionListener(this);
		btn_push.addActionListener(this);
		btn_pull.addActionListener(this);


		try
		{
			String s="<html>Device ID: "+d;
			Process process = Runtime.getRuntime().exec("adb -s "+d+" shell getprop ro.product.model");
			in = new BufferedReader(new InputStreamReader(process.getInputStream()));  
			
			line = in.readLine();
			s=s+"<br>Device name: "+line;
			//l1.setText("<html>Device ID: "+d+"<br>Device name: "+line+"</html>");
			process = Runtime.getRuntime().exec("adb -s "+d+" shell getprop ro.product.manufacturer");
			in = new BufferedReader(new InputStreamReader(process.getInputStream()));  
			line = in.readLine();
			s=s+"<br>Company: "+line;

			process = Runtime.getRuntime().exec("adb -s "+d+" shell getprop ro.com.android.mobiledata");
			in = new BufferedReader(new InputStreamReader(process.getInputStream()));  
			line = in.readLine();
			if(line.equals("false"))
			{
				line="Off";
			}
			else
			{
				line="On";
			}
			s=s+"<br>Mobile Data: "+line;
			
			process = Runtime.getRuntime().exec("adb -s "+d+" shell getprop ro.build.version.release");
			in = new BufferedReader(new InputStreamReader(process.getInputStream()));  
			line = in.readLine();
			
			s=s+"<br>Android Version: "+line;

			process = Runtime.getRuntime().exec("adb -s "+d+" shell getprop ril.iccid.sim1");
			in = new BufferedReader(new InputStreamReader(process.getInputStream()));  
			line = in.readLine();
			if(line.length()==0)
			{
				line="No sim";
			}
			s=s+"<br>Sim 1: "+line;

			process = Runtime.getRuntime().exec("adb -s "+d+" shell dumpsys battery | grep level");
			in = new BufferedReader(new InputStreamReader(process.getInputStream()));  
			line = in.readLine();
			
			
			s=s+"<br>Battery Level: "+line+" %";

			l1.setText(s+"</html>");

			
			System.out.println(line);
        		  
		}
		catch (Exception e) 
		{
        		System.out.println(e);
    		}
		add(l1,"North");
		add(p1);
		pack();
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
	
		if(ae.getSource()==btn_cameraopen)
		{
			
			//Process process1 = Runtime.getRuntime().exec("adb shell am start -a android.media.action.IMAGE_CAPTURE");
			Process process1 = Runtime.getRuntime().exec("adb shell am start -a android.media.action.STILL_IMAGE_CAMERA  && sleep 1 && adb shell input keyevent 27");
			

			//Process process3 = Runtime.getRuntime().exec(new String[] {"suu", "-c", "input keyevent 5"});
			BufferedReader in = new BufferedReader(new InputStreamReader(process1.getInputStream()));  
        		String line = null;  
			while ((line = in.readLine()) != null) 
			{  
            			 
				System.out.println(line);
        		}  
			System.out.println(line);

			
		}
		else if(ae.getSource()==btn_camerafocus)
		{
			Process process2 = Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_FOCUS");
		}
		else if(ae.getSource()==btn_cameraclick)
		{
			Process process3 = Runtime.getRuntime().exec("adb shell input keyevent 27");
			System.out.println("p3");
		}
		else if(ae.getSource()==btn_screenshot)
		{
			JFileChooser fc=new JFileChooser();
			FileNameExtensionFilter filter1 = new FileNameExtensionFilter("JPEG images", ".jpg");
			FileNameExtensionFilter filter2 = new FileNameExtensionFilter("PNG images", ".png");
			fc.setAcceptAllFileFilterUsed(false);
			fc.addChoosableFileFilter(filter1);
			fc.addChoosableFileFilter(filter2);	
			
			fc.setDialogTitle("Save File");
			int button=fc.showSaveDialog(this);
			
			if(button==JFileChooser.APPROVE_OPTION)
			{
				String extension="";
				String ext=fc.getFileFilter().getDescription();
				if(ext.equalsIgnoreCase("jpeg images"))
				{
					extension=".jpg";
				}
				else if(ext.equalsIgnoreCase("png images"))
				{
					extension=".png";
				}
				File f=new File(fc.getSelectedFile()+extension);	
				String f1=f.toString();
				Process process1 = Runtime.getRuntime().exec("adb shell screencap -p /sdcard/screen"+i+".png");
				
				Thread.sleep(5000);
				

				Process process2 = Runtime.getRuntime().exec("adb pull /sdcard/screen"+i+".png "+f);
				Thread.sleep(1000);
				Process process3 = Runtime.getRuntime().exec("adb shell rm /sdcard/screen"+i+".png");
				Thread.sleep(1000);										
				
				process1.destroy();
				process2.destroy();
				process3.destroy();
				i++;
				
			}
			
		}
		else if(ae.getSource()==btn_woff)
		{
			Process process2 = Runtime.getRuntime().exec("adb shell svc wifi disable");
		}
		else if(ae.getSource()==btn_won)
		{
			Process process3 = Runtime.getRuntime().exec("adb shell svc wifi enable");
			System.out.println("p3");
		}
		else if(ae.getSource()==btn_mdoff)
		{
			Process process2 = Runtime.getRuntime().exec("adb shell svc data disable");
		}
		else if(ae.getSource()==btn_mdon)
		{
			Process process3 = Runtime.getRuntime().exec("adb shell svc data enable");
			
		}
		else if(ae.getSource()==btn_bloff)
		{
			Process process2 = Runtime.getRuntime().exec("adb shell service call bluetooth_manager 8");
		}
		else if(ae.getSource()==btn_blon)
		{
			Process process3 = Runtime.getRuntime().exec("adb shell service call bluetooth_manager 6");
			
		}
		else if(ae.getSource()==btn_off)
		{
			Process process2 = Runtime.getRuntime().exec("adb shell reboot -p");
		}
		else if(ae.getSource()==btn_restart)
		{
			Process process3 = Runtime.getRuntime().exec("adb shell reboot");
			
		}
		else if(ae.getSource()==btn_sm)
		{				
			ScreenMirror sm=new ScreenMirror();						
			
		}
		else if(ae.getSource()==btn_startrecord)
		{
			precord = Runtime.getRuntime().exec("adb shell screenrecord /sdcard/e"+j+".mp4");						

		}
		else if(ae.getSource()==btn_stoprecord)
		{
			JFileChooser fc=new JFileChooser();
			FileNameExtensionFilter filter1 = new FileNameExtensionFilter("MP4 video", ".mp4");			
			fc.setAcceptAllFileFilterUsed(false);
			fc.addChoosableFileFilter(filter1);
			
			
			fc.setDialogTitle("Save Video");
			int button=fc.showSaveDialog(this);
			
			if(button==JFileChooser.APPROVE_OPTION)
			{
				File f=new File(fc.getSelectedFile()+".mp4");
				Process process2 = Runtime.getRuntime().exec("adb pull /sdcard/e"+j+".mp4 "+f);
				Thread.sleep(1000);
				Process process3 = Runtime.getRuntime().exec("adb shell rm /sdcard/e"+j+".mp4");
				Thread.sleep(1000);
				j++;
				JOptionPane.showMessageDialog(this, "File Saved", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		else if(ae.getSource()==btn_home)
		{
			Process process2 = Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_HOME");
		}
		else if(ae.getSource()==btn_back)
		{
			Process process3 = Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_BACK");
			
		}
		else if(ae.getSource()==btn_push)
		{
			JFileChooser fc=new JFileChooser();
			/*FileNameExtensionFilter filter1 = new FileNameExtensionFilter("MP4 video", ".mp4");			
			fc.setAcceptAllFileFilterUsed(false);
			fc.addChoosableFileFilter(filter1);*/
			
			
			fc.setDialogTitle("Select File");
			int button=fc.showOpenDialog(this);
			
			if(button==JFileChooser.APPROVE_OPTION)
			{
				File f=new File(fc.getSelectedFile()+"");
				String a=f.getAbsolutePath();
				System.out.println(a);
				Process process21 = Runtime.getRuntime().exec("adb remount");
				Thread.sleep(1000);
				Process process2 = Runtime.getRuntime().exec("adb push a /sdcard/");
				Thread.sleep(3000);
				JOptionPane.showMessageDialog(this, "File copied", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(ae.getSource()==btn_pull)
		{
			Process process3 = Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_BACK");
			
		}

		}
		catch (Exception e) 
		{
        		System.out.println(e);
    		}
	}
	public static void main(String args[])
	{
		//new adb_demo();
	}
}

/*

for bluetooth

https://stackoverflow.com/questions/37259260/android-enable-disable-bluetooth-via-command-line

*/


/*PackageManager packman = getPackageManager();
    			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    			String pack = intent.resolveActivity(packman).getPackageName();
			System.out.println(pack);*/


/*Process su = Runtime.getRuntime().exec("su");
    su.getOutputStream().write("input swipe 250 300 -800 300\n".getBytes());
    su.getOutputStream().write("exit\n".getBytes());
    su.waitFor(); */




/*byte[] buffer = new byte[90000];
    			int bytesRead;
			 ByteArrayOutputStream output = new ByteArrayOutputStream();
    			while ((bytesRead = is.read(buffer)) != -1)
    			{
        			output.write(buffer, 0, bytesRead);
    			}
			byte b[]=output.toByteArray();
		        FileOutputStream out = new FileOutputStream("d:\\video.mp4");
			out.write(b);
			out.close();
			precord.destroy();
			
			
			
is.read(buffer);
			File targetFile = new File("d:\\video.mp4");
    			OutputStream outStream = new FileOutputStream(targetFile);
    			outStream.write(buffer);*/

