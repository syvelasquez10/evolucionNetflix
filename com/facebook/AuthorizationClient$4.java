// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.model.GraphObjectList;
import java.util.Collection;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphMultiResult;
import java.util.ArrayList;

class AuthorizationClient$4 implements Request$Callback
{
    final /* synthetic */ AuthorizationClient this$0;
    final /* synthetic */ ArrayList val$tokenPermissions;
    
    AuthorizationClient$4(final AuthorizationClient this$0, final ArrayList val$tokenPermissions) {
        this.this$0 = this$0;
        this.val$tokenPermissions = val$tokenPermissions;
    }
    
    @Override
    public void onCompleted(final Response response) {
        try {
            final GraphMultiResult graphMultiResult = response.getGraphObjectAs(GraphMultiResult.class);
            if (graphMultiResult != null) {
                final GraphObjectList<GraphObject> data = graphMultiResult.getData();
                if (data != null && data.size() == 1) {
                    this.val$tokenPermissions.addAll(((GraphObject)data.get(0)).asMap().keySet());
                }
            }
        }
        catch (Exception ex) {}
    }
}
