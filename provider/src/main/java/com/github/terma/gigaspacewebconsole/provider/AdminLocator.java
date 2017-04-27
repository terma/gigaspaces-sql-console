/*
Copyright 2015-2017 Artem Stasiuk

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

package com.github.terma.gigaspacewebconsole.provider;

import com.github.terma.gigaspacewebconsole.core.GeneralRequest;
import org.openspaces.admin.Admin;

import java.util.concurrent.TimeUnit;

public class AdminLocator {

    private static final AdminCache CACHE = new AdminCache();

    private static final Thread CLEANER = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                CACHE.clearExpired();

                try {
                    Thread.sleep(TimeUnit.MINUTES.toMillis(5));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

    });

    static {
        CLEANER.setName("ADMIN-CACHE-CLEANER");
        CLEANER.setDaemon(true);
        CLEANER.start();
    }

    public static Admin get(final GeneralRequest request) {
        return CACHE.createOrGet(request).admin;
    }

}
