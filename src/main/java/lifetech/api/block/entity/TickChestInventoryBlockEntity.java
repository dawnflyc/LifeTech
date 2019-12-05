package lifetech.api.block.entity;

import lifetech.LifeTech;
import lifetech.api.inventory.TickChestInventory;
import lifetech.api.item.AcceleratorItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.util.Nameable;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameRules;

public class TickChestInventoryBlockEntity extends BlockEntity implements Nameable, Tickable, TickChestInventory {

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(10000, ItemStack.EMPTY);
    private int randomTicks;


    public TickChestInventoryBlockEntity() {
        super(Registry.BLOCK_ENTITY.get(new Identifier(LifeTech.MOD_ID, "tick_chest")));
    }


    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public Text getName() {
        return null;
    }

    @Override
    public void tick() {
        if (this.items.isEmpty()) {
            return;
        }

    }



    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        Inventories.fromTag(tag, items);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag, items);
        return super.toTag(tag);
    }
}
