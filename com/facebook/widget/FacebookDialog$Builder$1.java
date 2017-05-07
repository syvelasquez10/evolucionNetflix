// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.AppEventsLogger;
import com.facebook.Settings;
import com.facebook.internal.Utility$DialogFeatureConfig;
import com.facebook.internal.Utility;
import com.facebook.internal.NativeProtocol;
import com.facebook.FacebookException;
import java.util.ArrayList;
import android.os.Bundle;
import android.content.Intent;
import java.util.Iterator;
import java.util.EnumSet;
import android.support.v4.app.Fragment;
import android.app.Activity;
import com.facebook.NativeAppCallAttachmentStore;
import java.io.File;
import android.graphics.Bitmap;
import java.util.Map;
import android.content.Context;

class FacebookDialog$Builder$1 implements FacebookDialog$OnPresentCallback
{
    final /* synthetic */ FacebookDialog$Builder this$0;
    
    FacebookDialog$Builder$1(final FacebookDialog$Builder this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onPresent(final Context context) {
        if (this.this$0.imageAttachments != null && this.this$0.imageAttachments.size() > 0) {
            getAttachmentStore().addAttachmentsForCall(context, this.this$0.appCall.getCallId(), this.this$0.imageAttachments);
        }
        if (this.this$0.mediaAttachmentFiles != null && this.this$0.mediaAttachmentFiles.size() > 0) {
            getAttachmentStore().addAttachmentFilesForCall(context, this.this$0.appCall.getCallId(), this.this$0.mediaAttachmentFiles);
        }
    }
}
