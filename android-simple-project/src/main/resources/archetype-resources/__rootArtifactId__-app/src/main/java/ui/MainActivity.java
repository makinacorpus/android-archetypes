package ${package}.ui;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

import ${package}.BuildConfig;
import ${package}.R;

public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (BuildConfig.DEBUG)
		{
			Log.d(getClass().getName(), "onCreate");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}
}
