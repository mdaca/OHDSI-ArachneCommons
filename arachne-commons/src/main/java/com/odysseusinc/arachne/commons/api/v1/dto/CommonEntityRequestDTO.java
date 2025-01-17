/*
 *
 * Copyright 2018 Odysseus Data Services, inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Company: Odysseus Data Services, Inc.
 * Product Owner/Architecture: Gregory Klebanov
 * Authors: Pavel Grafkin, Alexandr Ryabokon, Vitaly Koulakov, Anton Gackovka, Maria Pozhidaeva, Mikhail Mironov
 * Created: July 19, 2017
 *
 */

package com.odysseusinc.arachne.commons.api.v1.dto;

import java.io.Serializable;
import jakarta.validation.constraints.NotNull;

public class CommonEntityRequestDTO implements Serializable {

    @NotNull
    private String id;
    @NotNull
    private String entityGuid;

    private CommonAnalysisType entityType;

    public CommonEntityRequestDTO() {

    }

    public CommonEntityRequestDTO(String entityGuid, CommonAnalysisType entityType) {

        this.entityGuid = entityGuid;
        this.entityType = entityType;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getEntityGuid() {

        return entityGuid;
    }

    public CommonAnalysisType getEntityType() {

        return entityType;
    }

}
