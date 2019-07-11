package me.russoul.realdropsaddon.common

import java.util.UUID

import io.netty.buffer.ByteBuf
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

class MsgSetAutoPickup extends IMessage{

  var uuid : UUID = _
  var on : Boolean = _

  def this(id : UUID, on : Boolean){
    this()
    uuid = id
    this.on = on
  }

  override def fromBytes(buf: ByteBuf): Unit = {
    val most = buf.readLong()
    val least = buf.readLong()
    uuid = new UUID(most, least)
    on = buf.readBoolean()
  }

  override def toBytes(buf: ByteBuf): Unit = {
    buf.writeLong(uuid.getMostSignificantBits)
    buf.writeLong(uuid.getLeastSignificantBits)
    buf.writeBoolean(on)
  }
}
