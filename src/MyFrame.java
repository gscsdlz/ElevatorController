import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame{
	MyPanel mp = new MyPanel();
	private JButton jb1;
	private JButton jb2;
	private JButton jb3;
	private JPanel jp1;

    public static void main(String[] args) {

    }

	public void init() {

		jb1=new JButton("正常模式");
		Font f=new Font("华文行楷",Font.BOLD,20);//根据指定字体名称、样式和磅值大小，创建一个新 Font。
		jb1.setFont(f);
		//jb1.setBounds(20,10,800,560);
		/*jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("切换到正常模式");
				JOptionPane.showMessageDialog(Start.ui,"正常模式", "提示信息",JOptionPane.WARNING_MESSAGE );

				SelectController.setStrategy(new NormalAlg());
				Start.init();
			}
		});*/
		jb2=new JButton("单双层模式");
		jb2.setFont(f);
		jb2.setBounds(20,10,830,560);
		/*jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("切换到单双层模式");
				JOptionPane.showMessageDialog(Start.ui,"切换到单双层模式", "提示信息",JOptionPane.WARNING_MESSAGE );

				SelectController.setStrategy(new SingleDoubleAlg());
				Start.init();
			}
		});
*/
		
		jb3=new JButton("分层模式");
		jb3.setFont(f);
		jb3.setBounds(20,10,860,560);
	/*	jb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("切换到分层模式");
				JOptionPane.showMessageDialog(Start.ui,"切换到分层模式", "提示信息",JOptionPane.WARNING_MESSAGE );

				SelectController.setStrategy(new LayerModeAlg());
				Start.init();

			}
		});*/
		jp1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
	
		this.setTitle("电梯系统的仿真与可视化");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH	);
		this.setAlwaysOnTop(true);
		this.setLayout(new BorderLayout());
		
		this.add(jp1,BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(mp);
		this.setVisible(true);
	}

	public void drawDown(int id, int size, int floor) {
    	mp.setDrawDown(id, size, floor);
	}

	public void drawDownOn() {
		mp.drawDownOn = true;
	}
}
