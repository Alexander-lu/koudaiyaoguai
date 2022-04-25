package engine;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

public class RPG extends ConsoleProgram {

    public static final RandomGenerator randomGenerator = RandomGenerator.getInstance();
    Player player = new Player();
    Enemy enemy = new Enemy();

    public void run() {

        setupPlayer();

        playScene("一座老宅");
        if (!isPlayerDead()) {
            playScene("一口枯井");
        }

        if (!isPlayerDead()) {
            println("恭喜你！通关了！");
        }

    }
    private void setupPlayer() {
        print("请输入你的名字：");
        player.name = readLine();

        player.role = choose("请选择角色", "战士", "游侠", "术士");
        println("");
        player.initialize();

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
     * 进入/触发一个新的游戏场景
     *
     * @param scene 游戏场景名称
     */
    private void playScene(String scene) {
        println(player.name + "来到了" + scene + "...");

        // 每个场景随机产生1-3个敌人
        int enemyCount = randomGenerator.nextInt(1, 3);
        for (int i = 0; i < enemyCount; i++) {
            if (isPlayerDead()) {
                println("胜败乃兵家常事，大侠请重新来过。");
                break;
            }
            enemy.generateRandomEnemy(player);
            println();
            println("你遇到了" + enemy.role + enemy.name + "。");
            battle();
        }
    }


    /**
     * 玩家和敌人对战
     */
    private void battle() {
        printPlayer();
        printEnemy();

        // 回合制循环进行，直至某一方阵亡
        while (true) {
            println();

            // 每一回合都首先从玩家开始行动
            String userChoice = choose("请选择你的行动", "攻击", "逃跑", "补血", "查看状态");
            if (userChoice.equals("查看状态")) {
                printPlayerStatus();
                continue;
            } else if (userChoice.equals("补血")) {
                useHealthPotion();
                printPlayerStatus();
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
                attackEnemy();
                printEnemyStatus();
            }

            if (isEnemyDead()) {
                // 如果敌人阵亡，玩家经验值提升
                println(String.format("你杀死了%s。\n", enemy.name));
                println("你获得了" + player.gainXp(enemy) + "点经验值。");
                if(player.checkLevelUp()){
                    println("你升级了！血量恢复满格！");
                }
                println("你当前拥有" + player.xp + "点经验值。");
                printPlayer();
                break;
            } else {
                // 没阵亡则轮到敌人行动
                attackPlayer();
                printPlayerStatus();

                // 如果敌人将玩家打死，游戏结束
                if (isPlayerDead()) {
                    println("你挂了！");
                    break;
                }
            }
        }
    }

    /**
     * 玩家攻击敌人
     */
    private void attackEnemy() {

        int damage = player.attack(enemy);

        println(String.format("%s对%s造成%d点伤害。", player.name, enemy.name, damage));
    }

    /**
     * 敌人攻击玩家
     */
    private void attackPlayer() {

        int damage = enemy.attack(player);

        println(String.format("%s对%s造成%d点伤害。", enemy.name, player.name, damage));
    }
    /**
     * 打印玩家信息
     */
    private void printPlayer() {
        String message = String.format("『%s』是等级为%d的%s，当前有血量%d/%d，攻击力是%d-%d。",
                player.name, player.level, player.role, player.curHp,player.maxHp, player.minAtt, player.maxAtt);
        println(message);
    }
    /**
     * 打印敌人信息
     */
    private void printEnemy() {
        String message = String.format("『%s』是等级为%d的%s，当前有血量%d/%d，攻击力是%d-%d。",
                enemy.name, enemy.level, enemy.role, enemy.curHp,enemy.maxHp, enemy.minAtt, enemy.maxAtt);
        println(message);
    }
    /**
     * 打印玩家状态：名字，当前血量
     */
    private void printPlayerStatus() {
        String message = String.format("%s当前血量%d/%d。", player.name, player.curHp, player.maxHp);
        println(message);
    }
    /**
     * 打印敌人状态：名字，当前血量
     */
    private void printEnemyStatus() {
        String message = String.format("%s当前血量%d/%d。", enemy.name, enemy.curHp, enemy.maxHp);
        println(message);
    }

    /**
     * 判断玩家是否已经死亡
     *
     * @return 死亡返回true，未死亡返回false
     */
    private boolean isPlayerDead() {
        return player.curHp <= 0;
    }
    /**
     * 判断敌人是否已经死亡
     *
     * @return 死亡返回true，未死亡返回false
     */
    private boolean isEnemyDead() {
        return enemy.curHp <= 0;
    }

    /**
     * 使用补血药剂，可以补血25。
     */

    private void useHealthPotion() {
        if (player.hpPotions > 0) {
            int hpIncreased = 25;
            if (hpIncreased > (player.maxHp - player.curHp)) {
                hpIncreased = player.maxHp - player.curHp;
            }
            player.curHp += hpIncreased;
            player.hpPotions--;
            println(String.format("成功回血%d。当前血量%d, 还剩%d瓶回血药。", hpIncreased, player.curHp, player.hpPotions));
        } else {
            println("回血失败：回血药已用完。");
        }
    }



}
