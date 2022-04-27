package engine;

import acm.util.RandomGenerator;

public class Playerpokemon extends Pokemon {
    RandomGenerator randomGenerator = RandomGenerator.getInstance();
    public int xp;
//    宝可梦的经验值
    public int hpPotions;
//    宝可梦的血瓶
    public Playerpokemon() {};
    public Playerpokemon(String name) {
        super(name, 1);
    }
    /**
     * 获得经验值
     * @param enemy 被玩家杀死的敌人
     * @return      增长的经验值
     */
    public int gainXp(Pokemon enemy) {
        int baseXp = 20;
        int randomXp = randomGenerator.nextInt(0, 40);
        int gainedXp = enemy.level * (baseXp + randomXp);
        xp += gainedXp;
        return gainedXp;
    }

    /**
     * 检查玩家是否该升级
     */
    public boolean checkLevelUp() {
        if (xp >= level * 40) {
            xp = xp - (level * 40);
            level++;
            levelUp();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算玩家当前拥有的经验值和升级所需要的经验值
     * @return 包含了经验值信息的字符串
     */
    public String getXp() {
        return xp + "/" + (level * 40);
    }

    /**
     * 对玩家的各种属性进行初始化
     */
    @Override
    public void initialize() {
        int baseHp = 0;
        int baseAttack = 0;
        xp = 0;
        hpPotions = 3;
        level = 1;
//        switch (role) {
//            case "战士":
//                baseHp = 16;
//                baseAttack = 10;
//                break;
//            case "游侠":
//                baseHp = 13;
//                baseAttack = 13;
//                break;
//            case "术士":
//                baseHp = 12;
//                baseAttack = 14;
//                break;
//        }
//        maxHp = randomGenerator.nextInt(1, 6) + baseHp;
//        curHp = maxHp;
//         = randomGenerator.nextInt(1, 6) + baseAttack;
//        minAtt = maxAtt - 3;


    }

    /**
     * 升级
     */
    public void levelUp() {
        int hpMaxIncrease = 0;
        int attackMaxIncrease = 0;
//        switch (role) {
//            case "战士":
//                hpMaxIncrease = 12;
//                attackMaxIncrease = 7;
//                break;
//            case "游侠":
//                hpMaxIncrease = 10;
//                attackMaxIncrease = 19;
//                break;
//            case "术士":
//                hpMaxIncrease = 8;
//                attackMaxIncrease = 11;
//                break;
//
//        }
        maxHp += randomGenerator.nextInt(0, hpMaxIncrease);
//        maxAtt += randomGenerator.nextInt(0, attackMaxIncrease);
//        minAtt = maxAtt - 3;
        curHp = maxHp;

    }



}
