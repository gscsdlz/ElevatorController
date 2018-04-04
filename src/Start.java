import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.*;
import java.util.Timer;

public class Start {
    public static Queue<People> peopleQueue[] = new LinkedList[4];  //等待区队列
    public static Elevator elevatorQueue[] = new Elevator[4];  //电梯队列
    public static int aveQueueSize = 0;
    public static int aveWaitTime = 0;

    public static void main(String args[]) throws InterruptedException {


        int MAX_MINTUES = 30; //最大分钟
        int HIGH_VALUE = 40;  //每分钟人数峰值
        int QUEUE_SIZE = 20;  //电梯等待区大小
        int ELEVATO_SIZE = 10; //电梯容量
        int ELEVATO_SPEED = 16; //电梯每分钟运行楼层数

        int queue[] = new int[MAX_MINTUES];

        for(int i = 0; i < MAX_MINTUES; i++) {
            int value = Possion.getPossion(HIGH_VALUE);
            queue[i] = value;
        }

        SelectController sc = new SelectController();

        //初始化队列
        for(int i = 0; i < 4; i++) {
            peopleQueue[i] = new LinkedList<People>();
            elevatorQueue[i] = new Elevator();
        }
        MyFrame ui = new MyFrame();
        ui.init();

        boolean overflowerSig = false;
        int totalWaitCount = 0;
        int totalSizeCount = 0;

        for(int i = 0; i < MAX_MINTUES; i++) {
            ui.repaint();
            for(int j = 0; j < queue[i]; j++) {
                sc.setStrategy(new NormalAlg());
                int id = sc.getQueueID();
                int endFloor = Possion.getPossion(4);   //目标楼层

                endFloor = endFloor > 10 ? 10 : endFloor < 2 ?  2 : endFloor;
                peopleQueue[id].add(new People(1,endFloor, i));
                if(peopleQueue[id].size() > QUEUE_SIZE) {
                   // JOptionPane.showMessageDialog(ui,"等待区队列已满", "提示信息",JOptionPane.WARNING_MESSAGE );
                    //overflowerSig = true;
                    //break;
                }
            }
            if(overflowerSig) {
                break;
            }

            int tmp = 0;
            for(int k = 0; k < 4; k++) {

                totalSizeCount++;
                aveQueueSize += peopleQueue[k].size();
            }
            System.out.println(peopleQueue[0].size() + " " + peopleQueue[1].size() + " "  + peopleQueue[2].size() + " " + peopleQueue[3].size());
            for(int t = 0; t < ELEVATO_SPEED; t++) {

                //离开一楼等待区
                for(int k = 0; k < 4; k++) {
                    if(elevatorQueue[k].getCurrentFloor() == 1) {
                        while(elevatorQueue[k].size() < ELEVATO_SIZE && peopleQueue[k].size() != 0) {
                            People p = peopleQueue[k].remove();
                            aveWaitTime += i - p.comingTime;
                            totalWaitCount++;
                            elevatorQueue[k].push(p);
                        }
                    }
                }

                for(int j = 0; j < 4; j++) {
                    elevatorQueue[j].move();
                    elevatorQueue[j].popAll();
                }
                ui.repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        }
        aveQueueSize /= totalSizeCount;
        aveWaitTime  /= totalWaitCount;
        ui.repaint();
        JOptionPane.showMessageDialog(ui,"运行已经结束", "提示信息",JOptionPane.WARNING_MESSAGE );

    }
}
