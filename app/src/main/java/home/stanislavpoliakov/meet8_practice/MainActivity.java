package home.stanislavpoliakov.meet8_practice;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<Integer> {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private final static int LOADER_ID = 1001;
    private Loader mLoader;
    private static final String TAG = "meet8_logs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Load
        initFragments();
    }

    private void initFragments() {
        mLoader = getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        mLoader.forceLoad();

        Bundle defaultBundle = new Bundle();
        defaultBundle.putInt("Color", 0);

        fragmentManager.beginTransaction()
                .add(R.id.frameLayout1, Fragment1.newInstance(), "fragment1")
                .add(R.id.frameLayout2, Fragment2.newInstance(), "fragment2")
                .add(R.id.frameLayout3, Fragment3.newInstance(), "fragment3")
                .commit();
    }

    @NonNull
    @Override
    public Loader<Integer> onCreateLoader(int id, @Nullable Bundle args) {
        mLoader = null;
        if (id == LOADER_ID) mLoader = new MyLoader(this);
        return mLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Integer> loader, Integer color) {
        Fragment1 fragment1 = (Fragment1) fragmentManager.findFragmentByTag("fragment1");
        fragment1.setColor(color);
        mLoader.forceLoad();
        //Log.d(TAG, "onLoadFinished: bundle = " + fragment1.getArguments());
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Integer> loader) {

    }
}
