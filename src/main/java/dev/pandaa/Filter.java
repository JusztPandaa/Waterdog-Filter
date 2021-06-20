package dev.pandaa;

import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import dev.waterdog.waterdogpe.utils.Configuration;
import dev.waterdog.waterdogpe.event.defaults.PlayerChatEvent;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class Filter extends Plugin {

@Override
 public void onEnable(){
     createDefaultConfig();
     getProxy().getEventManager().subscribe(PlayerChatEvent.class, this::onChat);
 }

public void onChat(PlayerChatEvent e){
      List<String> Blacklist = getConfig().getStringList("blacklisted-words");
      String Message = (e.getMessage()).toLowerCase();
      ProxiedPlayer sender = e.getPlayer();
   
      for(String words : Blacklist){
         if(Message.contains(words)){
              e.setCancelled(true);
              sender.sendMessage(getConfig().getString("blocked"));
            }
        }
    }
    
private void createDefaultConfig() {
     if (!getDataFolder().exists())
          getDataFolder().mkdir();
       
     File file = new File(getDataFolder(), "config.yml");

     if (!file.exists()) {
         try (InputStream in = getResourceFile("config.yml")) {
             Files.copy(in, file.toPath());
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  }

}
