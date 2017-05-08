// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.modal;

import android.annotation.TargetApi;
import android.view.Display;
import com.facebook.infer.annotation.Assertions;
import android.view.WindowManager;
import android.content.Context;
import android.graphics.Point;

class ModalHostHelper
{
    private static final Point MAX_POINT;
    private static final Point MIN_POINT;
    private static final Point SIZE_POINT;
    
    static {
        MIN_POINT = new Point();
        MAX_POINT = new Point();
        SIZE_POINT = new Point();
    }
    
    @TargetApi(16)
    public static Point getModalHostSize(final Context context) {
        final Display defaultDisplay = Assertions.assertNotNull(context.getSystemService("window")).getDefaultDisplay();
        defaultDisplay.getCurrentSizeRange(ModalHostHelper.MIN_POINT, ModalHostHelper.MAX_POINT);
        defaultDisplay.getSize(ModalHostHelper.SIZE_POINT);
        if (ModalHostHelper.SIZE_POINT.x < ModalHostHelper.SIZE_POINT.y) {
            return new Point(ModalHostHelper.MIN_POINT.x, ModalHostHelper.MAX_POINT.y);
        }
        return new Point(ModalHostHelper.MAX_POINT.x, ModalHostHelper.MIN_POINT.y);
    }
}
