import javax.swing.*;
import java.lang.*;
import java.util.ArrayList;

public class Start {

    private Elevator elevator[] = new Elevator[4];  //电梯对象
    private boolean hasEnd = false;
    private MyFrame ui;
    private PublicPool pool;
    private RandController randCtl;
    public int time = 0;
    public int mintues = 0;

    public int totalWaitCount = 0;
    public int totalSizeCount = 0;


    public Start(QueueSelectStrategy queueSelectStrategy, int id) {
        pool = new PublicPool(queueSelectStrategy);
        randCtl = new RandController(pool, this);

        for(int i = 0; i < 4; i++)
            elevator[i] = new Elevator(pool, i, id, this);
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

                //统计队列中剩余的人  避免误差
                ArrayList<People> res = pool.getAll();

                int currentTime = mintues * Config.ONE_MINUTE ;
                for(int i = 0; i < res.size(); i++) {
                    totalWaitCount += currentTime - res.get(i).comingTime;
                }
                ui.drawEnd(totalSizeCount, totalWaitCount);
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
    }
}
