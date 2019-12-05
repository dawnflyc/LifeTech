package lifetech.common.item;

import lifetech.LifeTech;
import lifetech.common.util.LifeTechCreativeTab;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import vazkii.arl.item.ItemModSword;

import java.util.Random;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-11-22 12:10
 **/
public  class ItemLifeExtractor extends ItemModSword {

    protected Random random=new Random();

    protected ItemLifeExtractor(String name, ToolMaterial material, String... variants) {
        super(name, material, variants);
        this.setCreativeTab(LifeTechCreativeTab.INSTANCE);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) { if (attacker.getEntityWorld().isRemote){return  true;}
        if (!target.isEntityAlive()){
        int num= (int) (random.nextInt((int) (target.getMaxHealth()/4)+1)+target.getMaxHealth()/4);
            attacker.world.spawnEntity(new EntityItem(attacker.world,target.posX,target.posY,target.posZ,new ItemStack(ModItemInitializer.LIFE_ESSENCE,num)));
        }
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public String getModNamespace() {
        return LifeTech.MODID;
    }
}
