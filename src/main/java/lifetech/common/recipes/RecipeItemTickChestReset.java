package lifetech.common.recipes;

import lifetech.common.container.ContainerItemTickChest;
import lifetech.common.item.ItemTickChest;
import lifetech.common.item.ModItemInitializer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-12-05 14:32
 **/
public class RecipeItemTickChestReset extends ModResetRecipe {

    protected final Class<? extends Item> resetClass;

    public RecipeItemTickChestReset(ResourceLocation res,Class<? extends Item> resetClass) {
        super(res,ItemTickChest.class);
        this.resetClass=resetClass;
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
        ItemStack stack=new ItemStack(ModItemInitializer.TICK_CHEST);
        ContainerItemTickChest.saveByNBT(nbtReset(ContainerItemTickChest.getListByNBT(input)),stack);
        return stack;
    }

    protected NonNullList<ItemStack> nbtReset(NonNullList<ItemStack> list){
        for (int i = 0; i < list.size(); i++) {
            ItemStack itemStack=list.get(i);
            if (itemStack.getItem().getClass().isAssignableFrom(resetClass)){
                list.set(i,new ItemStack(itemStack.getItem(),itemStack.getCount()));
            }
        }
        return list;
    }
}
