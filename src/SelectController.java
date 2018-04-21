/**
 * 等待区选择策略的控制器，单例模式
 */
public class SelectController {
    private QueueSelectStrategy strategy = null;
    private static SelectController instance = null;

    public static void setStrategy(QueueSelectStrategy strategy) {
        SelectController in = getInstance();
        in.strategy = strategy;

    }

    public static int getQueueID() {
        SelectController in  = getInstance();
        return in.strategy.doSelect();
    }

    private static SelectController getInstance() {
        if(instance == null) {
            return instance = new SelectController();
        } else {
            return instance;
        }
    }
}
