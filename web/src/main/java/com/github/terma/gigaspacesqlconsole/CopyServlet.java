/*
Copyright 2015 Artem Stasiuk

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.github.terma.gigaspacesqlconsole;

import com.github.terma.gigaspacesqlconsole.core.CopyRequest;

public class CopyServlet extends JsonServlet<CopyRequest> {

    @Override
    protected Object doJson(CopyRequest request) throws Exception {
        return CachedProviderResolver.getProvider(request.gs).copy(request);
    }

    @Override
    protected Class<CopyRequest> getRequestClass() {
        return CopyRequest.class;
    }

    @Override
    protected Validator<CopyRequest> getValidator() {
        return new AppVersionValidator<>();
    }

}
