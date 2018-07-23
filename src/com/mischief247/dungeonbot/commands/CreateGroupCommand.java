package com.mischief247.dungeonbot.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import com.mischief247.dungeonbot.util.MessageHelper;
import com.mischief247.dungeonbot.util.MessageWithState;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.managers.GuildController;
import net.dv8tion.jda.core.requests.restaction.ChannelAction;
import net.dv8tion.jda.core.requests.restaction.PermissionOverrideAction;
import net.dv8tion.jda.core.requests.restaction.RoleAction;

public class CreateGroupCommand extends Command {

    public MessageWithState invoke(String[] args, Message message) {
        System.out.println(message.getMember().getNickname());
        System.out.println(message.getMember().getRoles());
        if (!message.getMember().getRoles().contains(message.getGuild().getRolesByName("Dungeon Master", false).get(0))) {
            return new MessageWithState("you do not have permission to use that command please speak to an admin if you believe this is in error", false);
        } else {
            GuildController gc = new GuildController(message.getGuild());
            ChannelAction channelAction = gc.createTextChannel(args[1]);
            ChannelAction voice = gc.createVoiceChannel(args[1]);
            channelAction.queue();
            voice.queue();

            boolean found  = false;
            Channel channel = null;
            Channel voiceChannel = null;
            while(!found) {
                try {
                     channel = gc.getGuild().getTextChannelsByName(args[1], false).get(0);
                     voiceChannel = gc.getGuild().getVoiceChannelsByName(args[1], false).get(0);
                    found = true;
                }catch (IndexOutOfBoundsException e){
                    try{
                        TimeUnit.MILLISECONDS.sleep(200);
                    }catch (InterruptedException ie){

                        return new MessageWithState(MessageHelper.getStackTraceasSting(ie),true);
                    }
                }
            }
            ((TextChannel)channel).sendMessage("test").queue();
            PermissionOverrideAction paEveryone = channel.createPermissionOverride(message.getGuild().getRolesByName("@everyone", false).get(0));
            paEveryone = paEveryone.setDeny(Permission.VIEW_CHANNEL);
            paEveryone.queue();
            paEveryone = voiceChannel.createPermissionOverride(message.getGuild().getRolesByName("@everyone", false).get(0));
            paEveryone = paEveryone.setDeny(Permission.VIEW_CHANNEL);
            paEveryone.queue();
            RoleAction ra = gc.createRole();
            ra = ra.setName(message.getMember().getEffectiveName());
            ra.queue();
            found = false;
            Role role = null;
            while (!found){
                try {
                     role = gc.getGuild().getRolesByName(message.getMember().getEffectiveName(), false).get(0);
                     found = true;
                }catch (IndexOutOfBoundsException e){
                    try{
                        TimeUnit.MILLISECONDS.sleep(200);
                    }catch (InterruptedException ie){
                        return new MessageWithState(MessageHelper.getStackTraceasSting(ie),true);
                    }
                }
            }


            PermissionOverrideAction paRoll = channel.createPermissionOverride(role);
            paRoll = paRoll.setAllow(Permission.VIEW_CHANNEL);
            paRoll.queue();
            paRoll = voiceChannel.createPermissionOverride(role);
            paRoll =paRoll.setAllow(Permission.VIEW_CHANNEL);
            paRoll.queue();
            gc.addRolesToMember(message.getMember(), role).queue();

            for (Member m : message.getMentionedMembers()) {
                gc.addRolesToMember(m, role).queue();
            }

            return new MessageWithState("  ",false);
        }
    }

    public CommandList getCommand() {
        return CommandList.CREATE_GROUP;
    }

    public static String getHelp() {
        return "<group name> [list of invited players] eg @p1 @p2 ect...";
    }
}
