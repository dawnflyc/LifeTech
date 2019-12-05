package lifetech.api.item;

import lifetech.LifeTech;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

/**
 * @program: LifeTech
 * @description:
 * @author: xiaofei
 * @create: 2019-10-27 17:51
 **/
public class CookedEssenceItem extends Item {

    public static final CookedEssenceItem INSTANCE=new CookedEssenceItem(new Settings().food(new FoodComponent.Builder().hunger(1).saturationModifier(1F).build()));
    public static   final Identifier ITEM_ID=new Identifier(LifeTech.MOD_ID,"cooked_essence");

    public CookedEssenceItem(Settings item$Settings_1) {
        super(item$Settings_1.group(LifeTech.ITEM_GROUP));
    }


    @Override
    public void inventoryTick(ItemStack itemStack_1, World world_1, Entity entity_1, int int_1, boolean boolean_1) {
        if (world_1.isClient){ return ;}
        if (entity_1 instanceof PlayerEntity){
            PlayerEntity playerEntity= (PlayerEntity) entity_1;
            if(playerEntity.getHungerManager().isNotFull()){
                playerEntity.getHungerManager().eat(this,itemStack_1);
                itemStack_1.setCount(itemStack_1.getCount()-1);
            }
        }
    }
}
