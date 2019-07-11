package me.russoul.realdropsaddon

import me.russoul.realdropsaddon.client.ClientMsgHandlerUpdatePickupConfig
import me.russoul.realdropsaddon.common.{CommonProxy, MsgSetAutoPickup, MsgToggleAutoPickup, MsgUpdateClientPickupConfig}
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.{EventHandler, Instance}
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
import net.minecraftforge.fml.relauncher.Side
import org.apache.logging.log4j.Logger
import net.minecraftforge.fml.common.network.NetworkRegistry

@Mod(modid = "realdropsaddon", name = "Realistic Item Drops Addon", modLanguage = "scala", dependencies = "required-after:realdrops")
object RealDropsAddon {
  val MODID = "realdropsaddon"
  val NAME = "Realistic Item Drops Addon"

  @SidedProxy(
    clientSide = "me.russoul.realdropsaddon.client.ClientProxy",
    serverSide = "me.russoul.realdropsaddon.ServerProxy")
  var proxy : CommonProxy = _
  var logger : Logger = _
  val network: SimpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MODID)

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    logger = event.getModLog
    proxy.preInit(event)

    network.registerMessage(classOf[ServerMsgHandlerToggleAutoPickup], classOf[MsgToggleAutoPickup], 0, Side.SERVER)
    network.registerMessage(classOf[ServerMsgHandlerSetAutoPickup], classOf[MsgSetAutoPickup], 1, Side.SERVER)
    network.registerMessage(classOf[ClientMsgHandlerUpdatePickupConfig], classOf[MsgUpdateClientPickupConfig], 2, Side.CLIENT)
  }

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {
    proxy.init(event)
  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit = {

    logger.log(logger.getLevel, "Realistic Item Drops Addon loaded !")
  }

}

