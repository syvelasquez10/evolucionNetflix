// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.widget.TextView$BufferType;
import android.widget.TextView;
import java.net.MalformedURLException;
import com.facebook.model.GraphObject$Factory;
import org.json.JSONObject;
import android.view.ViewGroup$LayoutParams;
import java.util.HashSet;
import com.facebook.android.R$drawable;
import android.widget.CheckBox;
import android.view.ViewStub;
import java.util.Comparator;
import java.util.Collections;
import android.graphics.Bitmap;
import com.facebook.android.R$id;
import android.widget.ProgressBar;
import com.facebook.android.R$layout;
import android.view.ViewGroup;
import android.view.View;
import java.net.URL;
import java.util.Iterator;
import com.facebook.FacebookException;
import java.text.Collator;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.Map;
import android.content.Context;
import android.widget.SectionIndexer;
import android.widget.BaseAdapter;
import com.facebook.model.GraphObject;
import android.widget.ImageView;

class GraphObjectAdapter$2 implements ImageRequest$Callback
{
    final /* synthetic */ GraphObjectAdapter this$0;
    final /* synthetic */ ImageView val$imageView;
    final /* synthetic */ String val$profileId;
    
    GraphObjectAdapter$2(final GraphObjectAdapter this$0, final String val$profileId, final ImageView val$imageView) {
        this.this$0 = this$0;
        this.val$profileId = val$profileId;
        this.val$imageView = val$imageView;
    }
    
    @Override
    public void onCompleted(final ImageResponse imageResponse) {
        this.this$0.processImageResponse(imageResponse, this.val$profileId, this.val$imageView);
    }
}
