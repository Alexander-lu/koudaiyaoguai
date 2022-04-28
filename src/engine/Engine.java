package engine;
import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Engine extends ConsoleProgram implements Backgroundmusic2{
  RandomGenerator randomGenerator = RandomGenerator.getInstance();
  /** 定义窗口的宽度和高度 */
  public static final int APPLICATION_WIDTH = 1200;
  public static final int APPLICATION_HEIGHT = 800;
  /** 导入地图 */
  public static final String GAME_FILE = "res/map-startingareafinal.txt";
  /** 设置帧率 */
  private static final int DELAY = 1600;
  /** currPlace表示玩家现在所处的位置 */
  private Place currPlace;
  ArrayList<Place> places;
  ArrayList<String> daojus = new ArrayList<>();
  ArrayList<Pokemon> playerpokemon = new ArrayList<>();
  boolean gameEnded;
  public String playername;

  @Override
  public void run() {
    /* 设定窗口背景色 */
    getConsole().setBackground(Color.gray);
    /* 设定窗口字体颜色 */
    getConsole().setForeground(Color.WHITE);
    /* 窗口大小不可更改 */
    this.setResizable(false);
    /* 调用开场背景图片 */
    Picture.gameStart(this);
    /* 设置背景音乐，并且无限循环播放 */
    gamestart = new Play("res/mp3/gamestart.mp3");
    gamestart.start();//调用音乐播放方法
    if (loadGame()) {
      mainLoop();
    }
  }
  /** 主循环。只要玩家不选择退出，游戏就一直运行下去 */
  private void mainLoop() {
    gameEnded = false;
    openMap2();
    openMap1();
    openMap3();
    opentalking();
    /** 切换歌曲 */
    changeMusic("res/mp3/Pokemon-fight.mp3");
    while (!gameEnded) {
      /** 如果玩家第二次到研究所,触发和博士的对话 */
      if (yanJiuSuoCount == 2) {
        if (currPlace.getbianhao() == 1) {
          if (ifZijinStone) {
            boShiFinalTalking();
            yanJiuSuoCount++;
          }
        }
      }
      /** 如果玩家第一次到研究所,触发和博士的对话 */
      if (yanJiuSuoCount == 1) {
        if (currPlace.getbianhao() == 1) {
          boShiTalking();
          yanJiuSuoCount++;
          while (ifStopThisWhile) {
            println("你要？（输入小锯鳄，火球鼠和菊草叶来挑选你的精灵伴侣）");
            print("> ");
            String d1 = readLine();
            switch (d1) {
              case "小锯鳄":
                Picture.小锯鳄(this);
                println("小锯鳄个性较为好动，喜欢跳舞。有看到眼前活动的物体会忍不住一口咬下去的习性。结构发达的大下颚，咬碎物品的力量非常大，在对战中有很大的发挥空间。");
                pause(DELAY);
                println("输入小锯鳄Yes 来获得小锯鳄");
                println("");
                break;
              case "小锯鳄Yes":
                Pokemon xiaoJuE = new Pokemon("小锯鳄", 1, 40, 40, 20, 20, 0, "抓 水枪 咬碎 蛮力");
                playerpokemon.add(xiaoJuE);
                ifStopThisWhile = false;
                ifStopThisWhile1 = true;
                break;
              case "火球鼠":
                Picture.火球鼠(this);
                println("火球鼠是一种小型的双足宝可梦，身体上部有着浅蓝色的绒毛，暗面呈奶黄色。火球鼠看上去像针鼹和鼩鼱的结合。其针鼹的特征源自于背部窜出的火焰，而从整体形态方面来讲与鼩鼱的体型特征相近。");
                pause(DELAY);
                println("火球鼠天性胆小，受到惊吓时总是将身体缩成球形。它自背部的红色斑点中喷出火焰，并用以自卫。");
                pause(DELAY);
                println("输入火球鼠Yes 来获得火球鼠");
                println("");
                break;
              case "火球鼠Yes":
                Pokemon huoQiuShu = new Pokemon("火球鼠", 1, 40, 40, 20, 20, 0, "喷火 瞪眼 舍身冲撞 变圆");
                playerpokemon.add(huoQiuShu);
                ifStopThisWhile = false;
                ifStopThisWhile1 = true;
                break;
              case "菊草叶":
                Picture.菊草叶(this);
                println("菊草叶是种主要色调是淡绿色的小型神奇宝贝，头上有一片深绿色的叶子，脖子长著一圈芽。它最大的特点是头上的大叶子，叶片长度常常超过身体其他部份的长度。");
                pause(DELAY);
                println("输入菊草叶Yes 来获得菊草叶");
                println("");
                break;
              case "菊草叶Yes":
                Pokemon juCaoYe = new Pokemon("菊草叶", 1, 40, 40, 20, 20, 0, "撞击 叫声 飞叶快刀 光合作用");
                playerpokemon.add(juCaoYe);
                ifStopThisWhile = false;
                ifStopThisWhile1 = true;
                break;
              default:
                println("你输入的命令有误，请重新输入");
            }
          }
        }
      }
      /** 如果玩家进入草丛，触发战斗 */
      caoCongShuaGuai();
      if(isPlayerPokemonAllDead()){
        println("你的宝可梦已经全部死亡，游戏失败");
        break;
      }
      println();
      println("你要？（输入\"退出\"结束游戏）（输入\"搜索\"获取道具）（输入\"道具\"查看道具）（输入\"东南西北\"进入下一个地点）（输入\"宝可梦\"查看你的宝可梦）");
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
        case "道具":
          chakan(currPlace);
          break;
        case "宝可梦":
          checkYourPokemon();
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
  /** 用Swing写一个打开地图的方法 */
  JFrame frame;
  JLabel label;
  Image image = null;
  /** 打开地图1 */
    private void openMap1(){
      try {
        image= ImageIO.read(new File("res/gamemap1.png"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      frame=new JFrame();
      label=new JLabel(new ImageIcon(image));
      frame.getContentPane().add(label,BorderLayout.CENTER);
      frame.pack();
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
  /** 打开地图2 */
  private void openMap2(){
    try {
      image= ImageIO.read(new File("res/gamemap2.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    frame=new JFrame();
    label=new JLabel(new ImageIcon(image));
    frame.getContentPane().add(label,BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }
  /** 打开地图3 */
  private void openMap3(){
    try {
      image= ImageIO.read(new File("res/gamemap3.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    frame=new JFrame();
    label=new JLabel(new ImageIcon(image));
    frame.getContentPane().add(label,BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }
  /** 用于循环播放歌曲和切换歌曲 */
  public Play gamestart;
  /** 切换歌曲的方法 */
    private void changeMusic(String file){
      gamestart.stop();
      gamestart = new Play(file);
      gamestart.start();
    }
  /**
   * 开场白&妈妈和我的对话
   */
  private void opentalking() {
    pause(DELAY);
    pause(DELAY);
    pause(DELAY);
    println("欢迎你来到精灵世界！");
    pause(DELAY);
    Picture.博士(this);
    println("我叫空木，作为精灵博士受到尊敬");
    pause(DELAY);
    println("");
    println("在精灵的世界里，被称为精灵的生物，无所不在。");
    pause(DELAY);
    println("");
    println("人和精灵友好的玩耍，一起战斗，互相帮助，共同生活。");
    pause(DELAY);
    println("");
    println("但我们并不了解精灵！在它们身上还有许多秘密!为了解开迷，我天天都在研究。");
    pause(DELAY);
    Picture.玩家(this);
    println("对了，快告诉我你叫什么名字？");
    pause(DELAY);
    print("请输入你的名字：");
    pause(DELAY);
    playername = readLine();
    println(playername+"祝你好运，希望你能抓住全部的精灵！");
    pause(DELAY);
    moveTo(currPlace);
    Picture.妈妈(this);
    println("妈妈：喂 "+playername+" 空木博士在找你。可能是要你帮忙。忘了！给，你的电话修理好了");
    pause(DELAY);
    println("");
    println("输入搜索获得修理好的电话");
  }
  /** 统计玩家到达研究所的次数,以此触发博士和我的对话 */
  int yanJiuSuoCount = 1;
  /**
   * 博士和我的第一次对话
   */
  private void boShiTalking() {
    Picture.博士(this);
    println("喂！"+playername+"来啦。今天找你，是要你帮忙！");
    pause(DELAY);
    println("");
    println("我最近的研究需要用到一个东西，叫做紫金石。只有小镇东边的山洞里有");
    pause(DELAY);
    println("");
    println("我想要你我带点紫金石回来给我");
    pause(DELAY);
    println("");
    println("现在我的研究工作很忙，没法抽出身来。当然我会给你配一个精灵搭档。");
    pause(DELAY);
    println("");
    println("最近找到的珍贵的精灵，你选一个吧，有小锯鳄，火球鼠和菊草叶");
    pause(DELAY);
    println("");
  }
  /**
   * 博士和我的第二次对话
   */
  private void boShiFinalTalking() {
    Picture.博士(this);
    println(playername+"你拿到紫金石了吗？");
    pause(DELAY);
    println("");
    println("哦，太感谢了。你帮了我一个大忙");
    pause(DELAY);
    println("");
    println("有了紫金石，我的研究很快就能有结果了");
    pause(DELAY);
    println("");
    println("你已经有这么多精灵搭档了啊，想要更多吗？");
    pause(DELAY);
    println("");
    println("去京都吧，京都汇集了很多世界一流的精灵大师，去找他们切磋吧。");
    pause(DELAY);
    println("");
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
//      Pokemon.loadPokemon();
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
  /** daoll保存地图中所有道具的数量 */
  int daoll = 0;
  /**
   * 读取道具
   *
   * @param scanner 用来读取输入的scanner对象
   */
  private void loadDaoju(Scanner scanner) {
    // 读取道具
    int nPlaces = scanner.nextInt(); // 道具的数量
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
  /**
   * 搜索当前地点
   */
  private void sousuo(Place place) {
    if (place.getbaowu() != null) {
      println("DEBUG2 - 你找到了一个" + place.getbaowu() + "！");
      daojus.add(place.getbaowu());
      this.currPlace.setbaowu(null);
    } else {
      println("DEBUG2 - 你什么没找到。");
    }
  }
  /**
   * 查看你的物品箱有哪些物品
   */
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
  /** 判断是否有紫金石 */
  boolean ifZijinStone = false;
  /**
   * 查看你的物品里有没有紫金石
   */
  private void checkZiJinStone() {
    for (int k = 0; k < daojus.size(); k++) {
        int l = k + 1;
        if (daojus.get(k).equals("紫金石")){
          ifZijinStone = true;
        }
      }
    }
  /**
   * 查看你的宝可梦数量
   */
  private void checkYourPokemon() {
    if (playerpokemon.isEmpty()) {
      println("你没有宝可梦。");
    } else {
      println("你现在共有" + playerpokemon.size() + "只宝可梦，依次是：");
      for (int k = 0; k < playerpokemon.size(); k++) {
        int l = k + 1;
        println(l + "." + playerpokemon.get(k).getName()+" 等级："+playerpokemon.get(k).getLevel());
      }
    }
  }
  /**
   * 玩家和敌人对战
   */
  private void battle(Pokemon enemypokemon) {
    int key = -1;
    checkYourPokemon();
    println("输入数字来选择出战的宝可梦");
    print("> ");
    String pickYourPokemon = readLine();
    switch (pickYourPokemon) {
      case "1":
         key=0;
        break;
      case "2":
        key=1;
        break;
      case "3":
        key=2;
        break;
      case "4":
        key=3;
        break;
      case "5":
        key=4;
        break;
      case "6":
        key=5;
        break;
      case "7":
        key=6;
        break;
      case "8":
        key=7;
        break;
      case "9":
        key=8;
        break;
      case "10":
        key=9;
        break;
      case "11":
        key=10;
        break;
      default:
        println("你输入的命令有误，请重新输入");
    }
    // 回合制循环进行，直至某一方阵亡
    println(playerpokemon.get(key).toString());
    println(enemypokemon.toString());
    while (true) {
      println();
      // 每一回合都首先从玩家开始行动
      String userChoice = choose("请选择你的行动", "战斗", "背包", "精灵", "逃跑");
      if (userChoice.equals("战斗")) {
        if (playerpokemon.get(key).name.equals("小锯鳄")) {
          boolean ifStopThis = true;
          while (ifStopThis) {
            println("小锯鳄有技能：抓 水枪 咬碎 蛮力");
            println("（输入技能名称来使用技能");
            print("> ");
            String skillname1 = readLine();
            switch (skillname1) {
              case "抓":
                Skill.抓(enemypokemon);
                println(enemypokemon.toString());
                ifStopThis = false;
                break;
              case "水枪":
                Skill.水枪(enemypokemon);
                println(enemypokemon.toString());
                ifStopThis = false;
                break;
              case "咬碎":
                Skill.咬碎(enemypokemon);
                println(enemypokemon.toString());
                ifStopThis = false;
                break;
              case "蛮力":
                Skill.蛮力(enemypokemon, playerpokemon.get(key));
                println(enemypokemon.toString());
                ifStopThis = false;
                break;
              default:
                println("你输入的命令有误，请重新输入");
            }
          }
        }
        if (playerpokemon.get(key).name.equals("火球鼠")) {
          boolean ifStopThis = true;
          while (ifStopThis) {
            println("火球鼠有技能：喷火 瞪眼 舍身冲撞 变圆");
            println("（输入技能名称来使用技能");
            print("> ");
            String skillname1 = readLine();
            switch (skillname1) {
              case "喷火":
                Skill.喷火(enemypokemon);
                println(enemypokemon.toString());
                ifStopThis = false;
                break;
              case "瞪眼":
                Skill.瞪眼(enemypokemon);
                println(enemypokemon.toString());
                ifStopThis = false;
                break;
              case "舍身冲撞":
                Skill.舍身冲撞(enemypokemon,playerpokemon.get(key));
                println(enemypokemon.toString());
                ifStopThis = false;
                break;
              case "变圆":
                Skill.变圆(playerpokemon.get(key));
                println(playerpokemon.get(key).toString());
                ifStopThis = false;
                break;
              default:
                println("你输入的命令有误，请重新输入");
            }
          }
        }
        if (playerpokemon.get(key).name.equals("菊草叶")) {
          boolean ifStopThis = true;
          while (ifStopThis) {
            println("菊草叶有技能：撞击 叫声 飞叶快刀 光合作用");
            println("（输入技能名称来使用技能");
            print("> ");
            String skillname1 = readLine();
            switch (skillname1) {
              case "撞击":
                Skill.撞击(enemypokemon);
                println(enemypokemon.toString());
                ifStopThis = false;
                break;
              case "叫声":
                Skill.叫声(enemypokemon);
                println(enemypokemon.toString());
                ifStopThis = false;
                break;
              case "飞叶快刀":
                Skill.飞叶快刀(enemypokemon);
                println(enemypokemon.toString());
                ifStopThis = false;
                break;
              case "光合作用":
                Skill.光合作用(playerpokemon.get(key));
                println(playerpokemon.get(key).toString());
                ifStopThis = false;
                break;
              default:
                println("你输入的命令有误，请重新输入");
            }
          }
        }
        if (playerpokemon.get(key).name.equals("可达鸭")) {
          boolean ifStopThis = true;
          while (ifStopThis) {
            println("可达鸭有技能：乱抓 瞬间失忆 摇尾巴 水泡");
            println("（输入技能名称来使用技能");
            print("> ");
            String skillname1 = readLine();
            switch (skillname1) {
              case "乱抓 ":
                Skill.乱抓 (enemypokemon);
                println(enemypokemon.toString());
                ifStopThis = false;
                break;
              case "瞬间失忆":
                Skill.瞬间失忆(playerpokemon.get(key));
                println(playerpokemon.get(key).toString());
                ifStopThis = false;
                break;
              case "摇尾巴":
                Skill.摇尾巴(enemypokemon);
                println(enemypokemon.toString());
                ifStopThis = false;
                break;
              case "水泡":
                Skill.水泡(enemypokemon);
                println(enemypokemon.toString());
                ifStopThis = false;
                break;
              default:
                println("你输入的命令有误，请重新输入");
            }
          }
        }
        continue;
      } else if (userChoice.equals("背包")) {
//        useHealthPotion();
//        printPlayerStatus();
        continue;
      } else if (userChoice.equals("精灵")) {
        checkYourPokemon();
//        useHealthPotion();
//        printPlayerStatus();
        continue;
      }else if (userChoice.equals("逃跑")) {
        boolean success = randomGenerator.nextBoolean();
        if (success) {
          println("逃跑成功！");
          break;
        } else {
          println("逃跑失败！");
        }
      }
      if (isEnemyDead(enemypokemon)){
        break;
      } else {
//        attackplayer
      }
      if(isPlayerPokemonDead(playerpokemon.get(key))){
        println("你的宝可梦死了，你需要选择一只新的宝可梦加入战斗");
      }
      if(isPlayerPokemonAllDead()){
        break;
      }
    }
    }
  /**
   * 判断敌人宝可梦是否死亡
   */
  private boolean isEnemyDead(Pokemon pokemon){
    return pokemon.curHp>0;
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
   * 捕捉宝可梦
   */
  private void catchPokemon(Pokemon enemypokemon) {

  }
  /**
   * 道馆的剧情
   */
  private void daoGuan(Pokemon enemypokemon) {

  }
  /**
   * 山洞的剧情
   */
  private void shanDong(Pokemon enemypokemon) {

  }
  /**
   * 有钥匙后山洞开门的方法
   */
  private void openShanDon(Pokemon enemypokemon) {

  }
  /**
   * 没钥匙到达山洞门口会被遣返回上一个地点的方法
   */
  private void rejiectShanDon() {

  }
  /**
   * 获得精灵球的方法
   */
  private void getJinLingQiu() {

  }
  /**
   * 判断玩家宝可梦是否死亡
   */
  private boolean isPlayerPokemonDead(Pokemon pokemon){
    return pokemon.curHp>0;
  }
  /**
   * 判断玩家宝可梦是否全部死亡
   */
  private boolean isPlayerPokemonAllDead(){
    return playerpokemon.isEmpty();
  }
  /**
   * 玩家宝可梦全部死亡后退出游戏的方法
   */
  private void deadExit(){
    gameEnded=true;
  }
  /**
   * 添加后门的方法
   */
  private void houMen() {

  }
  /** 统计玩家有没有选择初始小精灵 用于判断是否刷怪 */
  boolean ifStopThisWhile = true;
  boolean ifStopThisWhile1 = false;
  /**
   * 添加草丛刷怪的方法
   */
  private void caoCongShuaGuai(){
    if (currPlace.getbianhao() == 16 & ifStopThisWhile1) {
      boolean success = randomGenerator.nextBoolean();
      if (success) {
        Pokemon 绿毛虫 = new Pokemon("绿毛虫", 2, 40, 40, 20, 20, 0, "");
        battle(绿毛虫);
      }
    }
    if (currPlace.getbianhao() == 17 & ifStopThisWhile1) {
      boolean success = randomGenerator.nextBoolean();
      if (success) {
        Pokemon 尾立 = new Pokemon("尾立", 2, 40, 40, 20, 20, 0, "");
        battle(尾立);
      }
    }
    if (currPlace.getbianhao() == 19 & ifStopThisWhile1) {
      boolean success = randomGenerator.nextBoolean();
      if (success) {
        Pokemon 尾立 = new Pokemon("尾立", 2, 40, 40, 20, 20, 0, "");
        battle(尾立);
      }
    }
    if (currPlace.getbianhao() == 10 & ifStopThisWhile1) {
      boolean success = randomGenerator.nextBoolean();
      if (success) {
        Pokemon 尾立 = new Pokemon("尾立", 2, 40, 40, 20, 20, 0, "");
        battle(尾立);
      }
    }
    if (currPlace.getbianhao() == 14 & ifStopThisWhile1) {
      boolean success = randomGenerator.nextBoolean();
      if (success) {
        Pokemon 尾立 = new Pokemon("尾立", 2, 40, 40, 20, 20, 0, "");
        battle(尾立);
      }
    }
    if (currPlace.getbianhao() == 12 & ifStopThisWhile1) {
      boolean success = randomGenerator.nextBoolean();
      if (success) {
        Pokemon 尾立 = new Pokemon("尾立", 2, 40, 40, 20, 20, 0, "");
        battle(尾立);
      }
    }
    if (currPlace.getbianhao() == 23 & ifStopThisWhile1) {
      boolean success = randomGenerator.nextBoolean();
      if (success) {
        Pokemon 尾立 = new Pokemon("尾立", 2, 40, 40, 20, 20, 0, "");
        battle(尾立);
      }
    }
    if (currPlace.getbianhao() == 26 & ifStopThisWhile1) {
      boolean success = randomGenerator.nextBoolean();
      if (success) {
        Pokemon 尾立 = new Pokemon("尾立", 2, 40, 40, 20, 20, 0, "");
        battle(尾立);
      }
    }
  }
}