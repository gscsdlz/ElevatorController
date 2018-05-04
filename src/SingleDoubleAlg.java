/**
 * 单双层模式 电梯正常运行，进队选择单双层 默认规定，第一 第二电梯为单层模式，第三，第四为双层
 */
public class SingleDoubleAlg implements QueueSelectStrategy{

    @Override
    public int doSelect(int beginFloor, int endFloor, int[] nums) {
        if (endFloor % 2 != 0) {   //1 3 5 7 9 11 13
            return nums[0 * 14 + beginFloor] >  nums[1 * 14 + beginFloor] ? 1 : 0;
        } else {
            return  nums[2 * 14 + beginFloor] >  nums[3 * 14 + beginFloor] ? 3 : 2;
        }
    }
}
