package lifetech.api.item;

import com.google.gson.JsonElement;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.JsonOps;
import lifetech.LifeTech;
import net.minecraft.datafixers.NbtOps;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

/**
 * @program: LifeTech
 * @description:
 * @author: xiaofei
 * @create: 2019-10-27 21:09
 **/
public class AcceleratorItem extends Item {
    public static final AcceleratorItem NORMAL_LIFE = new AcceleratorItem(new Settings().maxCount(1), 10, false);
    public static final Identifier NORMAL_LIFE_ID = new Identifier(LifeTech.MOD_ID, "normal_life_accelerator");
    public static final AcceleratorItem ADVANCED_LIFE = new AcceleratorItem(new Settings().maxCount(1), 100, false);
    public static final Identifier ADVANCED_LIFE_ID = new Identifier(LifeTech.MOD_ID, "advanced_life_accelerator");
    public static final AcceleratorItem NORMAL_SOUL = new AcceleratorItem(new Settings().maxCount(1), 50, true);
    public static final Identifier NORMAL_SOUL_ID = new Identifier(LifeTech.MOD_ID, "normal_soul_accelerator");
    public static final AcceleratorItem ADVANCED_SOUL = new AcceleratorItem(new Settings().maxCount(1), 500, true);
    public static final Identifier ADVANCED_SOUL_ID = new Identifier(LifeTech.MOD_ID, "advanced_soul_accelerator");
    protected final boolean soulAccelerator;
    protected final int speed;

    protected AcceleratorItem(Settings item$Settings_1, int speed, boolean soulAccelerator) {
        super(item$Settings_1.group(LifeTech.ITEM_GROUP));
        this.speed = speed;
        this.soulAccelerator = soulAccelerator;
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext itemUsageContext_1) {
        if (itemUsageContext_1.getWorld().isClient()) {
            return ActionResult.SUCCESS;
        }
        if (itemUsageContext_1.isPlayerSneaking()) {
            CompoundTag nbt = itemUsageContext_1.getStack().getTag();
            if (itemUsageContext_1.isPlayerSneaking()) {
                CompoundTag compoundTag = new CompoundTag();
                BlockPos p = itemUsageContext_1.getBlockPos();
                Tag tag= p.serialize(NbtOps.INSTANCE);
                compoundTag.put("pos",tag);
                itemUsageContext_1.getStack().setTag(compoundTag);
            }
        }

        return ActionResult.SUCCESS;
    }

    public boolean isSoulAccelerator() {
        return soulAccelerator;
    }

    public int getSpeed() {
        return speed;
    }
}
