/**
 * Copyright (C) 2017 Naoghuman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.naoghuman.lib.testdata.internal.configurationcomponent.items;

import java.util.List;
import javafx.collections.FXCollections;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class QuantityItems {
    
    private static final List<Integer> QUANTITY_ITEMS = FXCollections.observableArrayList();
    
    static {
        QUANTITY_ITEMS.add(100);
        QUANTITY_ITEMS.add(250);
        QUANTITY_ITEMS.add(500);
        QUANTITY_ITEMS.add(1000);
        QUANTITY_ITEMS.add(2500);
        QUANTITY_ITEMS.add(5000);
        QUANTITY_ITEMS.add(10000);
        QUANTITY_ITEMS.add(12500);
        QUANTITY_ITEMS.add(15000);
        QUANTITY_ITEMS.add(20000);
        QUANTITY_ITEMS.add(25000);
        QUANTITY_ITEMS.add(30000);
        QUANTITY_ITEMS.add(35000);
        QUANTITY_ITEMS.add(40000);
        QUANTITY_ITEMS.add(50000);
        QUANTITY_ITEMS.add(75000);
        QUANTITY_ITEMS.add(100000);
    }
    
    public static List<Integer> getQuantityItems() {
        return QUANTITY_ITEMS;
    }
    
    private QuantityItems() {
        
    }
    
}
