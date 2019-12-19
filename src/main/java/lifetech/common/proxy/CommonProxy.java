package lifetech.common.proxy;

import lifetech.LifeTech;
import lifetech.common.block.ModBlockInitializer;
import lifetech.common.event.ModEventInitializer;
import lifetech.common.item.ModItemInitializer;
import lifetech.common.network.GuiHandler;
import lifetech.common.network.MessageAccelerator;
import lifetech.common.recipes.ModRecipeInitializer;
import lifetech.common.recipes.ModSmeltingInitializer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

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
        ModSmeltingInitializer.initializer();
        ModRecipeInitializer.initializer();
        GuiHandler.initializer();
        LifeTech.NETWORK.registerMessage(MessageAccelerator.HandlerAccelerator.class,MessageAccelerator.class,0,Side.CLIENT);


    }

    public void init(FMLInitializationEvent event) {

    }
    public void postInit(FMLPostInitializationEvent event) {
    }
}
