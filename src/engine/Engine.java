package engine;
import acm.program.ConsoleProgram;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Engine extends ConsoleProgram {
  public static final String GAME_FILE = "res/map-starting-area.txt";
  private static final int DELAY = 1600;
  int daoll = 0;
  private Place currPlace; // 当前所处的地点
  ArrayList<Place> places; // 保存所有的地点
  ArrayList<String> daojus = new ArrayList<>(); // 保存所有的地点
  boolean gameEnded; // 玩家是否退出游戏
  public String playername;
  public void run() {
    if (loadGame()) {
      mainLoop();
    }
  }

  /** 主循环。只要玩家不选择退出，游戏就一直运行下去 */
  private void mainLoop() {
    gameEnded = false;
    opentalking();
    while (!gameEnded) {
      println();
      println("你要？（输入\"退出\"结束游戏）（输入\"搜索\"获取道具）（输入\"查看行李\"查看道具）（输入\"东南西北\"进入下一个地点）");
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
   * 开场白
   */
  private void opentalking() {
    println("欢迎你来到精灵世界！");
    pause(DELAY);
    println("我叫空木，作为精灵博士受到尊敬");
    pause(DELAY);
    println("在精灵的世界里，被称为精灵的生物，无所不在。");
    pause(DELAY);
    println("人和精灵友好的玩耍，一起战斗，互相帮助，共同生活。");
    pause(DELAY);
    println("但我们并不了解精灵！在它们身上还有许多秘密!为了解开迷，我天天都在研究。");
    pause(DELAY);
    println("对了，快告诉我你叫什么名字？");
    pause(DELAY);
    print("请输入你的名字：");
    pause(DELAY);
    playername = readLine();
    println(playername+"祝你好运，希望你能抓住全部的精灵！");
    pause(DELAY);
    moveTo(currPlace);
    println("妈妈：喂 "+playername+" 空木博士在找你。可能是要你帮忙。忘了！给，你的电话修理好了");
    pause(DELAY);
    println("输入搜索获得修理好的电话");
    pause(DELAY);
  }
  /**
   * 博士和我的对话
   */
  private void boShiTalking() {
    println("喂！"+playername+"来啦。今天找你，是要你帮忙！");
    pause(DELAY);
    println("朋友中有位精灵爷爷，发现了奇怪的东西。");
    pause(DELAY);
    println("现在我们的研究工作很忙。希望你能去，当然要成为精灵搭档。");
    pause(DELAY);
    println("最近找到的珍贵的精灵，你选一个吧");
    pause(DELAY);
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