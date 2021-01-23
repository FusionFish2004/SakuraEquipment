package cn.sakuratown.sakuraequipment.items;

import cn.sakuratown.sakuraequipment.gun.Gun;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ItemFactory {

    Map<String, Constructor<?>> factories = new HashMap<>();

    private ItemFactory() {

    }

    private static Constructor<?> load(String type, String name) {

        try {
            return Class.forName("cn.sakuratown.sakuraequipment" + type.toLowerCase() + "." + name + "$Builder").getConstructor();
        } catch (Exception e) {
            throw new NullPointerException();
        }

    }

    public Gun.Builder<?> gunCreate(String name) {
        try {
            return (Gun.Builder<?>) factories
                    .computeIfAbsent(name, k -> load("gun", name))
                    .newInstance();
        } catch (Exception e) {
            throw new NullPointerException(name);
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
        Method createItemMethod = ItemFactory.class.getDeclaredMethod(type.toLowerCase() + "Create");
        Item.Builder<?> builder = (Item.Builder<?>) createItemMethod.invoke(ItemFactory.getInstance(), name);
        return builder.buildFromItemStack(itemStack);
    }
}
