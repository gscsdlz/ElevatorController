import javax.swing.*;
import java.lang.*;

public class Start {

    public static Elevator elevatorQueue[] = new Elevator[4];  //电梯队列
    public static int aveQueueSize = 0;
    public static int aveWaitTime = 0;
    public static int endFloor;
    public static boolean hasEnd = false;
    public static MyFrame ui = new MyFrame();

    public static int MAX_MINTUES = 10; //最大分钟
    public static int HIGH_VALUE = 50;  //每分钟人数峰值
    public static int QUEUE_SIZE = 20;  //电梯等待区大小
    public static int ELEVATO_SIZE = 10; //电梯容量
    public static int ELEVATO_SPEED = 20; //电梯每分钟运行楼层数 包括上下
    public static int queue[] = new int[MAX_MINTUES];
    public static PeopleQueue peopleQueue = new PeopleQueue();  //等待区

    public static void main(String args[]) throws InterruptedException {

        //初始化相关参数
        ui.init();
        SelectController.setStrategy(new NormalAlg());  //初始化为正常模式
        boolean overflowerSig = false;
        int totalWaitCount = 0;
        int totalSizeCount = 0;

        for(int i = 0; i < 4; i++) {
            elevatorQueue[i] = new Elevator();
        }

        /**
         * 每分钟来的人的总数
         */
        for (int i = 0; i < MAX_MINTUES; i++) {
            int value = Possion.getPossion(HIGH_VALUE);
            queue[i] = value;
        }
        WaitQueue waitQueue = new WaitQueue();

        for (int i = 0; i < MAX_MINTUES; i++) {
            waitQueue.setCurrent(queue[i], i);
            new Thread(waitQueue).start();
            if(i == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            totalSizeCount++;
            aveQueueSize += peopleQueue.size();

            ui.repaint();
            for(int t = 0; t < ELEVATO_SPEED; t++) {

                //离开一楼等待区
                for(int k = 0; k < 4; k++) {
                    if(elevatorQueue[k].getCurrentFloor() == 1) {
                        while(elevatorQueue[k].size() < ELEVATO_SIZE && peopleQueue.size(k) != 0) {   //当电梯有剩余空间，且等待区有人时进队列
                            People p = peopleQueue.pop(k);
                            aveWaitTime += i - p.comingTime;
                            totalWaitCount++;
                            elevatorQueue[k].push(p);  //压入优先队列
                        }
                    }
                }

                for(int j = 0; j < 4; j++) {
                    int size = elevatorQueue[j].size();
                    elevatorQueue[j].popAll();
                    size -= elevatorQueue[j].size();

                    elevatorQueue[j].move();
                    ui.drawDown(j, size, elevatorQueue[j].getCurrentFloor() - 1);

                }
                ui.drawDownOn();
                ui.repaint();

                 try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        aveQueueSize /= (totalSizeCount * 4);
        aveWaitTime  /= totalWaitCount;
        hasEnd = true;   //运行结束
        ui.repaint();
        JOptionPane.showMessageDialog(ui,"运行已经结束", "提示信息",JOptionPane.WARNING_MESSAGE );

    }
}
