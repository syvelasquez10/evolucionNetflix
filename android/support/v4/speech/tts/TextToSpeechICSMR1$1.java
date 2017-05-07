// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.speech.tts;

import android.speech.tts.UtteranceProgressListener;

final class TextToSpeechICSMR1$1 extends UtteranceProgressListener
{
    final /* synthetic */ TextToSpeechICSMR1$UtteranceProgressListenerICSMR1 val$listener;
    
    TextToSpeechICSMR1$1(final TextToSpeechICSMR1$UtteranceProgressListenerICSMR1 val$listener) {
        this.val$listener = val$listener;
    }
    
    public void onDone(final String s) {
        this.val$listener.onDone(s);
    }
    
    public void onError(final String s) {
        this.val$listener.onError(s);
    }
    
    public void onStart(final String s) {
        this.val$listener.onStart(s);
    }
}
