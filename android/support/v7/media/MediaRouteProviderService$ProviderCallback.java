// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.IBinder;
import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.Message;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.os.Messenger;
import java.util.ArrayList;
import android.app.Service;

final class MediaRouteProviderService$ProviderCallback extends MediaRouteProvider$Callback
{
    final /* synthetic */ MediaRouteProviderService this$0;
    
    private MediaRouteProviderService$ProviderCallback(final MediaRouteProviderService this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onDescriptorChanged(final MediaRouteProvider mediaRouteProvider, final MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
        this.this$0.sendDescriptorChanged(mediaRouteProviderDescriptor);
    }
}
