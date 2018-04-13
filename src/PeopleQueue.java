import java.util.LinkedList;
import java.util.Queue;

/**
 * 用于放置人的队列 抽离出来为了实现线程安全
 */
public class PeopleQueue {
    private Queue<People> queue[] = new LinkedList[4];  //等待区队列

    public PeopleQueue() {
        for(int i = 0; i < 4; i++) {
            queue[i] = new LinkedList<People>();
        }
    }

    public synchronized void push(People p, int id) {
        queue[id].add(p);
    }

    public synchronized People pop(int id) {
        return queue[id].remove();
    }

    public int size(int i) {
        return queue[i].size();
    }

    public int size() {
        return queue[0].size() + queue[1].size() + queue[2].size() + queue[3].size();
    }
}
