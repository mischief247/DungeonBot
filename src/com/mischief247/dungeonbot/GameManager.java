package com.mischief247.dungeonbot;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

class GameManager {
    private static final File file = new File("./active_games.txt");
    private HashMap<String, String> games = new HashMap<>();

    public void load() {
    try(Scanner sc = new Scanner(file)){
        while(sc.hasNext()){
            String[] temp = sc.nextLine().split(":");
            games.put(temp[0],temp[1]);
        }
    }catch (IOException e){
        e.printStackTrace();
    }

    }
    /*public void save(){

    }*/
}
