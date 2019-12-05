package lifetech.block;

import lifetech.LifeTech;
import lifetech.api.block.CookedEssenceConverterBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

/**
 * @program: LifeTech
 * @description:
 * @author: xiaofei
 * @create: 2019-10-27 19:15
 **/
public class BlockInitializer {
    public static void onInitializer() {
        Registry.register(Registry.BLOCK, CookedEssenceConverterBlock.BLOCK_ID, CookedEssenceConverterBlock.INSTANCE);
        Registry.register(Registry.ITEM, CookedEssenceConverterBlock.BLOCK_ID,new BlockItem(CookedEssenceConverterBlock.INSTANCE,new Item.Settings().group(LifeTech.ITEM_GROUP)));
        //Registry.register(Registry.BLOCK, BlockTickChest.BLOCK_ID,BlockTickChest.INSTANCE);
        //Registry.register(Registry.ITEM,BlockTickChest.BLOCK_ID,new BlockItem( BlockTickChest.INSTANCE,new Item.Settings().group(LifeTech.ITEM_GROUP)));
        //Registry.register(Registry.BLOCK_ENTITY, BlockTickChest.BLOCK_ID, BlockEntityType.Builder.create(BlockEntityTickChest::new).build(null));
    }
}
