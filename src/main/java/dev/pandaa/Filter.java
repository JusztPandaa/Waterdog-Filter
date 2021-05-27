package dev.pandaa;

import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.waterdog.waterdogpe.event.defaults.PlayerChatEvent;



public class Filter extends Plugin {

    @Override
    public void onEnable(){

        this.getProxy().getEventManager().subscribe(PlayerChatEvent.class, this::onChat);

    }

    public void onChat(PlayerChatEvent e){

        List<String> Blacklist = new ArrayList<>();

         // to add to the array you can just do something in the config like blacklisted words or something. or do Blacklist.add("word");
        Blacklist.add("fuck");
        Blacklist.add("bitch");
        Blacklist.add("ass");
        Blacklist.add("dumbass");
        Blacklist.add("ez")
        Blacklist.add("suck ass");
        Blacklist.add("fuck u");
        Blacklist.add("fuck you");
        Blacklist.add("pp");
        Blacklist.add("suck pp");
        String Message = (e.getMessage()).toLowerCase();
        ProxiedPlayer sender = e.getPlayer();
        String Censored = "§c[FILTER]: §7Please do not use that word.";
        Boolean status = null;
        for(String words : Blacklist){
            if(Message.contains(words)){
                status = true;
            } else {
                status = false;
            }
        }
        if(status){
            e.setCancelled(true);
            sender.sendMessage(Censored);
        }
        return;
    }
}
