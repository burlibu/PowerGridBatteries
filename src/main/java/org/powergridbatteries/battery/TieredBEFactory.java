package org.powergridbatteries.battery;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TieredBEFactory {
    public static BlockEntity create(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        return new TieredBatteryBlockEntity(type, pos, state);
    }
}
