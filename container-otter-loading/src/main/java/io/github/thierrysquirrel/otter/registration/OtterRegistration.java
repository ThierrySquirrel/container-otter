/**
 * Copyright 2026/6/2 ThierrySquirrel
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package io.github.thierrysquirrel.otter.registration;

import io.github.thierrysquirrel.otter.annotation.Otter;
import io.github.thierrysquirrel.otter.annotation.Repair;
import io.github.thierrysquirrel.otter.init.core.factory.execution.RepairInitFactoryExecution;
import io.github.thierrysquirrel.otter.proxy.utils.OtterProxyUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * ClassName: OtterRegistration
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class OtterRegistration {

    private OtterRegistration() {
    }

    public static void otterRegistrationScannerAll(List<Class<?>> scannerClassList, Map<Class<?>, Object> registrationMap) {
        for (Class<?> thisClass : scannerClassList) {
            Otter annotation = thisClass.getAnnotation(Otter.class);
            if (!Objects.isNull(annotation)) {
                Object otterProxy = OtterProxyUtils.createOtterProxy(thisClass);

                registrationMap.put(thisClass, otterProxy);

                for (Method method : thisClass.getMethods()) {
                    Repair repair = method.getAnnotation(Repair.class);
                    if (!Objects.isNull(repair)) {
                        RepairInitFactoryExecution.execution(otterProxy, repair, method);
                    }
                }

            }
        }
    }
}
