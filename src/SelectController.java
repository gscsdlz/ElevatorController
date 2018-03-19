
public class SelectController {
    private QueueSelectStrategy strategy = null;

    public void setStrategy(QueueSelectStrategy strategy) {
        this.strategy  = strategy;
    }

    public int getQueueID() {
        return this.strategy.doSelect();
    }
}
