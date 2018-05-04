
public class Main {

    public static int possionNumber[] = new int[Config.MAX_MINTUES];

    public static void main(String args[]) throws InterruptedException {
        //初始化全局泊松数值
        for (int i = 0; i < Config.MAX_MINTUES; i++) {
            possionNumber[i]  = Possion.getPossion(Config.HIGH_VALUE);
        }

        Start start[]= new Start[3];

        start[0] = new Start(new NormalAlg(), 0);
        start[1] = new Start(new SingleDoubleAlg(), 1);
        start[2] = new Start(new LayerModeAlg(), 2);

        start[0].start();
        start[1].start();
        start[2].start();

    }
}
