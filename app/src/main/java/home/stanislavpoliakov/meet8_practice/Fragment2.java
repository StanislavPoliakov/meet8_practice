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
        return inflater.inflate(R.layout.fragment2, container, false);
    }

    /**
     * После отрисовки View инициализируем текстовое поле, инициализируем AsyncTask и запускаем
     * на исполнение
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.textView = view.findViewById(R.id.textView2);
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }

    /**
     * Логику асинхронной задачи описываем внутренним классом для легкого доступа к полям
     * класса-обертки. То есть для отрисовки изменений в UI-компоненте textView второго фрагмента
     */
    private class MyAsyncTask extends AsyncTask<Void, Integer, Integer> {

        /**
         * Метод обработки логики AsyncTask. Работа проводится в отдельном потоке
         * @param voids
         * @return
         */
        @Override
        protected Integer doInBackground(Void... voids) {
            int randomNumber = (int) Math.round(Math.random() * 100);
            publishProgress(randomNumber);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            doInBackground(); // Зацикливаем этот Thread
            return null;
        }

        /**
         * Метод, через который мы отрисовываем textView. Работа проводится в UI-потоке
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            Fragment2.this.textView.setText(String.valueOf(values[0]));
        }
    }
}
