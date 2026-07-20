package org.powergridbatteries;

import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import org.patryk3211.powergrid.ponder.scenes.DeviceScenes;

public class PowerGridBatteriesPonderPlugin implements PonderPlugin {
    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(
                ModBlocks.SMALL_BATTERY.getId(),
                ModBlocks.MEDIUM_BATTERY.getId(),
                ModBlocks.HIGH_VOLTAGE_BATTERY.getId(),
                ModBlocks.SUBSTATION_BATTERY.getId()
        ).addStoryBoard("battery", (scene, util) -> DeviceScenes.battery(scene, util));
    }

    @Override
    public String getModId() {
        return PowerGridBatteries.MOD_ID;
    }
}
