/*
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
package com.github.naoghuman.lib.testdata.internal.testdataframework;

import java.util.Optional;
import javafx.scene.Parent;

/**
 *
 * @author Naoghuman
 */
public class TestdataFrameworkProvider {
    
    private static final Optional<TestdataFrameworkProvider> INSTANCE = Optional.of(new TestdataFrameworkProvider());

    public static final TestdataFrameworkProvider getDefault() {
        return INSTANCE.get();
    }
    
    private TestdataFrameworkPresenter presenter = null;
    private TestdataFrameworkView view = null;
    
    private TestdataFrameworkProvider() {
        this.initialize();
    }
    
    private void initialize() {
        view = new TestdataFrameworkView();
        presenter = view.getRealPresenter();
    }
    
    public void shutdown() throws InterruptedException {
        presenter.onActionShutdown();
    }
    
    public Parent getView() {
        return view.getView();
    }
}
