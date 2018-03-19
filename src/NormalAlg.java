
/**
 * 常规算法 前往人数最少的队列
 */
public class NormalAlg implements QueueSelectStrategy {
    @Override
    public int doSelect() {
        int length = Integer.MAX_VALUE;
        int pos = -1;
        for(int i = 0; i < 4; i++) {
            if(length > Start.peopleQueue[i].size()) {
                length = Start.peopleQueue[i].size();
                pos = i;
            }
        }
        return pos;
}
}
