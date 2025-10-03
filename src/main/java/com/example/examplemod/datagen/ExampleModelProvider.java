package com.example.examplemod.datagen;

import com.example.examplemod.registries.blocks.ExampleBlocks;
import com.example.examplemod.registries.items.ExampleItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.stream.Stream;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ExampleModelProvider extends ModelProvider {
        public ExampleModelProvider(PackOutput packOutput) {
            super(packOutput);
        }

        @Override
        protected Stream<Item> getKnownItems() {
            return ExampleItems.getAllItems();
        }

        @Override
        protected Stream<Block> getKnownBlocks() {
            return ExampleBlocks.getAllBlocks();
        }

        @Override
        protected BlockModelGenerators getBlockModelGenerators(BlockStateGeneratorCollector blocks, ItemInfoCollector items, SimpleModelCollector models) {
            return new ExampleBlockModelGenerator(blocks, items, models);
        }

        @Override
        protected ItemModelGenerators getItemModelGenerators(ItemInfoCollector items, SimpleModelCollector models) {
            return new ExampleItemModelGenerator(items, models);
        }
}
