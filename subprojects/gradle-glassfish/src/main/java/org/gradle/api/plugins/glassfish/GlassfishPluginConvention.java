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

import org.gradle.api.internal.plugins.AbstractConvention;

import java.io.File;

/**
 * Convention object for the plugin.
 *
 * @author Jason Porter <a href="mailto:lightguard.jp@gmail.com">lightguard.jp@gmail.com</a>
 */
public class GlassfishPluginConvention extends AbstractConvention {
    private int port = 8080;
    private String name = "gradle-glassfish";
    private File configurationFile;
    private File installRoot;
    private String containerType = "all";

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getConfigurationFile() {
        return configurationFile;
    }

    public void setConfigurationFile(File configurationFile) {
        this.configurationFile = configurationFile;
    }

    public File getInstallRoot() {
        return installRoot;
    }

    public void setInstallRoot(File installRoot) {
        this.installRoot = installRoot;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        if (!("all".equals(containerType) && "web".equals(containerType)
                && "ejb".equals(containerType) && "jpa".equals(containerType)
                && "webservices".equals(containerType) && "jruby".equals(containerType)))
            throw new IllegalArgumentException("Container type must be one of 'web', 'ejb', 'jpa', 'webservices', or 'jruby'");
        this.containerType = containerType;
    }
}
