package me.russoul.realdropsaddon.asm

import java.util

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.Name("Real item drops plugin")
@IFMLLoadingPlugin.TransformerExclusions(Array("me.russoul.realdropsaddon.asm"))
@IFMLLoadingPlugin.SortingIndex(1001) // After runtime deobfuscation
class Coremod extends IFMLLoadingPlugin{


  override def getASMTransformerClass: Array[String] = Array("me.russoul.realdropsaddon.asm.ClassTransformer")

  override def getModContainerClass: String = null

  override def getSetupClass: String = null

  override def injectData(data: util.Map[String, AnyRef]): Unit = {

  }

  override def getAccessTransformerClass: String = null
}
