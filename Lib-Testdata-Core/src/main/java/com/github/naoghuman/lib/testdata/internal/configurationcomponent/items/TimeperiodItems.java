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
public final class TimeperiodItems {
    
    private static final List<Integer> TIMEPERIOD_ITEMS = FXCollections.observableArrayList();
    
    static {
        TIMEPERIOD_ITEMS.add(1);
        TIMEPERIOD_ITEMS.add(2);
        TIMEPERIOD_ITEMS.add(3);
        TIMEPERIOD_ITEMS.add(4);
        TIMEPERIOD_ITEMS.add(5);
        TIMEPERIOD_ITEMS.add(10);
        TIMEPERIOD_ITEMS.add(15);
        TIMEPERIOD_ITEMS.add(20);
        TIMEPERIOD_ITEMS.add(25);
        TIMEPERIOD_ITEMS.add(50);
        TIMEPERIOD_ITEMS.add(75);
    }
    
    public static List<Integer> getTimeperiodItems() {
        return TIMEPERIOD_ITEMS;
    }
    
    private TimeperiodItems() {
        
    }
    
}
