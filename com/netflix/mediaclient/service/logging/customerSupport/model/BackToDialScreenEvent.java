// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerSupport.model;

import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$ReturnToDialScreenFrom;
import com.netflix.mediaclient.service.logging.apm.model.Orientation;
import com.netflix.mediaclient.service.logging.client.model.DiscreteEvent;

public class BackToDialScreenEvent extends DiscreteEvent
{
    protected static final String CATEGORY = "customerSupport";
    public static final String ENTRY = "entryPoint";
    protected static final String NAME = "dialScreenDismissed";
    public static final String ORIENTATION = "orientation";
    public static final String SOURCE = "sourceModalView";
    private Orientation mOrientation;
    private CustomerServiceLogging$ReturnToDialScreenFrom mReturnUsing;
    private IClientLogging$ModalView mSource;
    
    public BackToDialScreenEvent(final IClientLogging$ModalView mSource, final Orientation mOrientation, final CustomerServiceLogging$ReturnToDialScreenFrom mReturnUsing) {
        this.category = "customerSupport";
        this.name = "dialScreenDismissed";
        this.mSource = mSource;
        this.mOrientation = mOrientation;
        this.mReturnUsing = mReturnUsing;
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.mSource != null) {
            data.put("sourceModalView", (Object)this.mSource.name());
        }
        if (this.mOrientation != null) {
            data.put("orientation", (Object)this.mOrientation.name());
        }
        if (this.mReturnUsing != null) {
            data.put("entryPoint", (Object)this.mReturnUsing.name());
        }
        return data;
    }
}
