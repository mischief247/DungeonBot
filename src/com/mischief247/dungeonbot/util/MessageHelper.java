package com.mischief247.dungeonbot.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MessageHelper {
    public static String getStackTraceasSting(Throwable t){
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
         return sw.toString();
    }
}
