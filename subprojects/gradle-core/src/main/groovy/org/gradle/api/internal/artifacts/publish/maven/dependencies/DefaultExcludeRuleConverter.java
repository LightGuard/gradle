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
package org.gradle.api.internal.artifacts.publish.maven.dependencies;

import org.gradle.api.artifacts.ExcludeRule;


/**
 * @author Hans Dockter
 */
public class DefaultExcludeRuleConverter implements ExcludeRuleConverter {
    public DefaultMavenExclude convert(ExcludeRule excludeRule) {
        if (isConvertable(excludeRule)) {
            return new DefaultMavenExclude(excludeRule.getExcludeArgs().get(ExcludeRule.GROUP_KEY),
                    excludeRule.getExcludeArgs().get(ExcludeRule.MODULE_KEY));
        }
        return null;
    }

    private boolean isConvertable(ExcludeRule excludeRule) {
        return excludeRule.getExcludeArgs().containsKey(ExcludeRule.GROUP_KEY) && excludeRule.getExcludeArgs().containsKey(ExcludeRule.MODULE_KEY);
    }
}