package org.powergridbatteries.battery;

import com.simibubi.create.api.connectivity.ConnectivityHandler;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.ConnectedTextureBehaviour;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class TieredBatteryCTBehaviour extends ConnectedTextureBehaviour.Base {
    private static final Map<String, CTSpriteShiftEntry> TOP_BOTTOM_ENTRIES = new HashMap<>();
    private static final Map<String, CTSpriteShiftEntry> SIDES_ENTRIES = new HashMap<>();

    public static void init() {
        // Must be called on client setup to ensure sprite shifts are registered
        registerTier("small");
        registerTier("medium");
        registerTier("high_voltage");
        registerTier("substation");
    }

    private static void registerTier(String tier) {
        TOP_BOTTOM_ENTRIES.put(tier, CTSpriteShifter.getCT(
                AllCTTypes.RECTANGLE,
                ResourceLocation.fromNamespaceAndPath("powergrid_batteries", "block/battery/" + tier + "_battery_top"),
                ResourceLocation.fromNamespaceAndPath("powergrid_batteries", "block/battery/" + tier + "_battery_top_connected")
        ));
        SIDES_ENTRIES.put(tier, CTSpriteShifter.getCT(
                AllCTTypes.RECTANGLE,
                ResourceLocation.fromNamespaceAndPath("powergrid_batteries", "block/battery/" + tier + "_battery_side"),
                ResourceLocation.fromNamespaceAndPath("powergrid_batteries", "block/battery/" + tier + "_battery_side_connected")
        ));
    }

    private final String tier;

    public TieredBatteryCTBehaviour(String tier) {
        this.tier = tier;
    }

    @Override
    public @Nullable CTSpriteShiftEntry getShift(BlockState state, Direction direction, @Nullable TextureAtlasSprite sprite) {
        if (direction.getAxis() == Direction.Axis.Y)
            return TOP_BOTTOM_ENTRIES.get(tier);
        return SIDES_ENTRIES.get(tier);
    }

    @Override
    public boolean connectsTo(BlockState state, BlockState other, BlockAndTintGetter reader, BlockPos pos, BlockPos otherPos, Direction face) {
        return state == other && ConnectivityHandler.isConnected(reader, pos, otherPos);
    }
}
