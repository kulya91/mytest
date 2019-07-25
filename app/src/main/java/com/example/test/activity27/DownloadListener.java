package com.example.test.activity27;

/*
项目名称： test
创建人：黄大神
类描述：下载过程的监听回调
创建时间：2019/7/25 14:16
*/
public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}
