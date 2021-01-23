package cn.sakuratown.sakuraequipment.utils;

import cn.sakuratown.sakuraequipment.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class KeyUtil {

    public static final Main PLUGIN = JavaPlugin.getPlugin(Main.class);
    public static final NamespacedKey BULLET_AMOUNT = new NamespacedKey(PLUGIN,"bullet_amount");

}
