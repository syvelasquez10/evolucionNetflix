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
import android.widget.ImageView;
import java.util.HashMap;
import java.util.List;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.Map;
import android.content.Context;
import android.widget.SectionIndexer;
import android.widget.BaseAdapter;
import java.util.Collection;
import java.text.Collator;
import com.facebook.model.GraphObject;
import java.util.Comparator;

class GraphObjectAdapter$1 implements Comparator<GraphObject>
{
    final /* synthetic */ GraphObjectAdapter this$0;
    final /* synthetic */ Collator val$collator;
    
    GraphObjectAdapter$1(final GraphObjectAdapter this$0, final Collator val$collator) {
        this.this$0 = this$0;
        this.val$collator = val$collator;
    }
    
    @Override
    public int compare(final GraphObject graphObject, final GraphObject graphObject2) {
        return compareGraphObjects(graphObject, graphObject2, this.this$0.sortFields, this.val$collator);
    }
}
