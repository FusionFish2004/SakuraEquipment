package cn.sakuratown.sakuraequipment.armor;

import cn.sakuratown.sakuraequipment.items.Item;
import cn.sakuratown.sakuraequipment.items.ItemBuilder;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class Armor extends Item {

    public final int durability;
    public final double damageAbsorbRate;

    protected Armor(Builder<?> builder) {
        super(builder);
        this.durability = builder.durability;
        this.damageAbsorbRate = builder.damageAbsorbRate;
    }

    @Override
    public ItemStack getItemStack() {
        ItemStack itemStack = super.getItemStack();
        List<String> lore = new ArrayList<>();

        lore.add("");
        lore.add("&6耐久度: &e&l" + durability);

        return new ItemBuilder(itemStack).lore(lore).build();
    }

    @Override
    protected void setNBTCompound(NBTCompound compound) {

        compound.setInteger("Durability", durability);
        compound.setDouble("DamageAbsorbRate", damageAbsorbRate);

    }

    public static abstract class Builder<T extends Item.Builder<T>> extends Item.Builder<T> {

        private int durability = 500;
        private double damageAbsorbRate = 0.1;

        protected Builder() {
            super("Armor");
        }

        public T durability(int val) {
            durability = val;
            return self();
        }

        public T damageAbsorbRate(double val){
            damageAbsorbRate = val;
            return self();
        }

        @Override
        public Armor buildFromItemStack(ItemStack itemStack) {

            super.buildFromItemStack(itemStack);

            NBTItem nbtItem = new NBTItem(itemStack);
            NBTCompound compound = nbtItem.getCompound("SakuraEquipment");

            durability = compound.getInteger("Durability");
            damageAbsorbRate = compound.getDouble("DamageAbsorbRate");

            return build();
        }


        public abstract Armor build();
    }
}
