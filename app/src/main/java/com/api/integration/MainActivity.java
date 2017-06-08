package com.api.integration;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.api.integration.adapters.CommitsInfoAdapter;
import com.api.integration.network.GitHubClient;
import com.api.integration.network.models.commits.GithubCommitsAPIResponse;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private Subscription subscription;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        if (connectionCheck()) {
            dialog.show();
            subscription = GitHubClient.getInstance()
                    .getRailsCommits()
                    .subscribeOn(Schedulers.io())
                    .retry(3)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<GithubCommitsAPIResponse>() {
                        @Override
                        public void onCompleted() {
                            Log.d(TAG, "In onCompleted()");
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            Log.d(TAG, e.getLocalizedMessage());
                        }

                        @Override
                        public void onNext(GithubCommitsAPIResponse githubCommitsAPIResponse) {
                            Log.d(TAG, "In onNext()");
                            if (githubCommitsAPIResponse.totalCount > 0) {
                                mAdapter = new CommitsInfoAdapter(githubCommitsAPIResponse.items);
                                mAdapter.notifyDataSetChanged();
                                mRecyclerView.setAdapter(mAdapter);
                                dialog.cancel();
                            } else {
                                Toast.makeText(MainActivity.this, "Didn't found any result!", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
        } else {
            Toast.makeText(this, "Connect to internet and relaunch the app!", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean connectionCheck() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
