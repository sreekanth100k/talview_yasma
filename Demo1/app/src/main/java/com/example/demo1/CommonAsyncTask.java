package com.example.demo1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class CommonAsyncTask extends AsyncTask<String,Void,String> {

    private AsyncTaskCompleteListener<String> callback;
    private ProgressDialog mProgressDialog;
    private Context mContext;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("loading");
        mProgressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    public CommonAsyncTask(Context context, AsyncTaskCompleteListener<String> cb) {
        super();

        mContext = context;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(mProgressDialog!=null)
            mProgressDialog.dismiss();
        System.out.println("on Post execute called");
        callback.onTaskComplete(s);
    }
}
