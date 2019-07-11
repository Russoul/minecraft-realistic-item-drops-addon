package me.russoul.realdropsaddon.client

import java.util.UUID

import me.russoul.realdropsaddon.RealDropsAddon
import me.russoul.realdropsaddon.common.{CommonProxy, MsgSetAutoPickup, MsgToggleAutoPickup}
import net.minecraft.client.Minecraft
import net.minecraft.client.settings.KeyBinding
import net.minecraft.util.text.TextComponentString
import net.minecraftforge.client.settings.KeyModifier
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}
import org.lwjgl.input.Keyboard
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.{ClientTickEvent, Phase}
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import scala.collection.mutable

class ClientProxy extends CommonProxy{


  val keyBindings : Array[KeyBinding] = Array.ofDim(2)

  override def preInit(event: FMLPreInitializationEvent): Unit = {
    super.preInit(event)
    MinecraftForge.EVENT_BUS.register(this)
  }


  override def init(event: FMLInitializationEvent) : Unit = {
    super.init(event)
    keyBindings(0) = new KeyBinding("key.realdropsaddon.desc1", Keyboard.KEY_Z, "key.categories.inventory")
    keyBindings(1) = new KeyBinding("key.realdropsaddon.desc2", Keyboard.KEY_LMENU, "key.categories.inventory")

    keyBindings.foreach(ClientRegistry.registerKeyBinding)

  }


  @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
  def onKeyInputEvent(event: InputEvent.KeyInputEvent): Unit = {
    val keyBindings = RealDropsAddon.proxy.asInstanceOf[ClientProxy].keyBindings
    if (keyBindings(0).isPressed) {
      if(Minecraft.getMinecraft != null && Minecraft.getMinecraft.player != null){
        RealDropsAddon.network.sendToServer(new MsgToggleAutoPickup(Minecraft.getMinecraft.player.getUniqueID))

        val id = Minecraft.getMinecraft.player.getUniqueID

        if(autoPickupConfigsToggle.get(id).nonEmpty){
          autoPickupConfigsToggle(id) = !autoPickupConfigsToggle(id)
          autoPickupConfigs(id) = !autoPickupConfigs(id)
        }else{
          autoPickupConfigsToggle(id) = true
          autoPickupConfigs(id) = true
        }

        val isOn = autoPickupConfigsToggle(id)
        Minecraft.getMinecraft.player.sendMessage(new TextComponentString(s"auto pickup §2${if(isOn) "enabled" else "disabled"}§f"))
      }

    }
  }


  private var downFirstTime = true
  private var upFirstTime = true
  @SubscribeEvent
  def onClientTickEvent(event : ClientTickEvent) : Unit = {
    if(event.phase == Phase.END){

      if(Minecraft.getMinecraft != null && Minecraft.getMinecraft.player != null){
        val id = Minecraft.getMinecraft.player.getUniqueID

        if(Minecraft.getMinecraft.currentScreen == null){
          if(Keyboard.isKeyDown(keyBindings(1).getKeyCode)){
            if(downFirstTime){
              downFirstTime = false
              upFirstTime = true
              RealDropsAddon.network.sendToServer(new MsgSetAutoPickup(Minecraft.getMinecraft.player.getUniqueID, true))
              autoPickupConfigs(id) = true
            }
          }else{

            downFirstTime = true
            if(upFirstTime && !getAutoPickupConfigToggleForPlayerID(id)){
              RealDropsAddon.network.sendToServer(new MsgSetAutoPickup(Minecraft.getMinecraft.player.getUniqueID, false))
              autoPickupConfigs(id) = false
            }
            upFirstTime = false
          }
        }else{
          if(!downFirstTime && !getAutoPickupConfigToggleForPlayerID(id)){
            downFirstTime = true
            upFirstTime = true
            RealDropsAddon.network.sendToServer(new MsgSetAutoPickup(Minecraft.getMinecraft.player.getUniqueID, false))
            autoPickupConfigs(id) = false
          }
        }
      }


    }
  }

}
