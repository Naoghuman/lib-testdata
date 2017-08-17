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
package com.github.naoghuman.lib.testdata.internal.framework;

import java.util.Optional;
import javafx.scene.Parent;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public class FrameworkProvider {
    
    private static final Optional<FrameworkProvider> INSTANCE = Optional.of(new FrameworkProvider());

    public static final FrameworkProvider getDefault() {
        return INSTANCE.get();
    }
    
    private FrameworkPresenter presenter = null;
    private FrameworkView view = null;
    
    private FrameworkProvider() {
        this.initialize();
    }
    
    private void initialize() {
        view = new FrameworkView();
        presenter = view.getRealPresenter();
    }
    
    public void shutdown() throws InterruptedException {
        presenter.onActionShutdown();
    }
    
    public Parent getView() {
        return view.getView();
    }
}
