package me.russoul.realdropsaddon.common

import io.netty.buffer.ByteBuf
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

class MsgUpdateClientPickupConfig extends IMessage{

  var toggled : Boolean = false

  def this(toggled : Boolean){
    this()
    this.toggled = toggled
  }

  override def fromBytes(byteBuf: ByteBuf): Unit = {
    toggled = byteBuf.readBoolean()
  }

  override def toBytes(byteBuf: ByteBuf): Unit = {
    byteBuf.writeBoolean(toggled)
  }
}
