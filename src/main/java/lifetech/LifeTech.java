package lifetech;

import lifetech.api.item.LifeEssenceItem;
import lifetech.block.BlockInitializer;
import lifetech.item.ItemInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @program: LifeTech
 * @description: Main
 * @author: xiaofei
 * @create: 2019-10-26 23:48
 **/
public class LifeTech implements ModInitializer {

    public static final String MOD_ID = "lifetech";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(LifeTech.MOD_ID,LifeTech.MOD_ID),
            () -> new ItemStack(LifeEssenceItem.INSTANCE));

    @Override
    public void onInitialize() {
        ItemInitializer.onInitializer();
        BlockInitializer.onInitializer();
    }
}
