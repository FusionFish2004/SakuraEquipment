package cn.sakuratown.sakuraequipment.items;

import cn.sakuratown.sakuraequipment.Main;
import cn.sakuratown.sakuraequipment.utils.MessageUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    private final ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = new ItemStack(itemStack);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(ItemStack itemStack, int amount) {
        this.itemStack = new ItemStack(itemStack);
        this.itemStack.setAmount(amount);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material) {
        this(material, 1);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder name(String displayName) {

        if (displayName == null) return this;

        itemMeta.setDisplayName(MessageUtil.toColor(displayName));
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder lore(String... lore) {

        itemMeta.setLore(Arrays.asList(MessageUtil.toColor(lore)));
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder lore(List<String> lore) {

        itemMeta.setLore(MessageUtil.toColor(lore));
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder addLore(String... lore) {

        List<String> itemLore = itemMeta.getLore();
        if (itemLore == null) return this;

        itemLore.addAll(Arrays.asList(MessageUtil.toColor(lore)));

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addLore(List<String> lore) {

        List<String> itemLore = itemMeta.getLore();
        if (itemLore == null) return this;

        itemLore.addAll(MessageUtil.toColor(lore));

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder addFlag(ItemFlag itemFlag) {
        itemMeta.addItemFlags(itemFlag);
        itemStack.setItemMeta(itemMeta);
        return this;
    }


    public ItemBuilder setUnbreakable() {

        itemMeta.setUnbreakable(true);

        itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder persistentDataContainer(String namespacedKey, Integer integer) {

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        NamespacedKey bulletAmount = new NamespacedKey(Main.getInstance(), namespacedKey);
        container.set(bulletAmount, PersistentDataType.INTEGER, integer);

        itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemStack build() {
        return itemStack;
    }
}