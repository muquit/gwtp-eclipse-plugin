/**
 * Copyright 2014 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.gwtp.plugin.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Sample integration test. In Eclipse, right-click > Run As > JUnit-Plugin. <br/>
 * In Maven CLI, run "mvn integration-test".
 */
public class ActivatorTest {

    @Test
    public void veryStupidTest() {
        assertEquals("gwtp.plugin.core", Activator.PLUGIN_ID);
        assertTrue("Plugin should be started", Activator.getDefault().started);
    }
}