/**
 * 单双层模式 电梯正常运行，进队选择单双层 默认规定，第一 第二电梯为单层模式，第三，第四为双层
 */
public class SingleDoubleAlg implements QueueSelectStrategy{

    @Override
    public int doSelect() {
        if (Start.endFloor % 2 == 0) {   //2 4 6 8 10 12 14
            return Start.peopleQueue.size(2) > Start.peopleQueue.size(3) ? 3 : 2;
        } else {
            return Start.peopleQueue.size(0) > Start.peopleQueue.size(1) ? 1 : 0;
        }
    }
}
