package com.example.examplemod.datagen;

import com.example.examplemod.registries.items.ExampleItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.BiConsumer;

public class ExampleItemModelGenerator extends ItemModelGenerators {
    public ExampleItemModelGenerator(ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> consumer) {
        super(itemModelOutput, consumer);
    }

    @Override
    public void run() {
        for (RegistryObject<Item> entry : ExampleItems.getMaterialItems().getEntries()) {
            this.generateFlatItem(entry.get(), ModelTemplates.FLAT_ITEM);
        }

    }
}
