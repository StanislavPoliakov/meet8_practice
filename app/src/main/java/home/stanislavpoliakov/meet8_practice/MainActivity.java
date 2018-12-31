package home.stanislavpoliakov.meet8_practice;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<Integer> {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private final static int LOADER_ID = 1001;
    private Loader mLoader;
    private static final String TAG = "meet8_logs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLoader();
        initFragments();
    }

    /**
     * Инициализиурем Loader
     */
    private void initLoader() {
        mLoader = getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        mLoader.forceLoad();
    }

    /**
     * Инициализиурем фрагменты программно
     */
    private void initFragments() {
        fragmentManager.beginTransaction()
                .add(R.id.frameLayout1, Fragment1.newInstance(), "fragment1")
                .add(R.id.frameLayout2, Fragment2.newInstance(), "fragment2")
                .add(R.id.frameLayout3, Fragment3.newInstance(), "fragment3")
                .commitNow();
    }

    @NonNull
    @Override
    public Loader<Integer> onCreateLoader(int id, @Nullable Bundle args) {
        mLoader = null;
        if (id == LOADER_ID) mLoader = new MyLoader(this);
        return mLoader;
    }

    /**
     * Метод результата работы Loader'-а. Получаем данные, достаем фрагмент по TAG'-у,
     * вызваем метод фрагмента для изменения цвета
     * @param loader
     * @param color
     */
    @Override
    public void onLoadFinished(@NonNull Loader<Integer> loader, Integer color) {
        Fragment1 fragment1 = (Fragment1) fragmentManager.findFragmentByTag("fragment1");
        fragment1.setColor(color);
        mLoader.forceLoad(); // Зацикливаем логику выбора случайного цвета
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Integer> loader) {

    }
}
