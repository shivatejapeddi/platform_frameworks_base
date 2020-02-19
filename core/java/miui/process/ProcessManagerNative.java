package miui.process;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import java.util.ArrayList;
import java.util.List;
import miui.process.IForegroundInfoListener.Stub;

public abstract class ProcessManagerNative extends Binder implements IProcessManager {
    private static volatile IProcessManager pm = null;

    public ProcessManagerNative() {
        attachInterface(this, IProcessManager.descriptor);
    }

    public static IProcessManager asInterface(IBinder obj) {
        if (obj == null) {
            return null;
        }
        IProcessManager in = (IProcessManager) obj.queryLocalInterface(IProcessManager.descriptor);
        if (in != null) {
            return in;
        }
        return new ProcessManagerProxy(obj);
    }

    public IBinder asBinder() {
        return this;
    }

    public static IProcessManager getDefault() {
        if (pm == null) {
            synchronized (ProcessManagerNative.class) {
                if (pm == null) {
                    pm = asInterface(ServiceManager.getService("ProcessManager"));
                }
            }
        }
        return pm;
    }

    /* Access modifiers changed, original: protected */
    public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        Parcel parcel = data;
        Parcel parcel2 = reply;
        boolean isLocked = false;
        String userId = IProcessManager.descriptor;
        List<String> list;
        boolean success;
        switch (code) {
            case 4:
                parcel.enforceInterface(userId);
                list = getLockedApplication(data.readInt());
                reply.writeNoException();
                return true;
            case 5:
                parcel.enforceInterface(userId);
                registerForegroundInfoListener(Stub.asInterface(data.readStrongBinder()));
                return true;
            case 6:
                parcel.enforceInterface(userId);
                unregisterForegroundInfoListener(Stub.asInterface(data.readStrongBinder()));
                return true;
            case 7:
                parcel.enforceInterface(userId);
                ForegroundInfo foregroundInfo = getForegroundInfo();
                reply.writeNoException();
                foregroundInfo.writeToParcel(parcel2, 0);
                return true;
            case 8:
                parcel.enforceInterface(userId);
                List<String> targetPackages = new ArrayList();
                parcel.readStringList(targetPackages);
                list = new ArrayList();
                parcel.readStringList(list);
                registerActivityChangeListener(targetPackages, list, IActivityChangeListener.Stub.asInterface(data.readStrongBinder()));
                return true;
            case 9:
                parcel.enforceInterface(userId);
                unregisterActivityChangeListener(IActivityChangeListener.Stub.asInterface(data.readStrongBinder()));
                return true;
            case 10:
                parcel.enforceInterface(userId);
                adjBoost(data.readString(), data.readInt(), data.readLong(), data.readInt());
                reply.writeNoException();
                return true;
            default:
                return super.onTransact(code, data, reply, flags);
    }                
    }
}
