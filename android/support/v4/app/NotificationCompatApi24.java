// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.Notification$MessagingStyle$Message;
import android.app.Notification$MessagingStyle;
import android.net.Uri;
import java.util.List;
import android.annotation.TargetApi;

@TargetApi(24)
class NotificationCompatApi24
{
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    
    public static void addMessagingStyle(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, final CharSequence charSequence, final CharSequence conversationTitle, final List<CharSequence> list, final List<Long> list2, final List<CharSequence> list3, final List<String> list4, final List<Uri> list5) {
        final Notification$MessagingStyle setConversationTitle = new Notification$MessagingStyle(charSequence).setConversationTitle(conversationTitle);
        for (int i = 0; i < list.size(); ++i) {
            final Notification$MessagingStyle$Message notification$MessagingStyle$Message = new Notification$MessagingStyle$Message((CharSequence)list.get(i), (long)list2.get(i), (CharSequence)list3.get(i));
            if (list4.get(i) != null) {
                notification$MessagingStyle$Message.setData((String)list4.get(i), (Uri)list5.get(i));
            }
            setConversationTitle.addMessage(notification$MessagingStyle$Message);
        }
        setConversationTitle.setBuilder(notificationBuilderWithBuilderAccessor.getBuilder());
    }
}
