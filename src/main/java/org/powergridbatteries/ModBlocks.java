package org.powergridbatteries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.powergridbatteries.battery.BatteryTiers;
import org.powergridbatteries.battery.TieredBatteryBlock;
import org.powergridbatteries.battery.TieredBatteryItem;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PowerGridBatteries.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PowerGridBatteries.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PowerGridBatteries.MOD_ID);

    public static final DeferredBlock<TieredBatteryBlock> COPPER_BATTERY = registerBattery("copper_battery", BatteryTiers.COPPER);
    public static final DeferredBlock<TieredBatteryBlock> IRON_BATTERY = registerBattery("iron_battery", BatteryTiers.IRON);
    public static final DeferredBlock<TieredBatteryBlock> GOLD_BATTERY = registerBattery("gold_battery", BatteryTiers.GOLD);
    public static final DeferredBlock<TieredBatteryBlock> DIAMOND_BATTERY = registerBattery("diamond_battery", BatteryTiers.DIAMOND);
    public static final DeferredBlock<TieredBatteryBlock> NETHERITE_BATTERY = registerBattery("netherite_battery", BatteryTiers.NETHERITE);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BATTERIES_TAB = CREATIVE_MODE_TABS.register("batteries_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.powergrid_batteries"))
            .icon(() -> new ItemStack(COPPER_BATTERY.get()))
            .displayItems((parameters, output) -> {
                output.accept(COPPER_BATTERY.get());
                output.accept(IRON_BATTERY.get());
                output.accept(GOLD_BATTERY.get());
                output.accept(DIAMOND_BATTERY.get());
                output.accept(NETHERITE_BATTERY.get());
            })
            .build());

    private static DeferredBlock<TieredBatteryBlock> registerBattery(String name, org.patryk3211.powergrid.electricity.battery.BatterySpec spec) {
        DeferredBlock<TieredBatteryBlock> block = BLOCKS.register(name, () -> new TieredBatteryBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK), spec));
        ITEMS.register(name, () -> new TieredBatteryItem(block.get(), new Item.Properties()));
        return block;
    }
}
