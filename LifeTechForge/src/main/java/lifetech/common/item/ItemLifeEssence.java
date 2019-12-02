package lifetech.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-11-22 20:52
 **/
public class ItemLifeEssence extends ItemMod {
    public ItemLifeEssence(String name, String... variants) {
        super(name, variants);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (worldIn.isRemote){return ;}
        if (entityIn instanceof EntityLivingBase && ((EntityLivingBase)entityIn).getHealth()+1<((EntityLivingBase)entityIn).getMaxHealth()){
            EntityLivingBase entityLivingBase= (EntityLivingBase) entityIn;
            entityLivingBase.setHealth(entityLivingBase.getHealth()+2);
            stack.setCount(stack.getCount()-1);
        }
    }
}

