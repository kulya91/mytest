package com.example.test.activity26;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/*
项目名称： test
创建人：黄大神
类描述：
创建时间：2019/7/15 11:01
*/
public class DownloadTask extends AsyncTask<Void, Integer, Boolean> {
    private ProgressDialog dialog;
    private int num = 0;

    public DownloadTask(Context context) {
       // Toast.makeText(MyApplication.getContext(), "下载完成2", Toast.LENGTH_SHORT).show();
        dialog = new ProgressDialog(context);

    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        while (true) {
            int downloadPercent = doDownload();
            publishProgress(downloadPercent);
            if (downloadPercent >= 100)
                break;
        }
        return true;
    }

    private int doDownload() {
        num++;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        dialog.dismiss();
        //  Toast.makeText(MyApplication.getContext(), "下载完成", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        dialog.setMessage("下载" + values[0] + "%");
    }

    @Override
    protected void onPreExecute() {
        dialog.show();
    }
}
