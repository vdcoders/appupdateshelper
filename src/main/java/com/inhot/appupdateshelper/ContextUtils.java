package com.inhot.appupdateshelper;

import android.content.Context;
import android.os.Build;

public class ContextUtils {
    /**
     * Constant to use in [registerReceiver] to declare that a registered receiver is not exported.
     * <p>
     * This is needed as the constant does not exist in Android < 34
     */
    public static int getReceiverNotExported() {
        int value;
        value = Context.RECEIVER_NOT_EXPORTED;
        return value;
    }

    /**
     * Constant to use in [registerReceiver] to declare that a registered receiver is exported.
     * <p>
     * This is needed as the constant does not exist in Android < 34
     */
    public static int getReceiverExported() {
        int value;
        value = Context.RECEIVER_EXPORTED;
        return value;
    }
}
