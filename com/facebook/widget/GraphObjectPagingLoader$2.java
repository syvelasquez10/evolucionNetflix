// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.os.Handler;
import com.facebook.RequestBatch;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookException;
import com.facebook.internal.CacheableRequestBatch;
import com.facebook.model.GraphObjectList;
import java.util.Collection;
import com.facebook.Response$PagingDirection;
import android.content.Context;
import com.facebook.Request;
import android.support.v4.content.Loader;
import com.facebook.model.GraphObject;
import com.facebook.Response;
import com.facebook.Request$Callback;

class GraphObjectPagingLoader$2 implements Request$Callback
{
    final /* synthetic */ GraphObjectPagingLoader this$0;
    
    GraphObjectPagingLoader$2(final GraphObjectPagingLoader this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onCompleted(final Response response) {
        this.this$0.requestCompleted(response);
    }
}
