package com.lijian.binderdemo;

import android.os.RemoteException;

public interface IDownload extends android.os.IInterface {
    /**
     * Local-side IPC implementation stub class.
     */
    abstract class Stub extends android.os.Binder implements com.lijian.binderdemo.IDownload {
        private static final String DESCRIPTOR = "com.lijian.binderdemo.IDownload";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an com.lijian.binderdemo.IDownload interface,
         * generating a proxy if needed.
         */
        public static com.lijian.binderdemo.IDownload asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof com.lijian.binderdemo.IDownload))) {
                return ((com.lijian.binderdemo.IDownload) iin);
            }
            return new com.lijian.binderdemo.IDownload.Stub.Proxy(obj);
        }

        @Override
        public android.os.IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                case TRANSACTION_getTasks: {
                    data.enforceInterface(DESCRIPTOR);
                    java.util.List<DownloadTask> _result = this.getTasks();
                    reply.writeNoException();
                    reply.writeTypedList(_result);
                    return true;
                }
                case TRANSACTION_addTask: {
                    data.enforceInterface(DESCRIPTOR);
                    DownloadTask _arg0;
                    if ((0 != data.readInt())) {
                        _arg0 = DownloadTask.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    this.addTask(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_registerUpdateListener: {
                    data.enforceInterface(DESCRIPTOR);
                    com.lijian.binderdemo.OnTaskUpdateListener _arg0;
                    _arg0 = com.lijian.binderdemo.OnTaskUpdateListener.Stub.asInterface(data.readStrongBinder());
                    this.registerUpdateListener(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_unRegisterUpdateListener: {
                    data.enforceInterface(DESCRIPTOR);
                    com.lijian.binderdemo.OnTaskUpdateListener _arg0;
                    _arg0 = com.lijian.binderdemo.OnTaskUpdateListener.Stub.asInterface(data.readStrongBinder());
                    this.unRegisterUpdateListener(_arg0);
                    reply.writeNoException();
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements com.lijian.binderdemo.IDownload {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public java.util.List<DownloadTask> getTasks() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                java.util.List<DownloadTask> _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_getTasks, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.createTypedArrayList(DownloadTask.CREATOR);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            @Override
            public void addTask(DownloadTask task) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((task != null)) {
                        _data.writeInt(1);
                        task.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    mRemote.transact(Stub.TRANSACTION_addTask, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override
            public void registerUpdateListener(OnTaskUpdateListener listener) throws RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder((((listener != null)) ? (listener.asBinder()) : (null)));
                    mRemote.transact(Stub.TRANSACTION_registerUpdateListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override
            public void unRegisterUpdateListener(OnTaskUpdateListener listener) throws RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder((((listener != null)) ? (listener.asBinder()) : (null)));
                    mRemote.transact(Stub.TRANSACTION_unRegisterUpdateListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        static final int TRANSACTION_getTasks = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_addTask = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
        static final int TRANSACTION_registerUpdateListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
        static final int TRANSACTION_unRegisterUpdateListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    }

    java.util.List<DownloadTask> getTasks() throws android.os.RemoteException;

    void addTask(DownloadTask task) throws android.os.RemoteException;

    void registerUpdateListener(OnTaskUpdateListener listener) throws android.os.RemoteException;

    void unRegisterUpdateListener(OnTaskUpdateListener listener) throws android.os.RemoteException;
}
