package me.russoul.realdropsaddon

import me.russoul.realdropsaddon.common.MsgToggleAutoPickup
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.common.network.simpleimpl.{IMessage, IMessageHandler, MessageContext}

class ServerMsgHandlerToggleAutoPickup extends IMessageHandler[MsgToggleAutoPickup, IMessage]{

  override def onMessage(message: MsgToggleAutoPickup, ctx: MessageContext): IMessage = {

    if(FMLCommonHandler.instance().getSide.isServer){
      val id = message.uuid

      if(RealDropsAddon.proxy.autoPickupConfigs.get(id).nonEmpty){
        RealDropsAddon.proxy.autoPickupConfigs(id) = !RealDropsAddon.proxy.autoPickupConfigs(id)
        RealDropsAddon.proxy.autoPickupConfigsToggle(id) = !RealDropsAddon.proxy.autoPickupConfigsToggle(id)
      }else{
        RealDropsAddon.proxy.autoPickupConfigs(id) = true
        RealDropsAddon.proxy.autoPickupConfigsToggle(id) = true
      }
    }

    null
  }
}
