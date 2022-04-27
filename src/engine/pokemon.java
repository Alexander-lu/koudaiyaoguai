package engine;

import static engine.RPG.randomGenerator;

public class pokemon {
    public String name;     // 玩家和敌人都有名字
    public int level;       // 玩家和敌人都有等级
    public int maxHp;       // 玩家和敌人都有最大血量
    public int curHp;       // 玩家和敌人都有当前血量
    public int maxAtt;      // 玩家和敌人都有攻击力下限
    public int minAtt;      // 玩家和敌人都有攻击力上限


    public pokemon() {
    }

    public pokemon(String name, int level) {
        this.name = name;
        this.level = level;
        initialize();
    }

    /**
     * 攻击另一个生物，可以是玩家攻击敌人，也可以是敌人攻击玩家。
     *
     * @param other 被攻击的生物
     * @return 本次攻击造成的伤害值
     */
    public int attack(pokemon other) {
        int damage = randomGenerator.nextInt(minAtt, maxAtt);
        other.curHp -= damage;
        return damage;
    }

    public int getCurHp() {
        return curHp;
    }
    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }

    /**
     * 设置当前血量，不能超过该生物的最大血量
     *
     * @param hp 设置为的血量
     */
    public void setCurrHp(int hp) {
        if (hp >= maxHp) {
            curHp = maxHp;
        } else {
            curHp = hp;
        }
    }

    /**
     * 当前所有状态信息
     * @return 包含了所有信息的字符串
     */
    public String toString() {
        return "『" + name + "』" + "是等级为" + level  + "，当前有血量" + curHp + "/" + maxHp + "，攻击力是" + minAtt + "-" + maxAtt;
    }

    /**
     * 初始化玩家/敌人，此方法应该在子类中被overridden
     */
    void initialize() {
    }

//    public String hello() {
//        return "Hello，我是一个生物，Creature！";
//    }
//
//    public String helloWorld() {
//        return "Hello, World!";
//    }
}