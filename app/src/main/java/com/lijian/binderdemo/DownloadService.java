package com.lijian.binderdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by lijian on 2017/3/22.
 */

public class DownloadService extends Service {

    private static final String TAG = "DownloadService";

    private List<DownloadTask> mTasks = new CopyOnWriteArrayList<>();

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
                }
            }
        };
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
