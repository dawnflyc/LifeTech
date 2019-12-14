package lifetech.common.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vazkii.arl.recipe.ModRecipe;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-12-05 14:36
 **/
public abstract class ModResetRecipe<T extends Item> extends ModRecipe {

    protected final Class<? extends Item> inputClass;
    protected ItemStack input=ItemStack.EMPTY;

    public ModResetRecipe(ResourceLocation res,Class<? extends Item> inputClass) {
        super(res);
        this.inputClass=inputClass;

    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        return matches(inv);
    }
    public <T extends Item> boolean matches(InventoryCrafting inv){
        int noairamount=0;
        ItemStack itemStack=ItemStack.EMPTY;
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack temp= inv.getStackInSlot(i);
            if (!temp.isEmpty()){itemStack=temp;}
            if (temp.isEmpty()){
                noairamount++;
            }
        }
        if (noairamount==inv.getSizeInventory()-1 && itemStack.getItem().getClass().isAssignableFrom(inputClass)){ input=itemStack; return true;}
        return false;
    }

}
