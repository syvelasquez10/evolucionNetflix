// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import java.util.Collection;
import java.util.Arrays;
import java.util.HashSet;

public class ViewProps
{
    public static final int[] BORDER_SPACING_TYPES;
    private static final HashSet<String> LAYOUT_ONLY_PROPS;
    public static final int[] PADDING_MARGIN_SPACING_TYPES;
    public static final int[] POSITION_SPACING_TYPES;
    
    static {
        BORDER_SPACING_TYPES = new int[] { 8, 4, 5, 1, 3 };
        PADDING_MARGIN_SPACING_TYPES = new int[] { 8, 7, 6, 4, 5, 1, 3 };
        POSITION_SPACING_TYPES = new int[] { 4, 5, 1, 3 };
        LAYOUT_ONLY_PROPS = new HashSet<String>(Arrays.asList("alignSelf", "alignItems", "collapsable", "flex", "flexDirection", "flexWrap", "justifyContent", "overflow", "position", "right", "top", "bottom", "left", "width", "height", "minWidth", "maxWidth", "minHeight", "maxHeight", "margin", "marginVertical", "marginHorizontal", "marginLeft", "marginRight", "marginTop", "marginBottom", "padding", "paddingVertical", "paddingHorizontal", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom"));
    }
    
    public static boolean isLayoutOnly(final String s) {
        return ViewProps.LAYOUT_ONLY_PROPS.contains(s);
    }
}
