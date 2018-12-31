package home.stanislavpoliakov.meet8_practice;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class Fragment2 extends Fragment {
    private TextView textView;
    private static final String TAG = "meet8_logs";

    public static Fragment2 newInstance() {
        return new Fragment2();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.textView = view.findViewById(R.id.textView2);
        Log.d(TAG, "onViewCreated: ");
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }

    private class MyAsyncTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            int randomNumber = (int) Math.round(Math.random() * 100);
            publishProgress(randomNumber);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            doInBackground();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Fragment2.this.textView.setText(String.valueOf(values[0]));
        }
    }
}
