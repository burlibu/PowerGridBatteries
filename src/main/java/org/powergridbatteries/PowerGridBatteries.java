package org.powergridbatteries;

import com.simibubi.create.api.behaviour.display.DisplaySource;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.jetbrains.annotations.Nullable;
import org.patryk3211.powergrid.collections.ModdedBlockEntities;
import org.patryk3211.powergrid.collections.ModdedDisplaySources;
import org.patryk3211.powergrid.config.ThermalValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.function.DoubleSupplier;

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
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::loadComplete);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            try {
                // Register Thermal Values Provider for PowerGrid's thermal dissipation & heat calculations
                ThermalValues.register(new ThermalValues.Provider() {
                    @Override
                    public @Nullable DoubleSupplier getPower(Block block) {
                        if (block == ModBlocks.SMALL_BATTERY.get()) return () -> 100.0;
                        if (block == ModBlocks.MEDIUM_BATTERY.get()) return () -> 2400.0;
                        if (block == ModBlocks.HIGH_VOLTAGE_BATTERY.get()) return () -> 60000.0;
                        if (block == ModBlocks.SUBSTATION_BATTERY.get()) return () -> 500000.0;
                        return null;
                    }

                    @Override
                    public @Nullable DoubleSupplier getMass(Block block) {
                        if (block == ModBlocks.SMALL_BATTERY.get()) return () -> 1.5;
                        if (block == ModBlocks.MEDIUM_BATTERY.get()) return () -> 3.0;
                        if (block == ModBlocks.HIGH_VOLTAGE_BATTERY.get()) return () -> 6.0;
                        if (block == ModBlocks.SUBSTATION_BATTERY.get()) return () -> 12.0;
                        return null;
                    }
                });

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

                newSet.add(ModBlocks.SMALL_BATTERY.get());
                newSet.add(ModBlocks.MEDIUM_BATTERY.get());
                newSet.add(ModBlocks.HIGH_VOLTAGE_BATTERY.get());
                newSet.add(ModBlocks.SUBSTATION_BATTERY.get());

                unsafe.putObject(type, offset, newSet);

                // Register Display Sources for Display Links
                DisplaySource displaySource = ModdedDisplaySources.BATTERY.get();
                DisplaySource.BY_BLOCK.add(ModBlocks.SMALL_BATTERY.get(), displaySource);
                DisplaySource.BY_BLOCK.add(ModBlocks.MEDIUM_BATTERY.get(), displaySource);
                DisplaySource.BY_BLOCK.add(ModBlocks.HIGH_VOLTAGE_BATTERY.get(), displaySource);
                DisplaySource.BY_BLOCK.add(ModBlocks.SUBSTATION_BATTERY.get(), displaySource);

                LOGGER.info("Successfully registered 4 Tiered Batteries to PowerGrid MultiBlockBatteryEntity & ThermalValues!");
            } catch (Exception e) {
                LOGGER.error("Failed to register tiered battery blocks to MultiBlockBatteryEntity", e);
            }
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == ModBlocks.BATTERIES_TAB.get()) {
            event.accept(ModBlocks.SMALL_BATTERY.get());
            event.accept(ModBlocks.MEDIUM_BATTERY.get());
            event.accept(ModBlocks.HIGH_VOLTAGE_BATTERY.get());
            event.accept(ModBlocks.SUBSTATION_BATTERY.get());
        }
    }

    private void loadComplete(final FMLLoadCompleteEvent event) {
        event.enqueueWork(() -> {
            if (FMLEnvironment.dist == Dist.CLIENT) {
                PonderIndex.addPlugin(new PowerGridBatteriesPonderPlugin());
                LOGGER.info("Registered PowerGridBatteries Ponder Plugin safely during FMLLoadCompleteEvent!");
            }
        });
    }
}
