package org.powergridbatteries;

import com.simibubi.create.foundation.block.connected.CTModel;
import com.simibubi.create.foundation.block.connected.ConnectedTextureBehaviour;
import net.minecraft.client.resources.model.BakedModel;

public class CTInspector {
    public static void check() {
        // Just checking if this compiles
        BakedModel original = null;
        ConnectedTextureBehaviour behaviour = null;
        BakedModel wrapped = new CTModel(original, behaviour);
    }
}
