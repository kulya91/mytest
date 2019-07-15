package com.example.test.activity26;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test.R;
import com.example.test.util.baseactivity;

public class activity26_main extends baseactivity implements View.OnClickListener {
    //private ProgressDialog dialog;
    private Button act26_download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity26_main);
        initView();
    }

    private void initView() {
        act26_download = (Button) findViewById(R.id.act26_download);

        act26_download.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act26_download:
                new DownloadTask(this).execute();
                break;
        }
    }
//    private class DownloadTask extends AsyncTask<Void, Integer, Boolean> {
//int num=0;
//        public DownloadTask() {
//           dialog=new ProgressDialog(activity26_main.this);
//           // Toast.makeText(MyApplication.getContext(), "下载完成1", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... voids) {
//            while (true) {
//                int downloadPercent = doDownload();
//                publishProgress(downloadPercent);
//                if (downloadPercent >= 100)
//                    break;
//            }
//            return true;
//        }
//
//        private int doDownload() {
//            num++;
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return num;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean aBoolean) {
//            super.onPostExecute(aBoolean);
//            dialog.dismiss();
//            Toast.makeText(MyApplication.getContext(), "下载完成", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//            dialog.setMessage("下载" + values[0] + "%");
//        }
//
//        @Override
//        protected void onPreExecute() {
//            dialog.show();
//        }
//    }

}
