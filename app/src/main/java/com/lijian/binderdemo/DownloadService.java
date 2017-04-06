package com.lijian.binderdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by lijian on 2017/3/22.
 */

public class DownloadService extends Service {

    private static final String TAG = "DownloadService";

    private List<DownloadTask> mTasks = new CopyOnWriteArrayList<>();

    private RemoteCallbackList<OnTaskUpdateListener> callbackList = new RemoteCallbackList<>();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IDownload.Stub() {
            @Override
            public List<DownloadTask> getTasks() throws RemoteException {
                return mTasks;
            }

            @Override
            public void addTask(DownloadTask task) throws RemoteException {
                if (mTasks != null) {
                    mTasks.add(task);
                    if (callbackList != null) {
                        int N = callbackList.beginBroadcast();
                        for (int i = 0; i < N; i++) {
                            OnTaskUpdateListener listener = callbackList.getBroadcastItem(i);
                            listener.onTaskUpdate(task);
                        }
                        callbackList.finishBroadcast();
                    }
                }
            }

            @Override
            public void registerUpdateListener(OnTaskUpdateListener listener) throws RemoteException {
                if (listener != null) {
                    Log.d(TAG, "registerUpdateListener " + listener);
                    callbackList.register(listener);
                }
            }

            @Override
            public void unRegisterUpdateListener(OnTaskUpdateListener listener) throws RemoteException {
                if (listener != null) {
                    Log.d(TAG, "unRegisterUpdateListener " + listener);
                    boolean success = callbackList.unregister(listener);
                    Log.d(TAG, "unRegisterUpdateListener " + "success=" + success);
                }
            }
        };
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
