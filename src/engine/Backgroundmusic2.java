package engine;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public interface Backgroundmusic2 {



        Play gamestart = new Play("res/mp3/gamestart.mp3");
        Play pokemonCave = new Play("res/mp3/Pokemon-cave.mp3");
        Play Fight01 = new Play("res/mp3/Pokemon-fight.mp3");
        Play Fight02 = new Play("res/mp3/Pokemon-fight2.mp3");
        Play Fight03 = new Play("res/mp3/Pokemon-fight3.mp3");
        Play gymFightSuccess01 = new Play("res/mp3/gymFightSuccess.mp3");
        Play gymFightSuccess02 = new Play("res/mp3/gymFightSuccess02.mp3");
        Play gymFightSuccess03 = new Play("res/mp3/gymFightSuccess03.mp3");
        Play outsideWalk01 = new Play("res/mp3/outsideWalk.mp3");
        Play outsideWalk02 = new Play("res/mp3/outsideWalk02.mp3");
        Play passengerFight = new Play("res/mp3/passengerFight.mp3");
        Play pokemonRecovery = new Play("res/mp3/pokemonRecovery.mp3");
        Play receiveItems = new Play("res/mp3/receiveItems.mp3");
        Play wildPokemonFightSuccess01 = new Play("res/mp3/wildPokemonFightSuccess.mp3");
        Play wildPokemonFightSuccess02 = new Play("res/mp3/wildPokemonFightSuccess.mp3");



        class Play implements Runnable {

            private Player player = null;
            private Thread thread = null;

            public Play(String file) {
                try {
                    player = new Player(new FileInputStream(file));
                } catch (JavaLayerException | FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            public void run() {
                try {
                    player.play();
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


