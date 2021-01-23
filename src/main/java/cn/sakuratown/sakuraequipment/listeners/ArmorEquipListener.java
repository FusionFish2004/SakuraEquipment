package cn.sakuratown.sakuraequipment.listeners;

import cn.sakuratown.sakuraequipment.event.ArmorEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ArmorEquipListener implements Listener {
    @EventHandler
    public void onEquip(ArmorEvent event){
        Player player = event.getPlayer();
    }
}
