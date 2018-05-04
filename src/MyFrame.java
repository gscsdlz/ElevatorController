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
        MyFrame myFrame1 = new MyFrame();
        myFrame1.init(0);
//        MyFrame myFrame2 = new MyFrame();
//        myFrame2.init(1);
//        MyFrame myFrame3 = new MyFrame();
//        myFrame3.init(2);
    }

	public void init(int id) {

        Font f = new Font("华文行楷", Font.BOLD, 20);//根据指定字体名称、样式和磅值大小，创建一个新 Font。
        jp1 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        if(id == 0) {
            jb1 = new JButton("正常模式");
            jb1.setFont(f);
            jb1.setBounds(20, 10, 830, 560);
            jp1.add(jb1);
        }

        if(id == 1) {
            jb2 = new JButton("单双层模式");
            jb2.setFont(f);
            jb2.setBounds(20, 10, 830, 560);
            jp1.add(jb2);
        }

        if(id == 2) {
            jb3 = new JButton("分层模式");
            jb3.setFont(f);
            jb3.setBounds(20, 10, 860, 560);
            jp1.add(jb3);
        }


		this.setTitle("电梯系统的仿真与可视化");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH	);
		this.setAlwaysOnTop(true);
		this.setLayout(new BorderLayout());

		this.add(jp1,BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(mp);
		this.setVisible(true);
		this.setSize(455, 768);
		this.setLocation(id * 455, 0);
	}

	public void drawDown(int id, int size, int floor) {
    //	mp.setDrawDown(id, size, floor);
	}

	public void drawDownOn() {
	//	mp.drawDownOn = true;
	}

	public void drawPublicPool(int num[]) {
        mp.setDrawPeople(num);
    }

    public void drawElevator(int id, int currentFloor, int direct, int size) {
        mp.setElevator(id, currentFloor, direct, size);
    }

    public void drawOut(int id, int floor, int size) {
        mp.setOut(id, floor, size);
    }

    public void drawTime(int time) {
        mp.setTime(time);
    }

    public void drawPeople(int size) {
        mp.setPeople(size);
    }

    public void drawEnd(int totalSizeCount) {
        mp.setEnd(totalSizeCount);
    }
}
