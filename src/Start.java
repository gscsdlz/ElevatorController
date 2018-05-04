import javax.swing.*;
import java.lang.*;

public class Start {

    private Elevator elevator[] = new Elevator[4];  //电梯对象
    private boolean hasEnd = false;
    private MyFrame ui;
    private PublicPool pool;
    private RandController randCtl;
    private int time = 0;
    public int mintues = 0;
//    public static boolean overflowerSig = false;
    //public static int totalWaitCount = 0;
    public int totalSizeCount = 0;


    public Start(QueueSelectStrategy queueSelectStrategy, int id) {
        pool = new PublicPool(queueSelectStrategy);
        randCtl = new RandController(pool);

        for(int i = 0; i < 4; i++)
            elevator[i] = new Elevator(pool, i, id);
        ui = new MyFrame();

        ui.init(id);
    }

    public void refresh() {
        ui.drawPublicPool(pool.size());
        for(int i = 0; i < 4; i++) {
            ui.drawElevator(i, elevator[i].getCurrentFloor(), elevator[i].getDirect(), elevator[i].getSize());
            ui.drawOut(i, elevator[i].getCurrentFloor(), elevator[i].getOutNum());
        }
        ui.drawTime(mintues);
        ui.drawPeople(randCtl.getPeopleID());
        ui.repaint();
    }

    /**
     * 确定什么时候是虚拟的一分钟
     */
    public void checkTime() {
        time++;
        if(time == Config.ONE_MINUTE / Config.REFRESH_TIME) {
            mintues++;
            time = 0;
            int tmp[] = pool.size();

            for(int i = 0; i < 56; i++) {
                totalSizeCount += tmp[i];
            }

            if(mintues == Config.MAX_MINTUES) {
                if(Config.ELEVATO_STOP) {
                    elevator[0].stop();
                    elevator[1].stop();
                    elevator[2].stop();
                    elevator[3].stop();
                    hasEnd = true;
                }
                refresh();
                ui.drawEnd(totalSizeCount);
                ui.repaint();
            }
        }
    }

    public void start() throws InterruptedException {

        Thread t  = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!hasEnd)
                {
                    refresh();
                    checkTime();
                    try {
                        Thread.sleep(Config.REFRESH_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();

        randCtl.start();
        elevator[0].start();
        elevator[1].start();
        elevator[2].start();
        elevator[3].start();


//        for (int i = 0; i < MAX_MINTUES; i++) {
//            waitQueue.setCurrent(queue[i], i);
//            new Thread(waitQueue).start();
//
//            if(i == 0) {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            totalSizeCount++;
//            aveQueueSize += peopleQueue.size();
//
//            ui.repaint();
//            for(int t = 0; t < ELEVATO_SPEED; t++) {
//
//                //离开一楼等待区
//                for(int k = 0; k < 4; k++) {
//                    if(elevatorQueue[k].getCurrentFloor() == 1) {
//                        while(elevatorQueue[k].size() < ELEVATO_SIZE && peopleQueue.size(k) != 0) {   //当电梯有剩余空间，且等待区有人时进队列
//                            People p = peopleQueue.pop(k);
//                            aveWaitTime += i - p.comingTime;
//                            totalWaitCount++;
//                            elevatorQueue[k].push(p);  //压入优先队列
//                        }
//                    }
//                }
//
//                for(int j = 0; j < 4; j++) {
//                    int size = elevatorQueue[j].size();
//                    elevatorQueue[j].popAll();
//                    size -= elevatorQueue[j].size();
//
//                    elevatorQueue[j].move();
//                    ui.drawDown(j, size, elevatorQueue[j].getCurrentFloor() - 1);
//
//                }
//                ui.drawDownOn();
//                ui.repaint();
//
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//        aveQueueSize /=  4;
//        aveWaitTime  /= totalWaitCount;
//        hasEnd = true;   //运行结束
//        ui.repaint();
//        JOptionPane.showMessageDialog(ui,"运行已经结束", "提示信息",JOptionPane.WARNING_MESSAGE );
    }
}
