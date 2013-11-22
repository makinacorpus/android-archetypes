package ${package};

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ResourcesTest
{
	@Test
	public void testGetLibraryName()
	{
		final String libraryName = Resources.getLibraryName(Robolectric.application);

		Assert.assertNotNull(libraryName);
		Assert.assertEquals(Robolectric.application.getString(R.string.lib_name), libraryName);
	}

	@Test
	public void testGetLibraryVersion()
	{
		final String libraryVersion = Resources.getLibraryVersion(Robolectric.application);

		Assert.assertNotNull(libraryVersion);
		Assert.assertEquals(Robolectric.application.getString(R.string.lib_version), libraryVersion);
	}
}
