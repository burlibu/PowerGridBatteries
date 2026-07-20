package org.powergridbatteries.battery;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.patryk3211.powergrid.electricity.battery.BatteryBlock;
import org.patryk3211.powergrid.electricity.battery.BatterySpec;
import org.patryk3211.powergrid.electricity.battery.MultiBlockBatteryEntity;

import java.util.ArrayList;
import java.util.List;

public class TieredBatteryBlock extends BatteryBlock {
    public TieredBatteryBlock(BlockBehaviour.Properties settings, BatterySpec spec) {
        super(settings);
        this.spec = spec;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TieredBatteryBlockEntity(getBlockEntityType(), pos, state);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder);
        if (drops.isEmpty()) {
            drops = new ArrayList<>();
            ItemStack stack = new ItemStack(this);
            var be = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
            if (be instanceof MultiBlockBatteryEntity battery) {
                var tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                tag.putDouble("Energy", Math.floor(battery.getIndividualEnergy()));
                stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
            }
            drops.add(stack);
        }
        return drops;
    }
}
