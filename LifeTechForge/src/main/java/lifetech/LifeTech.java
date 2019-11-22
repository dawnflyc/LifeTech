package lifetech;

import lifetech.common.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @program: LifeTechForge
 * @description: main
 * @author: xiaofei
 * @create: 2019-11-21 16:07
 **/
@Mod(modid = LifeTech.MODID, name = LifeTech.NAME, version = LifeTech.VERSION, dependencies = LifeTech.DEPEND, acceptedMinecraftVersions = "[1.12]")
public class LifeTech {
    /**
     * id
     */
    public static final String MODID = "lifetech";
    /**
     * name
     */
    public static final String NAME = "Life Tech";
    /**
     * 版本
     */
    public static final String VERSION = "@VERSION@";
    /**
     * 前置
     */
    public static final String DEPEND = "required-after:autoreglib";
    /**
     * 日志
     */
    public static final Logger LOGGER = LogManager.getLogger();

    public static final SimpleNetworkWrapper NETWORK = new SimpleNetworkWrapper(MODID);

    @SidedProxy(clientSide = "lifetech.client.proxy.ClientProxy", serverSide = "lifetech.common.proxy.CommonProxy")
    public static CommonProxy PROXY;

    @Mod.Instance
    public static LifeTech instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    PROXY.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    PROXY.init(event);
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
     PROXY.postInit(event);
    }
}
