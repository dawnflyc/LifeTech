package lifetech.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-11-22 12:15
 **/
public class ItemCookedEssence extends ItemMod {
    public ItemCookedEssence(String name, String... variants) {
        super(name, variants);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (worldIn.isRemote){return ;}
        if (entityIn instanceof EntityPlayer && ((EntityPlayer)entityIn).getFoodStats().getFoodLevel()+1<20){
            EntityPlayer entityPlayer= (EntityPlayer) entityIn;
                entityPlayer.getFoodStats().setFoodLevel(entityPlayer.getFoodStats().getFoodLevel()+2);
                stack.setCount(stack.getCount()-1);
        }
    }

}
