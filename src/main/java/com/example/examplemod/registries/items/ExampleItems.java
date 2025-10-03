package com.example.examplemod.registries.items;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.stream.Stream;

public class ExampleItems {
    private static final String MODID = ExampleMod.MODID;

    private static final DeferredRegister<Item> MATERIAL_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<CreativeModeTab> EXAMPLE_TAB_REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    private static final RegistryObject<Item> EXAMPLE_ITEM = MATERIAL_ITEMS.register("example_item",
            () -> new Item(new Item.Properties().setId(MATERIAL_ITEMS.key("example_item"))));

    private static final RegistryObject<CreativeModeTab> MATERIAL_TAB = EXAMPLE_TAB_REGISTRY.register("example_tab",
            () -> CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
                    .displayItems((p,o) -> {
                        o.accept(EXAMPLE_ITEM.get());
                    }).build());
    static {

    }
    public static void register(BusGroup busGroup) {
        MATERIAL_ITEMS.register(busGroup);
        EXAMPLE_TAB_REGISTRY.register(busGroup);
    }

    public static DeferredRegister<Item> getMaterialItems() {
        return MATERIAL_ITEMS;
    }

    public static Stream<Item> getAllItems() {
        var itemRegistries = List.of(MATERIAL_ITEMS);
        Stream<Item> out = Stream.empty();
        for (DeferredRegister<Item> registry : itemRegistries) {
            out = Stream.concat(out, registry.getEntries().stream().map(RegistryObject::get));
        }
        return out;
    }
}
