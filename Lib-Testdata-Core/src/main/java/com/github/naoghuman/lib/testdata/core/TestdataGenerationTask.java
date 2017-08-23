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
package com.github.naoghuman.lib.testdata.core;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import org.apache.commons.lang3.time.StopWatch;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public abstract class TestdataGenerationTask extends Task<Void> {
    
    private final StopWatch stopWatch = new StopWatch();
    
    private final IntegerProperty maxEntitiesProperty = new SimpleIntegerProperty(0);
    private final IntegerProperty timePeriodProperty  = new SimpleIntegerProperty(0);
    
    public void configure(
            final ReadOnlyObjectProperty<Integer>  maxEntitiesProperty,
            final ReadOnlyObjectProperty<Integer>  timePeriodProperty
    ) {
        this.maxEntitiesProperty.bind(maxEntitiesProperty);
        this.timePeriodProperty .bind(timePeriodProperty);
    }
    
    protected StopWatch getStopWatch() {
        return stopWatch;
    }
    
    protected ReadOnlyIntegerProperty maxEntitiesProperty() {
        return maxEntitiesProperty;
    }
    
    protected abstract void setTitle();
    
    protected ReadOnlyIntegerProperty timePeriodProperty() {
        return timePeriodProperty;
    }
    
}
