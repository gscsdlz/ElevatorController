import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//设置标题
		drawBasicUI(g);
		for(int i = 0; i < 4; i++) {
		    drawPeople(g, i, Start.peopleQueue[i].size());
        }
	}

    /**
     * 绘制基础界面
     * @param g
     */
	private  void drawBasicUI(Graphics g) {
        g.setFont(new Font("楷体",Font.BOLD,25));
        g.setColor(Color.black);
        g.drawString("电梯系统的仿真与可视化", 500, 30);

        g.setFont(new Font("楷体",Font.BOLD,20));
        g.setColor(Color.black);
        g.drawString("第", 20, 80);
        g.setColor(Color.red);
        g.drawString(People.id + "", 40, 80);
        g.setColor(Color.black);
        if(People.id > 100)
            g.drawString("名用户", 70, 80);
        else
            g.drawString("名用户", 60, 80);


        for(int i = 0; i < 4 ; i++) {
            g.setColor(Color.yellow);
            g.drawRect(20 + i * 335, 400, 280, 40);
            g.fillRect(20+ i * 335, 400, 280, 40);
        }
        for(int j = 0 ; j < 10 ; j++) {
            g.setColor(Color.BLACK);
            g.drawRect(300, 400 - 40 * j, 55, 40);
        }
        for(int j = 0 ; j < 10 ; j++) {
            g.setColor(Color.BLACK);
            g.drawRect(635, 400 - 40 * j, 55, 40);
        }
        for(int j = 0 ; j < 10 ; j++) {
            g.setColor(Color.BLACK);
            g.drawRect(970, 400 - 40 * j, 55, 40);
        }
        for(int j = 0 ; j < 10 ; j++) {
            g.setColor(Color.BLACK);
            g.drawRect(1305, 400 - 40 * j, 55, 40);
        }

        g.setColor(Color.black);
        g.drawLine(20, 500, 1300, 500);

        g.setColor(Color.black);
        g.drawString("平均等待时间：" + Start.aveWaitTime + "秒", 100, 550);
        g.drawString("平均队列人数：" + Start.aveQueueSize + "人", 100, 590);
    }

    /**
     * 绘制小人
     * 小人宽度等于zoom
     * 小人高度等于zoom * 2
     * @param g
     * @param elevatorID 电梯编号 从0开始
     * @param length 小人个数
     */
    private void drawPeople(Graphics g, int elevatorID, int length) {

        int zoom = 14;
        int space = 10;
        if(length > 9) {
            g.drawString(length + " x",
                    20 + elevatorID * 335 + 280 - 3 * zoom - 10 * 3,
                    435);
            length = 1;
        }
        for(int i = 1; i <= length; i++) {
            int x = 20 + elevatorID * 335 + 280 - i * zoom - 10 * i;
            int y = 400;

            g.fillArc(x, y, zoom, zoom, 0, 360);
            g.drawLine(x + zoom / 2, y + zoom, x + zoom / 2, y + zoom + zoom);

            g.drawLine(x, y + zoom + zoom / 2, x + zoom, y + zoom + zoom / 2);

            g.drawLine(x + zoom / 2, y + zoom * 2, x, y + zoom * 3);
            g.drawLine(x + zoom / 2, y + zoom * 2, x + zoom, y + zoom * 3);
        }
    }
}
