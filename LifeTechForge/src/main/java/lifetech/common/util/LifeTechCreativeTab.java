package lifetech.common.util;

import lifetech.common.item.ModItemInitializer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-11-21 21:36
 **/
public class LifeTechCreativeTab extends CreativeTabs {

    public static final LifeTechCreativeTab INSTANCE=new LifeTechCreativeTab();
    public LifeTechCreativeTab() {
        super("lifetech_creative_tab");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItemInitializer.LIFE_ESSENCE);
    }

}
