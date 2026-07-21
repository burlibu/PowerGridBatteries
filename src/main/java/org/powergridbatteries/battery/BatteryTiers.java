package org.powergridbatteries.battery;

import org.patryk3211.powergrid.electricity.battery.BatterySpec;
import org.patryk3211.powergrid.electricity.battery.SimpleBatterySpec;

public class BatteryTiers {
    // Tier 1: Small Battery - 12.7 V, 508 A max (R=0.025 ohm), 6.4 kW max power, 3.6 MJ (3,600,000 J) capacity
    public static final BatterySpec SMALL_BATTERY = new SimpleBatterySpec(
            3600000f,
            1800000f,
            e -> 1.2f * e + 11.5f,
            e -> Math.min(10000, (float) Math.exp(-21.18323 * e + 10.58157) + 0.025f)
    );

    // Tier 2: Medium Battery - 48 V, 240 A max (R=0.2 ohm), 11.5 kW max power, 18 MJ (18,000,000 J) capacity
    public static final BatterySpec MEDIUM_BATTERY = new SimpleBatterySpec(
            18000000f,
            9000000f,
            e -> 6.0f * e + 42.0f,
            e -> Math.min(10000, (float) Math.exp(-21.18323 * e + 10.58157) + 0.2f)
    );

    // Tier 3: High-Voltage Battery - 400 V, 400 A max (R=1.0 ohm), 160 kW max power, 90 MJ (90,000,000 J) capacity
    public static final BatterySpec HIGH_VOLTAGE_BATTERY = new SimpleBatterySpec(
            90000000f,
            45000000f,
            e -> 50.0f * e + 350.0f,
            e -> Math.min(10000, (float) Math.exp(-21.18323 * e + 10.58157) + 1.0f)
    );

    // Tier 4: Power Grid Substation - 1000 V, 1000 A max (R=1.0 ohm), 1 MW (1,000,000 W) max power, 450 MJ (450,000,000 J) capacity
    public static final BatterySpec SUBSTATION_BATTERY = new SimpleBatterySpec(
            450000000f,
            225000000f,
            e -> 100.0f * e + 900.0f,
            e -> Math.min(10000, (float) Math.exp(-21.18323 * e + 10.58157) + 1.0f)
    );
}
