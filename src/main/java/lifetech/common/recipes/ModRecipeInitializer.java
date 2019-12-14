package lifetech.common.recipes;

import lifetech.common.item.ItemAccelerator;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-12-04 16:56
 **/
public class ModRecipeInitializer {
    public static final IRecipe acceleratorReset=new RecipeItemAcceleratorReset(new ResourceLocation("accelerator_reset"));
    public static final IRecipe tickChestReset=new RecipeItemTickChestReset(new ResourceLocation("tick_chest_reset"),ItemAccelerator.class);
    public static void initializer(){

    }
}
