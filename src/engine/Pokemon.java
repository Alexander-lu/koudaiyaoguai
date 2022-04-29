package engine;

import acm.program.ConsoleProgram;

import java.util.ArrayList;

public class Pokemon {
    public String name;     // 精灵名字
    public int level;       // 精灵等级
    public int maxHp;       // 精灵最大血量
    public int curHp;       // 精灵当前血量
    public int attack;      // 精灵都有攻击力
    public int defence;     // 精灵都有防御力
    public int xp;
    public String skill;    // 精灵技能

    public Pokemon() {
    }

    public Pokemon(String name, int level,int maxHp,int curHp,int attack,int defence,int xp,String skill) {
        this.name = name;
        this.level = level;
        this.maxHp = maxHp;
        this.curHp = curHp;
        this.attack = attack;
        this.defence = defence;
        this.xp = xp;
        this.skill = skill;
    }

    /**
     * 攻击另一个生物，可以是玩家攻击敌人，也可以是敌人攻击玩家。
     *
     * @return 本次攻击造成的伤害值
     */

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
    @Override
    public String toString() {
        return "『" + name + "』" + "等级为" + level  + "，当前有血量" + curHp + "/" + maxHp;
    }

    /**
     * 初始化玩家/敌人，此方法应该在子类中被overridden
     */
    void initialize() {
    }

    /** 精灵列表
     * 读取宝可梦的信息
     */

    /**
     * 宝可梦升级的方法
     */
    public void levelup(ConsoleProgram program) {
        if (this.xp>=50) {
            this.xp= 0;
            this.level++;
            this.maxHp+=20;
            this.curHp=this.maxHp;
            program.println(this.name+"升级了现在的血量是"+this.maxHp);

        }

    }
    public static void loadPokemon(){
        //  代号小锯鳄
        Pokemon 小锯鳄 = new Pokemon("小锯鳄",5,43,46,55,20,0,"技能");
        //    代号菊草叶
        Pokemon 菊草叶 = new Pokemon("菊草叶",5,45,39,65,20,0,"技能");
        //    代号火球鼠
        Pokemon 火球鼠 = new Pokemon("火球鼠",5,50,43,60,0,0,"技能");
        //波波
        Pokemon 波波 = new Pokemon("波波",3,30,25,30,0,0,"技能");
        Pokemon 绿毛虫 = new Pokemon("绿毛虫",2,25,20,23,0,0,"技能");
        Pokemon 小拉达 = new Pokemon("小拉达",3,30,30,25,0,0,"技能");
        Pokemon 地鼠 = new Pokemon("地鼠",4,40,25,35,0,0,"技能");
        Pokemon 可达鸭 = new Pokemon("可达鸭",6,55,35,35,0,0,"技能");
        Pokemon 尾立 = new Pokemon("尾立",3,35,30,34,0,0,"技能");

    }
}