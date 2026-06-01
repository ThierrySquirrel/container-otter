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
package io.github.thierrysquirrel.otter.core.domain;


/**
 * ClassName: SnowFlakeDomain
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class SnowFlakeDomain {
    private long thisTime;
    private int dataCenterId;
    private int machineId;
    private int sequence;

    public long getThisTime() {
        return thisTime;
    }

    public void setThisTime(long thisTime) {
        this.thisTime = thisTime;
    }

    public int getDataCenterId() {
        return dataCenterId;
    }

    public void setDataCenterId(int dataCenterId) {
        this.dataCenterId = dataCenterId;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "SnowFlakeDomain{" +
                "thisTime=" + thisTime +
                ", dataCenterId=" + dataCenterId +
                ", machineId=" + machineId +
                ", sequence=" + sequence +
                '}';
    }
}
