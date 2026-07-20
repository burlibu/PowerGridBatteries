package org.powergridbatteries.battery;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.patryk3211.powergrid.electricity.info.ElectricPropertiesUtils;
import org.powergridbatteries.utility.ClientUtils;

import java.util.List;

public class TieredBatteryItemClient {
    public static void appendHoverText(TieredBatteryBlock block, ItemStack stack, TooltipFlag tooltipFlag, List<Component> tooltipComponents) {
        Player player = ClientUtils.getClientPlayer();
        if (player != null) {
            ElectricPropertiesUtils.modify(block, stack, player, tooltipFlag, tooltipComponents);
        }
    }
}
