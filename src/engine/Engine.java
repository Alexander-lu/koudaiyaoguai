package engine;

import acm.program.ConsoleProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Engine extends ConsoleProgram {

  //    public static final String GAME_FILE = "res/map.txt";
  public static final String GAME_FILE = "res/map-with-item.txt";
  int daoll = 0;
  private Place currPlace; // 当前所处的地点
  ArrayList<Place> places; // 保存所有的地点
  ArrayList<String> daojus = new ArrayList<>(); // 保存所有的地点
  boolean gameEnded; // 玩家是否退出游戏

  public void run() {
    if (loadGame()) {
      mainLoop();
    }
  }

  /** 主循环。只要玩家不选择退出，游戏就一直运行下去 */
  private void mainLoop() {
    gameEnded = false;
    moveTo(currPlace);
    while (!gameEnded) {
      println();
      println("请输入移动方向（输入\"退出\"结束游戏）");
      print("> ");

      String direction = readLine();
      switch (direction) {
        case "东":
          if (currPlace.getEast() != null) {
            moveTo(currPlace.getEast());
          } else {
            println("那边好像无路可走了……");
          }
          break;
        case "南":
          if (currPlace.getSouth() != null) {
            moveTo(currPlace.getSouth());
          } else {
            println("那边好像无路可走了……");
          }
          break;
        case "西":
          if (currPlace.getWest() != null) {
            moveTo(currPlace.getWest());
          } else {
            println("那边好像无路可走了……");
          }
          break;
        case "北":
          if (currPlace.getNorth() != null) {
            moveTo(currPlace.getNorth());
          } else {
            println("那边好像无路可走了……");
          }
          break;
        case "搜索":
          sousuo(currPlace);
          break;
        case "查看行李":
          chakan(currPlace);
          break;
        case "退出":
          gameEnded = true;
          println("欢迎再来！");
          break;
        default:
          println("你输入的命令有误，请重新输入");
      }
    }
  }

  /**
   * 移动到一个新的地点
   *
   * @param place 要移动到的目的地
   */
  private void moveTo(Place place) {
    println("你现在到了" + place.getName());
    println(place.getMessage());
    if (place.getbaowu() != null) {
      println("DEBUG - 这里有一个" + place.getbaowu());
    } else {
      println("DEBUG - 这里什么都没有。");
    }
    currPlace = place;
  }

  /**
   * 读取游戏配置，如地图等
   *
   * @return 读取成功返回true，失败返回false
   */
  private boolean loadGame() {
    places = new ArrayList<>();
    try {
      Scanner scanner = new Scanner(new File(GAME_FILE));
      loadPlaces(scanner);
      loadRoutes(scanner);
      loadDaoju(scanner);
    } catch (FileNotFoundException e) {
      println("游戏文件读取错误！");
      return false;
    }
    return true;
  }

  /**
   * 读取道路
   *
   * @param scanner 用来读取输入的scanner对象
   */
  private void loadRoutes(Scanner scanner) {
    // 读取地点
    int nPlaces = scanner.nextInt(); // 道路的数量

    String a = scanner.next();
    // 读取所有地点
    for (int i = 0; i < nPlaces; i++) {
      int d = scanner.nextInt();
      String g = scanner.next();
      int f = scanner.nextInt();
      if (g.equals("西")) {
        places.get(d).setWest(places.get(f));
        places.get(f).setEast(places.get(d));
      }
      if (g.equals("东")) {
        places.get(d).setEast(places.get(f));
        places.get(f).setWest(places.get(d));
      }
      if (g.equals("南")) {
        places.get(d).setSouth(places.get(f));
        places.get(f).setNorth(places.get(d));
      }
      if (g.equals("北")) {
        places.get(d).setNorth(places.get(f));
        places.get(f).setSouth(places.get(d));
      }
    }
    // 设置初始地点，也就是读进来的第一个地点
  }

  /**
   * 读取地点
   *
   * @param scanner 用来读取输入的scanner对象
   */
  private void loadPlaces(Scanner scanner) {
    // 读取地点
    int nPlaces = scanner.nextInt(); // 地点的数量
    daoll = nPlaces;
    String a = scanner.nextLine();

    // 读取所有地点
    for (int i = 0; i < nPlaces; i++) {
      int bianhao = scanner.nextInt();
      String placeName = scanner.next(); // 地点的名称
      String message = scanner.next();
      Place place = new Place(placeName, message, bianhao); // 从Place类别中创建一个实例，并
      places.add(place); // 将这个地点保存进places中
    }

    // 设置初始地点，也就是读进来的第一个地点
    currPlace = places.get(0);
  }
  /**
   * 读取宝物
   *
   * @param scanner 用来读取输入的scanner对象
   */
  private void loadDaoju(Scanner scanner) {
    // 读取宝物
    int nPlaces = scanner.nextInt(); // 宝物的数量
    String a = scanner.next();
    // 读取所有地点
    for (int i = 0; i < nPlaces; i++) {
      int d = scanner.nextInt();
      String g = scanner.next();
      for (int k = 0; k < daoll; k++) {
        if (places.get(k).getbianhao() == d) {
          places.get(k).setbaowu(g);
        }
      }
    }
    // 设置初始地点，也就是读进来的第一个地点

  }

  private void sousuo(Place place) {
    if (place.getbaowu() != null) {
      println("DEBUG2 - 你找到了一个" + place.getbaowu() + "！");
      daojus.add(place.getbaowu());
      this.currPlace.setbaowu(null);
    } else {
      println("DEBUG2 - 你什么没找到。");
    }
  }

  private void chakan(Place place) {
    if (daojus.isEmpty()) {
      println("你的行李箱内空无一物。");
    } else {
      println("你现在共有" + daojus.size() + "个道具，依次是：");
      for (int k = 0; k < daojus.size(); k++) {
        int l = k + 1;
        println(l + "." + daojus.get(k));
      }
    }
  }
}