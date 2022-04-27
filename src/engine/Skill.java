package engine;

public class Skill {
    public String skillName;
    public String skillIntroduction;
    /**
     * 菊叶草的技能
     */
    public void  撞击(Pokemon pokemon){
        pokemon.curHp -= 40;
    }
    public void  叫声(Pokemon pokemon){
        pokemon.maxAtt -= 40;
    }
    public void  飞叶快刀(Pokemon pokemon){
        pokemon.curHp -= 55;
    }
    public void  光合作用(Playerpokemon playerpokemon){
        playerpokemon.curHp += 40;
    }
    /**
     * 火球鼠的技能
     */
    public void  喷火(Pokemon pokemon){
        pokemon.curHp -= 40;
    }
    public void  瞪眼(Pokemon pokemon){
        pokemon.deFence -= 40;
    }
    public void  舍身冲撞(Pokemon pokemon,Playerpokemon playerpokemon){
        pokemon.curHp -= 120;
        playerpokemon.curHp -= 40;
    }
    public void  变圆(Playerpokemon playerpokemon){
        playerpokemon.deFence += 40;
    }
    /**
     * 小锯鳄的技能
     */
    public void  抓(Pokemon pokemon){
        pokemon.curHp -= 40;
    }
    public void  水枪(Pokemon pokemon){
        pokemon.curHp -= 40;
    }
    public void  咬碎(Pokemon pokemon){
        pokemon.curHp -= 40;
        pokemon.deFence -= 40;
    }
    public void  蛮力(Pokemon pokemon,Playerpokemon playerpokemon){
        pokemon.curHp -= 120;
        playerpokemon.deFence -= 40;
        playerpokemon.maxAtt -= 40;
    }
    /**
     * 可达鸭的技能
     */
    public void  乱抓(Pokemon pokemon){
        pokemon.curHp -= 40;
    }
    public void  瞬间失忆(Playerpokemon playerpokemon){
        playerpokemon.deFence += 40;
    }
    public void  摇尾巴(Pokemon pokemon){
        pokemon.deFence -= 40;
    }
    public void  水泡(Pokemon pokemon){
        pokemon.curHp -= 120;
    }
    /**
     * 小拉达的技能:撞击，摇尾巴，猛撞，咬碎
     */
    public void  猛撞(Pokemon pokemon,Playerpokemon playerpokemon){
        pokemon.curHp -= 120;
        playerpokemon.curHp -= 40;
    }
    /**
     * 尾立的技能:抓，叫声，变圆，乱抓
     */
    /**
     * 波波的技能:撞击，起风，龙卷风，羽毛舞
     */
    public void  起风(Pokemon pokemon){
        pokemon.curHp -= 40;
    }
    public void  龙卷风(Pokemon pokemon){
        pokemon.deFence -= 40;
    }
    public void  羽毛舞风(Pokemon pokemon){
        pokemon.maxAtt -= 40;
    }
}
