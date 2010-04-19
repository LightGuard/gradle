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

import org.glassfish.api.deployment.DeployCommandParameters;
import org.glassfish.api.embedded.LifecycleException;
import org.glassfish.api.embedded.ScatteredArchive;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.plugins.WarPluginConvention;
import org.gradle.api.plugins.glassfish.internal.BaseGlassfishTask;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GlassfishRunTask extends BaseGlassfishTask {
    @TaskAction
    public void start() throws IOException, LifecycleException {
        this.configureServer();

        DeployCommandParameters params = new DeployCommandParameters();

        Set<File> runtimeDeps = this.getProject().getConvention().getPlugin(JavaPluginConvention.class).getSourceSets().getByName(SourceSet.MAIN_SOURCE_SET_NAME).getRuntimeClasspath().getFiles();
        List<URL> runtimeDepsAsUrl = new ArrayList<URL>(runtimeDeps.size());

        for (File f : runtimeDeps) {
            runtimeDepsAsUrl.add(f.toURI().toURL());
        }

        ScatteredArchive.Builder archive = new ScatteredArchive.Builder(this.getProject().getName(), runtimeDepsAsUrl)
                .addMetadata("WEB-INF/web.xml", new File(this.getWarConvention().getWebAppDir().getAbsolutePath()))
                .addClassPath(this.getWarConvention().getWebAppDir());

        this.deployer.deploy(archive.buildWar(), params);

        try {
            this.server.start();
        } finally {
            if (this.server != null)
                this.server.stop();
        }
    }

    private WarPluginConvention getWarConvention() {
        return this.getProject().getConvention().getPlugin(WarPluginConvention.class);
    }


}
