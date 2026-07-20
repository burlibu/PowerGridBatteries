package org.powergridbatteries;

import com.simibubi.create.foundation.item.TooltipModifier;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

public class TooltipInspector {
    public static void inspect() {
        try {
            System.out.println("Fields of TooltipModifier:");
            for (Field f : TooltipModifier.class.getDeclaredFields()) {
                System.out.println(f.getName() + " - " + f.getType().getName());
            }
            
            System.out.println("\nMethods of TooltipModifier:");
            for (Method m : TooltipModifier.class.getDeclaredMethods()) {
                System.out.println(m.getName() + " - " + m.getReturnType().getName());
            }
            
            Field registryField = TooltipModifier.class.getDeclaredField("REGISTRY");
            System.out.println("\nREGISTRY Type: " + registryField.getType().getName());
            
            System.out.println("\nMethods of REGISTRY type:");
            for (Method m : registryField.getType().getDeclaredMethods()) {
                System.out.println(m.getName() + " - " + m.getReturnType().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
