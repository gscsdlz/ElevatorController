import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 单个电梯对象
 * 存储电梯当前楼层以及People类
 *
 */
public class Elevator {
    private Queue<People> container;
    private int currentFloor;
    private int direct;

    public Elevator() {
        this.container = new PriorityQueue<People>();
        currentFloor = 1;
        direct = 1;
    }

    public void push(People p) {
        container.add(p);
    }

    public void move() {
        if(currentFloor == 14)
            direct = -1;
        if(currentFloor == 1)
            direct = 1;
        currentFloor = currentFloor + direct;
    }

    public People pop(){
        if(container.size() > 0 && container.peek().endFloor == currentFloor) {
            return container.remove();
        }
        return null;
    }

    public void popAll() {
        while(true) {
            People p = this.pop();
            if(p == null)
                break;
        }
    }

    public int size() {
        return container.size();
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public void init() {
        popAll();
        currentFloor = 1;
        direct = 1;
    }
}
