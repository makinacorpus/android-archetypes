package ${package}.test.ui;

import your.company.gmaps.R;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import android.test.ActivityInstrumentationTestCase2;

import ${package}.ui.MapsActivity;

public class MapsActivityTest extends ActivityInstrumentationTestCase2<MapsActivity>
{
	public MapsActivityTest()
	{
#set($Integer = 0)
#if($Integer.parseInt($minSdkVersion) > 7)
		super(MapsActivity.class);
#else
		super("${package}.ui", MapsActivity.class);
#end
	}

	public void testActivity()
	{
		MapsActivity mapsActivity = getActivity();

		assertNotNull(mapsActivity);

#if($Integer.parseInt($minSdkVersion) <= 10)
		GoogleMap googleMap = ((SupportMapFragment) mapsActivity.getSupportFragmentManager().findFragmentById(R.id.maps)).getMap();
#else
		GoogleMap googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.maps)).getMap();
#end

		assertNotNull(googleMap);
	}
}
