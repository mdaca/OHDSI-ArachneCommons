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
 * Created: January 31, 2017
 *
 */

package com.odysseusinc.arachne.execution_engine_common.api.v1.dto;

import jakarta.validation.constraints.NotNull;

public class AnalysisRequestDTO extends AnalysisSyncRequestDTO {

    @NotNull
    private String callbackPassword;

    @NotNull
    private String updateStatusCallback;

    @NotNull
    private String resultCallback;

    public String getCallbackPassword() {

        return callbackPassword;
    }

    public void setCallbackPassword(String callbackPassword) {

        this.callbackPassword = callbackPassword;
    }

    public String getUpdateStatusCallback() {

        return updateStatusCallback;
    }

    public void setUpdateStatusCallback(String updateStatusCallback) {

        this.updateStatusCallback = updateStatusCallback;
    }

    public String getResultCallback() {

        return resultCallback;
    }

    public void setResultCallback(String resultCallback) {

        this.resultCallback = resultCallback;
    }

}
