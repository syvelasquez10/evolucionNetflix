// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerevents.legacy;

import org.apache.http.HttpException;
import org.json.JSONException;
import java.io.IOException;
import com.netflix.mediaclient.Log;

class MdxLoggingManager$1 implements Runnable
{
    final /* synthetic */ MdxLoggingManager this$0;
    final /* synthetic */ MdxCustomerEvent val$cevent;
    
    MdxLoggingManager$1(final MdxLoggingManager this$0, final MdxCustomerEvent val$cevent) {
        this.this$0 = this$0;
        this.val$cevent = val$cevent;
    }
    
    @Override
    public void run() {
        try {
            this.val$cevent.execute();
        }
        catch (IOException ex) {
            Log.e("nf_mdxMdxLoggingManager", "sendEvent IOException " + ex);
        }
        catch (JSONException ex2) {
            Log.e("nf_mdxMdxLoggingManager", "sendEvent JSONException " + ex2);
        }
        catch (HttpException ex3) {
            Log.e("nf_mdxMdxLoggingManager", "sendEvent HttpException " + ex3);
        }
    }
}
