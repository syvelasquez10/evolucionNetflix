// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.scroll;

import com.facebook.react.uimanager.PixelUtil;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import java.util.Map;

public class ReactScrollViewCommandHelper
{
    public static Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("scrollTo", 1, "scrollToEnd", 2);
    }
    
    public static <T> void receiveCommand(final ReactScrollViewCommandHelper$ScrollCommandHandler<T> reactScrollViewCommandHelper$ScrollCommandHandler, final T t, final int n, final ReadableArray readableArray) {
        Assertions.assertNotNull(reactScrollViewCommandHelper$ScrollCommandHandler);
        Assertions.assertNotNull(t);
        Assertions.assertNotNull(readableArray);
        switch (n) {
            default: {
                throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", n, reactScrollViewCommandHelper$ScrollCommandHandler.getClass().getSimpleName()));
            }
            case 1: {
                reactScrollViewCommandHelper$ScrollCommandHandler.scrollTo(t, new ReactScrollViewCommandHelper$ScrollToCommandData(Math.round(PixelUtil.toPixelFromDIP(readableArray.getDouble(0))), Math.round(PixelUtil.toPixelFromDIP(readableArray.getDouble(1))), readableArray.getBoolean(2)));
            }
            case 2: {
                reactScrollViewCommandHelper$ScrollCommandHandler.scrollToEnd(t, new ReactScrollViewCommandHelper$ScrollToEndCommandData(readableArray.getBoolean(0)));
            }
        }
    }
}
