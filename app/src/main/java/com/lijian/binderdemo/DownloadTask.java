package com.lijian.binderdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 下载任务的JavaBean
 * 为了Binder传输，必须实现Parcelable序列化
 * Created by lijian on 2017/3/23.
 */

public class DownloadTask implements Parcelable {
    public int taskId;
    public String fileName;
    public String downloadUrl;

    public DownloadTask(int taksId, String fileName, String downloadUrl) {
        this.taskId = taksId;
        this.fileName = fileName;
        this.downloadUrl = downloadUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.taskId);
        dest.writeString(this.fileName);
        dest.writeString(this.downloadUrl);
    }

    protected DownloadTask(Parcel in) {
        this.taskId = in.readInt();
        this.fileName = in.readString();
        this.downloadUrl = in.readString();
    }

    public static final Parcelable.Creator<DownloadTask> CREATOR = new Parcelable.Creator<DownloadTask>() {
        @Override
        public DownloadTask createFromParcel(Parcel source) {
            return new DownloadTask(source);
        }

        @Override
        public DownloadTask[] newArray(int size) {
            return new DownloadTask[size];
        }
    };

    @Override
    public String toString() {
        return "DownloadTask{" +
                "taskId=" + taskId +
                ", fileName='" + fileName + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                "}\n";
    }
}
