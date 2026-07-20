package org.powergridbatteries.battery;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class TieredBatteryItem extends BlockItem {
    public TieredBatteryItem(TieredBatteryBlock block, Properties properties) {
        super(block, properties);
    }

    @Override
    public TieredBatteryBlock getBlock() {
        return (TieredBatteryBlock) super.getBlock();
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        var player = (context.level() != null && context.level().isClientSide) ? Minecraft.getInstance().player : null;
        getBlock().appendProperties(stack, player, tooltipComponents);
    }
}
