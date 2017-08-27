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
package com.github.naoghuman.lib.testdata.internal.configuration;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public interface PreferencesConfiguration {
    
    public static final String  PREF__TESTDATA__DATABASE = "PREF__TESTDATA__DATABASE"; // NOI18N
    public static final String  PREF__TESTDATA__DATABASE__DEFAULT_VALUE  = "application"; // NOI18N
    
    public static final String  PREF__TESTDATA__QUANTITY_ENTITIES = "PREF__TESTDATA__QUANTITY_ENTITIES"; // NOI18N
    public static final Integer PREF__TESTDATA__QUANTITY_ENTITIES__DEFAULT_VALUE = 100;
    
    public static final String  PREF__TESTDATA__QUANTITY_TIMEPERIOD = "PREF__TESTDATA__QUANTITY_TIMEPERIOD"; // NOI18N
    public static final Integer PREF__TESTDATA__QUANTITY_TIMEPERIOD__DEFAULT_VALUE = 1;
    
}
