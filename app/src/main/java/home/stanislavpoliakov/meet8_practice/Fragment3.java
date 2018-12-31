package home.stanislavpoliakov.meet8_practice;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {
    private List<Integer> idList = new ArrayList<>();
    private Handler handler;

    public static Fragment3 newInstance() {
        return new Fragment3();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //createIdList();
        return inflater.inflate(R.layout.fragment3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        handler = new Handler();
        handler.post(createIdList);
        MyAdapter myAdapter = new MyAdapter(idList);
        recyclerView.setAdapter(myAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    private Runnable createIdList = new Runnable() {
        private int randomResourceId;

        @Override
        public void run() {
            while (idList.size() <= 20) {
                randomResourceId = (int) Math.round(Math.random() * 20);
                if (randomResourceId > 0) idList.add(getResources().getIdentifier("image_" +
                        randomResourceId, "drawable", getContext().getPackageName()));
            }
        }
    };
}
