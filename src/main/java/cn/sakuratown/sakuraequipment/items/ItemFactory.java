package cn.sakuratown.sakuraequipment.items;

import cn.sakuratown.sakuraequipment.gun.Gun;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static cn.sakuratown.sakuraequipment.utils.KeyUtil.PLUGIN;

public class ItemFactory {

    Map<String, Constructor<?>> factories = new HashMap<>();

    private ItemFactory() {

    }

    private static Constructor<?> load(String type, String name) {

        try {
            return Class.forName("cn.sakuratown.sakuraequipment." + type.toLowerCase() + "." + name + "$Builder").getConstructor();
        } catch (Exception e) {
            throw new NullPointerException();
        }

    }

    public Gun.Builder<?> gunCreate(String name){
        try {
            return (Gun.Builder<?>) factories
                    .computeIfAbsent(name, k -> load("gun", name))
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException();
        }
    }

    public Gun.Builder<?> armorCreate(String name) {
        try {
            return (Gun.Builder<?>) factories
                    .computeIfAbsent(name, k -> load("armor", name))
                    .newInstance();
        } catch (Exception e) {
            throw new NullPointerException(name);
        }
    }

    public static ItemFactory getInstance() {
        return new ItemFactory();
    }

    public static Item getItem(String type, String name, ItemStack itemStack) throws Exception {
        //使用反射获取物品
        Method createItemMethod = ItemFactory.class.getDeclaredMethod(type.toLowerCase() + "Create",String.class);
        Item.Builder<?> builder = (Item.Builder<?>) createItemMethod.invoke(ItemFactory.getInstance(), name);

        PLUGIN.getLogger().info("method = " + createItemMethod.getName());
        PLUGIN.getLogger().info("name = " + name);

        return builder.buildFromItemStack(itemStack);
    }
}
