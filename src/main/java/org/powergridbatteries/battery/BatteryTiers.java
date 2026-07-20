package org.powergridbatteries.battery;

import org.patryk3211.powergrid.electricity.battery.BatterySpec;
import org.patryk3211.powergrid.electricity.battery.SimpleBatterySpec;

public class BatteryTiers {
    // Copper Tier: 500 Wh capacity, 500 W max power rating
    public static final BatterySpec COPPER = new SimpleBatterySpec(
            500f * 3600f,
            500f * 3600f * 0.5f,
            e -> 1.5f * e + 12.0f,
            e -> Math.min(10000, (float) Math.exp(-21.18323 * e + 10.58157) + 0.05f)
    );

    // Iron Tier: 2,000 Wh capacity, 2,000 W max power rating
    public static final BatterySpec IRON = new SimpleBatterySpec(
            2000f * 3600f,
            2000f * 3600f * 0.5f,
            e -> 2.0f * e + 24.0f,
            e -> Math.min(10000, (float) Math.exp(-21.18323 * e + 10.58157) + 0.02f)
    );

    // Gold Tier: 10,000 Wh capacity, 10,000 W max power rating
    public static final BatterySpec GOLD = new SimpleBatterySpec(
            10000f * 3600f,
            10000f * 3600f * 0.5f,
            e -> 3.0f * e + 48.0f,
            e -> Math.min(10000, (float) Math.exp(-21.18323 * e + 10.58157) + 0.01f)
    );

    // Diamond Tier: 50,000 Wh capacity, 50,000 W max power rating
    public static final BatterySpec DIAMOND = new SimpleBatterySpec(
            50000f * 3600f,
            50000f * 3600f * 0.5f,
            e -> 5.0f * e + 120.0f,
            e -> Math.min(10000, (float) Math.exp(-21.18323 * e + 10.58157) + 0.005f)
    );

    // Netherite Tier: 250,000 Wh capacity, 250,000 W max power rating
    public static final BatterySpec NETHERITE = new SimpleBatterySpec(
            250000f * 3600f,
            250000f * 3600f * 0.5f,
            e -> 10.0f * e + 240.0f,
            e -> Math.min(10000, (float) Math.exp(-21.18323 * e + 10.58157) + 0.001f)
    );
}
