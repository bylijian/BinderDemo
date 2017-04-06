// OnTaskUpdateListener.aidl
package com.lijian.binderdemo;

// Declare any non-default types here with import statements
import com.lijian.binderdemo.DownloadTask;
interface OnTaskUpdateListener {

    void onTaskUpdate(in DownloadTask task);

}
