package lifetech.item;

import lifetech.api.item.*;
import net.minecraft.util.registry.Registry;

/**
 * @program: LifeTech
 * @description:
 * @author: xiaofei
 * @create: 2019-10-27 12:29
 **/
public class ItemInitializer {

    public static void onInitializer() {
        Registry.register(Registry.ITEM, LifeExtractorItem.IRON_ITEM_ID, LifeExtractorItem.IRON);
        Registry.register(Registry.ITEM, LifeExtractorItem.DIAMOND_ITEM_ID, LifeExtractorItem.DIAMOND);

        Registry.register(Registry.ITEM, LifeEssenceItem.ITEM_ID, LifeEssenceItem.INSTANCE);
        Registry.register(Registry.ITEM, CookedEssenceItem.ITEM_ID, CookedEssenceItem.INSTANCE);

        Registry.register(Registry.ITEM, AcceleratorItem.NORMAL_LIFE_ID, AcceleratorItem.NORMAL_LIFE);
        Registry.register(Registry.ITEM, AcceleratorItem.ADVANCED_LIFE_ID, AcceleratorItem.ADVANCED_LIFE);
        Registry.register(Registry.ITEM, AcceleratorItem.NORMAL_SOUL_ID, AcceleratorItem.NORMAL_SOUL);
        Registry.register(Registry.ITEM, AcceleratorItem.ADVANCED_SOUL_ID, AcceleratorItem.ADVANCED_SOUL);

        Registry.register(Registry.ITEM, TickChestItem.ITEM_ID, TickChestItem.INSTANCE);

    }
}
