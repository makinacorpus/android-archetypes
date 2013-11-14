package ${package}.ui;

import android.app.Activity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import ${package}.ui.MainActivity;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest
{
	@Test
	public void testActivity()
	{
		Activity activity = Robolectric.buildActivity(MainActivity.class).create().get();

		Assert.assertTrue(activity != null);
	}
}
