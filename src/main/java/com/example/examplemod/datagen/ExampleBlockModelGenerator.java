package com.example.examplemod.datagen;

import com.example.examplemod.registries.blocks.ExampleBlocks;
import com.example.examplemod.registries.items.ExampleItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ExampleBlockModelGenerator extends BlockModelGenerators {
    public ExampleBlockModelGenerator(Consumer<BlockModelDefinitionGenerator> p_378137_, ItemModelOutput p_378502_, BiConsumer<ResourceLocation, ModelInstance> p_378240_) {
        super(p_378137_, p_378502_, p_378240_);
    }

    @Override
    public void run() {
        for (RegistryObject<Block> entry : ExampleBlocks.getBuildingBlocks().getEntries()) {
            this.createTrivialCube(entry.get());
        }
        ExampleBlocks.getBuildingBlockItems().getEntries().forEach((item) -> {
            itemModelOutput.accept(item.get(), ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(((BlockItem) item.get()).getBlock())));
        });
    }
}
