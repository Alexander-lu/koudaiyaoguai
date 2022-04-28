package engine;

public final class Skill {
    public String skillName;
    public String skillIntroduction;
    public static void  抓精灵(Pokemon pokemon){
        pokemon.curHp = 0;
    }
    /**
     * 菊草叶的技能
     */
    public static void  撞击(Pokemon pokemon){
        pokemon.curHp -= 40;
    }
    public static void  叫声(Pokemon pokemon){
        pokemon.attack -= 40;
    }
    public static void  飞叶快刀(Pokemon pokemon){
        pokemon.curHp -= 55;
    }
    public static void  光合作用(Pokemon playerpokemon){
        playerpokemon.curHp += 40;
    }
    /**
     * 火球鼠的技能
     */
    public static void  喷火(Pokemon pokemon){
        pokemon.curHp -= 40;
    }
    public static void  瞪眼(Pokemon pokemon){
        pokemon.defence -= 40;
    }
    public static void  舍身冲撞(Pokemon pokemon,Pokemon playerpokemon){
        pokemon.curHp -= 120;
        playerpokemon.curHp -= 40;
    }
    public static void  变圆(Pokemon playerpokemon){
        playerpokemon.defence += 40;
    }
    /**
     * 小锯鳄的技能
     */
    public static void  抓(Pokemon pokemon){
        pokemon.curHp -= 40;
    }
    public static void  水枪(Pokemon pokemon){
        pokemon.curHp -= 40;
    }
    public static void  咬碎(Pokemon pokemon){
        pokemon.curHp -= 40;
        pokemon.defence -= 40;
    }
    public static void  蛮力(Pokemon pokemon,Pokemon playerpokemon){
        pokemon.curHp -= 120;
        playerpokemon.defence -= 40;
        playerpokemon.attack -= 40;
    }
    /**
     * 可达鸭的技能
     */
    public static void  乱抓(Pokemon pokemon){
        pokemon.curHp -= 40;
    }
    public static void  瞬间失忆(Pokemon playerpokemon){
        playerpokemon.defence += 40;
    }
    public static void  摇尾巴(Pokemon pokemon){
        pokemon.defence -= 40;
    }
    public static void  水泡(Pokemon pokemon){
        pokemon.curHp -= 120;
    }
    /**
     * 小拉达的技能:撞击，摇尾巴，猛撞，咬碎
     */
    public static void  猛撞(Pokemon pokemon,Pokemon playerpokemon){
        pokemon.curHp -= 120;
        playerpokemon.curHp -= 40;
    }
    /**
     * 尾立的技能:抓，叫声，变圆，乱抓
     */
    /**
     * 波波的技能:撞击，起风，龙卷风，羽毛舞
     */
    public static void  起风(Pokemon pokemon){
        pokemon.curHp -= 40;
    }
    public static void  龙卷风(Pokemon pokemon){
        pokemon.defence -= 40;
    }
    public static void  羽毛舞风(Pokemon pokemon){
        pokemon.attack -= 40;
    }
}
