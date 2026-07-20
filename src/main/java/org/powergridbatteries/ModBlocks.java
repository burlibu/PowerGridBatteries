package org.powergridbatteries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.powergridbatteries.battery.BatteryTiers;
import org.powergridbatteries.battery.TieredBatteryBlock;
import org.powergridbatteries.battery.TieredBatteryItem;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PowerGridBatteries.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PowerGridBatteries.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PowerGridBatteries.MOD_ID);

    public static final DeferredBlock<TieredBatteryBlock> SMALL_BATTERY = registerBattery("small_battery", BatteryTiers.SMALL_BATTERY);
    public static final DeferredBlock<TieredBatteryBlock> MEDIUM_BATTERY = registerBattery("medium_battery", BatteryTiers.MEDIUM_BATTERY);
    public static final DeferredBlock<TieredBatteryBlock> HIGH_VOLTAGE_BATTERY = registerBattery("high_voltage_battery", BatteryTiers.HIGH_VOLTAGE_BATTERY);
    public static final DeferredBlock<TieredBatteryBlock> SUBSTATION_BATTERY = registerBattery("substation_battery", BatteryTiers.SUBSTATION_BATTERY);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BATTERIES_TAB = CREATIVE_MODE_TABS.register("batteries_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.powergrid_batteries"))
            .icon(() -> new ItemStack(SMALL_BATTERY.get()))
            .build());

    private static DeferredBlock<TieredBatteryBlock> registerBattery(String name, org.patryk3211.powergrid.electricity.battery.BatterySpec spec) {
        DeferredBlock<TieredBatteryBlock> block = BLOCKS.register(name, () -> new TieredBatteryBlock(
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.METAL)
                        .strength(2.0f, 3.0f)
                        .requiresCorrectToolForDrops(),
                spec
        ));
        ITEMS.register(name, () -> new TieredBatteryItem(block.get(), new Item.Properties()));
        return block;
    }
}
