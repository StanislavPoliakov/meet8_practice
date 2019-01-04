package home.stanislavpoliakov.meet8_practice;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {
    private static final String TAG = "meet8_logs";
    private List<Integer> idList = new ArrayList<>();
    //private HandlerThread myHandlerThread;
    //private Handler uiHandler, htHandler;

    private final static int LIST_CREATED = 1;

    public static Fragment3 newInstance() {
        return new Fragment3();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        startItemsThread();

        // Инициализируем RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        MyAdapter myAdapter = new MyAdapter(idList);
        recyclerView.setAdapter(myAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == LIST_CREATED) {
                Log.d(TAG, "handleMessage: received message!");
                idList.addAll((List<Integer>) msg.obj);
            }
        }
    };

    private void startItemsThread() {
        Log.d(TAG, "startItemsThread: fragment3 thread = " + Thread.currentThread().getName());

        new Thread(new Runnable() {
            private List<Integer> generatedIds = new ArrayList<>();

            @Override
            public void run() {
                while (generatedIds.size() <= 20) {
                    int randomResourceId = (int) Math.round(Math.random() * 20);
                    if (randomResourceId > 0) generatedIds.add(getResources().getIdentifier("image_" +
                            randomResourceId, "drawable", getContext().getPackageName()));
                }
                Message message = Message.obtain(uiHandler, LIST_CREATED, generatedIds);
                Fragment3.this.uiHandler.sendMessage(message);
                Log.d(TAG, "run: message sent from = " + Thread.currentThread().getName());
            }
        }).start();
    }

    /*private Runnable createIdList = new Runnable() {
        private int randomResourceId;

        @Override
        public void run() {
            while (idList.size() <= 20) {
                randomResourceId = (int) Math.round(Math.random() * 20);
                if (randomResourceId > 0) idList.add(getResources().getIdentifier("image_" +
                        randomResourceId, "drawable", getContext().getPackageName()));
            }
        }
    };*/
}
