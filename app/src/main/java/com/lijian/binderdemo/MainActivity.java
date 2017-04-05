package com.lijian.binderdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private IDownload download;

    private Button addTaskBtn;
    private TextView addTaskInfo;
    private Button getTasksBtn;
    private TextView getTasksInfo;

    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindDownloadService();
        setContentView(R.layout.activity_main);
        setupView();
    }

    private void setupView() {
        addTaskBtn = (Button) findViewById(R.id.btn_add_task);
        getTasksBtn = (Button) findViewById(R.id.btn_get_tasks);
        addTaskInfo = (TextView) findViewById(R.id.tv_add_task_info);
        getTasksInfo = (TextView) findViewById(R.id.tv_tasks_info);
        addTaskBtn.setOnClickListener(this);
        getTasksBtn.setOnClickListener(this);
    }

    private void bindDownloadService() {
        Intent intent = new Intent(this, DownloadService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                download = IDownload.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_tasks:
                getTasks();
                break;
            case R.id.btn_add_task:
                addTask();
                break;
            default:
                break;
        }
    }

    private void addTask() {
        if (download != null) {
            DownloadTask task = new DownloadTask(index++, "下载文件" + index, "www.baidu.com/" + index + ".txt");
            try {
                download.addTask(task);
                addTaskInfo.setText(task.toString());
            } catch (RemoteException e) {
                Log.w(TAG, e.toString());
            }
        }
    }

    private void getTasks() {
        if (download != null) {
            try {
                List<DownloadTask> tasks = download.getTasks();
                getTasksInfo.setText(tasks == null ? "no task" : tasks.toString());
            } catch (RemoteException e) {
                Log.w(TAG, e.toString());
            }
        }
    }
}

