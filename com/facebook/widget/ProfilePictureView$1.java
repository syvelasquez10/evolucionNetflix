// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.internal.Utility;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.graphics.BitmapFactory;
import com.facebook.android.R$drawable;
import java.net.URISyntaxException;
import com.facebook.internal.ImageDownloader;
import com.facebook.internal.ImageRequest$Builder;
import com.facebook.FacebookException;
import com.facebook.internal.Logger;
import com.facebook.LoggingBehavior;
import android.content.res.TypedArray;
import com.facebook.android.R$styleable;
import android.view.View;
import android.widget.ImageView$ScaleType;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.facebook.android.R$dimen;
import android.util.AttributeSet;
import android.content.Context;
import com.facebook.internal.ImageRequest;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.widget.FrameLayout;
import com.facebook.internal.ImageResponse;
import com.facebook.internal.ImageRequest$Callback;

class ProfilePictureView$1 implements ImageRequest$Callback
{
    final /* synthetic */ ProfilePictureView this$0;
    
    ProfilePictureView$1(final ProfilePictureView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onCompleted(final ImageResponse imageResponse) {
        this.this$0.processResponse(imageResponse);
    }
}
