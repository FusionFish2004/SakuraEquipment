package cn.sakuratown.sakuraequipment;

import cn.sakuratown.sakuraequipment.items.Item;
import cn.sakuratown.sakuraequipment.listeners.ArmorEquipListener;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import cn.sakuratown.sakuraequipment.gun.吸血鬼节杖;
import cn.sakuratown.sakuraequipment.utils.MessageUtil;
import cn.sakuratown.sakuraequipment.listeners.GunShootListener;
import cn.sakuratown.sakuraequipment.listeners.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main extends JavaPlugin {

    private static Main plugin;
    private Map<String, Item> itemMap = new HashMap<>();

    public static Main getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        String[] message = {
                "&e&l" + getName() + "&a 插件&e v" + getDescription().getVersion() + " &a已启用",
                "&a插件制作作者:&e EnTIv &aQQ群:&e 600731934"
        };
        MessageUtil.sendConsole(message);

        getServer().getPluginManager().registerEvents(new ArmorEquipListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new GunShootListener(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if (!player.isOp()) return false;

        吸血鬼节杖 testGun = 吸血鬼节杖.getInstance();

        player.getInventory().addItem(testGun.getItemStack());
        return true;
    }

    void spawnEntity(Location loc) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer packet = manager.createPacket(PacketType.Play.Server.SPAWN_ENTITY);
        packet.getModifier().writeDefaults(); // 写入默认值

        packet.getIntegers().write(0, 1); // 对integers的第0个写入实体ID，注意这里，实体ID不要重叠，否则会被覆盖
        packet.getUUIDs().write(0, UUID.randomUUID()); // 对UUID的第0个写入UUID值，这里使用了随机生成UUID值
        packet.getEntityTypeModifier().write(0, EntityType.DRAGON_FIREBALL);
        packet.getDoubles().write(0, loc.getX());
        packet.getDoubles().write(1, loc.getY());
        packet.getDoubles().write(2, loc.getZ());

        manager.broadcastServerPacket(packet);
    }

    public Map<String, Item> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map<String, Item> itemMap) {
        this.itemMap = itemMap;
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}
