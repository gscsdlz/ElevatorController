public class RandController  extends Thread{

    private PublicPool poolInstance;
    private int index = 0;
    private int peopleID = 0;
    private Start instance;

    public RandController(PublicPool pool, Start s) {
        this.poolInstance = pool;
        instance = s;
    }

    public void run() {
        while(time());
    }

    public void add(People p) {
        People c = (People)p.clone();
        c.comingTime = instance.mintues * Config.ONE_MINUTE + instance.time * Config.REFRESH_TIME;
        poolInstance.push(c);
        peopleID++;
    }

    public boolean time() {
        if(index == Config.MAX_MINTUES) {
            return false;
        }
        int num = Main.possionNumber[index++];

        for(int i = 0; i < num; i++) {
            add(Main.people.get(i));
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
