// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lolomo;

import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter$RowHolder;
import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter$LoMoRowContent;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.Iterator;
import com.netflix.mediaclient.service.webclient.model.leafs.KubrickLoMoDuplicate;
import com.netflix.mediaclient.service.webclient.model.leafs.KubrickLoMoHeroDuplicate;
import java.util.ArrayList;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import java.util.List;
import com.netflix.mediaclient.ui.lolomo.BaseLoLoMoAdapter;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import java.util.EnumSet;

public class KubrickLolomoUtils
{
    private static final String TAG = "KubrickLolomoUtils";
    private static final EnumSet<LoMoType> duplicateRowTypes;
    
    static {
        duplicateRowTypes = EnumSet.of(LoMoType.STANDARD, LoMoType.POPULAR_TITLES, LoMoType.TOP_TEN, LoMoType.SOCIAL_FRIEND, LoMoType.SOCIAL_GROUP, LoMoType.SOCIAL_POPULAR);
    }
    
    public static <T extends LoMo> List<T> createDuplicateRows(final BaseLoLoMoAdapter<?> baseLoLoMoAdapter, final List<? extends LoMo> list, int n) {
        ThreadUtils.assertOnMain();
        List<T> list2 = (List<T>)list;
        if (baseLoLoMoAdapter.getCount() < n) {
            Log.v("KubrickLolomoUtils", "Manipulating lomo data to inject Kubrick duplicate rows");
            list2 = new ArrayList<T>(list.size() * 2);
            for (final LoMo loMo : list) {
                if (n > 0 && shouldDuplicateLomo(loMo)) {
                    Log.v("KubrickLolomoUtils", "Adding duplicate lomo for row: " + loMo.getTitle());
                    list2.add((T)new KubrickLoMoHeroDuplicate(loMo));
                    list2.add((T)new KubrickLoMoDuplicate(loMo));
                    --n;
                }
                else {
                    list2.add((T)loMo);
                }
            }
        }
        return list2;
    }
    
    public static BaseLoLoMoAdapter$RowHolder createHolder(final NetflixActivity netflixActivity, final View view, final LinearLayout linearLayout, final TextView textView, final BaseLoLoMoAdapter$LoMoRowContent baseLoLoMoAdapter$LoMoRowContent, final View view2) {
        return new KubrickLolomoUtils$KubrickRowHolder(netflixActivity, (View)linearLayout, textView, baseLoLoMoAdapter$LoMoRowContent, view2, view.findViewById(2131427566));
    }
    
    public static boolean isDuplicateRow(final BasicLoMo basicLoMo) {
        return basicLoMo instanceof KubrickLoMoDuplicate;
    }
    
    public static boolean shouldDuplicateLomo(final LoMo loMo) {
        return KubrickLolomoUtils.duplicateRowTypes.contains(loMo.getType());
    }
    
    public static boolean shouldFetchByLomoType(final String s, final LoMo loMo) {
        boolean b = true;
        boolean b2;
        if (BrowseExperience.isKubrickKids() && loMo.getType() == LoMoType.POPULAR_TITLES) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (b2) {
            Log.v(s, "For Kubrick Kids POPULAR_TITLES row, doing fetchVideos via lomo type");
        }
        if (!BrowseExperience.isKubrick() || loMo.getType() != LoMoType.INSTANT_QUEUE) {
            b = false;
        }
        final boolean b3 = b | b2;
        if (b3) {
            Log.v(s, "For Kubrick INSTANT_QUEUE row, doing fetchVideos via lomo type");
        }
        return b3;
    }
    
    public static void updateRowViews(final BaseLoLoMoAdapter$RowHolder baseLoLoMoAdapter$RowHolder, final LoMo loMo, int n) {
        final boolean b = false;
        int visibility;
        if (n == 0) {
            visibility = 8;
        }
        else if (loMo instanceof KubrickLoMoHeroDuplicate) {
            visibility = 0;
        }
        else if (loMo instanceof KubrickLoMoDuplicate) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        if (Log.isLoggable()) {
            Log.v("KubrickLolomoUtils", "updateRowViews, spacerVisibility: " + ViewUtils.getVisibilityAsString(visibility) + ", position: " + n);
        }
        ((KubrickLolomoUtils$KubrickRowHolder)baseLoLoMoAdapter$RowHolder).topSpacer.setVisibility(visibility);
        if (baseLoLoMoAdapter$RowHolder.title.getVisibility() == 0) {
            n = 1;
        }
        else {
            n = 0;
        }
        int dimensionPixelSize = b ? 1 : 0;
        if (n != 0) {
            dimensionPixelSize = baseLoLoMoAdapter$RowHolder.contentGroup.getResources().getDimensionPixelSize(2131296451);
        }
        ViewUtils.setPaddingTop(baseLoLoMoAdapter$RowHolder.contentGroup, dimensionPixelSize);
    }
}
