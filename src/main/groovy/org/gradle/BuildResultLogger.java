/*
 * Copyright 2007-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle;

import org.slf4j.Logger;
import org.gradle.util.Clock;

/**
 * A {@link BuildListener} which logs the final result of the build.
 */
public class BuildResultLogger implements BuildListener {
    private final Logger logger;
    private final Clock buildTimeClock;

    public BuildResultLogger(Logger logger) {
        this.logger = logger;
        buildTimeClock = new Clock();
    }

    public void buildFinished(BuildResult result) {
        if (result.getFailure() == null) {
            logger.info(String.format("%nBUILD SUCCESSFUL%n"));
        }
        else {
            logger.error(String.format("%nBUILD FAILED%n"));
        }
        logger.info(String.format("Total time: %s", buildTimeClock.getTime()));
    }
}