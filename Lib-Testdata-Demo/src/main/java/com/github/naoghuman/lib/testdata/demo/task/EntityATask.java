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
package com.github.naoghuman.lib.testdata.demo.task;

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.testdata.core.TestdataGenerationTask;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class EntityATask extends TestdataGenerationTask {
    
    public EntityATask() {
        
    }

    @Override
    protected Void call() throws Exception {
        LoggerFacade.getDefault().deactivate(Boolean.TRUE);
        super.getStopWatch().start();
        
        final int maxEntities = super.maxEntitiesProperty().getValue();
        super.updateProgress(0, maxEntities);
        
        for (int index = 0; index < maxEntities; index++) {
            Thread.sleep(50); // TODO replace with testdata logic
            super.updateProgress(index, maxEntities);
        }
        
        LoggerFacade.getDefault().deactivate(Boolean.FALSE);
        super.getStopWatch().split();
        LoggerFacade.getDefault().debug(this.getClass(), String.format("  + %s for %s entities.", // NOI18N
                super.getStopWatch().toSplitString(), maxEntities));
        super.getStopWatch().stop();
        
        return null;       
    }

    @Override
    protected void setTitle() {
        super.updateTitle(EntityATask.class.getSimpleName());
    }
    
}