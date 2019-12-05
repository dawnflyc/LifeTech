package lifetech.common.container;

import lifetech.common.item.ItemTickChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-11-29 20:18
 **/
public class ContainerItemTickChest extends ContainerChest {

    private final EntityPlayer player;
    private final ItemStack itemStack;
    private final static int size=27;
    private final World world;

    public ContainerItemTickChest(EntityPlayer player, World world) {
        super(player.inventory,new InventoryTickChest(getListByNBT(player.getHeldItem(EnumHand.MAIN_HAND))), player);
        this.player=player;
        this.itemStack=player.getHeldItem(EnumHand.MAIN_HAND);
        this.world=world;
        if (itemStack.getTagCompound()!=null){
            itemStack.getTagCompound().setBoolean("is_open_container",true);
        }
    }

    public static NonNullList<ItemStack> getListByNBT(ItemStack itemStack) {
        NBTTagCompound nbt=itemStack.getTagCompound();
        if (nbt==null){return NonNullList.withSize(size,ItemStack.EMPTY);}
        return readNBT(nbt,size);
    }
    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        if (world.isRemote){return;}
        if (super.getLowerChestInventory() instanceof InventoryTickChest) {
            NonNullList<ItemStack> list=((InventoryTickChest)super.getLowerChestInventory()).getInventoryContents();
            saveByNBT(list, itemStack);
            itemStack.getTagCompound().setBoolean("is_open_container",false);
        }
    }

    public static void saveByNBT(NonNullList<ItemStack> list,ItemStack itemStack) {
        itemStack.setTagCompound((NBTTagCompound) writeNBT(list,itemStack));
    }
    public static NBTBase writeNBT(NonNullList<ItemStack> instance,ItemStack itemStack) {
        return ItemStackHelper.saveAllItems(itemStack.getTagCompound()==null ? new NBTTagCompound() : itemStack.getTagCompound() , instance);
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
        if (slotId>=0 && slotId< super.inventorySlots.size()){
            if (super.inventorySlots.get(slotId).getStack().getItem() instanceof ItemTickChest){
                return ItemStack.EMPTY;
            }
        }
        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }

    public static NonNullList<ItemStack> readNBT(NBTBase nbt, int size) {
        if (nbt instanceof NBTTagCompound){
            NonNullList list=NonNullList.withSize(size,ItemStack.EMPTY);
            ItemStackHelper.loadAllItems((NBTTagCompound)nbt,list);
            return list;
        }
        return null;
    }
}
