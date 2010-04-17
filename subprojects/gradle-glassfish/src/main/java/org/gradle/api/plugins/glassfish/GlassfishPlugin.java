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

package org.gradle.api.plugins.glassfish;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.Convention;
import org.gradle.api.plugins.WarPlugin;
import org.gradle.api.plugins.glassfish.internal.BaseGlassfishTask;
import org.gradle.api.plugins.osgi.OsgiPlugin;

/**
 * Embedded Glassfish v3 plugin.  Allows for ejb, war, webservice, and jruby
 * deployment inside an embedded glassfish (or existing glassfish) installation.
 *
 * @author Jason Porter <a href="mailto:lightguard.jp@gmail.com">lightguard.jp@gmail.com</a>
 */
public class GlassfishPlugin implements Plugin<Project> {
    public static final String GLASSFISH_RUN = "glassfishRun";
    public static final String GLASSFISH_RUN_WAR = "glassfishRunWar";
    public static final String GLASSFISH_OSGI = "glassfishOsgi";
    public static final String GLASSFISH_STOP = "glassfishStop";

    /**
     * Apply this plugin to the given project object.
     *
     * @param project The project object
     */
    public void apply(Project project) {
        project.getPlugins().apply(WarPlugin.class); // Assuming, at least for now we're doing war
        project.getPlugins().apply(OsgiPlugin.class); // We should also be able to handle OSGi as well

        GlassfishPluginConvention gfConvention = new GlassfishPluginConvention();
        Convention convention = project.getConvention();
        convention.getPlugins().put("glassfish", gfConvention);

        this.configureBaseTask(project, gfConvention);
        this.configureGlassfishRun(project);
        this.configureGlassfishRunWar(project);
        this.configureGlassfishOsgi(project);
        this.configureGlassfishStop(project);
    }

    private void configureBaseTask(final Project project, final GlassfishPluginConvention convention) {
        project.getTasks().withType(BaseGlassfishTask.class).whenTaskAdded(new Action<BaseGlassfishTask>() {
            public void execute(BaseGlassfishTask task) {
                task.setConvention(convention);
            }
        });
    }

    private void configureGlassfishRun(final Project project) {
        project.getTasks().withType(GlassfishRunTask.class).whenTaskAdded(new Action<GlassfishRunTask>() {
            public void execute(GlassfishRunTask task) {
                task.dependsOn(WarPlugin.WAR_TASK_NAME);
            }
        });
    }

    private void configureGlassfishRunWar(final Project project) {
        project.getTasks().withType(GlassfishRunTask.class).whenTaskAdded(new Action<GlassfishRunTask>() {
            public void execute(GlassfishRunTask task) {
                task.dependsOn(WarPlugin.WAR_TASK_NAME);
            }
        });
    }

    private void configureGlassfishOsgi(final Project project) {

    }

    private void configureGlassfishStop(final Project project) {

    }
}
