// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.util.DeviceUtils;
import android.util.Log;
import android.content.Context;
import android.util.SparseIntArray;
import android.util.SparseArray;

public class SearchUtils
{
    private static final String TAG = "SearchUtils";
    private static SearchUtils$SearchExperience currentExperience;
    private static final SparseArray<SparseIntArray> numPeopleColumnsTable;
    private static final SparseArray<SparseIntArray> numRelatedColumnsTable;
    private static final SparseArray<SparseIntArray> numVideoColumnsTable;
    
    static {
        numRelatedColumnsTable = new SparseArray(2);
        numPeopleColumnsTable = new SparseArray(2);
        numVideoColumnsTable = new SparseArray(2);
        setSearchExperience(SearchUtils$SearchExperience.STANDARD);
    }
    
    private static int computeMaxResultsForPeople(final Context context) {
        int n = 8;
        final int numPeopleGridCols = getNumPeopleGridCols(context);
        int n2 = n;
        if (numPeopleGridCols > 0) {
            int n3 = -1;
            while (true) {
                n2 = n;
                if (n3 == 0) {
                    break;
                }
                n3 = ++n % numPeopleGridCols;
            }
        }
        return n2;
    }
    
    private static int computeMaxResultsForVideos(final Context context) {
        final int n = 21;
        final int numVideoGridCols = getNumVideoGridCols(context);
        int n2 = n;
        if (numVideoGridCols > 0) {
            final int n3 = 21 % numVideoGridCols;
            n2 = n;
            if (n3 != 0) {
                n2 = 21 - n3;
            }
        }
        return n2;
    }
    
    public static int getMaxResultsPeople(final Context context) {
        if (context == null) {
            Log.w("SearchUtils", "getMaxResultsPeople, Context is null");
            return 1;
        }
        final int basicScreenOrientation = DeviceUtils.getBasicScreenOrientation(context);
        final int screenSizeCategory = DeviceUtils.getScreenSizeCategory(context);
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$SearchExperience[SearchUtils.currentExperience.ordinal()]) {
            default: {
                return computeMaxResultsForPeople(context);
            }
            case 1: {
                if (basicScreenOrientation == 1 && (screenSizeCategory == 3 || screenSizeCategory == 4)) {
                    return 3;
                }
                return computeMaxResultsForPeople(context);
            }
        }
    }
    
    public static int getMaxResultsRelated(final Context context) {
        if (context == null) {
            Log.w("SearchUtils", "getMaxResultsRelated, Context is null");
            return 1;
        }
        final int basicScreenOrientation = DeviceUtils.getBasicScreenOrientation(context);
        final int screenSizeCategory = DeviceUtils.getScreenSizeCategory(context);
        if (basicScreenOrientation == 1 && (screenSizeCategory == 3 || screenSizeCategory == 4)) {
            return 3;
        }
        return 40;
    }
    
    public static int getMaxResultsVideos(final Context context) {
        return computeMaxResultsForVideos(context);
    }
    
    public static int getNumPeopleGridCols(final Context context) {
        if (context == null) {
            Log.w("SearchUtils", "getNumPeopleGridCols, Context is null");
            return 2;
        }
        return ((SparseIntArray)SearchUtils.numPeopleColumnsTable.get(DeviceUtils.getBasicScreenOrientation(context))).get(DeviceUtils.getScreenSizeCategory(context));
    }
    
    public static int getNumRelatedGridCols(final Context context) {
        if (context == null) {
            Log.w("SearchUtils", "getNumRelatedGridCols, Context is null");
            return 2;
        }
        return ((SparseIntArray)SearchUtils.numRelatedColumnsTable.get(DeviceUtils.getBasicScreenOrientation(context))).get(DeviceUtils.getScreenSizeCategory(context));
    }
    
    public static int getNumVideoGridCols(final Context context) {
        if (context == null) {
            Log.w("SearchUtils", "getNumVideoGridCols, Context is null");
            return 2;
        }
        return ((SparseIntArray)SearchUtils.numVideoColumnsTable.get(DeviceUtils.getBasicScreenOrientation(context))).get(DeviceUtils.getScreenSizeCategory(context));
    }
    
    public static double getPeopleImageAspectRatio() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$SearchExperience[SearchUtils.currentExperience.ordinal()]) {
            default: {
                return 1.0;
            }
        }
    }
    
    public static int getSearchFragLayout() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$SearchExperience[SearchUtils.currentExperience.ordinal()]) {
            default: {
                return 2130903191;
            }
            case 1: {
                return 2130903192;
            }
        }
    }
    
    public static int getSearchViewLayoutPeople() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$SearchExperience[SearchUtils.currentExperience.ordinal()]) {
            default: {
                return 2130903188;
            }
            case 1: {
                return 2130903190;
            }
        }
    }
    
    public static int getSearchViewLayoutRelated() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$SearchExperience[SearchUtils.currentExperience.ordinal()]) {
            default: {
                return 2130903189;
            }
            case 1: {
                return 2130903190;
            }
        }
    }
    
    public static double getVideoImageAspectRatio() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$SearchExperience[SearchUtils.currentExperience.ordinal()]) {
            default: {
                return 1.4299999475479126;
            }
            case 1: {
                return 0.5625;
            }
        }
    }
    
    private static void initColumnsTable() {
        if (Log.isLoggable("SearchUtils", 2)) {
            Log.v("SearchUtils", "init'ing columns table for seach experience: " + SearchUtils.currentExperience);
        }
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$SearchExperience[SearchUtils.currentExperience.ordinal()]) {
            default: {
                intColumnTableStandardLandscape();
                intColumnTableStandardPortrait();
            }
            case 1: {
                intColumnTableKubrickLandscape();
                intColumnTableKubrickPortrait();
            }
        }
    }
    
    private static void intColumnTableKubrickLandscape() {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        final SparseIntArray sparseIntArray3 = new SparseIntArray();
        sparseIntArray.put(1, 2);
        sparseIntArray2.put(1, 4);
        sparseIntArray3.put(1, 1);
        sparseIntArray.put(2, 3);
        sparseIntArray2.put(2, 6);
        sparseIntArray3.put(2, 1);
        sparseIntArray.put(3, 3);
        sparseIntArray2.put(3, 1);
        sparseIntArray3.put(3, 1);
        sparseIntArray.put(4, 3);
        sparseIntArray2.put(4, 1);
        sparseIntArray3.put(4, 1);
        SearchUtils.numVideoColumnsTable.put(2, (Object)sparseIntArray);
        SearchUtils.numPeopleColumnsTable.put(2, (Object)sparseIntArray2);
        SearchUtils.numRelatedColumnsTable.put(2, (Object)sparseIntArray3);
    }
    
    private static void intColumnTableKubrickPortrait() {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        final SparseIntArray sparseIntArray3 = new SparseIntArray();
        sparseIntArray.put(1, 3);
        sparseIntArray2.put(1, 4);
        sparseIntArray3.put(1, 3);
        sparseIntArray.put(2, 2);
        sparseIntArray2.put(2, 4);
        sparseIntArray3.put(2, 3);
        sparseIntArray.put(3, 2);
        sparseIntArray2.put(3, 3);
        sparseIntArray3.put(3, 3);
        sparseIntArray.put(4, 3);
        sparseIntArray2.put(4, 3);
        sparseIntArray3.put(4, 3);
        SearchUtils.numVideoColumnsTable.put(1, (Object)sparseIntArray);
        SearchUtils.numPeopleColumnsTable.put(1, (Object)sparseIntArray2);
        SearchUtils.numRelatedColumnsTable.put(1, (Object)sparseIntArray3);
    }
    
    private static void intColumnTableStandardLandscape() {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        final SparseIntArray sparseIntArray3 = new SparseIntArray();
        sparseIntArray.put(1, 3);
        sparseIntArray2.put(1, 3);
        sparseIntArray3.put(1, 1);
        sparseIntArray.put(2, 4);
        sparseIntArray2.put(2, 4);
        sparseIntArray3.put(2, 1);
        sparseIntArray.put(3, 5);
        sparseIntArray2.put(3, 5);
        sparseIntArray3.put(3, 1);
        sparseIntArray.put(4, 6);
        sparseIntArray2.put(4, 6);
        sparseIntArray3.put(4, 1);
        SearchUtils.numVideoColumnsTable.put(2, (Object)sparseIntArray);
        SearchUtils.numPeopleColumnsTable.put(2, (Object)sparseIntArray2);
        SearchUtils.numRelatedColumnsTable.put(2, (Object)sparseIntArray3);
    }
    
    private static void intColumnTableStandardPortrait() {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        final SparseIntArray sparseIntArray3 = new SparseIntArray();
        sparseIntArray.put(1, 2);
        sparseIntArray2.put(1, 2);
        sparseIntArray3.put(1, 1);
        sparseIntArray.put(2, 3);
        sparseIntArray2.put(2, 3);
        sparseIntArray3.put(2, 1);
        sparseIntArray.put(3, 4);
        sparseIntArray2.put(3, 4);
        sparseIntArray3.put(3, 1);
        sparseIntArray.put(4, 4);
        sparseIntArray2.put(4, 4);
        sparseIntArray3.put(4, 1);
        SearchUtils.numVideoColumnsTable.put(1, (Object)sparseIntArray);
        SearchUtils.numPeopleColumnsTable.put(1, (Object)sparseIntArray2);
        SearchUtils.numRelatedColumnsTable.put(1, (Object)sparseIntArray3);
    }
    
    public static String sanitizeQuery(final String s) {
        final String replaceAll = s.replaceAll("\\s+", " ");
        if (Log.isLoggable("SearchUtils", 2)) {
            Log.v("SearchUtils", "Sanitized query from: \"" + s + "\" to \"" + replaceAll + "\"");
        }
        return replaceAll;
    }
    
    public static void setSearchExperience(final SearchUtils$SearchExperience currentExperience) {
        if (currentExperience != SearchUtils.currentExperience) {
            SearchUtils.currentExperience = currentExperience;
            initColumnsTable();
        }
    }
    
    public static boolean shouldHandleBackPress() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$SearchExperience[SearchUtils.currentExperience.ordinal()]) {
            default: {
                return false;
            }
            case 1: {
                return true;
            }
        }
    }
    
    public static boolean shouldOpenNewActivityForRelated() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$SearchExperience[SearchUtils.currentExperience.ordinal()]) {
            default: {
                return true;
            }
            case 1: {
                return false;
            }
        }
    }
    
    public static boolean shouldResetQueryOnTouch() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$SearchExperience[SearchUtils.currentExperience.ordinal()]) {
            default: {
                return true;
            }
            case 2: {
                return false;
            }
        }
    }
    
    public static boolean shouldShowVerticalBoxArt() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$SearchExperience[SearchUtils.currentExperience.ordinal()]) {
            default: {
                return true;
            }
            case 1: {
                return false;
            }
        }
    }
    
    public static boolean shouldUpperCaseTitleLabels() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$SearchExperience[SearchUtils.currentExperience.ordinal()]) {
            default: {
                return true;
            }
            case 1: {
                return false;
            }
        }
    }
}
