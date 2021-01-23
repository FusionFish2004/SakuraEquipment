package cn.sakuratown.sakuraequipment.listeners;

import cn.sakuratown.sakuraequipment.Main;
import cn.sakuratown.sakuraequipment.gun.BulletRunnable;
import cn.sakuratown.sakuraequipment.event.GunShootEvent;
import cn.sakuratown.sakuraequipment.gun.Gun;
import cn.sakuratown.sakuraequipment.utils.MessageUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import static cn.sakuratown.sakuraequipment.utils.KeyUtil.BULLET_AMOUNT;

public class GunShootListener implements Listener {

    @EventHandler
    public void onGunShootEvent(GunShootEvent event) {

        Gun gun = event.getGun();
        Player player = event.getShooter();
        ItemStack itemStack = event.getItem();
        Integer bulletAmount = getBulletAmount(itemStack);

        if (player.hasCooldown(itemStack.getType())) {
            return;
        }

        consumeBullet(player, itemStack, gun.magazineSize);
        BulletRunnable runnable = new BulletRunnable(player, gun.getBullet());
        runnable.runTaskTimer(Main.getInstance(), 0, 1);
        player.setCooldown(itemStack.getType(), gun.attackSpeed);

        if (bulletAmount <= 0) {
            reload(player, itemStack, gun);
        }
    }

    private Integer getBulletAmount(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        Integer bulletAmount = container.get(BULLET_AMOUNT, PersistentDataType.INTEGER);
        assert bulletAmount != null;

        return bulletAmount;
    }

    private void consumeBullet(Player player, ItemStack itemStack, Integer magazineSize) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        Integer bulletAmount = getBulletAmount(itemStack);

        container.set(BULLET_AMOUNT, PersistentDataType.INTEGER, bulletAmount -= 1);
        itemStack.setItemMeta(itemMeta);

        MessageUtil.sendActionBar(player, "&a&l" + bulletAmount + " | " + magazineSize);
    }

    private void reload(Player player, ItemStack itemStack, Gun gun) {
        MessageUtil.sendActionBar(player, "&c&l" + "换弹中...");

        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        container.set(BULLET_AMOUNT, PersistentDataType.INTEGER, gun.magazineSize);
        itemStack.setItemMeta(itemMeta);

        player.setCooldown(itemStack.getType(), gun.reloadSpeed);
    }
}
