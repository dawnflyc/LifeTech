package lifetech.common.proxy;

import lifetech.common.block.ModBlockInitializer;
import lifetech.common.event.ModEventInitializer;
import lifetech.common.item.ModItemInitializer;
import lifetech.common.network.GuiHandler;
import lifetech.common.recipes.Smelting;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @program: LifeTechForge
 * @description: 通用代理
 * @author: xiaofei
 * @create: 2019-11-21 16:26
 **/
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        ModItemInitializer.initializer();
        ModBlockInitializer.initializer();
        ModEventInitializer.initializer();
        GuiHandler.initializer();
        Smelting.initializer();

    }

    public void init(FMLInitializationEvent event) {

    }
    public void postInit(FMLPostInitializationEvent event) {
    }
}
