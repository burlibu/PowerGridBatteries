package org.powergridbatteries.battery;

import net.minecraft.world.level.block.state.BlockBehaviour;
import org.patryk3211.powergrid.electricity.battery.BatteryBlock;
import org.patryk3211.powergrid.electricity.battery.BatterySpec;

public class TieredBatteryBlock extends BatteryBlock {
    public TieredBatteryBlock(BlockBehaviour.Properties settings, BatterySpec spec) {
        super(settings);
        this.spec = spec;
    }
}
