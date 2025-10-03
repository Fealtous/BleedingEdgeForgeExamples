package com.example.examplemod;

import com.example.examplemod.datagen.ExampleModelProvider;
import com.example.examplemod.registries.blocks.ExampleBlocks;
import com.example.examplemod.registries.items.ExampleItems;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.AddGuiOverlayLayersEvent;
import net.minecraftforge.client.gui.overlay.ForgeLayer;
import net.minecraftforge.client.gui.overlay.ForgeLayeredDraw;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MODID)
public final class ExampleMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "examplemod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final ResourceLocation rlTex = ResourceLocation.fromNamespaceAndPath(MODID, "textures/gui/basic_tex.png");

    public ExampleMod(FMLJavaModLoadingContext context) {
        BusGroup modBusGroup = context.getModBusGroup();
//        FMLCommonSetupEvent.getBus(modBusGroup).addListener(ExampleItems::commonSetup);
        BuildCreativeModeTabContentsEvent.getBus(modBusGroup).addListener(ExampleBlocks::addCreative);

        ExampleItems.register(modBusGroup);
        GatherDataEvent.getBus(modBusGroup).addListener(ExampleMod::gatherData);
    }


    public static void gatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var output = gen.getPackOutput();
        gen.addProvider(event.includeClient(), new ExampleModelProvider(output));
    }
}



