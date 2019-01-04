package home.stanislavpoliakov.meet8_practice;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class MyLoader extends AsyncTaskLoader<Integer> {
    private static final String TAG = "meet8_logs";

    // Описываем массив цветовых значений. Будем выбирать случайный индекс и передавать элемент массива
    // в качестве результата работы логики Loader'-а
    private int[] colors = {Color.BLACK, Color.GRAY, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN};

    public MyLoader(@NonNull Context context) {
        super(context);
    }

    /**
     * Метод работы Loader'-а. Выполнение в отдельном потоке
     * @return
     */
    @Nullable
    @Override
    public Integer loadInBackground() {
        int colorIndex = (int) Math.round(Math.random() * 7);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return colors[colorIndex];
    }
}
