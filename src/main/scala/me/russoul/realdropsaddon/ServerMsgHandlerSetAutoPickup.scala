package me.russoul.realdropsaddon

import me.russoul.realdropsaddon.common.MsgSetAutoPickup
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.common.network.simpleimpl.{IMessage, IMessageHandler, MessageContext}
import net.minecraftforge.fml.relauncher.Side

class ServerMsgHandlerSetAutoPickup extends IMessageHandler[MsgSetAutoPickup, IMessage]{

  override def onMessage(message: MsgSetAutoPickup, ctx: MessageContext): IMessage = {

    if(FMLCommonHandler.instance().getSide.isServer){
      val id = message.uuid
      val on = message.on

      RealDropsAddon.proxy.autoPickupConfigs(id) = on
    }

    null
  }
}
