package lifetech.common.recipes;

import lifetech.common.item.ItemAccelerator;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-12-04 17:01
 **/
public class RecipeItemAcceleratorReset extends ModResetRecipe {


    public RecipeItemAcceleratorReset(ResourceLocation res) {
        super(res, ItemAccelerator.class);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return getRecipeOutput().copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return new ItemStack(input.getItem());
    }

}
