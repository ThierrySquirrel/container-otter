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
package io.github.thierrysquirrel.otter.core.factory.execution;

import io.github.thierrysquirrel.otter.core.domain.RepairDomain;
import io.github.thierrysquirrel.otter.core.factory.RepairFactory;
import io.github.thierrysquirrel.otter.core.factory.constant.ThreadPoolFactoryConstant;
import io.github.thierrysquirrel.otter.core.thread.execution.RepairRetryThreadExecution;

import java.util.Iterator;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClassName: RepairFactoryExecution
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class RepairFactoryExecution {
    private static final Logger logger = Logger.getLogger(RepairFactoryExecution.class.getName());

    private RepairFactoryExecution() {
    }

    public static void repairRetry(Runnable repairThreadExecution, Iterator<Integer> repairInterval, RepairDomain repairDomain, Object[] methodParam) {

        try {
            RepairFactory.retry(repairDomain, methodParam);
        } catch (Exception e) {
            String logMsg = "Retry Error";
            logger.log(Level.WARNING, logMsg, e);

            if (repairInterval.hasNext()) {
                Integer repairTime = repairInterval.next();

                logMsg = "Next Retry Time:" + repairTime;
                logger.log(Level.WARNING, logMsg);
                RepairFactory.tryNextTimeRepair(repairThreadExecution, repairTime);
            }
        }

    }

    public static void repair(Iterator<Integer> repairInterval, RepairDomain repairDomain, Object[] methodParam) {
        if (repairInterval.hasNext()) {
            Integer repairIntervalTime = repairInterval.next();
            RepairRetryThreadExecution repairThreadExecution = new RepairRetryThreadExecution(repairInterval, repairDomain, methodParam);
            ScheduledThreadPoolExecutor repairThreadPool = ThreadPoolFactoryConstant.REPAIR_THREAD_POOL;
            ThreadPoolFactoryExecution.statsDelayThread(repairThreadPool, repairThreadExecution, repairIntervalTime);

            String logMsg = "Next Retry Time:" + repairIntervalTime;
            logger.log(Level.WARNING, logMsg);
        }
    }
}
