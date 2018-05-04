public class RandController  extends Thread{

    private PublicPool poolInstance;
    private int index = 0;
    private int peopleID = 0;

    public RandController(PublicPool pool) {
        this.poolInstance = pool;
    }

    public void run() {
        while(time());
    }

    public void add() {
        People p = new People();
        p.beginFloor = (int)(Math.random() * 13);
        p.id = peopleID++;

        while(true) {
            p.endFloor = (int)(Math.random() * 13);
            if(p.endFloor != p.beginFloor)
                break;
        }

        poolInstance.push(p);
    }

    public boolean time() {
        if(index == Config.MAX_MINTUES) {
            return false;
        }
        int num = Main.possionNumber[index++];

        for(int i = 0; i < num; i++) {
            add();
            try {
                Thread.sleep((Config.ONE_MINUTE - 10) / num);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        return true;
    }

    public int getPeopleID() {
        return peopleID;
    }
}
