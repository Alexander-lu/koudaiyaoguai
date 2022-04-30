package engine;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public interface Backgroundmusic2 {
/**音乐库，复制到对应的场景并调用方法即可
 举例：
 Play gamestart = new Play("res/mp3/gamestart.mp3");
   播放    gamestart.start();
   暂停    gamestart.stop();
 切换歌曲：changeMusic("res/mp3/Pokemon-fight.mp3");
 * */
//  开场         Play gamestart = new Play("res/mp3/gamestart.mp3");
//  进入山洞      Play pokemonCave = new Play("res/mp3/Pokemon-cave.mp3");
//   战斗1           Play Fight01 = new Play("res/mp3/Pokemon-fight.mp3");
//   战斗2     Play Fight02 = new Play("res/mp3/Pokemon-fight2.mp3");
//   战斗3     Play Fight03 = new Play("res/mp3/Pokemon-fight3.mp3");
//   道馆战斗胜利1     Play gymFightSuccess01 = new Play("res/mp3/Pokemon-gymFightSuccess.mp3");
//   道馆战斗胜利2     Play gymFightSuccess02 = new Play("res/mp3/Pokemon-gymFightSuccess02.mp3");
//   道馆战斗胜利3     Play gymFightSuccess03 = new Play("res/mp3/Pokemon-gymFightSuccess03.mp3");
//   户外行走1     Play outsideWalk01 = new Play("res/mp3/Pokemon-outsideWalk.mp3");
//   户外行走2     Play outsideWalk02 = new Play("res/mp3/Pokemon-outsideWalk02.mp3");
//   路人战斗     Play passengerFight = new Play("res/mp3/Pokemon-passengerFight.mp3");
//   医疗站恢复     Play pokemonRecovery = new Play("res/mp3/Pokemon-pokemonRecovery.mp3");
//   获得物品     Play receiveItems = new Play("res/mp3/Pokemon-receiveItems.mp3");
//   野外精灵战斗胜利1     Play wildPokemonFightSuccess01 = new Play("res/mp3/Pokemon-wildPokemonFightSuccess.mp3");
//   野外精灵战斗胜利2     Play wildPokemonFightSuccess02 = new Play("res/mp3/Pokemon-wildPokemonFightSuccess.mp3");
        class Play implements Runnable {

            private Player player = null;
            private Thread thread = null;
            private String file;

            public Play(String files) {
                try {
                    file=files;
                    player = new Player(new FileInputStream(file));
                } catch (JavaLayerException | FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void run() {
                try {
                    player.play();
                    if (player.isComplete()) {
                        player.play();
                    }
                } catch (JavaLayerException ex) {
                    System.err.println("Problem playing audio: " + ex);
                }
            }

            public void start() {
                thread = new Thread(this, "Player thread");
                thread.start();
            }

            public void stop() {
                player.close();
                thread = null;
            }

            public Player getPlayer() {
                return player;
            }

        }
    }


