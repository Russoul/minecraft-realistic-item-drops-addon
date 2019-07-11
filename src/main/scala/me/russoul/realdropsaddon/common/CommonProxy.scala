package me.russoul.realdropsaddon.common

import java.util.UUID

import me.russoul.realdropsaddon.RealDropsAddon
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}

import scala.collection.mutable

class CommonProxy {

  val autoPickupConfigs : mutable.Map[UUID, Boolean] = mutable.Map.empty

  def getAutoPickupConfigForPlayerID(id : UUID) : Boolean = {
    autoPickupConfigs.getOrElse(id, false)
  }

  val autoPickupConfigsToggle : mutable.Map[UUID, Boolean] = mutable.Map.empty

  def getAutoPickupConfigToggleForPlayerID(id : UUID) : Boolean = {
    autoPickupConfigsToggle.getOrElse(id, false)
  }

  def preInit(event: FMLPreInitializationEvent): Unit = {

  }

  def init(event: FMLInitializationEvent) : Unit = {

  }

}
