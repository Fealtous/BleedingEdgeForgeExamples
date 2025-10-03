package com.example.examplemod.registries;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;

public final class RegisterUtils {

    /**
     * Register a block and associated block item from/into specific mod registries
     */
    private static void registerBlockAndItemWithProperties(DeferredRegister<Block> blockReg, DeferredRegister<Item> itemReg, String name, final BlockBehaviour.Properties properties) {
        var blk = blockReg.register(name, () -> new Block(properties.setId(blockReg.key(name))));
        itemReg.register(name, () -> new BlockItem(blk.get(), new Item.Properties().setId(itemReg.key(name))));
    }

    public static void registerTrivialBlock(DeferredRegister<Block> blockReg, DeferredRegister<Item> itemReg, String name, Block toCopy) {
        registerBlockAndItemWithProperties(blockReg, itemReg, name, BlockBehaviour.Properties.ofFullCopy(toCopy));
    }
}
