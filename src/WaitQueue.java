/**
 * 等待区调度，线程函数 设置每分钟需要装入的People数 自动载入
 */
public class WaitQueue extends Thread {

    private int currentSize = 0;
    private int currentTime = 0;

    public void run() {
        for(int j = 0; j < currentSize; j++) {
            Start.endFloor = (int)(Math.random() * 8) + 2;   //目标楼层
            People p = new People(1,Start.endFloor, currentTime);
            int id = SelectController.getQueueID();
            Start.peopleQueue.push(p, id);
            try {
                sleep(170 / currentSize);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCurrent(int currentSize, int currentTime) {
        this.currentSize = currentSize;
        this.currentTime = currentTime;
    }
}
