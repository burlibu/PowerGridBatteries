package org.powergridbatteries.battery;

import org.patryk3211.powergrid.electricity.battery.BatterySpec;
import org.patryk3211.powergrid.electricity.battery.SimpleBatterySpec;

public class BatteryTiers {
    // Tier 1: Small Battery - 12.7 V, 8 A max (R=1.5875 ohm), 100 W max power, 10 kJ (10,000 J) capacity
    public static final BatterySpec SMALL_BATTERY = new SimpleBatterySpec(
            10000f,
            5000f,
            e -> 1.2f * e + 11.5f,
            e -> Math.min(10000, (float) Math.exp(-21.18323 * e + 10.58157) + 1.5875f)
    );

    // Tier 2: Medium Battery - 48 V, 50 A max (R=0.96 ohm), 2.4 kW (2,400 W) max power, 250 kJ (250,000 J) capacity
    public static final BatterySpec MEDIUM_BATTERY = new SimpleBatterySpec(
            250000f,
            125000f,
            e -> 6.0f * e + 42.0f,
            e -> Math.min(10000, (float) Math.exp(-21.18323 * e + 10.58157) + 0.96f)
    );

    // Tier 3: High-Voltage Battery - 400 V, 150 A max (R=2.667 ohm), 60 kW (60,000 W) max power, 5 MJ (5,000,000 J) capacity
    public static final BatterySpec HIGH_VOLTAGE_BATTERY = new SimpleBatterySpec(
            5000000f,
            2500000f,
            e -> 50.0f * e + 350.0f,
            e -> Math.min(10000, (float) Math.exp(-21.18323 * e + 10.58157) + 2.667f)
    );

    // Tier 4: Power Grid Substation - 1000 V, 500 A max (R=2.0 ohm), 500 kW (500,000 W) max power, 50 MJ (50,000,000 J) capacity
    public static final BatterySpec SUBSTATION_BATTERY = new SimpleBatterySpec(
            50000000f,
            25000000f,
            e -> 100.0f * e + 900.0f,
            e -> Math.min(10000, (float) Math.exp(-21.18323 * e + 10.58157) + 2.0f)
    );
}
