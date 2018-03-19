import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.*;
import java.util.Timer;

public class Start {
    public static Queue<People> peopleQueue[] = new LinkedList[4];
    public static int aveQueueSize = 0;
    public static int aveWaitTime = 0;

    public static void main(String args[]) throws InterruptedException {


        int MAX_MINTUES = 30; //最大分钟
        int HIGH_VALUE = 30;  //每分钟人数峰值
        int QUEUE_SIZE = 30;  //电梯等待区大小
        int ELEVATO_SIZE = 10; //电梯容量
        int queue[] = new int[MAX_MINTUES];
        int totalQueueSize = 0;

        for(int i = 0; i < MAX_MINTUES; i++) {
            int value = Possion.getPossion(HIGH_VALUE);
            queue[i] = value;
            totalQueueSize += value;
        }

        SelectController sc = new SelectController();
        peopleQueue[0] = new LinkedList<People>();
        peopleQueue[1] = new LinkedList<People>();
        peopleQueue[2] = new LinkedList<People>();
        peopleQueue[3] = new LinkedList<People>();

        MyFrame ui = new MyFrame();
        ui.init();

        int signals = 0;
        boolean overflowerSig = false;


        for(int i = 0; i < MAX_MINTUES || totalQueueSize != 0; i++) {
            ui.repaint();
            for(int j = 0; i < MAX_MINTUES && j < queue[i]; j++) {
                sc.setStrategy(new NormalAlg());
                int id = sc.getQueueID();
                peopleQueue[id].add(new People(1,1, i));
                if(peopleQueue[id].size() > QUEUE_SIZE) {
                    JOptionPane.showMessageDialog(ui,"队列已满", "提示信息",JOptionPane.WARNING_MESSAGE );
                    overflowerSig = true;
                    break;
                }
            }
            if(overflowerSig) {
                break;
            }

            int tmp = 0;
            for(int k = 0; k < 4; k++)
                tmp += peopleQueue[k].size();
            aveQueueSize = (aveQueueSize + tmp / 4) / 2;

            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            ui.repaint();
            signals++;
            if(signals == 2) {
                for(int k = 0; k < 4; k++) {
                    int s = ELEVATO_SIZE;
                    while(!peopleQueue[k].isEmpty() && s != 0) {
                        People fr = peopleQueue[k].remove();
                        aveWaitTime = (aveWaitTime + i - fr.comingTime) / 2;
                        totalQueueSize--;
                        s--;
                    }
                }
                signals = 0;
            }
        }
        System.out.println(totalQueueSize);
        JOptionPane.showMessageDialog(ui,"运行已经结束", "提示信息",JOptionPane.WARNING_MESSAGE );

    }
}
