package lifetech.api.item;

import lifetech.LifeTech;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * @program: LifeTech
 * @description: 生命提取器
 * @author: xiaofei
 * @create: 2019-10-27 12:21
 **/
public class LifeExtractorItem extends SwordItem {

    public static final LifeExtractorItem IRON=new LifeExtractorItem(ToolMaterials.IRON,new Settings());
    public static final Identifier IRON_ITEM_ID=new Identifier(LifeTech.MOD_ID,"iron_life_extractor");
    public static final LifeExtractorItem DIAMOND=new LifeExtractorItem(ToolMaterials.DIAMOND,new Settings());
    public static final Identifier DIAMOND_ITEM_ID=new Identifier(LifeTech.MOD_ID,"diamond_life_extractor");

    public LifeExtractorItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial,0,toolMaterial.getMiningSpeed(),settings.group(LifeTech.ITEM_GROUP));
    }
    @Override
    public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
        if (world_1.isClient){ return new TypedActionResult<ItemStack>(ActionResult.FAIL,playerEntity_1.getStackInHand(hand_1));}
        if (playerEntity_1.damage(new ItemLifeExtractorDamageSource(),2F)){
            playerEntity_1.giveItemStack(new ItemStack(LifeEssenceItem.INSTANCE));
            return new TypedActionResult<ItemStack>(ActionResult.SUCCESS,playerEntity_1.getStackInHand(hand_1));
        }
        return new TypedActionResult<ItemStack>(ActionResult.FAIL,playerEntity_1.getStackInHand(hand_1));
    }


    @Override
    public boolean postHit(ItemStack itemStack_1, LivingEntity livingEntity_1, LivingEntity livingEntity_2) {
        if (livingEntity_2 instanceof PlayerEntity){
            if (((PlayerEntity)livingEntity_2).getEntityWorld().isClient){return false;}
        }
        if (!livingEntity_1.isAlive() && livingEntity_2 instanceof PlayerEntity && livingEntity_1 instanceof MobEntityWithAi){
            int health= (int) (livingEntity_1.getHealthMaximum()/2);
            ((PlayerEntity)livingEntity_2).giveItemStack(new ItemStack(LifeEssenceItem.INSTANCE,health));
            return true;
        }
        return super.postHit(itemStack_1, livingEntity_1, livingEntity_2);
    }

    private class ItemLifeExtractorDamageSource extends  DamageSource{

        protected ItemLifeExtractorDamageSource() {
            super("life_extractor");
        }
    }
}
