package com.mischief247.dungeonbot;

import com.mischief247.dungeonbot.util.PropertiesManager;
import java.io.IOException;
import javax.security.auth.login.LoginException;

import com.mischief247.secret.Login;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws LoginException, InterruptedException, IOException {
        PropertiesManager.load();
        JDA jda = (new JDABuilder(AccountType.BOT)).setToken(Login.token).buildBlocking();
        jda.addEventListener(new DungeonListener());
        PropertiesManager.save();
    }
}
