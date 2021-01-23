package cn.sakuratown.sakuraequipment.event;

import cn.sakuratown.sakuraequipment.gun.Gun;
import cn.sakuratown.sakuraequipment.items.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EventFactory {
    public static ItemInteractEvent getEvent(Item item, Player player, ItemStack itemStack){
        switch (item.type){
            case "Gun":
                return new GunEvent(itemStack, (Gun) item, player);
        }
        return null;
    }
}
