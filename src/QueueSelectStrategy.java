/**
 * 队列选择策略，策略设计模式
 */

public  interface QueueSelectStrategy {
    int doSelect(int beginFloor, int endFloor, int nums[] );
}
