package cn.sakuratown.sakuraequipment.event;

import cn.sakuratown.sakuraequipment.armor.Armor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArmorEvent extends ItemInteractEvent{
    private final Player player;
    private final ItemStack itemStack;
    private final Armor armor;

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Armor getArmor() {
        return armor;
    }

    public ArmorEvent(ItemStack itemStack, Player player, Armor armor) {
        this.player = player;
        this.itemStack = itemStack;
        this.armor = armor;
    }

    public Player getPlayer() {
        return player;
    }
}
