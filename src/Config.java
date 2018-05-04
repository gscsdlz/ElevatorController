public class Config {
    public static int ONE_MINUTE = 1200;  //真实世界的一分钟与程序中的一分钟的毫秒数关系

    public static int ELEVATO_SPEED = ONE_MINUTE / 30; //电梯运行速度
    public static int POP_PUSH_SPEED = ONE_MINUTE / 15; //上下一个人所需的时间
    public static int OPEN_CLOSE_SPEED = ONE_MINUTE / 15; //开关门时间

    public static int MAX_MINTUES = 20; //最大分钟
    public static int HIGH_VALUE = 40;  //每分钟人数峰值
    public static int QUEUE_SIZE = 20;  //电梯等待区大小
    public static int ELEVATO_SIZE = 10; //电梯容量
    public static int REFRESH_TIME = 20;   //界面刷新定时  20ms

    public static boolean ELEVATO_STOP = true;   //计时停止后 电梯是否停止

    public static boolean DEBUG = true;   //是否启用调试
}
