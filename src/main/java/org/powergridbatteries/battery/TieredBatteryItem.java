package org.powergridbatteries.battery;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.minecraft.ChatFormatting;

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
        if (FMLEnvironment.dist == Dist.CLIENT) {
            tooltipComponents.add(Component.literal("Power Grid").withStyle(ChatFormatting.BLUE));
            TieredBatteryItemClient.appendHoverText(getBlock(), stack, tooltipFlag, tooltipComponents);
        }
        
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
