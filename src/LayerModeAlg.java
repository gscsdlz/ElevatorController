/**
 * 分层模式策略 1 2电梯只去底层 （0-6） 3 4电梯只去高层（7-13）
 */
public class LayerModeAlg implements QueueSelectStrategy {
    @Override
    public int doSelect(int beginFloor, int endFloor, int[] nums) {
        if(endFloor  >= 0 && endFloor <= 6) {
            return nums[0 * 14 + beginFloor] >  nums[1 * 14 + beginFloor] ? 1 : 0;
        } else {
            return  nums[2 * 14 + beginFloor] >  nums[3 * 14 + beginFloor] ? 3 : 2;
        }
    }
}
