package engine;
import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Engine extends ConsoleProgram {
  public static final RandomGenerator randomGenerator = RandomGenerator.getInstance();
  public static final String GAME_FILE = "res/map-starting-area.txt";
  private static final int DELAY = 1600;
  Enemypokemon enemypokemon = new Enemypokemon();
  int daoll = 0;
//  统计玩家到达研究所的次数,以此触发博士和我的对话.
  int yanJiuSuoCount = 1;
  private Place currPlace; // 当前所处的地点
  ArrayList<Place> places; // 保存所有的地点
  ArrayList<String> daojus = new ArrayList<>(); // 保存所有的道具
  ArrayList<Playerpokemon> playerpokemon = new ArrayList<>();
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
      /** 如果玩家第一次到研究所,触发和博士的对话 */
      if (yanJiuSuoCount==1){
        if(currPlace.getbianhao()== 1){
          boShiTalking();
          yanJiuSuoCount++;
        }
      }
      /** 如果玩家进入草丛，触发战斗 */
      if (currPlace.getbianhao()>4){
        enemypokemon.generateRandomEnemy();
      }
      println();
      println("你要？（输入\"退出\"结束游戏）（输入\"搜索\"获取道具）（输入\"查看道具）（输入\"东南西北\"进入下一个地点）");
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
        case "查看道具":
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
    println("现在我们的研究工作很忙。希望你能去，当然要给你配一个精灵搭档。");
    pause(DELAY);
    println("最近找到的珍贵的精灵，你选一个吧，有小锯鳄，火球鼠和菊草叶");
    pause(DELAY);
//    输出3只精灵的图片
//    小锯鳄为城都地区中，空木博士给新人训练家的三只神奇宝贝之一，个性较为好动，喜欢跳舞。有看到眼前活动的物体会忍不住一口咬下去的习性。结构发达的大下颚，咬碎物品的力量非常大，在对战中有很大的发挥空间.
//    火球鼠是一种小型的双足宝可梦，身体上部有着浅蓝色的绒毛，暗面呈奶黄色。火球鼠看上去像针鼹和鼩鼱的结合。其针鼹的特征源自于背部窜出的火焰，而从整体形态方面来讲与鼩鼱的体型特征相近。火球鼠天性胆小，受到惊吓时总是将身体缩成球形。它自背部的红色斑点中喷出火焰，并用以自卫。
//    菊草叶是种主要色调是淡绿色的小型神奇宝贝，头上有一片深绿色的叶子，脖子长著一圈芽。它最大的特点是头上的大叶子，叶片长度常常超过身体其他部份的长度。
    println("小锯鳄个性较为好动，喜欢跳舞。有看到眼前活动的物体会忍不住一口咬下去的习性。结构发达的大下颚，咬碎物品的力量非常大，在对战中有很大的发挥空间。");
    pause(DELAY);
    println("火球鼠是一种小型的双足宝可梦，身体上部有着浅蓝色的绒毛，暗面呈奶黄色。火球鼠看上去像针鼹和鼩鼱的结合。其针鼹的特征源自于背部窜出的火焰，而从整体形态方面来讲与鼩鼱的体型特征相近。火球鼠天性胆小，受到惊吓时总是将身体缩成球形。它自背部的红色斑点中喷出火焰，并用以自卫。");
    pause(DELAY);
    pause(DELAY);
    println("菊草叶是种主要色调是淡绿色的小型神奇宝贝，头上有一片深绿色的叶子，脖子长著一圈芽。它最大的特点是头上的大叶子，叶片长度常常超过身体其他部份的长度。");
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
      loadEnemypokemon();
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
   * 读取宝可梦的信息
   */
private void loadEnemypokemon(){
//  代号美后
    Enemypokemon meiHou = new Enemypokemon();
    meiHou.name="美后";
    meiHou.level=3;
//    代号菊草叶
    Enemypokemon juYeChao = new Enemypokemon();
    juYeChao.name="菊草叶";
    juYeChao.level=5;
//    代号阿童
    Enemypokemon xiaoNiao = new Enemypokemon();
    xiaoNiao.name="小鸟";
    xiaoNiao.level = 3;
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
      println("你的道具箱内空无一物。");
    } else {
      println("你现在共有" + daojus.size() + "个道具，依次是：");
      for (int k = 0; k < daojus.size(); k++) {
        int l = k + 1;
        println(l + "." + daojus.get(k));
      }
    }
  }
  /**
   * 玩家和敌人对战
   */
  private void battle() {
    // 回合制循环进行，直至某一方阵亡
    while (true) {
      println();
      // 每一回合都首先从玩家开始行动
      String userChoice = choose("请选择你的行动", "攻击", "逃跑", "补血", "查看状态");
      if (userChoice.equals("查看状态")) {
//        printPlayerStatus();
        continue;
      } else if (userChoice.equals("补血")) {
//        useHealthPotion();
//        printPlayerStatus();
        continue;
      } else if (userChoice.equals("逃跑")) {
        boolean success = randomGenerator.nextBoolean();
        if (success) {
          println("逃跑成功！");
          break;
        } else {
          println("逃跑失败！");
        }
      } else if (userChoice.equals("攻击")) {
//        attackEnemy();
//        printEnemyStatus();
      }
      if (isEnemyDead()) {
        // 如果敌人阵亡，玩家经验值提升
        println(String.format("你杀死了%s。\n", enemypokemon.name));
//        println("你获得了" + player.gainXp(enemy) + "点经验值。");
//        if(player.checkLevelUp()){
//          println("你升级了！血量恢复满格！");
//        }
//        println("你当前拥有" + playerpokemon.xp + "点经验值。");
//        printPlayer();
        break;
      } else {
        // 没阵亡则轮到敌人行动
//        attackPlayer();
//        printPlayerStatus();
        // 如果敌人将玩家打死，游戏结束
//        if (isPlayerDead()) {
//          println("你挂了！");
//          break;
//        }
      }
    }
  }
  /**
   * 提示用户做选择，如果用户的选择无效，就让用户重新输入
   *
   * @param prompt  提示语
   * @param choices 允许的几种选择
   * @return 玩家最终的选择
   */
  public final String choose(String prompt, String... choices) {
    // 将选择用逗号连起来
    String concatenatedChoices = String.join(", ", choices);
    // 最终的提示语
    String actualPrompt = String.format("%s (%s): ", prompt, concatenatedChoices);

    // 如果玩家的输入无效，就提示玩家重新输入
    while (true) {
      print(actualPrompt);
      String userChoice = readLine();

      // 逐个对比，看是否相等
      for (String choice : choices) {
        if (userChoice.equals(choice)) {
          return choice;
        }
      }

      println("您的选择无效，请重新输入。");
    }
  }
  /**
   * 打印玩家信息
   */
//  private void printPlayer() {
//    String message = String.format("『%s』是等级为%d的%s，当前有血量%d/%d，攻击力是%d-%d。",
//            player.name, player.level, player.role, player.curHp,player.maxHp, player.minAtt, player.maxAtt);
//    println(message);
//  }
  /**
   * 打印敌人信息
   */
  private void printEnemy() {
    String message = String.format("『%s』是等级为%d的%s，当前有血量%d/%d，攻击力是%d-%d。",
            enemypokemon.name, enemypokemon.level, enemypokemon.role, enemypokemon.curHp,enemypokemon.maxHp, enemypokemon.minAtt, enemypokemon.maxAtt);
    println(message);
  }
  /**
   * 打印玩家的宝可梦的状态：名字，当前血量
   */
//  private void printPlayerStatus() {
//    String message = String.format("%s当前血量%d/%d。", player.name, player.curHp, player.maxHp);
//    println(message);
//  }
  /**
   * 打印敌人状态：名字，当前血量
   */
  private void printEnemyStatus() {
    String message = String.format("%s当前血量%d/%d。", enemypokemon.name, enemypokemon.curHp, enemypokemon.maxHp);
    println(message);
  }

  /**
   * 判断玩家的宝可梦是否已经死亡
   * @return 死亡返回true，未死亡返回false
   */
//  private boolean isPlayerDead() {
//    return player.curHp <= 0;
//  }
  /**
   * 判断敌人是否已经死亡
   * @return 死亡返回true，未死亡返回false
   */
  private boolean isEnemyDead() {
    return enemypokemon.curHp <= 0;
  }
}