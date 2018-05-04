/**
 * 常规算法 前往人数最少的队列
 */
public class NormalAlg implements QueueSelectStrategy {
    @Override
    public int doSelect(int beginFloor, int endFloor, int nums[] ) {
        int length = Integer.MAX_VALUE;
        int pos = -1;

        for(int i = 0; i < 4; i++) {

            if(length > nums[i * 14 + beginFloor]) {
                length = nums[i * 14 + beginFloor];
                pos = i;
            }
        }
        return pos;
}
}
