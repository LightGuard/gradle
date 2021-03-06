/*
 * Copyright 2010 the original author or authors.
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
package org.gradle.process;

import java.util.List;

/**
 * @author Hans Dockter
 */
public interface ExecSpec extends BaseExecSpec {
    /**
     * Sets the command plus the args to be executed.
     * @param args the command plus the args to be executed
     *
     * @return this
     */
    ExecSpec commandLine(Object... args);

    /**
     * Sets the command plus the args to be executed.
     * @param args the command plus the args to be executed
     *
     * @return this
     */
    ExecSpec commandLine(Iterable<?> args);

    /**
     * Adds args for the command to be executed.
     *
     * @param args args for the command
     *
     * @return this
     */
    ExecSpec args(Object... args);

    /**
     * Adds args for the command to be executed.
     *
     * @param args args for the command
     *
     * @return this
     */
    ExecSpec args(Iterable<?> args);

    /**
     * Sets the args for the command to be executed.
     *
     * @param args args for the command
     *
     * @return this
     */
    ExecSpec setArgs(Iterable<?> args);

    /**
     * Returns the args for the command to be executed
     */
    List<String> getArgs();
}
