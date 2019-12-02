package lifetech.common.network;

import lifetech.LifeTech;
import lifetech.client.gui.GuiContainerItemTickChest;
import lifetech.common.container.ContainerItemTickChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-11-29 19:55
 **/
public class GuiHandler implements IGuiHandler {

    public static final int GUI_ITEM_TICK_CHEST_ID = 1;
    public static void initializer(){
        NetworkRegistry.INSTANCE.registerGuiHandler(LifeTech.instance,new GuiHandler());
    }
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID)
        {
            case GUI_ITEM_TICK_CHEST_ID:
                return new ContainerItemTickChest(player,world);
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID)
        {
            case GUI_ITEM_TICK_CHEST_ID:
                return new GuiContainerItemTickChest(new ContainerItemTickChest(player,world),player);
            default:
                return null;
        }
    }
}
