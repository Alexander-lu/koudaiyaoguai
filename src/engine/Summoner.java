package engine;

import java.util.ArrayList;

public class Summoner extends Engine {
    public String SummonerName;//名字
    public int age;//年龄
    ArrayList<Item> backpack;//道具背包

    /**
     * 玩家和敌人对战
     */
    private void eachBattle() {
         // 回合制循环进行，直至某一方阵亡
        while (true) {
            // 每一回合都首先从玩家开始行动
            println("请选择精灵进行战斗");
            showsummoner();
            String userChoiceProp = choose("请选择你的行动", "精灵1", "精灵2", "精灵3", "逃跑");
            switch (userChoiceProp){
                case "精灵1":
                    println("你选择了" + playerpokemon.get(0));
                    String userChoice = choose("请选择你的行动", "战斗", "查看背包");
                    if (userChoice.equals("查看背包")){
                        checkBack();
                        String userChoiceItem = choose("请选择你的行动", "使用精灵球", "退出","回血");
                        if (userChoiceItem.equals("使用精灵球")) {
                            continue;
                        } else if (userChoiceItem.equals("回血")) {
                            continue;
                        } else if (userChoiceItem.equals("退出")) {
                            break;
                        }

                    }else if (userChoice.equals("战斗")){
                        attackEnemy(enemypokemon().get(0));
                        if (isEnemypokemonDead()&&enemypokemon().size==0){
                            break;
                        }else if (enemypokemon().size!=0&&){

                        }

                        break;
                    }
                case "精灵2":
                    println("你选择了" + playerpokemon.get(1));
                case "精灵3":
                    println("你选择了" + playerpokemon.get(2));
                case "逃跑":
                    boolean success = randomGenerator.nextBoolean();
                    if (success) {
                        println("逃跑成功！");
                        break;
                    } else {
                        println("逃跑失败！");
                    }
            }





//
//            String userChoice = choose("请选择你的行动", "选择精灵", "查看背包");
//            if (userChoice.equals("查看背包")) {
//                checkBack();
//                String userChoiceProp = choose("请选择你的行动", "使用精灵球", "退出");
//                if (userChoiceProp.equals("使用精灵球")) {
//
//                } else if (userChoiceProp.equals("退出")) {
//                    break;
//                }
//                continue;
//            } else if (userChoice.equals("选择精灵")) {
//                showsummoner();
//                String userChoiceProp = choose("请选择你的行动", "精灵1", "精灵2","攻击", "精灵3", "退出");
//                if (userChoiceProp.equals("精灵1")) {
//                    println("你选择了" + playerpokemon.get(0));
//
//                } else if (userChoiceProp.equals("精灵2")) {
//                    println("你选择了" + playerpokemon.get(1));
//
//                } else if (userChoiceProp.equals("精灵3")) {
//                    println("你选择了" + playerpokemon.get(2));
//
//                } else if (userChoiceProp.equals("退出")) {
//                    break;
//                }else if (userChoiceProp.equals("攻击")) {
//
//                }
//                continue;
//            }

//            if (Enemy.pokrmonDead() && Enemy.pokrmonsum() == 0) {
//                // 如果敌人的宝可梦全部阵亡，玩家经验值提升
//                println(String.format("你杀死了%s。\n", enemyName));
//                println("你获得了" + gainXp() + "点经验值。");
//                checkLevelUp();
//                break;
//            } else {
//                // 没阵亡则轮到敌人行动
//
//
//                // 如果敌人将玩家打死，游戏结束
//                if (Player.pokrmonDead() && Player.pokemonsum() == 0) {
//                    println("你挂了！");
//                    break;
//                }
//            }
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

    //行李箱的的道具
    private void checkBack() {
        if (backpack.size() == 0) {
            println("你的行李箱内空无一物。");
        } else {
            println("你现在共有" + backpack.size() + "个道具，依次是：");
            for (int i = 0; i < backpack.size(); i++) {
                println((i + 1) + "." + backpack.get(i));
            }
        }
    }

    //背包里的精灵
    private void showsummoner() {
        if (playerpokemon.size() == 0) {
            println("你的包里没有精灵。");
        } else {
            println("你现在共有" + playerpokemon.size() + "个精灵，依次是：");
            for (int i = 0; i < playerpokemon.size(); i++) {
                println((i + 1) + "." + playerpokemon.get(i));
            }
        }
    }

    //summoner拥有精灵的数量
    private int Summonersum() {
        int sum = 0;
        for (int i = 0; i < playerpokemon.size(); i++) {
            sum++;
        }
        return sum;
    }

    //宝可梦死亡就消失
    private void pokrmonDead(String name) {
        playerpokemon.remove(name);
    }


}


