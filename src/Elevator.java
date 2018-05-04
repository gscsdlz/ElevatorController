import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 单个电梯对象
 * 存储电梯当前楼层以及People类
 *
 */
public class Elevator extends Thread{
    private LinkedList<People> container;
    private PublicPool poolInstance;
    private int currentFloor;
    private int direct;
    private int elevatorID;
    private int outNum = 0;
    private int runType;
    private Start instance;

    public Elevator(PublicPool pool, int id, int type, Start s) {
        this.container = new LinkedList<People>();
        this.poolInstance = pool;
        currentFloor = 0;
        direct = 1;
        elevatorID  = id;
        runType = type;
        instance = s;
    }

    protected void push() {
        try {
            Thread.sleep(Config.POP_PUSH_SPEED);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        People p;
        while(container.size() < Config.ELEVATO_SIZE && (p = poolInstance.get(elevatorID, currentFloor, direct)) != null) {
            container.add(p);
        }
    }

    public void move() {
        //先下后上 再移动电梯

        if(runType == 2 && elevatorID <= 1 && currentFloor == 6 || currentFloor == 13)
            direct = -1;
        if(currentFloor == 0)
            direct = 1;

        if( runType == 0 ||    //正常模式
            currentFloor == 0 ||   //最底层
            (runType == 1 && (elevatorID <= 1 && currentFloor % 2 != 0) || (elevatorID >= 2 && currentFloor % 2 == 0)) ||    //单双模式
            (runType == 2 && (elevatorID <= 1 && currentFloor <= 6) || (elevatorID >= 2 && currentFloor >= 7)) ) {

            popAll();
            push();
        }


        try {
            Thread.sleep(Config.ELEVATO_SPEED);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        currentFloor = currentFloor + direct;
    }

    protected void popAll() {

        outNum = 0;
        if(container.size() > 0) {
            for(int i = 0; i < container.size(); i++) {
                if (container.get(i).endFloor == currentFloor) {
                    People p = container.remove(i--);
                    instance.totalWaitCount += instance.mintues * Config.ONE_MINUTE + instance.time * Config.REFRESH_TIME - p.comingTime;
                    outNum++;
                }
            }
        }
        try {
            Thread.sleep(Config.POP_PUSH_SPEED * outNum);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public int getSize() {
        return container.size();
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public int getDirect() {return this.direct;}

    public void run() {
        while(true) move();
    }

    public int getOutNum() {
        return outNum;
    }
}
