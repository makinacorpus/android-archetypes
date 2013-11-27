package ${package}.ui;

#set($Integer = 0)
import android.os.Bundle;
#if($Integer.parseInt($minSdkVersion) > 10)
import android.app.Activity;
#end
import android.util.Log;
#if($Integer.parseInt($minSdkVersion) > 10)
import android.view.MenuItem;
#end

#if($Integer.parseInt($minSdkVersion) <= 10)
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
#end

import ${package}.BuildConfig;
import ${package}.R;

#if($Integer.parseInt($minSdkVersion) <= 10)
public class MainActivity extends SherlockActivity
#else
public class MainActivity extends Activity
#end
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

#if($Integer.parseInt($minSdkVersion) <= 10)
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
#else
		getActionBar().setDisplayHomeAsUpEnabled(true);
#end

		if (BuildConfig.DEBUG)
		{
			Log.d(getClass().getName(), "onCreate");
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				finish();
				return true;
			default:
				return false;
		}
	}
}
