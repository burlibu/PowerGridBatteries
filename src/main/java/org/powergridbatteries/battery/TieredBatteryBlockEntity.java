package org.powergridbatteries.battery;

import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.patryk3211.powergrid.electricity.battery.MultiBlockBatteryEntity;
import org.patryk3211.powergrid.utility.Lang;

import java.util.List;

public class TieredBatteryBlockEntity extends MultiBlockBatteryEntity implements IHaveGoggleInformation {
    public TieredBatteryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        var controllerBE = (TieredBatteryBlockEntity) getControllerBE();
        if (controllerBE == null) return false;

        Lang.builder().translate("gui.battery.info_header").forGoggles(tooltip);

        // Charge % & Energy
        double totalEnergy = controllerBE.getEnergy();
        double totalCapacity = controllerBE.capacity;
        float percent = (float) (totalEnergy / Math.max(1.0, totalCapacity) * 100.0);

        Lang.builder().translate("gui.battery.charge")
                .style(ChatFormatting.GRAY)
                .forGoggles(tooltip);

        Lang.builder()
                .text(String.format("%.1f%% (%.1f / %.1f kJ)", percent, totalEnergy / 1000.0, totalCapacity / 1000.0))
                .style(ChatFormatting.AQUA)
                .forGoggles(tooltip, 1);

        // Voltage
        if (controllerBE.sourceCoupling != null) {
            double voltage = controllerBE.sourceCoupling.getVoltage();
            Lang.builder().translate("gui.battery.voltage")
                    .style(ChatFormatting.GRAY)
                    .forGoggles(tooltip);
            Lang.builder()
                    .text(String.format("%.1f V", voltage))
                    .style(ChatFormatting.GOLD)
                    .forGoggles(tooltip, 1);
        }

        // Temperature
        if (controllerBE.thermalBehaviour != null) {
            float temp = controllerBE.thermalBehaviour.getTemperature();
            Lang.builder().translate("gui.battery.temperature")
                    .style(ChatFormatting.GRAY)
                    .forGoggles(tooltip);
            Lang.builder()
                    .text(String.format("%.1f °C", temp))
                    .style(temp > 100 ? ChatFormatting.RED : ChatFormatting.GREEN)
                    .forGoggles(tooltip, 1);
        }

        return true;
    }
}
