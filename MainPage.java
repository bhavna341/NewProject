import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.*;

class MainPage extends JFrame implements ActionListener
{

	JButton btn_scan,btn_connect,btn_exit;
	JLabel l1;
	JList l;
	DefaultListModel dm;
	JPanel p1;
	JScrollPane sp;
	BufferedReader in =null;
	public MainPage()
	{
		setLayout(new BorderLayout());

		p1=new JPanel(new FlowLayout());
		Image icon = Toolkit.getDefaultToolkit().getImage("E:\\icons8-android-64.png");  
		l1=new JLabel("List of devices attached");
		dm=new DefaultListModel();
		l=new JList(dm);
		sp=new JScrollPane(l);
		btn_scan=new JButton("Scan");
		btn_connect=new JButton("Connect");
		btn_exit=new JButton("Exit");
		

		p1.add(btn_scan);
		p1.add(btn_connect);
		p1.add(btn_exit);
		
		add(l1,"North");
		add(sp);
		add(p1,"South");

		btn_scan.addActionListener(this);
		btn_connect.addActionListener(this);
		btn_exit.addActionListener(this);
		
		setIconImage(icon);
		setLocationRelativeTo(null);
		setSize(300,300);
		setResizable(false);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
	
		if(ae.getSource()==btn_scan)
		{
			
			
			Process process1 = Runtime.getRuntime().exec("adb devices");
			

			in = new BufferedReader(new InputStreamReader(process1.getInputStream()));  
        		String line = null;  
			
        		Pattern pattern = Pattern.compile("^([a-zA-Z0-9\\-]+)(\\s+)(device)");
        		Matcher matcher;
			while ((line = in.readLine()) != null) 
			{  
            			if (line.matches(pattern.pattern())) 
				{
                			matcher = pattern.matcher(line);
                			if (matcher.find())
                    				dm.addElement(matcher.group(1));
            			}				
        		}
			if(dm.getSize()==0)
			{
				JOptionPane.showMessageDialog(this, "No connected device", "Info", JOptionPane.INFORMATION_MESSAGE);
			}  
			

			
		}
		else if(ae.getSource()==btn_connect)
		{
			if(l.getSelectedIndex()>=0)
			{
				new adb_demo(l.getSelectedValue().toString());
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Please select a device", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(ae.getSource()==btn_exit)
		{
			System.exit(0);
		}
		

		}
		catch (Exception e) 
		{
        		System.out.println(e);
    		}
	}
	public static void main(String args[])
	{
		new MainPage();
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