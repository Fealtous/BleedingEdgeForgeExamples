package com.example.examplemod.registries.blocks;

import com.example.examplemod.ExampleMod;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.stream.Stream;

import static com.example.examplemod.registries.RegisterUtils.*;

public class ExampleBlocks {
    private static final String MODID = ExampleMod.MODID;
    private static final DeferredRegister<Block> BUILDING_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> BUILDING_BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    static {
        registerTrivialBuildingBlock("example_block", Blocks.STONE);
    }

    public static void register(BusGroup busGroup) {
        BUILDING_BLOCKS.register(busGroup);
        BUILDING_BLOCK_ITEMS.register(busGroup);
    }
    public static DeferredRegister<Block> getBuildingBlocks() {return BUILDING_BLOCKS;}
    public static DeferredRegister<Item> getBuildingBlockItems() {return BUILDING_BLOCK_ITEMS;}

    public static Stream<Block> getAllBlocks() {
        var blockRegistries = List.of(BUILDING_BLOCKS);
        Stream<Block> out = Stream.empty();
        for (DeferredRegister<Block> registry : blockRegistries) {
            out = Stream.concat(out, registry.getEntries().stream().map(RegistryObject::get));
        }
        return out;
    }

    private static void registerTrivialBuildingBlock(String name, Block toCopy) {
        registerTrivialBlock(BUILDING_BLOCKS, BUILDING_BLOCK_ITEMS, name, toCopy);
    }

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            for (RegistryObject<Item> entry : BUILDING_BLOCK_ITEMS.getEntries()) {
                event.accept(entry);
            }
        }
    }
}
