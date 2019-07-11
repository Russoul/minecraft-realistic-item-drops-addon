package me.russoul.realdropsaddon

import me.russoul.realdropsaddon.common.{CommonProxy, MsgUpdateClientPickupConfig}
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.PlayerEvent

class ServerProxy extends CommonProxy{


  override def preInit(event : FMLPreInitializationEvent) : Unit = {
    super.preInit(event)

    MinecraftForge.EVENT_BUS.register(this)
  }


  @SubscribeEvent
  def playerLoggedInEvent(event : PlayerEvent.PlayerLoggedInEvent) : Unit = {
    RealDropsAddon.network.sendTo(new MsgUpdateClientPickupConfig(RealDropsAddon.proxy.getAutoPickupConfigToggleForPlayerID(event.player.getUniqueID)), event.player.asInstanceOf[EntityPlayerMP])
  }

  @SubscribeEvent
  def playerLoggedOutEvent(event : PlayerEvent.PlayerLoggedOutEvent) : Unit = {
    RealDropsAddon.proxy.autoPickupConfigs(event.player.getUniqueID) = false
  }


}
