package lifetech.common.item;

import net.minecraft.item.Item;

/**
 * @program: w
 * @description: 模组普通物品
 * @author: xiaofei
 * @create: 2019-11-21 17:28
 **/
public class ModItemInitializer {

    //生命精华
    public static final ItemLifeEssence LIFE_ESSENCE=new ItemLifeEssence("life_essence");
    //食物精华
    public static final ItemCookedEssence COOKED_ESSENCE=new ItemCookedEssence("cooked_essence");
    //生命提取器
    public static final ItemLifeExtractor IRON_LIFE_EXTRACTOR=new ItemLifeExtractor("iron_life_extractor",Item.ToolMaterial.IRON);
    public static final ItemLifeExtractor DIAMOND_LIFE_EXTRACTOR=new ItemLifeExtractor("diamond_life_extractor", Item.ToolMaterial.DIAMOND);
    //加速器
    public static final ItemAccelerator NORMAL_LIFE_ACCELERATOR=new ItemAccelerator("normal_life_accelerator",10,false);
    public static final ItemAccelerator ADVANCED_LIFE_ACCELERATOR=new ItemAccelerator("advanced_life_accelerator",50,false);
    public static final ItemAccelerator NORMAL_SOUL_ACCELERATOR=new ItemAccelerator("normal_soul_accelerator",20,true);
    public static final ItemAccelerator ADVANCED_SOUL_ACCELERATOR=new ItemAccelerator("advanced_soul_accelerator",100,true);

    public static final ItemTickChest TICK_CHEST=new ItemTickChest("tick_chest");



    public static void initializer(){

    }
}

