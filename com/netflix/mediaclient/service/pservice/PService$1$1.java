// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import android.os.IBinder;
import com.netflix.mediaclient.util.AndroidUtils;
import java.util.Iterator;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.app.Service;
import java.util.ArrayList;

class PService$1$1 extends ArrayList<PServiceAgent>
{
    final /* synthetic */ PService$1 this$1;
    
    PService$1$1(final PService$1 this$1) {
        this.this$1 = this$1;
        ((ArrayList<PServiceWidgetAgent>)this).add(this.this$1.this$0.mWidgetAgent);
    }
}
