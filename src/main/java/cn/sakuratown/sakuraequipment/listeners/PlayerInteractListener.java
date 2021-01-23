package cn.sakuratown.sakuraequipment.listeners;


import cn.sakuratown.sakuraequipment.Main;
import cn.sakuratown.sakuraequipment.event.EventFactory;
import cn.sakuratown.sakuraequipment.event.ItemInteractEvent;
import cn.sakuratown.sakuraequipment.items.Item;
import cn.sakuratown.sakuraequipment.items.ItemFactory;
import cn.sakuratown.sakuraequipment.items.ItemManager;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) throws Exception {

        Player player = event.getPlayer();

        if(event.getAction() == Action.PHYSICAL){
            //避免站在压力板上触发interact事件
            return;
        }


        ItemStack itemStack = event.getItem();



        if (itemStack == null) return;

        if (!ItemManager.isSakuraEquipment(itemStack)) return;

        Item item;

        NBTItem nbtItem = new NBTItem(itemStack);
        NBTCompound compound = nbtItem.getCompound("SakuraEquipment");


        String name = compound.getString("Name");
        String type = compound.getString("Type");
        String uuid = compound.getString("UUID");

        Map<String, Item> itemMap = Main.getInstance().getItemMap();
        //获取和uuid对应的物品map

        if(!itemMap.containsKey(uuid)){
            item = ItemFactory.getItem(type,name,itemStack);
            itemMap.put(uuid, item);
            //如果map中不含有该uuid对应的物品，创建一个新物品并存入其中
        }

        item = itemMap.get(uuid);

        ItemInteractEvent itemInteractEvent = EventFactory.getEvent(item, player, itemStack);

        if (itemInteractEvent != null) {
            Bukkit.getServer().getPluginManager().callEvent(itemInteractEvent);
        }

        event.setCancelled(true);
    }
}
