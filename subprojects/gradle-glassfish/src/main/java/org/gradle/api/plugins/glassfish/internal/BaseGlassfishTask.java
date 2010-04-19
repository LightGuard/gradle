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

package org.gradle.api.plugins.glassfish.internal;

import org.glassfish.api.embedded.ContainerBuilder;
import org.glassfish.api.embedded.EmbeddedDeployer;
import org.glassfish.api.embedded.EmbeddedFileSystem;
import org.glassfish.api.embedded.Server;
import org.gradle.api.DefaultTask;
import org.gradle.api.plugins.glassfish.GlassfishPluginConvention;

import java.io.File;
import java.io.IOException;

public class BaseGlassfishTask extends DefaultTask {
    protected Server server;
    protected EmbeddedDeployer deployer;
    protected GlassfishPluginConvention glassFishConvention;

    protected void configureServer() throws IOException {
        Server.Builder builder = new Server.Builder(this.glassFishConvention.getName());

        // Server location settings
        EmbeddedFileSystem.Builder efsBuilder = new EmbeddedFileSystem.Builder();
        efsBuilder.autoDelete(true);

        File domainXml = this.glassFishConvention.getConfigurationFile();
        File installRoot = this.glassFishConvention.getInstallRoot();

        if (domainXml != null && domainXml.isFile())
            efsBuilder.configurationFile(domainXml); // domain.xml to use

        if (installRoot != null && installRoot.isDirectory())
            efsBuilder.installRoot(installRoot, true);  // Existing install location

        builder.embeddedFileSystem(efsBuilder.build());

        this.server = builder.build();
        this.server.addContainer(ContainerBuilder.Type.valueOf(this.glassFishConvention.getContainerType()));
        this.server.createPort(this.glassFishConvention.getPort());

        this.deployer = this.server.getDeployer();
    }

    public GlassfishPluginConvention getGlassFishConvention() {
        return this.glassFishConvention;
    }

    public void setGlassFishConvention(GlassfishPluginConvention convention) {
        this.glassFishConvention = convention;
    }
}
