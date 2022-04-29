package engine;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

import java.util.ArrayList;
import java.util.Random;

public class Summoner  {
    RandomGenerator randomGenerator = RandomGenerator.getInstance();
    public String SummonerName;//名字
    public int age;//年龄
    ArrayList<Item> backpack;//道具背包


    public void npcBattle(ArrayList<Pokemon> a, ArrayList<Pokemon> b, ConsoleProgram program, int bkey,boolean end , Engine bgc,Place run) {
        bgc.changeMusic( "res/mp3/Pokemon-fight2.mp3"); //与npc对战音乐
        boolean ifSelectPokemon= true;
        while (ifSelectPokemon) {
        int key = -1;
        if (isaAllDead(a)) {
                program.println("你输了");
                deadExit(end);
                break;
        }
        checkYourPokemon(a, program);
        boolean selectePokemon = true;
        while (selectePokemon) {
            program.println("输入名字来选择出战的宝可梦");
            program.print("> ");
            String pickYourPokemon = program.readLine();
            for (int i = 0; i < a.size(); i++) {
                if (pickYourPokemon.equals(a.get(i).name)) {
                    key = i;
                    selectePokemon = false;
                } else {
                }
            }
        }
        program.println(a.get(key).toString());
        program.println(b.get(bkey).toString());
        while (true) {
            if (isbDead(b, bkey)) {
                ifSelectPokemon= false;
                program.println("恭喜你赢得了战斗");
                a.get(key).xp+=25;
                a.get(key).levelup(program);
               bgc.changeMusic("res/mp3/Pokemon-gymFightSuccess03.mp3");
                bgc.pause(1600*3);
                bgc.changeMusic("res/mp3/Pokemon-outsideWalk02.mp3");
                break;
            }
            if (isbAlive(b, bkey)) {
                Random random = new Random();
                int type = random.nextInt(7);
                switch (type){
                    case 1:Skill.撞击(a.get(key));
                        program.println("对方使用了撞击"+a.get(key).name+"还剩下"+a.get(key).curHp + "点血量");
                        break;
                    case 2:Skill.叫声(a.get(key));
                        program.println("对方使用了叫声"+a.get(key).name+"还剩下"+a.get(key).curHp + "点血量");
                        break;
                    case 3:Skill.飞叶快刀(a.get(key));
                        program.println("对方使用了飞叶快刀"+a.get(key).name+"还剩下"+a.get(key).curHp + "点血量");
                        break;
                    case 4:Skill.抓(a.get(key));
                        program.println("对方使用了抓"+a.get(key).name+"还剩下"+a.get(key).curHp + "点血量");
                        break;
                    case 5:Skill.龙卷风(a.get(key));
                        program.println("对方使用了龙卷风"+a.get(key).name+"还剩下"+a.get(key).curHp + "点血量");
                        break;
                    case 6:Skill.起风(a.get(key));
                        program.println("对方使用了起风"+a.get(key).name+"还剩下"+a.get(key).curHp + "点血量");
                        break;
                    default:break;
                }
            }

            if (isaDead(a, key)) {
                program.println("你的宝可梦死了，你需要选择一只新的宝可梦加入战斗");
                a.remove(key);
                break;
            }

            program.println();
            // 每一回合都首先从玩家开始行动
            String userChoice = choose("请选择你的行动", program, "zhandou", "beibao", "huanjinglin", "taopao");
            if (userChoice.equals("zhandou")) {
                if (a.get(key).name.equals("xiaojue")) {
                    boolean ifStopThis = true;
                    while (ifStopThis) {
                        Picture.小锯鳄战斗(program);
                        program.println("小锯鳄有技能：抓 水枪 咬碎 蛮力");
                        program.println("（输入技能名称来使用技能");
                        program.print("> ");
                        String skillname1 = program.readLine();
                        switch (skillname1) {
                            case "抓":
                                Skill.抓(b.get(bkey));
                                program.println(b.get(bkey).toString());
                                program.println(a.get(key).toString());
                                program.println(b.get(bkey).toString());
                                ifStopThis = false;
                                break;
                            case "水枪":
                                Skill.水枪(b.get(bkey));
                                program.println(b.get(bkey).toString());
                                program.println(a.get(key).toString());
                                program.println(b.get(bkey).toString());
                                ifStopThis = false;
                                break;
                            case "咬碎":
                                Skill.咬碎(b.get(bkey));
                                program.println(b.get(bkey).toString());
                                program.println(a.get(key).toString());
                                program.println(b.get(bkey).toString());
                                ifStopThis = false;
                                break;
                            case "蛮力":
                                Skill.蛮力(b.get(bkey), a.get(key));
                                program.println(b.get(bkey).toString());
                                program.println(a.get(key).toString());
                                program.println(b.get(bkey).toString());
                                ifStopThis = false;
                                break;
                            default:
                                program.println("你输入的命令有误，请重新输入");
                        }
                    }
                }
                if (a.get(key).name.equals("huoqiushu")) {
                    boolean ifStopThis = true;
                    while (ifStopThis) {
                        Picture.火球鼠战斗(program);
                        program.println("火球鼠有技能：喷火 瞪眼 舍身冲撞 变圆");
                        program.println("（输入技能名称来使用技能");
                        program.print("> ");
                        String skillname1 = program.readLine();
                        switch (skillname1) {
                            case "喷火":
                                Skill.喷火(b.get(bkey));
                                program.println(b.get(bkey).toString());
                                program.println(a.get(key).toString());
                                program.println(b.get(bkey).toString());
                                ifStopThis = false;
                                break;
                            case "瞪眼":
                                Skill.瞪眼(b.get(bkey));
                                program.println(b.get(bkey).toString());
                                program.println(a.get(key).toString());
                                program.println(b.get(bkey).toString());
                                ifStopThis = false;
                                break;
                            case "舍身冲撞":
                                Skill.舍身冲撞(b.get(bkey), a.get(key));
                                program.println(b.get(bkey).toString());
                                program.println(a.get(key).toString());
                                program.println(b.get(bkey).toString());
                                ifStopThis = false;
                                break;
                            case "变圆":
                                Skill.变圆(a.get(key));
                                program.println(a.get(key).toString());
                                program.println(a.get(key).toString());
                                program.println(b.get(bkey).toString());
                                ifStopThis = false;
                                break;
                            default:
                                program.println("你输入的命令有误，请重新输入");
                        }
                    }
                }
                if (a.get(key).name.equals("jucaoye")) {
                    boolean ifStopThis = true;
                    while (ifStopThis) {
                        Picture.菊草叶战斗(program);
                        program.println("菊草叶有技能：撞击 叫声 飞叶快刀 光合作用");
                        program.println("（输入技能名称来使用技能");
                        program.print("> ");
                        String skillname1 = program.readLine();
                        switch (skillname1) {
                            case "撞击":
                                Skill.撞击(b.get(bkey));
                                program.println(b.get(bkey).toString());
                                program.println(a.get(key).toString());
                                program.println(b.get(bkey).toString());
                                ifStopThis = false;
                                break;
                            case "叫声":
                                Skill.叫声(b.get(bkey));
                                program.println(b.get(bkey).toString());
                                program.println(a.get(key).toString());
                                program.println(b.get(bkey).toString());
                                ifStopThis = false;
                                break;
                            case "飞叶快刀":
                                Skill.飞叶快刀(b.get(bkey));
                                program.println(b.get(bkey).toString());
                                program.println(a.get(key).toString());
                                program.println(b.get(bkey).toString());
                                ifStopThis = false;
                                break;
                            case "光合作用":
                                Skill.光合作用(a.get(key));
                                program.println(a.get(key).toString());
                                program.println(a.get(key).toString());
                                program.println(b.get(bkey).toString());
                                ifStopThis = false;
                                break;
                            default:
                                program.println("你输入的命令有误，请重新输入");
                        }
                    }
                }
                if (a.get(key).name.equals("kedaya")) {
                    boolean ifStopThis = true;
                    while (ifStopThis) {
                        program.println("可达鸭有技能：乱抓 瞬间失忆 摇尾巴 水泡");
                        program.println("（输入技能名称来使用技能");
                        program.print("> ");
                        String skillname1 = program.readLine();
                        switch (skillname1) {
                            case "乱抓 ":
                                Skill.乱抓(b.get(bkey));
                                program.println(b.get(bkey).toString());
                                program.println(a.get(key).toString());
                                program.println(b.get(bkey).toString());
                                ifStopThis = false;
                                break;
                            case "瞬间失忆":
                                Skill.瞬间失忆(a.get(key));
                                program.println(a.get(key).toString());
                                program.println(a.get(key).toString());
                                program.println(b.get(bkey).toString());
                                ifStopThis = false;
                                break;
                            case "摇尾巴":
                                Skill.摇尾巴(b.get(bkey));
                                program.println(b.get(bkey).toString());
                                program.println(a.get(key).toString());
                                program.println(b.get(bkey).toString());
                                ifStopThis = false;
                                break;
                            case "水泡":
                                Skill.水泡(b.get(bkey));
                                program.println(b.get(bkey).toString());
                                program.println(a.get(key).toString());
                                program.println(b.get(bkey).toString());
                                ifStopThis = false;
                                break;
                            default:
                                program.println("你输入的命令有误，请重新输入");
                        }
                    }
                }
                continue;
            }
            else if (userChoice.equals("beibao")) {
                continue;
            }
            else if (userChoice.equals("huanjinglin")) {
                break;
            }
            else if (userChoice.equals("taopao")) {
//                boolean success = randomGenerator.nextBoolean();
//                if (success) {
//                    program.println("逃跑成功！");
//                    bgc.moveTo(run.getEast());
//                    ifSelectPokemon= false;
//                    break;
//                } else {
                    program.println("逃跑失败！");
                program.println("...");
                program.println("你退无可退了！");
//                }
            }

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
    public String choose(String prompt, ConsoleProgram program, String... choices) {
        // 将选择用逗号连起来
        String concatenatedChoices = String.join(", ", choices);
        // 最终的提示语
        String actualPrompt = String.format("%s (%s): ", prompt, concatenatedChoices);
        // 如果玩家的输入无效，就提示玩家重新输入
        while (true) {
            program.print(actualPrompt);
            String userChoice = program.readLine();
            // 逐个对比，看是否相等
            for (String choice : choices) {
                if (userChoice.equals(choice)) {
                    return choice;
                }
            }
            program.println("您的选择无效，请重新输入。");
        }
    }


    /**
     * 查看你的宝可梦数量
     */
    private void checkYourPokemon(ArrayList<Pokemon> a, ConsoleProgram program) {
        if (a.isEmpty()) {
            program.println("你没有宝可梦。");
        } else {
            program.println("你现在共有" + a.size() + "只宝可梦，依次是：");
            for (int k = 0; k < a.size(); k++) {
                int l = k + 1;
                program.println(l + "." + a.get(k).getName() + " 等级：" + a.get(k).getLevel());
            }
        }
    }

    /**
     * 判断玩家宝可梦是否死亡
     */
    protected boolean isaDead(ArrayList<Pokemon> a, int key) {
        return a.get(key).curHp <= 0;
    }

    /**
     * 判断玩家宝可梦是否全部死亡
     */
    private boolean isaAllDead(ArrayList<Pokemon> a) {
        return a.isEmpty();
    }

    /**
     * 判断敌人宝可梦是否死亡
     */
    protected boolean isbDead(ArrayList<Pokemon> b, int bkey) {
        return b.get(bkey).curHp <= 0;
    }
    /**
     * 判断敌人宝可梦是否死亡
     */
    protected boolean isbAlive(ArrayList<Pokemon> b, int bkey) {
        return b.get(bkey).curHp > 0;
    }

    /**
     * 判断敌人宝可梦是否全部死亡
     */
    protected boolean isbAllDead(ArrayList<Pokemon> b) {
        return b.isEmpty();
    }
    /**
     * 玩家宝可梦全部死亡后退出游戏的方法
     */
    private void deadExit(boolean gameEnded) {
        gameEnded = true;
    }
}


