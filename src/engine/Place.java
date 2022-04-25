package engine;

public class Place {
    private String message;
    private String baowu;
    private String name;    // 当前地点名称
    private Place north;    // 北边的地点
    private Place south;    // 南边的地点
    private Place east;     // 东边的地点
    private Place west;     // 西边的地点
    private int bianhao;

    public Place(String name,String message,int bianhao) {
        this.name = name;
        this.message = message;
        this.bianhao= bianhao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Place getNorth() {
        return north;
    }

    public void setNorth(Place north) {
        this.north = north;
    }

    public Place getSouth() {
        return south;
    }

    public void setSouth(Place south) {
        this.south = south;
    }

    public Place getEast() {
        return east;
    }

    public void setEast(Place east) {
        this.east = east;
    }

    public Place getWest() {
        return west;
    }

    public void setWest(Place west) {
        this.west = west;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message= message;
    }
    public String getbaowu() {
        return baowu;
    }
    public void setbaowu(String baowu) {
        this.baowu= baowu;
    }
    public int getbianhao() {
        return bianhao;
    }
    public void setbianhao(int bianhao) {
        this.bianhao= bianhao;
    }
}
