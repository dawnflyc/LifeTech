package lifetech.common.item;

import lifetech.LifeTech;
import net.minecraft.block.Block;
import vazkii.arl.item.ItemModTool;

import java.util.Set;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-11-22 12:10
 **/
public class ItemLifeExtractor extends ItemModTool {
    protected ItemLifeExtractor(String name, float attackDamage, float speed, ToolMaterial material, Set<Block> effectiveBlocks, String... variants) {
        super(name, attackDamage, speed, material, effectiveBlocks, variants);
    }

    @Override
    public String getModNamespace() {
        return LifeTech.MODID;
    }
}
