package lifetech.common.block;

import lifetech.common.item.ModItemInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-11-22 12:20
 **/
public class BlockCookedEssenceConverter extends BlockMod {
    public BlockCookedEssenceConverter(String name, String... variants) {
        super(name, Material.IRON, variants);
        this.setHardness(5F);
    }


    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (worldIn.isRemote) {
            return;
        }
        List<? extends EntityItem> es = worldIn.getEntities((Class<? extends EntityItem>) EntityItem.class, input -> input.getItem().getItem() instanceof ItemFood);
        for (EntityItem e : es) {
            if (e.getPosition().getX() == fromPos.getX() && e.getPosition().getY() == fromPos.getY() && e.getPosition().getZ() == fromPos.getZ()) {
                ItemStack itemStack = e.getItem();
                ItemFood itemFood = (ItemFood) itemStack.getItem();
               int amount = itemFood.getHealAmount(itemStack);
               int count = amount / 2 * itemStack.getCount();
                itemStack.setCount(0);
                worldIn.spawnEntity(new EntityItem(worldIn, e.getPosition().getX(), e.getPosition().getY() + 1, e.getPosition().getZ(), new ItemStack(ModItemInitializer.COOKED_ESSENCE, count)));
            }
        }
    }
}
