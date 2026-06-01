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

package io.github.thierrysquirrel.otter.proxy.domain;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * ClassName: OtterProxyDomain
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class OtterProxyDomain {
    private Object proxy;
    private Method method;
    private Object[] args;
    private Callable<?> callable;

    public OtterProxyDomain(Object proxy, Method method, Object[] args, Callable<?> callable) {
        this.proxy = proxy;
        this.method = method;
        this.args = args;
        this.callable = callable;
    }

    public Object getProxy() {
        return proxy;
    }

    public void setProxy(Object proxy) {
        this.proxy = proxy;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Callable<?> getCallable() {
        return callable;
    }

    public void setCallable(Callable<?> callable) {
        this.callable = callable;
    }

    @Override
    public String toString() {
        return "OtterProxyDomain{" +
                "proxy=" + proxy +
                ", method=" + method +
                ", args=" + Arrays.toString(args) +
                ", callable=" + callable +
                '}';
    }
}
