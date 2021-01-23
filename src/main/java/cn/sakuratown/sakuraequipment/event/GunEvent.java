package cn.sakuratown.sakuraequipment.event;

import cn.sakuratown.sakuraequipment.gun.Gun;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class GunEvent extends ItemInteractEvent{

    private final ItemStack itemStack;
    private final Gun gun;
    private final Player shooter;

    public GunEvent(ItemStack itemStack, Gun gun, Player shooter) {
        this.gun = gun;
        this.itemStack = itemStack;
        this.shooter = shooter;
    }

    public ItemStack getItem() {
        return itemStack;
    }

    public Gun getGun() {
        return gun;
    }

    public Player getShooter() {
        return shooter;
    }
}
