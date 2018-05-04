import java.util.Comparator;

public class People {
    public int id = 0;
    public int beginFloor = 1;
    public int endFloor = 14;
    public int comingTime = 0;

    public People(int beginFloor, int endFloor, int comingTime) {
        this.beginFloor = beginFloor;
        this.endFloor = endFloor;
        this.comingTime = comingTime;
    }

    public People() {
    }

    @Override
    public String toString() {
        return id + " " + this.beginFloor + " " + this.endFloor;
    }
}
