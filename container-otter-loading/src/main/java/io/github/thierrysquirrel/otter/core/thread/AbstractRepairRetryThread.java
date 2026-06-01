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
package io.github.thierrysquirrel.otter.core.thread;

import io.github.thierrysquirrel.otter.core.domain.RepairDomain;

import java.util.Arrays;
import java.util.Iterator;

/**
 * ClassName: AbstractRepairRetryThread
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public abstract class AbstractRepairRetryThread implements Runnable {

    private Iterator<Integer> repairInterval;
    private RepairDomain repairDomain;
    private Object[] methodParam;

    protected AbstractRepairRetryThread(Iterator<Integer> repairInterval, RepairDomain repairDomain, Object[] methodParam) {
        this.repairInterval = repairInterval;
        this.repairDomain = repairDomain;
        this.methodParam = methodParam;
    }

    /**
     * repairRetry
     *
     * @param repairInterval repairInterval
     * @param repairDomain   repairDomain
     * @param methodParam    methodParam
     */
    protected abstract void repairRetry(Iterator<Integer> repairInterval, RepairDomain repairDomain, Object[] methodParam);

    @Override
    public void run() {
        repairRetry(this.repairInterval,
                this.repairDomain,
                this.methodParam);
    }

    public Iterator<Integer> getRepairInterval() {
        return repairInterval;
    }

    public RepairDomain getRepairDomain() {
        return repairDomain;
    }

    public Object[] getMethodParam() {
        return methodParam;
    }

    @Override
    public String toString() {
        return "AbstractRepairRetryThread{" +
                "repairInterval=" + repairInterval +
                ", repairDomain=" + repairDomain +
                ", methodParam=" + Arrays.toString(methodParam) +
                '}';
    }
}
