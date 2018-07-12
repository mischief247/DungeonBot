package com.mischief247.dungeonbot;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class GameManager {
    private static File file = new File("./active_games.txt");
    private HashMap<String, String> games = new HashMap<>();

    public GameManager() {
    }

    public void load() {

    }
    public void save(){

    }
}
