package miui.process;

import android.os.Build.VERSION;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class ProcessManager {
    public static final int AI_MAX_ADJ = (VERSION.SDK_INT > 23 ? 400 : 4);
    public static final int AI_MAX_PROCESS_STATE = 14;
    public static final boolean DEBUG = true;
    public static final int DEFAULT_MAX_ADJ = (VERSION.SDK_INT > 23 ? 1001 : 16);
    public static final int DEFAULT_PROCESS_STATE = 21;
    public static final int FLAG_START_PROCESS_AI = 1;
    public static final int FLAG_START_PROCESS_FAST_RESTART = 2;
    public static final int LOCKED_MAX_ADJ = (VERSION.SDK_INT > 23 ? 400 : 4);
    public static final int LOCKED_MAX_PROCESS_STATE = 14;
    public static final long MAX_ADJ_BOOST_TIMEOUT = 300000;
    public static final int MIUI_AI_MODE_STACK_ID = 7;
    public static final int PROTECT_MAX_ADJ;
    public static final int PROTECT_MAX_PROCESS_STATE = 14;
    public static final String SERVICE_NAME = "ProcessManager";
    private static final int SINGLE_COUNT = 1;
    public static final String TAG = "ProcessManager";

    static {
        int i = 400;
        if (VERSION.SDK_INT <= 23) {
            i = 4;
        }
        PROTECT_MAX_ADJ = i;
    }

    public static List<String> getLockedApplication(int userId) {
        try {
            return ProcessManagerNative.getDefault().getLockedApplication(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void adjBoost(String processName, int targetAdj, long timeout, int userId) {
        try {
            ProcessManagerNative.getDefault().adjBoost(processName, targetAdj, timeout, userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public static void registerForegroundInfoListener(IForegroundInfoListener listener) {
        try {
            ProcessManagerNative.getDefault().registerForegroundInfoListener(listener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void unregisterForegroundInfoListener(IForegroundInfoListener listener) {
        try {
            ProcessManagerNative.getDefault().unregisterForegroundInfoListener(listener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static ForegroundInfo getForegroundInfo() {
        try {
            return ProcessManagerNative.getDefault().getForegroundInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void registerActivityChangeListener(List<String> targetPackages, List<String> targetActivities, IActivityChangeListener listener) {
        try {
            ProcessManagerNative.getDefault().registerActivityChangeListener(targetPackages, targetActivities, listener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void unregisterActivityChanageListener(IActivityChangeListener listener) {
        try {
            ProcessManagerNative.getDefault().unregisterActivityChangeListener(listener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
