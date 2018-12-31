package home.stanislavpoliakov.meet8_practice;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class Fragment1 extends Fragment {
    private static final String TAG = "meet8_logs";
    private View currentView;

    public static Fragment1 newInstance() {
        return new Fragment1();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.currentView = view;
    }

    /**
     * Public метод установки цвета
     * @param color цвет, который необходимо установить в качестве фона
     */
    public void setColor(Integer color) {
        this.currentView.setBackgroundColor(color);
    }
}
