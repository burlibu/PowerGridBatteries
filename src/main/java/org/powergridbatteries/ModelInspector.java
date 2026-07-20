package org.powergridbatteries;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

public class ModelInspector {
    public static void check() {
        ResourceLocation rl = ResourceLocation.fromNamespaceAndPath("test", "test");
        ModelResourceLocation mrl = new ModelResourceLocation(rl, "");
    }
}
