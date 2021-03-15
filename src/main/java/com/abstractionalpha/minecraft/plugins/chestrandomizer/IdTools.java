package com.abstractionalpha.minecraft.plugins.chestrandomizer;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

/**
 * Identification class. Mostly used to distinguish between inputs or properties.
 *
 * @author abstractionAlpha
 * @version 1.3
 */
public class IdTools {

    /**
     * Method that checks if an inputted argument is numeric or not.
     *
     * @param arg
     * @return boolean representing whether or not argument is numeric
     */
    public static boolean isNumeric(String arg) {
        String alpha = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i=0; i < alpha.length(); i++) {
            if (arg.indexOf(alpha.charAt(i)) >= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that returns a random element from an array list.
     *
     * @param list
     * @return random element
     */
    public static Object getRandomElement(ArrayList list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    /**
     * Method that returns to FillHandler or EmptyHandler which type of chests are being modified.
     *
     * @param argument
     * @return modifier for handler that called this method
     */
    public static String FillEmptyType(String argument) {
        if (argument.equalsIgnoreCase("all")) {
            return "allArg";
        } else if (IdTools.isNumeric(argument)) {
            return "radiusArg";
        } else {
            return "regionArg";
        }
    }

    /**
     * Method that returns whether or not an inputted item can stack
     *
     * @param item
     * @return boolean representing item's ability to stack
     */
    public static boolean Stackable(String item) {
        ItemStack itemStack = new ItemStack(Material.valueOf(item));
        int maxStackSize = itemStack.getMaxStackSize();
        if (maxStackSize == 1) {
            return false;
        } else {
            return true;
        }
    }
}