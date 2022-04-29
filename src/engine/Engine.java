package engine;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Engine extends ConsoleProgram implements Backgroundmusic2 {
    RandomGenerator randomGenerator = RandomGenerator.getInstance();
    /**
     * 定义窗口的宽度和高度
     */
    public static final int APPLICATION_WIDTH = 1200;
    public static final int APPLICATION_HEIGHT = 800;
    /**
     * 导入地图
     */
    public static final String GAME_FILE = "res/map-startingareafinal.txt";
    /**
     * 设置帧率
     */
    public static final int DELAY = 1600;
    /**
     * currPlace表示玩家现在所处的位置
     */
    private Place currPlace;
    ArrayList<Place> places;
    ArrayList<Item> Items = new ArrayList<>();
    ArrayList<Pokemon> playerpokemon = new ArrayList<>();
    boolean gameEnded;
    public String playername;
    Item map = new Item("地图");

    int yaoshi = 0;//打败道馆馆长获得钥匙，名字待修改

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

    /**
     * 主循环。只要玩家不选择退出，游戏就一直运行下去
     */
    private void mainLoop() {
        gameEnded = false;

        opentalking();
        /** 切换歌曲 */
        changeMusic("res/mp3/Pokemon-outsideWalk.mp3");
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
            shanDongJuQing();//进入山洞前的判断条件
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
////                                pause(DELAY);
                                println("输入小锯鳄Yes 来获得小锯鳄");
                                println("");
                                break;
                            case "小锯鳄Yes":
                                changeMusic("res/mp3/Pokemon-receiveItems.mp3");
                                println("你获得了小锯鳄！");
//                                pause(DELAY * 2);
                                changeMusic("res/mp3/Pokemon-outsideWalk.mp3");
                                Pokemon xiaoJuE = new Pokemon("小锯鳄", 1, 999, 999, 20, 20, 0, "抓 水枪 咬碎 蛮力");
                                playerpokemon.add(xiaoJuE);
                                getJinLingQiu();
                                Items.add(new Item("血瓶"));
                                ifStopThisWhile = false;
                                ifStopThisWhile1 = true;
                                break;
                            case "火球鼠":
                                Picture.火球鼠(this);
                                println("火球鼠是一种小型的双足宝可梦，身体上部有着浅蓝色的绒毛，暗面呈奶黄色。火球鼠看上去像针鼹和鼩鼱的结合。其针鼹的特征源自于背部窜出的火焰，而从整体形态方面来讲与鼩鼱的体型特征相近。");
////                                pause(DELAY);
                                println("火球鼠天性胆小，受到惊吓时总是将身体缩成球形。它自背部的红色斑点中喷出火焰，并用以自卫。");
////                                pause(DELAY);
                                println("输入火球鼠Yes 来获得火球鼠");
                                println("");
                                break;
                            case "火球鼠Yes":
                                changeMusic("res/mp3/Pokemon-receiveItems.mp3");
                                println("你获得了火球鼠！");
//                                pause(DELAY * 2);
                                changeMusic("res/mp3/Pokemon-outsideWalk02.mp3");
                                Pokemon huoQiuShu = new Pokemon("火球鼠", 1, 999, 999, 20, 20, 0, "喷火 瞪眼 舍身冲撞 变圆");
                                playerpokemon.add(huoQiuShu);
                                Pokemon xiaoJuEE = new Pokemon("小锯鳄", 1, 999, 999, 20, 20, 0, "抓 水枪 咬碎 蛮力");
                                playerpokemon.add(xiaoJuEE);
                                Pokemon juCaoYewe = new Pokemon("菊草叶", 1, 999, 999, 20, 20, 0, "撞击 叫声 飞叶快刀 光合作用");
                                playerpokemon.add(juCaoYewe);
                                getJinLingQiu();
                                Items.add(new Item("血瓶"));
                                ifStopThisWhile = false;
                                ifStopThisWhile1 = true;
                                break;
                            case "菊草叶":
                                Picture.菊草叶(this);
                                println("菊草叶是种主要色调是淡绿色的小型神奇宝贝，头上有一片深绿色的叶子，脖子长著一圈芽。它最大的特点是头上的大叶子，叶片长度常常超过身体其他部份的长度。");
////                                pause(DELAY);
                                println("输入菊草叶Yes 来获得菊草叶");
                                println("");
                                break;
                            case "菊草叶Yes":
                                changeMusic("res/mp3/Pokemon-receiveItems.mp3");
                                println("你获得了菊草叶！");
//                                pause(DELAY * 2);
                                changeMusic("res/mp3/Pokemon-outsideWalk02.mp3");
                                Pokemon juCaoYe = new Pokemon("菊草叶", 1, 999, 999, 20, 20, 0, "撞击 叫声 飞叶快刀 光合作用");
                                playerpokemon.add(juCaoYe);
                                getJinLingQiu();
                                Items.add(new Item("血瓶"));
                                ifStopThisWhile = false;
                                ifStopThisWhile1 = true;
                                break;
                            default:
                                println("你输入的命令有误，请重新输入");
                        }
                    }
                }
            }

            /**道馆剧情*/
            daoGuanJuQing();

            /**山洞剧情*/
            shanDongJuQing();

            /** 如果玩家进入草丛，触发战斗 */
            caoCongShuaGuai();
            /** 如果玩家进入山洞，触发战斗 */
            shanDongShuaGuai();

            println();
            println("你要？（输入\"退出\"结束游戏）（输入\"搜索\"获取道具）（输入\"道具\"查看道具）（输入\"打开地图\"使用地图）（输入\"东南西北\"进入下一个地点）（输入\"宝可梦\"查看你的宝可梦）");
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
                    chakan();
                    break;
                case "宝可梦":
                    checkYourPokemon();
                    break;
                case "打开地图":
                    map.openMap(currPlace);
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
     * 用于循环播放歌曲和切换歌曲
     */
    public Play gamestart;

    /**
     * 切换歌曲的方法
     */
    public void changeMusic(String file) {
        gamestart.stop();
        gamestart = new Play(file);
        gamestart.start();
    }

    /**
     * 开场白&妈妈和我的对话
     */
    private void opentalking() {
//        pause(DELAY*35);
//        pause(DELAY);
        changeMusic("res/mp3/Pokemon-chatWithDoctor.mp3");
//        pause(DELAY);
        println("欢迎你来到精灵世界！");
//        pause(DELAY);
        Picture.博士(this);
        println("我叫空木，作为精灵博士受到尊敬");
//        pause(DELAY);
        println("");
        println("在精灵的世界里，被称为精灵的生物，无所不在。");
//        pause(DELAY);
        println("");
        println("人和精灵友好的玩耍，一起战斗，互相帮助，共同生活。");
//        pause(DELAY);
        println("");
        println("但我们并不了解精灵！在它们身上还有许多秘密!为了解开迷，我天天都在研究。");
//        pause(DELAY);
        Picture.玩家(this);
        println("对了，快告诉我你叫什么名字？");
//        pause(DELAY);
        print("请输入你的名字：");
//        pause(DELAY);
        playername = readLine();
        println(playername + "祝你好运，希望你能抓住全部的精灵！");
//        pause(DELAY);
        moveTo(currPlace);
        Picture.妈妈(this);
        println("妈妈：喂 " + playername + " 空木博士在找你。可能是要你帮忙。忘了！给，带上你的地图");
//        pause(DELAY);
        println("");
        println("输入搜索获得修理好的地图");
    }

    /**
     * 统计玩家到达研究所的次数,以此触发博士和我的对话
     */
    int yanJiuSuoCount = 1;

    /**
     * 博士和我的第一次对话
     */
    private void boShiTalking() {
        Picture.博士(this);
        changeMusic("res/mp3/Pokemon-chatWithDoctor.mp3");
        println("喂！" + playername + "来啦。今天找你，是要你帮忙！");
//        pause(DELAY);
        println("");
        println("我最近的研究需要用到一个东西，叫做紫金石。只有小镇东边的山洞里有");
//        pause(DELAY);
        println("");
        println("我想要你我带点紫金石回来给我");
//        pause(DELAY);
        println("");
        println("现在我的研究工作很忙，没法抽出身来。当然我会给你配一个精灵搭档。");
//        pause(DELAY);
        println("");
        println("最近找到的珍贵的精灵，你选一个吧，有小锯鳄，火球鼠和菊草叶");
//        pause(DELAY);
        println("");
    }

    /**
     * 博士和我的第二次对话
     */
    private void boShiFinalTalking() {
        Picture.博士(this);
        changeMusic("res/mp3/Pokemon-chatWithDoctor.mp3");
        println(playername + "你拿到紫金石了吗？");
//        pause(DELAY);
        println("");
        println("哦，太感谢了。你帮了我一个大忙");
//        pause(DELAY);
        println("");
        println("有了紫金石，我的研究很快就能有结果了");
//        pause(DELAY);
        println("");
        println("你已经有这么多精灵搭档了啊，想要更多吗？");
//        pause(DELAY);
        println("");
        println("去京都吧，京都汇集了很多世界一流的精灵大师，去找他们切磋吧。");
//        pause(DELAY);
        println("");
    }

    /**
     * 移动到一个新的地点
     *
     * @param place 要移动到的目的地
     */
    public void moveTo(Place place) {
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

    /**
     * daoll保存地图中所有道具的数量
     */
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
            changeMusic("res/mp3/Pokemon-receiveItems.mp3");
            println("DEBUG2 - 你找到了一个" + place.getbaowu() + "！");
//            pause(DELAY*2);
            changeMusic("res/mp3/Pokemon-outsideWalk.mp3");
            Items.add(new Item (place.getbaowu()));
            this.currPlace.setbaowu(null);
        } else {
            println("DEBUG2 - 你什么没找到。");
        }
    }

    /**
     * 查看你的物品箱有哪些物品
     */
    private void chakan() {
        if (Items.isEmpty()) {
            println("你的道具箱内空无一物。");
        } else {
            println("你现在共有" + Items.size() + "个道具，依次是：");
            for (int k = 0; k < Items.size(); k++) {
                int l = k + 1;
                println(l + "." + Items.get(k).name);
            }
        }
    }

    /**
     * 判断是否有紫金石
     */
    boolean ifZijinStone = false;

    /**
     * 查看你的物品里有没有紫金石
     */
    private void checkZiJinStone() {
        for (int k = 0; k < Items.size(); k++) {
            int l = k + 1;
            if (Items.get(k).equals("紫金石")) {
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
                println(l + "." + playerpokemon.get(k).getName() + " 等级：" + playerpokemon.get(k).getLevel());
            }
        }
    }

    /**
     * 玩家和敌人对战
     */
    private void battle(Pokemon enemypokemon) {
        boolean ifSelectPokemon= true;
        while (ifSelectPokemon) {
        int key = -1;
            if (isPlayerPokemonAllDead()) {
                println("你的宝可梦已经全部死亡，游戏失败");
                deadExit();
                break;
            }
        checkYourPokemon();
        boolean selectePokemon = true; //设定循坏条件判断是否成功选取出战精灵
        while (selectePokemon) {
            println("输入名字来选择出战的宝可梦");
            print("> ");
            String pickYourPokemon = readLine();
            for (int i = 0; i < playerpokemon.size(); i++) {
                if (pickYourPokemon.equals(playerpokemon.get(i).name)) {
                    key = i;
                    selectePokemon = false;
                } else {
                }
            }
        }

        println(playerpokemon.get(key).toString());
        println(enemypokemon.toString());
      while (true) {
        if (isEnemyDead(enemypokemon)) {
            ifSelectPokemon= false;
            changeMusic("res/mp3/Pokemon-outsideWalk.mp3");
          break;
        }
        if (isEnemyAlive(enemypokemon)) {
          Random random = new Random();
          int type = random.nextInt(7);
          switch (type) {
            case 1:
              Skill.撞击(playerpokemon.get(key));
              println("对方使用了撞击"+playerpokemon.get(key).name+"血量减少了40点，还剩下"+playerpokemon.get(key).curHp + "点血量");
              break;
            case 2:
              Skill.叫声(playerpokemon.get(key));
                println("对方使用了撞击"+playerpokemon.get(key).name+"血量减少了40点，还剩下"+playerpokemon.get(key).curHp + "点血量");
              break;
            case 3:
              Skill.飞叶快刀(playerpokemon.get(key));
                println("对方使用了撞击"+playerpokemon.get(key).name+"血量减少了55点，还剩下"+playerpokemon.get(key).curHp + "点血量");
              break;
            case 4:
              Skill.抓(playerpokemon.get(key));
                println("对方使用了撞击"+playerpokemon.get(key).name+"血量减少了40点，还剩下"+playerpokemon.get(key).curHp + "点血量");
              break;
            case 5:
              Skill.龙卷风(playerpokemon.get(key));
                println("对方使用了撞击"+playerpokemon.get(key).name+"血量减少了40点，还剩下"+playerpokemon.get(key).curHp + "点血量");
              break;
            case 6:
              Skill.起风(playerpokemon.get(key));
                println("对方使用了撞击"+playerpokemon.get(key).name+"血量减少了40点，还剩下"+playerpokemon.get(key).curHp + "点血量");
              break;
            default:
              break;
          }
        }
        if (isPlayerPokemonAllDead()) {
            println("你输了");
            deadExit();
            ifSelectPokemon= false;
          break;
        }
        if (isPlayerPokemonDead(playerpokemon.get(key))) {
              println("你的宝可梦死了，你需要选择一只新的宝可梦加入战斗");
              playerpokemon.remove(key);
              break;
          }
        println();
        // 每一回合都首先从玩家开始行动
        String userChoice = choose("请选择你的行动", "战斗", "背包", "换精灵", "逃跑");
        if (userChoice.equals("战斗")) {
          if (playerpokemon.get(key).name.equals("小锯鳄")) {
              Picture.小锯鳄战斗(this);
            boolean ifStopThis = true;
            while (ifStopThis) {
              println("小锯鳄有技能：抓 水枪 咬碎 蛮力");
              println("（输入技能名称来使用技能");
              print("> ");
              String skillname1 = readLine();
              switch (skillname1) {
                case "抓":
                  Skill.抓(enemypokemon);
                    println(playerpokemon.get(key).toString());
                    println(enemypokemon.toString());
                  ifStopThis = false;
                  break;
                case "水枪":
                  Skill.水枪(enemypokemon);
                    println(playerpokemon.get(key).toString());
                    println(enemypokemon.toString());
                  ifStopThis = false;
                  break;
                case "咬碎":
                  Skill.咬碎(enemypokemon);
                    println(playerpokemon.get(key).toString());
                    println(enemypokemon.toString());
                  ifStopThis = false;
                  break;
                case "蛮力":
                  Skill.蛮力(enemypokemon, playerpokemon.get(key));
                    println(playerpokemon.get(key).toString());
                    println(enemypokemon.toString());
                  ifStopThis = false;
                  break;
                default:
                  println("你输入的命令有误，请重新输入");
              }
            }
          }
          if (playerpokemon.get(key).name.equals("火球鼠")) {
              Picture.火球鼠战斗(this);
            boolean ifStopThis = true;
            while (ifStopThis) {
              println("火球鼠有技能：喷火 瞪眼 舍身冲撞 变圆");
              println("（输入技能名称来使用技能");
              print("> ");
              String skillname1 = readLine();
              switch (skillname1) {
                case "喷火":
                  Skill.喷火(enemypokemon);
                    println(playerpokemon.get(key).toString());
                    println(enemypokemon.toString());
                  ifStopThis = false;
                  break;
                case "瞪眼":
                  Skill.瞪眼(enemypokemon);
                    println(playerpokemon.get(key).toString());
                    println(enemypokemon.toString());
                  ifStopThis = false;
                  break;
                case "舍身冲撞":
                  Skill.舍身冲撞(enemypokemon, playerpokemon.get(key));
                    println(playerpokemon.get(key).toString());
                    println(enemypokemon.toString());
                  ifStopThis = false;
                  break;
                case "变圆":
                  Skill.变圆(playerpokemon.get(key));
                    println(playerpokemon.get(key).toString());
                    println(enemypokemon.toString());
                  ifStopThis = false;
                  break;
                default:
                  println("你输入的命令有误，请重新输入");
              }
            }
          }
          if (playerpokemon.get(key).name.equals("菊草叶")) {
              Picture.菊草叶战斗(this);
            boolean ifStopThis = true;
            while (ifStopThis) {
              println("菊草叶有技能：撞击 叫声 飞叶快刀 光合作用");
              println("（输入技能名称来使用技能");
              print("> ");
              String skillname1 = readLine();
              switch (skillname1) {
                case "撞击":
                  Skill.撞击(enemypokemon);
                    println(playerpokemon.get(key).toString());
                    println(enemypokemon.toString());
                  ifStopThis = false;
                  break;
                case "叫声":
                  Skill.叫声(enemypokemon);
                    println(playerpokemon.get(key).toString());
                    println(enemypokemon.toString());
                  ifStopThis = false;
                  break;
                case "飞叶快刀":
                  Skill.飞叶快刀(enemypokemon);
                    println(playerpokemon.get(key).toString());
                    println(enemypokemon.toString());
                  ifStopThis = false;
                  break;
                case "光合作用":
                  Skill.光合作用(playerpokemon.get(key));
                    println(playerpokemon.get(key).toString());
                    println(enemypokemon.toString());
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
                  Skill.乱抓(enemypokemon);
                    println(playerpokemon.get(key).toString());
                    println(enemypokemon.toString());
                  ifStopThis = false;
                  break;
                case "瞬间失忆":
                  Skill.瞬间失忆(playerpokemon.get(key));
                    println(playerpokemon.get(key).toString());
                    println(enemypokemon.toString());
                  ifStopThis = false;
                  break;
                case "摇尾巴":
                  Skill.摇尾巴(enemypokemon);
                    println(playerpokemon.get(key).toString());
                    println(enemypokemon.toString());
                  ifStopThis = false;
                  break;
                case "水泡":
                  Skill.水泡(enemypokemon);
                    println(playerpokemon.get(key).toString());
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
            chakan();
            boolean selectePokemon2 = true; //设定循坏条件判断是否成功选取出战精灵
            while (selectePokemon2) {
                println("输入名字来选择使用的道具");
                print("> ");
                String pickYourItem = readLine();
                for (int i = 0; i < Items.size(); i++) {
                    if (pickYourItem.equals(Items.get(i).name)) {
                        switch (pickYourItem){
                            case "精灵球":Item a = new Item();
                            a.catchPokemon(playerpokemon,enemypokemon,this);
                                Skill.抓精灵(enemypokemon);
                            break;
                            case "血瓶":Item bb = new Item();
                                bb.useHpBottle(playerpokemon.get(key),this);
                                break;
                            default:
                        }
                        selectePokemon2 = false;
                    }
                }
            }
          continue;
        } else if (userChoice.equals("换精灵")) {
break;
        } else if (userChoice.equals("逃跑")) {
          boolean success = randomGenerator.nextBoolean();
          if (success) {
            println("逃跑成功！");
              changeMusic("res/mp3/Pokemon-outsideWalk02.mp3");
              ifSelectPokemon= false;
            break;
          } else {
            println("逃跑失败！");
          }
        }
}
        }
    }

    /**
     * 判断敌人宝可梦是否死亡
     */
    protected boolean isEnemyDead(Pokemon pokemon) {
        return pokemon.curHp <= 0;
    }
    /**
     * 判断敌人宝可梦是否活着
     */
    protected boolean isEnemyAlive(Pokemon pokemon) {
        return pokemon.curHp > 0;
    }

    /**
     * 提示用户做选择，如果用户的选择无效，就让用户重新输入
     *
     * @param prompt  提示语
     * @param choices 允许的几种选择
     * @return 玩家最终的选择
     */
    public String choose(String prompt, String... choices) {
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
     * 道馆的剧情
     */
    private void daoGuanJuQing() {
        /**道馆入口*/
        if (currPlace.getbianhao() == 43 && daoGuanCount == 1) {
            daoGuanRuKou();
        }
        /**道馆前院*/
        if (daoGuanCount == 1) {
            if (currPlace.getbianhao() == 44) {
                daoGuanQianYuan();
                daoGuanCount++;
            }
        }

        /**道馆内院*/
        if (daoGuanCount == 2) {
            if (currPlace.getbianhao() == 48) {
                daoGuanNeiYuan();
                daoGuanCount++;
            }
        }

        /**道馆正房*/
        if (daoGuanCount == 3) {
            if (currPlace.getbianhao() == 51) {
                daoGuanZhengFang();
                daoGuanCount++;
            }
        }

    }

    /**
     * 道馆入口提示
     */
    private void daoGuanRuKou() {
//        pause(DELAY);
        println("");
        println(playername + "来到飞行道馆，开始挑战。");
//        pause(DELAY);
        println("");
    }

    int daoGuanCount = 1;

    /**
     * 第一次到达道馆前院触发
     * 剧情文案结束后自动前进到晾晒小院进行战斗
     */
    private void daoGuanQianYuan() {
//        pause(DELAY);
        println("");
        println("此时，" + playername + "他们已经到达了道馆的门口。");
//        pause(DELAY);
        println("");
        println(playername + "说：“好，我们已经到了，昂首挺胸进去吧！” 他说完就向入口走去。");
//        pause(DELAY);
        println("");
        println("“等一下，" + playername + "！”pokemon叫住" + playername + "，“我总觉得这个入口有点不大对劲！”");
//        pause(DELAY);
        println("");
        println("“什么啊？你别吓我啦！”" + playername + "不以为然，继续走进去。");
//        pause(DELAY);
        println("");
        println("说时迟，那时快，一个晾衣架飞向" + playername + "，" + playername + "被突如其来的一幕惊呆了。");
//        pause(DELAY);
        println("");
        println("“" + playername + "，快躲开啊！”pokemon他们心都急得快跳出嗓子眼了，却丝毫帮不上忙。");
//        pause(DELAY);
        println("");
        println("在这时，道馆的西方突然出现了一道闪光，" + playername + "来不及多想，急忙俯身，避开了衣架的袭击。");
//        pause(DELAY);
        println("");
        println("一个人影闪身到他们面前，警惕的说道。");
//        pause(DELAY);
        println("");
        println("“HEY！YOU！WHO ARE YOU！ME在这里晾内裤竟然发现YOU擅闯道馆？？！！”陌生男子惊道。");
//        pause(DELAY);
        println("");
        println("“这个啊，我们要穿越东边的山洞，但是山洞入口被阿速馆长控制了。”" + playername + "解释道，“我们是来打败馆长，拿取钥匙的。”");
//        pause(DELAY);
//        pause(DELAY);
        println("");
        println("“WHAT？？！！用别人的东西，还要把人家打一顿。到底YOU是反派还是ME是反派......就没有想过问馆长打个借条......”");
//        pause(DELAY);
        println("");
        println("“诶？对啊！那这样......我们设计的战斗系统不就白费了......别废话，就先拿你练手。”");
//        pause(DELAY);
        println("");
        println("“挑战ME的YOU实在是不知死活！ME的精灵是NO.1！在战场上是不败的~！YOU也会和以前那些不知死活的敌人一样，麻痹着败北吧！”");
//        pause(DELAY);
        println("");
        println("你来到了前院西面的晾晒小院，进入战斗。");
        moveTo(currPlace.getWest());
        battleEnemyA();
//        pause(DELAY);
        println("");
        println("“OH~NO~！YOU真的厉害~OK~YOU可以从ME的身上过去。诶诶！别踩脸啊！”");
    }

    /**
     * 第一次到达道馆内院
     */
    private void daoGuanNeiYuan() {
//        pause(DELAY);
        println("");
        println("“呼~~刚才的战斗还真是惊险，一个小弟还是有两下子的，不知道会不会遇到更大的阻力......啊！！！！！！”" + playername + "话还没说完，大地忽然颤动起来。");
//        pause(DELAY);
        println("");
        println("“" + playername + ",这......这是怎么回事？”pokemon很惊慌。");
//        pause(DELAY);
        println("");
        println("“该不会是地震了吧。”" + playername + "扶稳pokemon道。");
//        pause(DELAY);
        println("");
        println("“不！这不是地震！而是其它的......”");
//        pause(DELAY);
        println("");
        println("“的确如此，这只不过是我走路引发的而已。”一只体型巨大的自然精灵出现了。");
//        pause(DELAY);
        println("");
        println("“你好。”pokemon上前搭话到，“请你帮我们把钥匙拿到好吗？”");
//        pause(DELAY);
        println("");
        println("“不可以！”精灵回答的很干脆，“数百年前我便身负使命要保护这里的一切，不论是一花一草，还是一砖一瓦，都不可被侵犯。”");
//        pause(DELAY);
        println("");
        println("“你在说什么啊？”" + playername + "上前说道，“馆长现在一人将硕大的山洞据为己有，私自占有公共资源，难道你都不管吗？你还是自然精灵吗？”");
//        pause(DELAY);
        println("");
        println("“我只是尽我的责任去守护者他而已。”自然之灵平静地说，并没有因为" + playername + "的语气而生气。");
//        pause(DELAY);
        println("");
        println("“可是......”" + playername + "刚想说什么，就吃惊的发现：“它变色了，它被精灵病毒控制了！！！！！！它现在应该是被馆长PUA了！！！！！！”");
//        pause(DELAY);
        println("");
        println("“哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦！我要杀了你们”自然之灵突然暴走，不顾一切地向" + playername + "他们扑来......");
        battleEnemyB();
//        pause(DELAY);
        println("");
        println("“竟然如此强劲......！比预想中的还要高呢......！......:~(”自然之灵虚弱倒在地上，还不明白自己为何竟羸弱至此，“原来......原来我一直是被利用了吗......可恶......你们......可以帮我报仇嘛。我的力量都被馆长吸收了，你们一定要小心。”");
    }

    /**
     * 第一次到达道馆正房
     */
    private void daoGuanZhengFang() {
//        pause(DELAY);
        println("");
        println("阿速：“我是飞行道馆的馆长阿速！”");
//        pause(DELAY);
        println("");
        println("阿速：“世界上的飞行类宝可梦一旦遭遇电击就很容易受伤。”");
//        pause(DELAY);
        println("");
        println("阿速：“受伤的宝可梦没法继续飞行了...”");
//        pause(DELAY);
        println("");
        println("阿速：“我把受伤的宝可梦都放在山洞里，保护的很好。”");
//        pause(DELAY);
        println("");
        println("阿速：“有我在，谁也别想进入山洞。”");
        battleEnemyC();
//        pause(DELAY);
        println("");
        println("“纵使你打败了我......我也不会告诉你钥匙就在旁边的耳房里......噗（吐血状）..........”阿速败北。");
        yaoshi++;
    }

    /**
     * 和enemyA战斗
     */
    private void battleEnemyA() {
        if (currPlace.getbianhao() == 45 & ifStopThisWhile1) {
            Summoner enemyA = new Summoner();
            ArrayList<Pokemon> A = new ArrayList<>();
            Pokemon 尾立 = new Pokemon("尾立", 2, 40, 40, 20, 20, 0, "");
//            Pokemon 金 = new Pokemon("金", 2, 40, 40, 20, 20, 0, "");
//            Pokemon 绿毛虫 = new Pokemon("绿毛虫", 2, 40, 40, 20, 20, 0, "");
            A.add(尾立);
//            A.add(金);
//            A.add(绿毛虫);
            enemyA.npcBattle(playerpokemon, A, this, 0,gameEnded,this,currPlace);
//            enemyA.npcBattle(playerpokemon, A, this, 1,gameEnded,this,currPlace);
//            enemyA.npcBattle(playerpokemon, A, this, 2,gameEnded,this,currPlace);
        }
    }

    /**
     * 和enemyB战斗
     */
    private void battleEnemyB() {
        if (currPlace.getbianhao() == 48 & ifStopThisWhile1) {
            Summoner enemyB = new Summoner();
            ArrayList<Pokemon> B = new ArrayList<>();
            Pokemon 尾立 = new Pokemon("尾立", 2, 40, 40, 20, 20, 0, "");
            Pokemon 金 = new Pokemon("金", 2, 40, 40, 20, 20, 0, "");
//            Pokemon 绿毛虫 = new Pokemon("绿毛虫", 2, 40, 40, 20, 20, 0, "");
            B.add(尾立);
            B.add(金);
//            B.add(绿毛虫);
            enemyB.npcBattle(playerpokemon, B, this, 0,gameEnded,this,currPlace);
            enemyB.npcBattle(playerpokemon, B, this, 1,gameEnded,this,currPlace);
//            enemyB.npcBattle(playerpokemon, B, this, 2,gameEnded,this,currPlace);
        }
    }

    /**
     * 和enemyC战斗
     */
    private void battleEnemyC() {
        if (currPlace.getbianhao() == 51 & ifStopThisWhile1) {
            Summoner enemyC = new Summoner();
            ArrayList<Pokemon> C = new ArrayList<>();
            Pokemon 尾立 = new Pokemon("尾立", 2, 40, 40, 20, 20, 0, "");
            Pokemon 金 = new Pokemon("金", 2, 40, 40, 20, 20, 0, "");
            Pokemon 绿毛虫 = new Pokemon("绿毛虫", 2, 40, 40, 20, 20, 0, "");
            C.add(尾立);
            C.add(金);
            C.add(绿毛虫);
            enemyC.npcBattle(playerpokemon, C, this, 0,gameEnded,this,currPlace);
            enemyC.npcBattle(playerpokemon, C, this, 1,gameEnded,this,currPlace);
            enemyC.npcBattle(playerpokemon, C, this, 2,gameEnded,this,currPlace);
        }
    }



    /**
     * 判断山洞的变量
     */
    int shanDong30Count = 0;
    int shanDong41Count = 0;
    /**
     * 山洞的剧情
     */
    private void shanDongJuQing() {
        openShanDon();//进入山洞前的判断条件

        /**山洞30剧情*/
        if (shanDong30Count == 0){
        if (currPlace.getbianhao() == 30) {
            shanDong30();
            shanDong30Count++;
        }}

        /**山洞41剧情*/
        if (shanDong41Count == 0) {
            if (currPlace.getbianhao() == 41) {
                shanDong41();
                shanDong41Count++;
            }
        }
    }

    private void openShanDon() {

        if (yaoshi == 0) {
            if (currPlace.getbianhao() == 28) {//需要插入音乐
                println("旁边的大爷：...");
////                pause(DELAY);
                println("旁边的大爷：前面是山洞，里面生活着许多奇特的宝可梦。");
////                pause(DELAY);
                println("旁边的大爷：穿越山洞，就可以通往下一个城市了。");
                println("旁边的大爷：...");
                println("旁边的大爷：这座山被飞行道馆的馆长霸占了，进入山洞需要钥匙。你身上有钥匙吗？");
                println("旁边的大爷：没有的话，去打败飞行道馆馆长把！");
            } else if (currPlace.getbianhao() == 29) {
                println("没有钥匙！，无法进入山洞");
                currPlace = places.get(28);
                moveTo(currPlace);

            }
        } else if (yaoshi == 1) {
            if (currPlace.getbianhao() == 29){
            println("使用钥匙进入了山洞");
            changeMusic("res/mp3/Pokemon-cave.mp3");
            currPlace = places.get(30);
            moveTo(currPlace);
            }
        }
    }

    private void shanDong30() {
//        pause(DELAY);
        println("");
        println("“好，我们终于进来了，快走吧。”pokemon说，“已经来到了山洞。“一定要把紫金石找到！”" + playername + "说。");
//        pause(DELAY);
        println("");
        println("“紫金石？你们说的是紫金石吗？”突然，地面上有只大岩蛇钻了出来。“竟敢觊觎我们祖传的宝物，我要让你们尝尝苦头！”大岩蛇使出了岩石封杀。");
//        pause(DELAY);
        println("");
        println("“干嘛？紫金石是用来做研究的，对人和精灵都要做出贡献的，不是吗？”" + playername + "使出了泡沫光线，岩石封杀没打中。");
//        pause(DELAY);
        println("");
        println("“哈哈哈哈，原来如此，这么看起来你们还是执迷不悟，我来做你们的对手！”大岩蛇使出了龙息打" + playername + "，" + playername + "麻痹了。");
//        pause(DELAY);
        println("");
        println("“" + playername + "，你没事吧？”pokemon大声叫道。");
//        pause(DELAY);
        println("");
        println("“接下来就轮到你了！”大岩蛇又使出铁尾，想攻击pokemon。");
//        pause(DELAY);
        println("");
        println("“会让你得逞吗？”" + playername + "用尽浑身力气，使出了泡沫光线，大岩蛇被打中了。");
//        pause(DELAY);
        println("");
        println("“哈哈哈，没事没事，这么点小伤还算不了什么......你们等着，我还会回来的！”大岩蛇使出了龙息。");
//        pause(DELAY);
        println("");
        println("说完，大岩蛇就沿着墙边飞快地爬进了山洞深处，刹那间消失不见。");
    }

    private void shanDong41() {
//        pause(DELAY);
        println("");
        println("“哈哈，没想到你们还真是契而不舍，竟然找到了我的老巢，既然如此......只有鱼死网破了！！”大岩蛇癫狂道。");
//        pause(DELAY);
        println("");
        println("“不会让你这么做的。”" + playername + "，“上了！”");
//        pause(DELAY);
        println("");
        println("“好！”" + playername + "和pokemon使出了电之潮旋，大岩蛇被打中了，疼得动不了。");
//        pause(DELAY);
        println("");
        println("“趁现在，接招吧，大岩蛇！”pokemon使出了铁尾。");
//        pause(DELAY);
        println("");
        println("“我才不吃这套呢！”大岩蛇使出了岩石封杀，pokemon被打中，速度下降了。");
//        pause(DELAY);
        println("");
        println("“该死。”pokemon又站了起来。");
//        pause(DELAY);
        println("");
        println("“没错，不过该死的那个是你！”大岩蛇使出了龙息。");
//        pause(DELAY);
        println("");
        println("“pokemon！！！！”这时，" + playername + "冲了过来，使出了潮水，龙息和潮水引起了一场爆炸。");
//        pause(DELAY);
        println("");
        println("“没用的，让你们尝尝这个绝招吧！”大岩蛇使出了岩石利刃，" + playername + "和pokemon都被打中了。");
//        pause(DELAY);
        println("");
        println("“我就说嘛，你们根本不是我的对手。”");
//        pause(DELAY);
        println("");
        println("“那我呢？”不知道什么时候，妈妈突然出现在大岩蛇身后，对大岩蛇“嘭！嘭！”就是两拳。");
//        pause(DELAY);
        println("");
        println("“哈......你......你趁我不备，背后偷袭，不讲武德！！”");
//        pause(DELAY);
        println("");
        println("“还没完！”说着，妈妈从身后拿出家里的菜刀、菜板、葱、姜、蒜、干辣椒、砂锅......似乎是准备现场制作一道蛇肉羹。");
//        pause(DELAY);
        println("");
        println("“你不要过来啊！！看我的，岩石利刃！！”");
//        pause(DELAY);
        println("");
        println("岩石利刃打中了" + playername + "，" + playername + "失去战斗能力了。");
//        pause(DELAY);
        println("");
        println("“你这混蛋，竟敢这样伤害" + playername + "！我不会放过你的！”妈妈抬手便要结果了大岩蛇。");
//        pause(DELAY);
        println("");
        println("就在此刻，一阵眩人眼目的紫光闪过，挡住了下落的刀锋，并将大岩蛇恢复了正常，又带着妈妈一起消失了......");
//        pause(DELAY);
        println("");
        println("片刻后，一阵铃声响起，拿起一看是妈妈打来的。");
//        pause(DELAY);
        println("");
        println("“喂，" + playername + "啊，我怎么突然之间到家了，刚才那个食材怎么也不见了。");
//        pause(DELAY);
        println("");
        println("原来，传说中的紫金石，不仅能保护这里的动物，还有传送的神奇能力......这块石头还有太多的秘密等待挖掘了......");
//        pause(DELAY);
        println("");
        println("”哦！对了妈妈，您是怎么知道我在哪里的呀？“");
//        pause(DELAY);
        println("");
        println("”这还不简单，你手机里的那些APP争先恐后地在报告你的位置呐“");
    }

    /**
     * 获得精灵球的方法
     */
    private void getJinLingQiu() {
Items.add(new Item("精灵球"));
    }

    /**
     * 判断玩家宝可梦是否死亡
     */
    private boolean isPlayerPokemonDead(Pokemon pokemon) {
        return pokemon.curHp <= 0;
    }

    /**
     * 判断玩家宝可梦是否全部死亡
     */
    private boolean isPlayerPokemonAllDead() {
        return playerpokemon.isEmpty() && yanJiuSuoCount != 1;
    }

    /**
     * 玩家宝可梦全部死亡后退出游戏的方法
     */
    private void deadExit() {
        gameEnded = true;
    }

    /**
     * 添加后门的方法
     */
    private void houMen() {

    }

    /**
     * 统计玩家有没有选择初始小精灵 用于判断是否刷怪
     */
    boolean ifStopThisWhile = true;
    boolean ifStopThisWhile1 = false;

    /**
     * 添加草丛刷怪的方法
     */
    private void caoCongShuaGuai() {
        if (currPlace.getbianhao() == 22 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {

                Pokemon 地鼠 = new Pokemon("地鼠", 4, 40, 40, 20, 20, 0, "");
                Picture.地鼠(this);
                changeMusic("res/mp3/Pokemon-fight3.mp3");
                battle(地鼠);
            }
        }
        if (currPlace.getbianhao() == 17 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 尾立 = new Pokemon("尾立", 2, 30, 30, 20, 20, 0, "");
                Picture.尾立(this);
                changeMusic("res/mp3/Pokemon-fight3.mp3");
                battle(尾立);
            }
        }
        if (currPlace.getbianhao() == 8 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 波波 = new Pokemon("波波", 3, 30, 30, 20, 20, 0, "");
                changeMusic("res/mp3/Pokemon-fight3.mp3");
                Picture.波波(this);
                battle(波波);
            }
        }
        if (currPlace.getbianhao() == 10 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 小拉达 = new Pokemon("小拉达", 5, 30, 30, 20, 20, 0, "");
                Picture.小拉达(this);
                changeMusic("res/mp3/Pokemon-fight.mp3");
                battle(小拉达);
            }
        }
        if (currPlace.getbianhao() == 20 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 地鼠 = new Pokemon("地鼠", 4, 40, 40, 20, 20, 0, "");
                Picture.地鼠(this);
                changeMusic("res/mp3/Pokemon-fight2.mp3");
                battle(地鼠);
            }
        }
        if (currPlace.getbianhao() == 11 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 尾立 = new Pokemon("尾立", 2, 35, 35, 20, 20, 0, "");
                Picture.尾立(this);
                changeMusic("res/mp3/Pokemon-fight3.mp3");
                battle(尾立);
            }
        }
        if (currPlace.getbianhao() == 19 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 波波 = new Pokemon("波波", 3, 40, 40, 20, 20, 0, "");
                Picture.波波(this);
                changeMusic("res/mp3/Pokemon-fight2.mp3");
                battle(波波);
            }
        }
        if (currPlace.getbianhao() == 5 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 小拉达 = new Pokemon("小拉达", 5, 30, 30, 20, 20, 0, "");
                Picture.小拉达(this);
                changeMusic("res/mp3/Pokemon-fight3.mp3");
                battle(小拉达);
            }
        }
        if (currPlace.getbianhao() == 14 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 波波 = new Pokemon("波波", 3, 40, 40, 20, 20, 0, "");
                Picture.波波(this);
                changeMusic("res/mp3/Pokemon-fight.mp3");
                battle(波波);
            }
        }
        if (currPlace.getbianhao() == 12 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 波波 = new Pokemon("波波", 3, 40, 40, 20, 20, 0, "");
                Picture.波波(this);
                changeMusic("res/mp3/Pokemon-fight3.mp3");
                battle(波波);
            }
        }
        if (currPlace.getbianhao() == 21 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 小拉达 = new Pokemon("小拉达", 5, 30, 30, 20, 20, 0, "");
                Picture.小拉达(this);
                changeMusic("res/mp3/Pokemon-fight3.mp3");
                battle(小拉达);
            }
        }
        if (currPlace.getbianhao() == 18 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 小拉达 = new Pokemon("小拉达", 5, 30, 30, 20, 20, 0, "");
                Picture.小拉达(this);
                changeMusic("res/mp3/Pokemon-fight2.mp3");
                battle(小拉达);
            }
        }
        if (currPlace.getbianhao() == 4 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 地鼠 = new Pokemon("地鼠", 10, 40, 40, 20, 20, 0, "");
                Picture.地鼠(this);
                changeMusic("res/mp3/Pokemon-fight.mp3");
                battle(地鼠);
            }
        }
        if (currPlace.getbianhao() == 3 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 小拉达 = new Pokemon("小拉达", 10, 30, 30, 20, 20, 0, "");
                Picture.小拉达(this);
                changeMusic("res/mp3/Pokemon-fight3.mp3");
                battle(小拉达);
            }
        }
        if (currPlace.getbianhao() == 2 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 可达鸭 = new Pokemon("可达鸭", 20, 200, 200, 20, 20, 0, "");
                Picture.可达鸭(this);
                changeMusic("res/mp3/Pokemon-fight.mp3");
                battle(可达鸭);
            }
        }if (currPlace.getbianhao() == 7 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 绿毛虫 = new Pokemon("绿毛虫",2,25,20,23,0,0,"技能");
                Picture.绿毛虫(this);
                changeMusic("res/mp3/Pokemon-fight3.mp3");
                battle(绿毛虫);
            }
        }
    }

    /**
     * 添加山洞刷怪的方法
     */
    private void shanDongShuaGuai() {
        if (currPlace.getbianhao() == 29 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 地鼠 = new Pokemon("地鼠", 15, 40, 40, 20, 20, 0, "");
                Picture.地鼠(this);
                changeMusic("res/mp3/Pokemon-fight.mp3");
                battle(地鼠);
            }
        }
        if (currPlace.getbianhao() == 30 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 地鼠 = new Pokemon("地鼠", 15, 40, 40, 20, 20, 0, "");
                Picture.地鼠(this);
                changeMusic("res/mp3/Pokemon-fight2.mp3");
                battle(地鼠);
            }
        }
        if (currPlace.getbianhao() == 31 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 地鼠 = new Pokemon("地鼠", 15, 40, 40, 20, 20, 0, "");
                Picture.地鼠(this);
                changeMusic("res/mp3/Pokemon-fight3.mp3");
                battle(地鼠);
            }
        }
        if (currPlace.getbianhao() == 32 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 地鼠 = new Pokemon("地鼠", 15, 40, 40, 20, 20, 0, "");
                Picture.地鼠(this);
                changeMusic("res/mp3/Pokemon-fight.mp3");
                battle(地鼠);
            }
        }
        if (currPlace.getbianhao() == 33 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 地鼠 = new Pokemon("地鼠", 15, 1500, 1500, 20, 20, 0, "");
                Picture.地鼠(this);
                changeMusic("res/mp3/Pokemon-fight2.mp3");
                battle(地鼠);
            }
        }
        if (currPlace.getbianhao() == 36 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 地鼠 = new Pokemon("地鼠", 15, 40, 40, 20, 20, 0, "");
                Picture.地鼠(this);
                changeMusic("res/mp3/Pokemon-fight3.mp3");
                battle(地鼠);
            }
        }
        if (currPlace.getbianhao() == 37 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 金 = new Pokemon("尾立", 15, 35, 35, 20, 20, 0, "");
                Picture.尾立(this);
                changeMusic("res/mp3/Pokemon-fight.mp3");
                battle(金);
            }
        }
        if (currPlace.getbianhao() == 38 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 波波 = new Pokemon("波波", 15, 30, 30, 20, 20, 0, "");
                Picture.波波(this);
                changeMusic("res/mp3/Pokemon-fight2.mp3");
                battle(波波);
            }
        }
        if (currPlace.getbianhao() == 39 & ifStopThisWhile1) {
            boolean success = randomGenerator.nextBoolean();
            if (success) {
                Pokemon 小拉达 = new Pokemon("小拉达", 15, 30, 30, 20, 20, 0, "");
                Picture.小拉达(this);
                changeMusic("res/mp3/Pokemon-fight3.mp3");
                battle(小拉达);
            }
        }
        if (currPlace.getbianhao() == 34 & ifStopThisWhile1) {
            Summoner addddd = new Summoner();
            ArrayList<Pokemon> b = new ArrayList<>();
            Pokemon 可达鸭 = new Pokemon("可达鸭", 15, 55, 55, 20, 20, 0, "");
            Pokemon 小拉达 = new Pokemon("小拉达", 17, 30, 30, 20, 20, 0, "");
            Pokemon 波波 = new Pokemon("波波", 18, 30, 30, 20, 20, 0, "");
            b.add(可达鸭);
            b.add(小拉达);
            b.add(波波);
            addddd.npcBattle(playerpokemon, b, this, 0,gameEnded,this,currPlace);
            addddd.npcBattle(playerpokemon, b, this, 1,gameEnded,this,currPlace);
            addddd.npcBattle(playerpokemon, b, this, 2,gameEnded,this,currPlace);
        }
        if (currPlace.getbianhao() == 35 & ifStopThisWhile1) {
            Items.add(new Item("紫金石"));
        }
    }
}