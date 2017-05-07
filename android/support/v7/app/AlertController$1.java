// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.DialogInterface$OnClickListener;
import android.view.KeyEvent;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.appcompat.R$id;
import android.text.TextUtils;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.view.ViewGroup;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$styleable;
import android.content.DialogInterface;
import android.view.Window;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.content.Context;
import android.widget.Button;
import android.widget.ListAdapter;
import android.os.Message;
import android.view.View;
import android.view.View$OnClickListener;

class AlertController$1 implements View$OnClickListener
{
    final /* synthetic */ AlertController this$0;
    
    AlertController$1(final AlertController this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        Message message;
        if (view == this.this$0.mButtonPositive && this.this$0.mButtonPositiveMessage != null) {
            message = Message.obtain(this.this$0.mButtonPositiveMessage);
        }
        else if (view == this.this$0.mButtonNegative && this.this$0.mButtonNegativeMessage != null) {
            message = Message.obtain(this.this$0.mButtonNegativeMessage);
        }
        else if (view == this.this$0.mButtonNeutral && this.this$0.mButtonNeutralMessage != null) {
            message = Message.obtain(this.this$0.mButtonNeutralMessage);
        }
        else {
            message = null;
        }
        if (message != null) {
            message.sendToTarget();
        }
        this.this$0.mHandler.obtainMessage(1, (Object)this.this$0.mDialog).sendToTarget();
    }
}
