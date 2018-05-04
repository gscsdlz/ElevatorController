import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel{
	private static final long serialVersionUID = 1L;
    private int nums[] = new int[56];
    private int elevators[] = new int[4];
    private int outNum[] = new int[4];
    private int time = 0;
    private int totalSizeQueue = -1;
    private int totalWaitTime = -1;
    private int people = 0;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//设置标题
		drawBasicUI(g);
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 14; j++) {
                drawPeople(g, i, nums[i * 14 + j], j);
            }
            drawElevator(g, i, elevators[i] & 15, elevators[i] & 240, elevators[i] >> 8);
            drawOut(g, i, outNum[i] & 15, outNum[i] >> 4);
        }
	}

    /**
     * 绘制基础界面
     * @param g
     */
	private  void drawBasicUI(Graphics g) {

        g.setFont(new Font("楷体",Font.BOLD,20));
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 14; j++) {
                g.setColor(Color.BLACK);
                g.drawRect(i * 60 + 50 * (i + 1), 410 - 30 * j, 40, 30);
            }
        }

        g.setColor(Color.black);
        g.drawLine(20, 480, 1300, 480);

        g.setColor(Color.black);
        g.drawString("当前运行时间： " + time +" 分钟", 100, 510);
        if(totalSizeQueue == -1) {
            g.drawString("平均等待时间： -- 分钟", 100, 550);
        } else {
            g.drawString("平均等待时间： "+(totalWaitTime / Config.ONE_MINUTE / 60 / 6)+" 分钟", 100, 550);
        }
        if(totalSizeQueue == -1) {
            g.drawString("平均队列人数： -- 人", 100, 590);
        } else {
            g.drawString("平均队列人数： " +  (totalSizeQueue / Config.MAX_MINTUES * 1.0)+ " 人", 100, 590);
        }


        g.drawString("第", 100, 630);
        g.setColor(Color.red);
        g.drawString(people + "", 125, 630);
        g.setColor(Color.black);
        if(people > 100)
            g.drawString("名用户", 170, 630);
        else
            g.drawString("名用户", 160, 630);

    }

    /**
     * 绘制小人
     * 小人宽度等于zoom
     * 小人高度等于zoom * 2
     * @param g
     * @param elevatorID 电梯编号 从0开始
     * @param length 小人个数
     */
    public void drawPeople(Graphics g, int elevatorID, int length, int floor) {

        g.setColor(Color.black);
        int x = elevatorID * 60 + 50 * (elevatorID + 1);
        int y = 410 - 30 * (floor - 1) - 8;
        g.drawString(length + "x", x - 50, y);
        x -= 14;
        y -= 20;
        //小人的头
        g.fillArc(x, y, 8, 8, 0, 360);
        //小人的身子
        g.drawLine(x + 8 / 2, y + 8, x + 8 / 2, y + 8 + 8);
        g.drawLine(x, y + 8 + 8 / 2, x + 8, y + 8 + 8 / 2);
        //小人的脚
        g.drawLine(x + 8 / 2, y + 8 * 2, x, y + 8 * 3);
        g.drawLine(x + 8 / 2, y + 8 * 2, x + 8, y + 8 * 3);
    }

    public void drawElevator(Graphics g, int id, int currentFloor, int direct, int size) {
        if(direct == 0)
            return;
        int x = id * 60 + 50 * (id + 1);
        int y = 410 - 30 * (currentFloor - 1) - 8;

        g.setColor(Color.BLUE);
        if(direct == 16)
            g.drawString(size + "↑", x, y);
        else
            g.drawString(size + "↓", x, y);
    }

    public void drawOut(Graphics g, int id, int floor, int size) {
        if(size == 0)
            return;

        int x = id * 60 + 50 * (id + 1) + 40;
        int y = 410 - 30 * (floor - 1) - 8;

        g.setColor(Color.RED);
        g.drawString("" + size, x, y);
    }

    public void setDrawPeople(int nums[]) {
        this.nums = nums;
    }

    public void setElevator(int id, int currentFloor, int direct, int size) {
        //direct == 1 ? 1 : 2避免由于-1 带来的位运算错误
        this.elevators[id]  = ((currentFloor) | ((direct == 1 ? 1 : 2) << 4) | (size << 8));

    }

    public void setOut(int id, int floor, int size) {
        this.outNum[id] = floor | (size << 4);
    }

    public void setTime(int t) {
        time = t;
    }

    public void setEnd(int total1, int total2) {
        totalSizeQueue = total1;
        totalWaitTime = total2;
    }

    public void setPeople(int p) {
        people = p;
    }

}
