package org.powergridbatteries;

import com.simibubi.create.api.behaviour.display.DisplaySource;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import org.patryk3211.powergrid.collections.ModdedBlockEntities;
import org.patryk3211.powergrid.collections.ModdedDisplaySources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

@Mod(PowerGridBatteries.MOD_ID)
public class PowerGridBatteries {
    public static final String MOD_ID = "powergrid_batteries";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public PowerGridBatteries(IEventBus modEventBus) {
        LOGGER.info("Initializing Create: Power Grid Batteries Add-on!");

        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.ITEMS.register(modEventBus);
        ModBlocks.CREATIVE_MODE_TABS.register(modEventBus);

        modEventBus.addListener(this::setup);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.addListener(this::clientSetup);
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            try {
                // Use Unsafe to mutate final validBlocks field in BlockEntityType
                Field validBlocksField = BlockEntityType.class.getDeclaredField("validBlocks");
                Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
                unsafeField.setAccessible(true);
                Unsafe unsafe = (Unsafe) unsafeField.get(null);
                long offset = unsafe.objectFieldOffset(validBlocksField);

                BlockEntityType<?> type = ModdedBlockEntities.MULTIBLOCK_BATTERY.get();
                @SuppressWarnings("unchecked")
                Set<Block> oldSet = (Set<Block>) unsafe.getObject(type, offset);
                Set<Block> newSet = new HashSet<>(oldSet);

                newSet.add(ModBlocks.COPPER_BATTERY.get());
                newSet.add(ModBlocks.IRON_BATTERY.get());
                newSet.add(ModBlocks.GOLD_BATTERY.get());
                newSet.add(ModBlocks.DIAMOND_BATTERY.get());
                newSet.add(ModBlocks.NETHERITE_BATTERY.get());

                unsafe.putObject(type, offset, newSet);

                // Register Display Sources for Display Links
                DisplaySource displaySource = ModdedDisplaySources.BATTERY.get();
                DisplaySource.BY_BLOCK.add(ModBlocks.COPPER_BATTERY.get(), displaySource);
                DisplaySource.BY_BLOCK.add(ModBlocks.IRON_BATTERY.get(), displaySource);
                DisplaySource.BY_BLOCK.add(ModBlocks.GOLD_BATTERY.get(), displaySource);
                DisplaySource.BY_BLOCK.add(ModBlocks.DIAMOND_BATTERY.get(), displaySource);
                DisplaySource.BY_BLOCK.add(ModBlocks.NETHERITE_BATTERY.get(), displaySource);

                LOGGER.info("Successfully registered 5 Tiered Batteries to PowerGrid MultiBlockBatteryEntity via Unsafe!");
            } catch (Exception e) {
                LOGGER.error("Failed to register tiered battery blocks to MultiBlockBatteryEntity", e);
            }
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            PonderIndex.addPlugin(new PowerGridBatteriesPonderPlugin());
            LOGGER.info("Registered PowerGridBatteries Ponder Plugin!");
        });
    }
}
