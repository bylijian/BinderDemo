// IDownload.aidl
package com.lijian.binderdemo;
// Declare any non-default types here with import statements
import com.lijian.binderdemo.DownloadTask;
interface IDownload {
    List<DownloadTask> getTasks();
    void addTask(in DownloadTask task);
}
