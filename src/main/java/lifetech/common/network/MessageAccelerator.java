package lifetech.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

/**
 * @program: LifeTechForge
 * @description:
 * @author: xiaofei
 * @create: 2019-12-16 16:18
 **/
public class MessageAccelerator implements IMessage {

    private BlockPos pos;

    private long MostId;
    private long LeastId;

    public MessageAccelerator() {
    }

    public MessageAccelerator(BlockPos pos,UUID uuid) {
        this.pos = pos;
        this.MostId=uuid.getMostSignificantBits();
        this.LeastId=uuid.getLeastSignificantBits();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.fromLong(buf.readLong());
        this.LeastId=buf.readLong();
        this.MostId=buf.readLong();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeLong(this.LeastId);
        buf.writeLong( this.MostId);
    }

    public BlockPos getPos() {
        return pos;
    }

    public UUID getPlayerId() {
        return new UUID(MostId,LeastId);
    }


    @SideOnly(Side.CLIENT)
    public static class HandlerAccelerator implements IMessageHandler<MessageAccelerator, IMessage> {
        @Override
        public IMessage onMessage(MessageAccelerator messageAccelerator, MessageContext ctx) {
            BlockPos pos=messageAccelerator.pos;
            TextComponentTranslation TextComponents=new TextComponentTranslation("bindtip.accelerator.name");
            TextComponentString textComponentString=new TextComponentString(TextComponents.getFormattedText()+"X:"+pos.getX()+",Y:"+pos.getY()+",Z:"+pos.getZ());
            Minecraft.getMinecraft().player.sendMessage(textComponentString);
            return messageAccelerator;
        }
    }
}
