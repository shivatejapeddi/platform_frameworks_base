package miui.process;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ProcessManagerNative */
class ProcessManagerProxy implements IProcessManager {
    private IBinder mRemote;

    public ProcessManagerProxy(IBinder remote) {
        this.mRemote = remote;
    }

    public IBinder asBinder() {
        return this.mRemote;
    }

    public List<String> getLockedApplication(int userId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        data.writeInterfaceToken(IProcessManager.descriptor);
        data.writeInt(userId);
        this.mRemote.transact(4, data, reply, 0);
        reply.readException();
        ArrayList<String> list = null;
        int N = reply.readInt();
        if (N >= 0) {
            list = new ArrayList();
            while (N > 0) {
                list.add(reply.readString());
                N--;
            }
        }
        data.recycle();
        reply.recycle();
        return list;
    }

    public void registerForegroundInfoListener(IForegroundInfoListener listener) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        data.writeInterfaceToken(IProcessManager.descriptor);
        data.writeStrongBinder(listener != null ? listener.asBinder() : null);
        this.mRemote.transact(10, data, reply, 0);
        reply.readException();
        data.recycle();
        reply.recycle();
    }

    public void unregisterForegroundInfoListener(IForegroundInfoListener listener) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        data.writeInterfaceToken(IProcessManager.descriptor);
        data.writeStrongBinder(listener != null ? listener.asBinder() : null);
        this.mRemote.transact(11, data, reply, 0);
        reply.readException();
        data.recycle();
        reply.recycle();
    }

    public ForegroundInfo getForegroundInfo() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        data.writeInterfaceToken(IProcessManager.descriptor);
        this.mRemote.transact(12, data, reply, 0);
        reply.readException();
        ForegroundInfo foregroundInfo = (ForegroundInfo) ForegroundInfo.CREATOR.createFromParcel(reply);
        data.recycle();
        reply.recycle();
        return foregroundInfo;
    }

    public void registerActivityChangeListener(List<String> targetPackages, List<String> targetActivities, IActivityChangeListener listener) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        data.writeInterfaceToken(IProcessManager.descriptor);
        data.writeStringList(targetPackages);
        data.writeStringList(targetActivities);
        data.writeStrongBinder(listener != null ? listener.asBinder() : null);
        this.mRemote.transact(15, data, reply, 0);
        reply.readException();
        data.recycle();
        reply.recycle();
    }

    public void unregisterActivityChangeListener(IActivityChangeListener listener) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        data.writeInterfaceToken(IProcessManager.descriptor);
        data.writeStrongBinder(listener != null ? listener.asBinder() : null);
        this.mRemote.transact(16, data, reply, 0);
        reply.readException();
        data.recycle();
        reply.recycle();
    }


    public void adjBoost(String processName, int targetAdj, long timeout, int userId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        data.writeInterfaceToken(IProcessManager.descriptor);
        data.writeString(processName);
        data.writeInt(targetAdj);
        data.writeLong(timeout);
        data.writeInt(userId);
        this.mRemote.transact(19, data, reply, 0);
        reply.readException();
        reply.recycle();
        data.recycle();
    }
}
