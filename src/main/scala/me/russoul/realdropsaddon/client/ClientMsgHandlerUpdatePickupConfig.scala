package me.russoul.realdropsaddon.client

import me.russoul.realdropsaddon.RealDropsAddon
import me.russoul.realdropsaddon.common.MsgUpdateClientPickupConfig
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.network.simpleimpl.{IMessage, IMessageHandler, MessageContext}

class ClientMsgHandlerUpdatePickupConfig extends IMessageHandler[MsgUpdateClientPickupConfig, IMessage]{

  override def onMessage(req: MsgUpdateClientPickupConfig, messageContext: MessageContext): IMessage = {

    RealDropsAddon.proxy.autoPickupConfigs(Minecraft.getMinecraft.player.getUniqueID) = false
    RealDropsAddon.proxy.autoPickupConfigsToggle(Minecraft.getMinecraft.player.getUniqueID) = req.toggled

    null
  }
}
