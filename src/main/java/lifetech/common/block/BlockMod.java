package lifetech.common.block;

import lifetech.LifeTech;
import lifetech.common.util.LifeTechCreativeTab;
import net.minecraft.block.material.Material;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-11-22 12:05
 **/
public class BlockMod extends vazkii.arl.block.BlockMod {

    public BlockMod(String name, Material materialIn, String... variants) {
        super(name, materialIn, variants);
        this.setCreativeTab(LifeTechCreativeTab.INSTANCE);
    }

    @Override
    public String getModNamespace() {
        return LifeTech.MODID;
    }
}
