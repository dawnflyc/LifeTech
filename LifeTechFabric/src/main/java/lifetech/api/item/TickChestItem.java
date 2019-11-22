package lifetech.api.item;

import com.mojang.datafixers.Dynamic;
import com.sun.org.apache.regexp.internal.RE;
import lifetech.LifeTech;
import lifetech.api.container.TickChestContainerProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.datafixers.NbtOps;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @program: LifeTech
 * @description:
 * @author: xiaofei
 * @create: 2019-11-19 21:04
 **/
public class TickChestItem extends Item  {

    public static final TickChestItem INSTANCE=new TickChestItem(new Settings().maxCount(1));
    public static final Identifier ITEM_ID=new Identifier(LifeTech.MOD_ID,"tick_chest");

    public TickChestItem(Settings item$Settings_1) {
        super(item$Settings_1.group(LifeTech.ITEM_GROUP));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
            if (world_1.isClient()){return super.use(world_1, playerEntity_1, hand_1);}
            playerEntity_1.openContainer(new TickChestContainerProvider(playerEntity_1.getStackInHand(hand_1)));
            playerEntity_1.getStackInHand(hand_1);
        return new TypedActionResult(ActionResult.SUCCESS, playerEntity_1.getStackInHand(hand_1));
    }

    @Override
    public void inventoryTick(ItemStack itemStack_1, World world_1, Entity entity_1, int int_1, boolean boolean_1) {
        super.inventoryTick(itemStack_1, world_1, entity_1, int_1, boolean_1);
        TickChestContainerProvider tickChestContainerProvider=new TickChestContainerProvider(itemStack_1);

        if (tickChestContainerProvider.getItems().isEmpty()) {
            return;
        }
        for (ItemStack item : tickChestContainerProvider.getItems()) {
            if (item.getItem() instanceof AcceleratorItem) {
                CompoundTag compoundTag = item.getTag();
                if (compoundTag == null) {
                    return;
                }
                BlockPos blockPos= BlockPos.deserialize(new Dynamic(NbtOps.INSTANCE,compoundTag.getTag("pos")));
                int speed=((AcceleratorItem) item.getItem()).getSpeed();
                boolean soulAccelerator=((AcceleratorItem) item.getItem()).isSoulAccelerator();
                tickBlock(world_1,blockPos,world_1.getGameRules().getInt(GameRules.RANDOM_TICK_SPEED),soulAccelerator,speed);
            }
        }
    }

    private void tickBlock(World world , BlockPos pos, int randomTicks, boolean soulAccelerator, int speed) {
        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        if (block.hasRandomTicks(blockState) &&
                world.getRandom().nextInt(MathHelper.clamp(speed, 1, 4096)) < randomTicks) {
            block.onRandomTick(blockState, world, pos, world.getRandom());
        }
        if (!block.hasBlockEntity() && !soulAccelerator) return;
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity == null || blockEntity.isInvalid() ||
                !(blockEntity instanceof Tickable)) {
            return;
        }
        for (int i = 0; i < speed; i++) {
            if (blockEntity.isInvalid()) break;
            ((Tickable) blockEntity).tick();
        }
    }

}
