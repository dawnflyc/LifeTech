package lifetech.api.block;


import lifetech.LifeTech;
import lifetech.api.item.CookedEssenceItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @program: LifeTech
 * @description:
 * @author: xiaofei
 * @create: 2019-10-27 18:54
 **/
public class CookedEssenceConverterBlock extends Block {

    public static final CookedEssenceConverterBlock INSTANCE;
    public static final Identifier BLOCK_ID=new Identifier(LifeTech.MOD_ID,"cooked_essence_converter");
    static {
        Settings settings=Settings.of(Material.STONE);
        settings.strength(5F,5F);
        INSTANCE = new CookedEssenceConverterBlock(settings);
    }
    public CookedEssenceConverterBlock(Settings block$Settings_1) {
        super(block$Settings_1);
    }

    @Override
    public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
        if (world_1.isClient){ return false;}
        if (playerEntity_1.getStackInHand(hand_1).isFood()){
            FoodComponent food=playerEntity_1.getStackInHand(hand_1).getItem().getFoodComponent();
            int ce= (int) ((food.getHunger()+food.getSaturationModifier())/2);
            playerEntity_1.getStackInHand(hand_1).setCount(playerEntity_1.getStackInHand(hand_1).getCount()-1);
            playerEntity_1.giveItemStack(new ItemStack(CookedEssenceItem.INSTANCE,ce));
            return true;
        }
        return false;
    }

}
