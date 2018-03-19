public class People {
    public static int id = 1;
    public int beginFloor = 1;
    public int endFloor = 10;
    public int comingTime = 0;

    public People(int beginFloor, int endFloor, int comingTime) {
        this.beginFloor = beginFloor;
        this.endFloor = endFloor;
        this.comingTime = comingTime;
        id = id + 1;
    }

    public People() {
        id = id + 1;
    }
}
