package me.russoul.realdropsaddon.common

import java.util.UUID

import io.netty.buffer.ByteBuf
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

class MsgToggleAutoPickup extends IMessage{

  var uuid : UUID = _

  def this(id : UUID){
    this()
    uuid = id
  }

  override def fromBytes(buf: ByteBuf): Unit = {
    val most = buf.readLong()
    val least = buf.readLong()
    uuid = new UUID(most, least)
  }

  override def toBytes(buf: ByteBuf): Unit = {
    buf.writeLong(uuid.getMostSignificantBits)
    buf.writeLong(uuid.getLeastSignificantBits)
  }
}
