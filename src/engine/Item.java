package engine;

import acm.program.ConsoleProgram;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Item {
    String name;
    int number;
    String description;//是否需要对于道具的描述

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    int hpBottle = 0;
    int ball = 0;
    /**
     * 用Swing写一个打开地图的方法
     */
    JFrame frame;
    JLabel label;
    Image image = null;

    /**
     * 打开地图1
     */
    public void openMap(Place currPlace) {
        if (currPlace.getbianhao() >= 0 && currPlace.getbianhao() <= 27) {
            openMap1();

        } else if (currPlace.getbianhao() >= 28 && currPlace.getbianhao() <= 42) {
            openMap2();
        } else if (currPlace.getbianhao() >= 43 && currPlace.getbianhao() <= 52) {
            openMap3();
        }

    }

    public void openMap1() {
        try {
            image = ImageIO.read(new File("res/mapstart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame = new JFrame();
        label = new JLabel(new ImageIcon(image));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * 打开地图3
     */
    public void openMap3() {
        try {
            image = ImageIO.read(new File("res/mapdaoguan.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame = new JFrame();
        label = new JLabel(new ImageIcon(image));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * 打开地图2
     */
    public void openMap2() {
        try {
            image = ImageIO.read(new File("res/mapshandong.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame = new JFrame();
        label = new JLabel(new ImageIcon(image));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void Item() {

    }
//    使用血瓶的方法


    public void useHpBottle(Pokemon pokemon, ConsoleProgram program) {
        if (pokemon.maxHp > 0) {
            int hpIncreased = 40;
            if (hpIncreased > (pokemon.maxHp - pokemon.curHp)) {
                hpIncreased = pokemon.maxHp - pokemon.curHp;
            }
            pokemon.curHp += hpIncreased;
            hpBottle--;
            program.println(String.format("成功回血%d。当前血量%d。", hpIncreased, pokemon.curHp));
        } else {
            program.println("回血失败：回血药已用完。");
        }
    }

//使用精灵球的方法

    /**
     * 捕捉宝可梦
     */

    public void catchPokemon(ArrayList<Pokemon> playerpokemon, Pokemon enemypokemon, ConsoleProgram program) {
        if (playerpokemon.size() < 6) {
            playerpokemon.add(enemypokemon); //玩家宝可梦集合里增加宝可梦

            program.println("你抓住了 " + enemypokemon + "!  " + enemypokemon + "的信息是：" + enemypokemon.toString());
        } else {
            program.println("你无法捕捉更多的宝可梦！");
        }
    }
}
