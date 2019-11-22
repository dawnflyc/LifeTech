package lifetech.api.block;

import lifetech.LifeTech;
import lifetech.api.block.entity.TickChestInventoryBlockEntity;
import lifetech.api.item.AcceleratorItem;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class TickChestBlock extends Block implements BlockEntityProvider {

    public static final TickChestBlock INSTANCE = new TickChestBlock(TickChestSettings.createSettings(Material.STONE, MaterialColor.STONE).ticksRandomly());
    public static final Identifier BLOCK_ID = new Identifier(LifeTech.MOD_ID, "tick_chest");

    protected TickChestBlock(FabricBlockSettings tickChestSettings) {
        super(tickChestSettings.build());
    }

    @Override
    public boolean activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        if (world.isClient) return true;
        Inventory blockEntity = (Inventory) world.getBlockEntity(blockPos);
        if (!player.getStackInHand(hand).isEmpty() && player.getStackInHand(hand).getItem() instanceof AcceleratorItem) {
            if (blockEntity.getInvStack(0).isEmpty()) {
                blockEntity.setInvStack(0, player.getStackInHand(hand).copy());
                player.getStackInHand(hand).setCount(0);
            }
            return true;
        } else {
            if (!blockEntity.getInvStack(0).isEmpty()) {
                player.inventory.offerOrDrop(world, blockEntity.getInvStack(0));
                blockEntity.removeInvStack(0);
            }
        }

        return true;
    }


    @Override
    public void onRandomTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
        if (world_1.isClient) return;
        BlockEntity blockEntity = world_1.getBlockEntity(blockPos_1);
        if (blockEntity instanceof TickChestInventoryBlockEntity) {
            ((TickChestInventoryBlockEntity) blockEntity).tick();
        }
    }


    @Override
    public BlockEntity createBlockEntity(BlockView blockView_1) {
        return new TickChestInventoryBlockEntity();
    }

    protected static class TickChestSettings extends FabricBlockSettings {
        protected TickChestSettings(Material material, MaterialColor color) {
            super(material, color);
        }

        protected static TickChestSettings createSettings(Material material, MaterialColor color) {
            return new TickChestSettings(material, color);
        }
    }
}
