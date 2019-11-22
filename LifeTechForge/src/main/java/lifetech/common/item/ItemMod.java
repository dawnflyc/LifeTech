package lifetech.common.item;

import lifetech.LifeTech;
import lifetech.common.util.LifeTechCreativeTab;

/**
 * @program: LifeTechForge
 * @description: 此mod的Item
 * @author: xiaofei
 * @create: 2019-11-21 17:29
 **/
public class ItemMod extends vazkii.arl.item.ItemMod {

    public ItemMod(String name, String... variants) {
        super(name, variants);
        this.setCreativeTab(LifeTechCreativeTab.INSTANCE);
    }

    @Override
    public String getModNamespace() {
        return LifeTech.MODID;
    }
}
