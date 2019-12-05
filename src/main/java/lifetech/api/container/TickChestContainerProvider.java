package lifetech.api.container;

import com.mojang.datafixers.types.JsonOps;
import lifetech.api.inventory.TickChestInventory;
import net.minecraft.container.*;
import net.minecraft.datafixers.NbtOps;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DefaultedList;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @program: LifeTech
 * @description:
 * @author: xiaofei
 * @create: 2019-11-19 21:48
 **/
public class TickChestContainerProvider implements NameableContainerProvider {

    protected final DefaultedList<ItemStack> items = DefaultedList.ofSize(54, ItemStack.EMPTY);
    protected TickChestInventory inventory=(TickChestInventory) () -> items;
    protected Container container;
    private ItemStack itemStack;

    public TickChestContainerProvider(ItemStack itemStack) {
        this.itemStack = itemStack;
        if (itemStack.getTag()!=null) {
            Inventories.fromTag(itemStack.getTag(), items);
        }
    }


    @Override
    public Text getDisplayName() {
        return new TranslatableText("container.chest_tick", new Object[0]);
    }


    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return container == null ? container = new GenericContainer(ContainerType.GENERIC_9X6, i, playerInventory,inventory, 6) {

            @Override
            public void close(PlayerEntity playerEntity_1) {
                super.close(playerEntity_1);
                itemStack.setTag(Inventories.toTag(new CompoundTag(),items));
            }
        } : container;
    }

    public DefaultedList<ItemStack> getItems() {
        return items;
    }
}


