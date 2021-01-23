package cn.sakuratown.sakuraequipment.listeners;


import cn.sakuratown.sakuraequipment.Main;
import cn.sakuratown.sakuraequipment.event.EventFactory;
import cn.sakuratown.sakuraequipment.event.ItemInteractEvent;
import cn.sakuratown.sakuraequipment.items.Item;
import cn.sakuratown.sakuraequipment.items.ItemFactory;
import cn.sakuratown.sakuraequipment.items.ItemManager;
import cn.sakuratown.sakuraequipment.event.GunShootEvent;
import cn.sakuratown.sakuraequipment.gun.Gun;
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
    public void onPlayerInteract(PlayerInteractEvent event) {

        if(event.getAction() != Action.LEFT_CLICK_AIR || event.getAction() != Action.RIGHT_CLICK_AIR){
            //避免站在压力板上触发interact事件
            return;
        }

        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();

        if (itemStack == null) return;

        if (ItemManager.isSakuraEquipment(itemStack)) {

            Item item;

            NBTItem nbtItem = new NBTItem(itemStack);
            NBTCompound compound = nbtItem.getCompound("SakuraEquipment");


            String name = compound.getString("Name");
            String type = compound.getString("Type");
            String uuid = compound.getString("UUID");

            Map<String, Item> itemMap = Main.getInstance().getItemMap();

            if(!itemMap.containsKey(uuid)){

            }

            item = itemMap.get(uuid);

            ItemInteractEvent itemInteractEvent = EventFactory.getEvent(item, player, itemStack);
            if (itemInteractEvent != null) {
                Bukkit.getServer().getPluginManager().callEvent(itemInteractEvent);
            }


            Gun.Builder<?> builder = ItemFactory.getInstance().gunCreate(name);
            Gun gun = builder.buildFromItemStack(itemStack);
            GunShootEvent gunShootEvent = new GunShootEvent(itemStack, gun, player);

        }
    }
}
