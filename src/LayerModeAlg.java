/**
 * 分层模式策略 1 2电梯只去底层 （2-7） 3 4电梯只去高层（8-14）
 */
public class LayerModeAlg implements QueueSelectStrategy {
    @Override
    public int doSelect() {
        if(Start.endFloor  >= 2 && Start.endFloor <= 7) {
            return Start.peopleQueue.size(0) > Start.peopleQueue.size(1) ? 1 : 0;
        } else {
            return Start.peopleQueue.size(2) > Start.peopleQueue.size(3) ? 3 : 2;
        }
    }
}
