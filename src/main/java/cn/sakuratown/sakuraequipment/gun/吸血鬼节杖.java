package cn.sakuratown.sakuraequipment.gun;

import cn.sakuratown.sakuraequipment.items.Item;
import org.bukkit.Material;

// 不可变类, 构造方法为 private 防止继承
public class 吸血鬼节杖 extends Gun {

    private 吸血鬼节杖(Builder builder) {
        super(builder);
    }

    public static 吸血鬼节杖 getInstance() {

        int damage = Item.randomInt(1, 20);
        double range = Item.randomDouble(1, 10, 1);

        int attackSpeed = Item.randomInt(1, 40);
        double bulletSpeed = Item.randomDouble(1.0, 40, 1);
        int reloadSpeed = Item.randomInt(10,60);

        int bulletAmount = Item.randomInt(1, 35);

        double criticalRate = Item.randomInt(1, 100);
        double criticalMultiply = Item.randomDouble(1.5, 2.5, 1);

        return new Builder()
                .material(Material.LEATHER_HORSE_ARMOR)
                .version(1)

                .damage(damage)
                .range(range)

                .attackSpeed(attackSpeed)
                .bulletSpeed(bulletSpeed)
                .reloadSpeed(reloadSpeed)

                .bulletAmount(bulletAmount)
                .criticalRate(criticalRate)
                .criticalMultiply(criticalMultiply)
                .build();
    }

    @Override
    public void onShoot() {

    }

    @Override
    public void onBulletFlyTick() {

    }

    @Override
    public void onHit() {

    }

    public static class Builder extends Gun.Builder<Builder> {

        public Builder() {
            super();
        }

        @Override
        public 吸血鬼节杖 build() {
            return new 吸血鬼节杖(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}



