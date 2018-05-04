import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 公共池 也作为生产者消费者队列
 * 代表全局4部电梯 14个等待区，共计4 * 14 = 56个普通队列 遵守FIFO原则
 * 为了实现快速区分上下 56个电梯翻倍 上行和下行分开 共计112个
 */
public class PublicPool {
    private Queue<People> queue[] = new LinkedList[112];
    private QueueSelectStrategy queueSelectStrategy;

    public PublicPool(QueueSelectStrategy queueSelectStrategy) {
        for(int i = 0; i < 112; i++) {
            queue[i] = new LinkedList<People>();
        }
        this.queueSelectStrategy = queueSelectStrategy;
    }

    public synchronized void push(People p) {
        int poolId = queueSelectStrategy.doSelect(p.beginFloor, p.endFloor, this.size());

        if(p.beginFloor - p.endFloor > 0)
            queue[56 + poolId * 14 + p.beginFloor].add(p);
        else
            queue[poolId * 14 + p.beginFloor].add(p);

    }

    public synchronized People get(int poolId, int floor, int direct) {
        if(direct > 0)
            return queue[poolId * 14 + floor].size() == 0 ? null : queue[poolId * 14 + floor].remove();
        else
            return queue[56 + poolId * 14 + floor].size() == 0 ? null : queue[56 + poolId * 14 + floor].remove();
    }

    public synchronized int size(int poolId, int floor) {
        return queue[poolId * 14 + floor].size() + queue[56 + poolId * 14 + floor].size();
    }

    public synchronized int[] size() {
        int num[] = new int[56];

        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 14; j++)
                num[i * 14 + j] = this.size(i, j);

        return num;
    }

    public synchronized ArrayList<People> getAll() {
        ArrayList<People> res = new ArrayList<People>();

        for(int i = 0; i < 112; i++) {
            while(!queue[i].isEmpty()) {
                res.add(queue[i].remove());
            }
        }

        return res;
    }
}
