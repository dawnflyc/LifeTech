package lifetech.common.recipes;

import lifetech.common.item.ModItemInitializer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-12-01 20:43
 **/
public class ModSmeltingInitializer {

    public static void initializer(){
        GameRegistry.addSmelting(ModItemInitializer.LIFE_ESSENCE,new ItemStack(ModItemInitializer.COOKED_ESSENCE),0.1F);
    }
}
