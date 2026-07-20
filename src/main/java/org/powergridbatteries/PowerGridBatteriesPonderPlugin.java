package org.powergridbatteries;

import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import org.patryk3211.powergrid.ponder.scenes.DeviceScenes;

public class PowerGridBatteriesPonderPlugin implements PonderPlugin {
    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(
                ModBlocks.COPPER_BATTERY.getId(),
                ModBlocks.IRON_BATTERY.getId(),
                ModBlocks.GOLD_BATTERY.getId(),
                ModBlocks.DIAMOND_BATTERY.getId(),
                ModBlocks.NETHERITE_BATTERY.getId()
        ).addStoryBoard("battery", DeviceScenes::battery);
    }

    @Override
    public String getModId() {
        return PowerGridBatteries.MOD_ID;
    }
}
