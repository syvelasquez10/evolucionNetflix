// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

class JobScheduler$JobStartExecutorSupplier
{
    private static ScheduledExecutorService sJobStarterExecutor;
    
    static ScheduledExecutorService get() {
        if (JobScheduler$JobStartExecutorSupplier.sJobStarterExecutor == null) {
            JobScheduler$JobStartExecutorSupplier.sJobStarterExecutor = Executors.newSingleThreadScheduledExecutor();
        }
        return JobScheduler$JobStartExecutorSupplier.sJobStarterExecutor;
    }
}
