package me.russoul.realdropsaddon.asm

import java.io.{File, FileInputStream}

import me.russoul.realdropsaddon.RealDropsAddon
import net.minecraft.launchwrapper.IClassTransformer
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.FMLLog
import org.apache.commons.io.IOUtils
import org.objectweb.asm._
import org.objectweb.asm.tree._
import net.minecraft.launchwrapper.Launch

class ClassTransformer extends IClassTransformer{

  override def transform(name: String, transformedName: String, basicClass: Array[Byte]): Array[Byte] = {
    if(name == "realdrops.entities.EntityItemLoot"){
      val devEnv = Launch.blackboard.get("fml.deobfuscatedEnvironment").asInstanceOf[Boolean]
      if(devEnv){
        val is = new FileInputStream("../src/main/resources/assets/realdropsaddon/bytecode/EntityItemLoot.class")
        IOUtils.toByteArray(is)
      }else{
        val is = getClass.getClassLoader
          .getResourceAsStream(s"assets/${RealDropsAddon.MODID}/bytecode/EntityItemLoot.class")
        IOUtils.toByteArray(is)
      }
    }else{
      basicClass
    }
  }

}

