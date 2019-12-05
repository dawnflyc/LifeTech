package lifetech.common.item;

import lifetech.LifeTech;
import lifetech.common.container.ContainerItemTickChest;
import lifetech.common.network.GuiHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-11-22 12:22
 **/
public class ItemTickChest extends ItemMod {
    public ItemTickChest(String name, String... variants) {
        super(name, variants);
        this.setMaxStackSize(1);
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (worldIn.isRemote && handIn==EnumHand.OFF_HAND){return new ActionResult<>(EnumActionResult.SUCCESS,playerIn.getHeldItem(handIn));}
        BlockPos pos=playerIn.getPosition();
        playerIn.openGui(LifeTech.instance,GuiHandler.GUI_ITEM_TICK_CHEST_ID,worldIn,pos.getX(),pos.getY(),pos.getZ());
        return new ActionResult<>(EnumActionResult.SUCCESS,playerIn.getHeldItem(EnumHand.MAIN_HAND));
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!worldIn.isRemote && entityIn instanceof EntityPlayer && (stack.getTagCompound()==null || !stack.getTagCompound().getBoolean("is_open_container"))) {
            NonNullList<ItemStack> list = ContainerItemTickChest.getListByNBT(stack);
            for (ItemStack itemStack : list) {
                if (itemStack.getItem() instanceof ItemAir) {
                    continue;
                }
                itemStack.getItem().onUpdate(itemStack, worldIn, entityIn, itemSlot, false);
            }
            ContainerItemTickChest.saveByNBT(list,stack);
        }
    }
}
