package lifetech.api.item;

import lifetech.LifeTech;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * @program: LifeTech
 * @description: 生命精华
 * @author: xiaofei
 * @create: 2019-10-27 12:21
 **/
public class LifeEssenceItem extends Item {

    public static  final LifeEssenceItem INSTANCE=new LifeEssenceItem(new Settings());
    public static final Identifier ITEM_ID=new Identifier(LifeTech.MOD_ID,"life_essence");

    public LifeEssenceItem(Settings settings) {
        super(settings.group(LifeTech.ITEM_GROUP));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
        if (world_1.isClient){ return new TypedActionResult<ItemStack>(ActionResult.PASS,playerEntity_1.getStackInHand(hand_1));}
        if (playerEntity_1.getHealth()<playerEntity_1.getHealthMaximum()){
                if (playerEntity_1.getStackInHand(hand_1).getCount()>0){
                    playerEntity_1.setHealth(playerEntity_1.getHealth()+1);
                    playerEntity_1.getStackInHand(hand_1).setCount(playerEntity_1.getStackInHand(hand_1).getCount()-1);
                    return new TypedActionResult<ItemStack>(ActionResult.SUCCESS,playerEntity_1.getStackInHand(hand_1));
            }
        }
        return new TypedActionResult<ItemStack>(ActionResult.PASS,playerEntity_1.getStackInHand(hand_1));
    }
}
