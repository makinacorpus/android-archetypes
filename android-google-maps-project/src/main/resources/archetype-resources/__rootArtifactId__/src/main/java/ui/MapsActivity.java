package ${package}.ui;

#set($Integer = 0)
#if($Integer.parseInt($minSdkVersion) > 10)
import android.app.Activity;
#end
import android.location.Location;
import android.os.Bundle;
#if($Integer.parseInt($minSdkVersion) <= 10)
import android.support.v4.app.FragmentActivity;
#end
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
#if($Integer.parseInt($minSdkVersion) > 10)
import com.google.android.gms.maps.MapFragment;
#else
import com.google.android.gms.maps.SupportMapFragment;
#end

import ${package}.BuildConfig;
import ${package}.R;

#if($Integer.parseInt($minSdkVersion) <= 10)
public class MapsActivity extends FragmentActivity implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener, OnMyLocationButtonClickListener
#else
public class MapsActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener, OnMyLocationButtonClickListener
#end
{
	// may be null if the Google Play services APK is not available
	private GoogleMap mGoogleMap;
	private LocationClient mLocationClient;

	private static final LocationRequest REQUEST = LocationRequest.create()
			.setInterval(5000) // 5 seconds
			.setFastestInterval(16) // 16ms = 60fps
			.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_maps);

		if (BuildConfig.DEBUG)
		{
			Log.d(getClass().getName(), "onCreate");
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();

		setUpMapIfNeeded();
		setUpLocationClientIfNeeded();

		mLocationClient.connect();
	}

	@Override
	protected void onPause()
	{
		super.onPause();

		if (mLocationClient != null)
		{
			mLocationClient.disconnect();
		}
	}

	/**
	 * Implementation of {@link ConnectionCallbacks}.
	 */
	@Override
	public void onConnected(Bundle connectionHint)
	{
		mLocationClient.requestLocationUpdates(
				REQUEST,
				this // LocationListener
		);
	}

	/**
	 * Implementation of {@link ConnectionCallbacks}.
	 */
	@Override
	public void onDisconnected()
	{
		// do nothing
	}

	/**
	 * Implementation of {@link OnConnectionFailedListener}.
	 */
	@Override
	public void onConnectionFailed(ConnectionResult result)
	{
		// do nothing

		Log.w(getClass().getName(), "onConnectionFailed : " + result.toString());
	}

	/**
	 * Implementation of {@link LocationListener}.
	 */
	@Override
	public void onLocationChanged(Location location)
	{
		if (BuildConfig.DEBUG)
		{
			Log.d(getClass().getName(), "onLocationChanged : " + location.toString());
		}
	}

	/**
	 * Implementation of {@link OnMyLocationButtonClickListener}.
	 */
	@Override
	public boolean onMyLocationButtonClick()
	{
		// Return false so that we don't consume the event and the default behavior still occurs (the camera animates to the user's current position)
		return false;
	}

	/**
	 * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly installed) and the map has not already been instantiated.
	 * This will ensure that we only ever call {@link #setUpMap()} once when {@link #gMap} is not null.
	 * <p>
#if($Integer.parseInt($minSdkVersion) <= 10)
	 * If it isn't installed {@link SupportMapFragment} (and {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to install/update the Google Play services APK on their device.
#else
	* If it isn't installed {@link MapFragment} (and {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to install/update the Google Play services APK on their device.
#end
	 * <p>
	 * A user can return to this FragmentActivity after following the prompt and correctly installing/updating/enabling the Google Play services.
	 * Since the FragmentActivity may not have been completely destroyed during this process (it is likely that it would only be stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this method in {@link #onResume()} to guarantee that it will be called.
	 */
	private void setUpMapIfNeeded()
	{
		// do a null check to confirm that we have not already instantiated the map
		if (mGoogleMap == null)
		{
#if($Integer.parseInt($minSdkVersion) <= 10)
			// try to obtain the map from the SupportMapFragment
			mGoogleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps)).getMap();
#else
			// try to obtain the map from the MapFragment
			mGoogleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.maps)).getMap();
#end

			// check if we were successful in obtaining the map.
			if (mGoogleMap != null)
			{
				mGoogleMap.setMyLocationEnabled(true);
				mGoogleMap.setOnMyLocationButtonClickListener(this);
			}
		}
	}

	private void setUpLocationClientIfNeeded()
	{
		if (mLocationClient == null)
		{
			mLocationClient = new LocationClient(
					getApplicationContext(),
					this, // ConnectionCallbacks
					this // OnConnectionFailedListener
			);
		}
	}
}
