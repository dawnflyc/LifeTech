package lifetech.common.item;

import lifetech.LifeTech;
import lifetech.common.network.MessageAccelerator;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-11-22 12:14
 **/
public class ItemAccelerator extends ItemMod {

    protected final boolean soulAccelerator;
    protected final int speed;

    public ItemAccelerator(String name, int speed, boolean soulAccelerator, String... variants) {
        super(name, variants);
        this.setMaxStackSize(1);
        this.speed = speed;
        this.soulAccelerator = soulAccelerator;

    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote && player.isSneaking()) {
            ItemStack itemStack=player.getHeldItem(hand);
            NBTTagCompound nbt = itemStack.getTagCompound();
            nbt=nbt==null ? new NBTTagCompound() : nbt;
            nbt.setLong("pos", pos.toLong());
            itemStack.setTagCompound(nbt);
            MessageAccelerator message=new MessageAccelerator(pos,player.getUniqueID());
            LifeTech.NETWORK.sendTo(message, (EntityPlayerMP) player);
            return EnumActionResult.SUCCESS;
        }else if (player.isSneaking()) {
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!worldIn.isRemote && stack.getTagCompound() != null) {
            BlockPos pos = BlockPos.fromLong(stack.getTagCompound().getLong("pos"));
            int speed = ((ItemAccelerator) stack.getItem()).getSpeed();
            boolean soulAccelerator = ((ItemAccelerator) stack.getItem()).isSoulAccelerator();
            tickBlock(worldIn, pos, soulAccelerator, speed);
        }
    }

    private void tickBlock(World world, BlockPos pos, boolean soulAccelerator, int speed) {
        IBlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        if (!block.hasTileEntity(blockState) || !soulAccelerator) {
            for (int i = 0; i < speed; i++) {
                block.randomTick(world, pos, blockState, world.rand);
            }
            return;
        }
        TileEntity blockEntity = world.getTileEntity(pos);
        if (blockEntity == null || blockEntity.isInvalid() ||
                !(blockEntity instanceof ITickable)) {
            return;
        }
        for (int i = 0; i < speed; i++) {
            if (blockEntity.isInvalid()) break;
            ((ITickable) blockEntity).update();
        }
    }

    public boolean isSoulAccelerator() {
        return soulAccelerator;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.getTagCompound()==null) return;
        BlockPos pos=BlockPos.fromLong(stack.getTagCompound().getLong("pos"));
        tooltip.add("X:"+pos.getX()+"，Y:"+pos.getY()+",Z:"+pos.getZ());
    }

    public int getSpeed() {
        return speed;
    }

}
