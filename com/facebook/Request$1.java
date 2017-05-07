// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.model.GraphUser;

final class Request$1 implements Request$Callback
{
    final /* synthetic */ Request$GraphUserCallback val$callback;
    
    Request$1(final Request$GraphUserCallback val$callback) {
        this.val$callback = val$callback;
    }
    
    @Override
    public void onCompleted(final Response response) {
        if (this.val$callback != null) {
            this.val$callback.onCompleted(response.getGraphObjectAs(GraphUser.class), response);
        }
    }
}
