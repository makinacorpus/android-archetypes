# android-archetypes
Provides Maven archetypes for Android to quickly bootstrap a Maven project and start developing an Android application.

All artifacts are based on the android-maven-plugin [http://code.google.com/p/maven-android-plugin/](http://code.google.com/p/maven-android-plugin/). It currently uses the 3.8.0 version.

## Before starting

### Android SDK
Download the latest Android SDK from
[http://developer.android.com/sdk/index.html](http://developer.android.com/sdk/index.html) and follow the instructions there.

From Android SDK, install at least the following packages :

* Tools (all packages)
* Android 4.4 (API 19)
* Android 2.3.3 (API 10)
* Extras (all packages according to your platform)

Or from the command line (headless environment) :

	android update sdk --no-ui --all --filter \
		tools,\
		platform-tools,\
		build-tools-19.0.0

	android update sdk --no-ui --all --filter \
		extra-android-m2repository,\
		extra-android-support,\
		extra-google-admob_ads_sdk,\
		extra-google-analytics_sdk_v2,\
		extra-google-google_play_services_froyo,\
		extra-google-google_play_services,\
		extra-google-m2repository,\
		extra-google-play_apk_expansion,\
		extra-google-play_billing,\
		extra-google-play_licensing,\
		extra-google-webdriver

	android update sdk --no-ui --all --filter \
		android-19,\
		sysimg-19,\
		addon-google_apis-google-19

	android update sdk --no-ui --all --filter \
		android-10,\
		sysimg-10,\
		addon-google_apis-google-10

where options :

* `--no-ui` : Updates from command-line (does not display the GUI)
* `--all` : Includes all packages (such as obsolete and non-dependent ones)
* `--filter`: A filter that limits the update to the specified types of packages

### Maven
Install [Maven](http://maven.apache.org/download.cgi) (**3.1.1 or higher is required**).

### Maven Android SDK Deployer
Install [maven-android-sdk-deployer](https://github.com/mosabua/maven-android-sdk-deployer) :

	git clone https://github.com/mosabua/maven-android-sdk-deployer.git
	cd maven-android-sdk-deployer/
	mvn install -P 2.3.3
	mvn install -P 4.4
	cd extras/compatibility-v4/
	mvn clean install

## Installation
Use Maven to install these archetypes in your local Maven repository :

	git clone https://github.com/makinacorpus/android-archetypes.git
	cd android-archetypes/
	mvn clean install

## android-quickstart archetype
This archetype creates a simple Android application ready to be deployed on an Android device.
To initiate this Android project :

	mvn archetype:generate \
		-DarchetypeArtifactId=android-quickstart \
		-DarchetypeGroupId=com.makina.android.archetypes \
		-DarchetypeVersion=0.0.9 \
		-DarchetypeCatalog=local \
		-DarchetypeRepository=local \
		-DgroupId=your.company \
		-DartifactId=my-android-application \
		-Dpackage=your.company.myapp \
		-Dversion=0.1 \
		-DinteractiveMode=false

where properties :

* `-DgroupId` : your Maven project groupId
* `-DartifactId` : the name of your Maven project
* `-Dversion` : the first version number of your Maven project

You can define three optional properties :

* `-Dpackage` : define the package used by the application (default : the given `groupId`)
* `-DminSdkVersion` : the minimum API Level required for the application to run (default : 10, Android 2.3.3)
* `-DtargetSdkVersion` : the targeted Android platform version to use (default : 19, Android 4.4)

Once generated, your application is ready to be built. Start an android emulator or plug an Android dev phone ([USB debugging must be enabled in Developer options settings](http://developer.android.com/tools/device.html#setting-up)) and execute the following commands :

	cd my-android-application
	mvn clean install

or with Gradle :

	cd my-android-application
	gradle clean build

To deploy and launch the application with Maven :

	mvn clean install android:deploy android:run

or with Gradle (only deploy) :

	gradle installDebug

To undeploy the application with Maven :

	mvn android:undeploy

This generated project use [JUnit](https://github.com/junit-team/junit) and [Robolectric](http://robolectric.org/) as default unit test framework.

## android-simple-project archetype
This archetype creates a multi-module project containing the Android application and a project for testing this application (instrumentation tests) :

	mvn archetype:generate \
		-DarchetypeArtifactId=android-simple-project \
		-DarchetypeGroupId=com.makina.android.archetypes \
		-DarchetypeVersion=0.0.9 \
		-DarchetypeCatalog=local \
		-DarchetypeRepository=local \
		-DgroupId=your.company \
		-DartifactId=my-android-application \
		-Dpackage=your.company.myapp \
		-Dversion=0.1 \
		-DinteractiveMode=false

where properties :

* `-DgroupId` : your Maven project groupId
* `-DartifactId` : the name of your Maven project
* `-Dversion` : the first version number of your Maven project

You can define three optional properties :

* `-Dpackage` : define the package used by the application (default : the given `groupId`)
* `-DminSdkVersion` : the minimum API Level required for the application to run (default : 10, Android 2.3.3)
* `-DtargetSdkVersion` : the targeted Android platform version to use (default : 19, Android 4.4)

Once generated, your application is ready to be built and tested. Start an android emulator or plug an Android dev phone ([USB debugging must be enabled in Developer options settings](http://developer.android.com/tools/device.html#setting-up)) and execute the following commands :

	cd my-android-application
	mvn clean install

To deploy and launch the application with Maven :

	cd my-android-application
	mvn clean install android:deploy android:run

This generated project use [JUnit](https://github.com/junit-team/junit) and [Robolectric](http://robolectric.org/) as default unit test framework.

To undeploy all installed applications (main application and instrumentation tests application) with Maven :

	cd my-android-application
	mvn android:undeploy
	cd ../my-android-application-instrumentation
	mvn android:undeploy

or from the parent pom (the application package name must be specified) :

	mvn android:undeploy -Dandroid.package=your.company.myapp
	mvn android:undeploy -Dandroid.package=your.company.myapp.test

## android-library-quickstart archetype
This archetype creates a simple Android library project ready to be used with another Android project application.
To initiate this Android library project :

	mvn archetype:generate \
		-DarchetypeArtifactId=android-library-quickstart \
		-DarchetypeGroupId=com.makina.android.archetypes \
		-DarchetypeVersion=0.0.9 \
		-DarchetypeCatalog=local \
		-DarchetypeRepository=local \
		-DgroupId=your.company \
		-DartifactId=my-android-library \
		-Dpackage=your.company.mylib \
		-Dversion=0.1 \
		-DinteractiveMode=false

where properties :

* `-DgroupId` : your Maven project groupId
* `-DartifactId` : the name of your Maven project
* `-Dversion` : the first version number of your Maven project

You can define three optional properties :

* `-Dpackage` : define the package used by the library (default : the given `groupId`)
* `-DminSdkVersion` : the minimum API Level required for the library (default : 10, Android 2.3.3)
* `-DtargetSdkVersion` : the targeted Android platform version to use (default : 19, Android 4.4)

Once generated, your library is ready to be built :

	cd my-android-library
	mvn clean install

This generated project use [JUnit](https://github.com/junit-team/junit) and [Robolectric](http://robolectric.org/) as default unit test framework.

## android-library-project archetype
This archetype creates a multi-module project containing the Android application, an Android library project and a project for testing this application and its library (instrumentation tests).
This archetype may be use to develop a complete Android application using a custom Android library or to develop an Android library with an example application using this library :

	mvn archetype:generate \
		-DarchetypeArtifactId=android-library-project \
		-DarchetypeGroupId=com.makina.android.archetypes \
		-DarchetypeVersion=0.0.9 \
		-DarchetypeCatalog=local \
		-DarchetypeRepository=local \
		-DgroupId=your.company \
		-DartifactId=my-android-library-project \
		-Dpackage=your.company.mylib \
		-Dversion=0.1 \
		-DinteractiveMode=false

where properties :

* `-DgroupId` : your Maven project groupId
* `-DartifactId` : the name of your Maven project
* `-Dversion` : the first version number of your Maven project

You can define three optional properties :

* `-Dpackage` : define the package used by the library (default : the given `groupId`)
* `-DminSdkVersion` : the minimum API Level required for the library (default : 10, Android 2.3.3)
* `-DtargetSdkVersion` : the targeted Android platform version to use (default : 19, Android 4.4)

Once generated, your application is ready to be built and tested. Start an android emulator or plug an Android dev phone ([USB debugging must be enabled in Developer options settings](http://developer.android.com/tools/device.html#setting-up)) and execute the following commands :

	cd my-android-library-project
	mvn clean install

To deploy and launch the application with Maven :

	cd my-android-library-project
	mvn clean install android:deploy android:run

This generated project use [JUnit](https://github.com/junit-team/junit) and [Robolectric](http://robolectric.org/) as default unit test framework.

## Perform a release build (signed and zipaligned APK)
**android-quickstart**, **android-simple-project** and **android-library-project** archetypes offers also a `release` profile to generate a signed and zipaligned APK.
By default the application is built in "debug mode". This means the generated class constant `BuildCongig.DEBUG` and Android Manifest attribute `android:debuggable` are set to `true`.
The APK is signed with the default debug key (`~/.android/debug.keystore`).

A release type build would be necessary for publication of the application to the [Play Store](https://play.google.com/store?hl=fr) and the necessary steps for it is configured. The following preparation for the execution is necessary :

* Create your private key following the instructions at [http://developer.android.com/tools/publishing/app-signing.html#cert](http://developer.android.com/tools/publishing/app-signing.html#cert)
* Create a `sign.properties` file in the root folder of your application project (not the instrumentation tests project) like this :

		sign.keystore=/absolute/path/to/your/keystore
		sign.alias=youralias
		sign.keypass=keypass
		sign.storepass=storepass

After this preparation, the release build can be invoked with the following command :

	mvn clean install -P release

which will in turn sign and zipalign the APK.

Instead of using a properties file (`sign.properties`), you may also define a Maven profile. Add a profile to your `~/.m2/settings.xml` file containing the signing informations :

```xml
<profile>
	<id>android-release</id>
	<properties>
		<sign.keystore>/absolute/path/to/your/keystore</sign.keystore>
		<sign.alias>key alias</sign.alias>
		<sign.keypass>key password</sign.keypass>
		<sign.storepass>keystore password</sign.storepass>
	</properties>
</profile>
```

And invoke the following command :

	mvn clean install -P android-release,release

## Licensing
Licensed under the Apache Software License, Version 2.0.

## History
Inspired from [android-archetypes Github project](https://github.com/akquinet/android-archetypes) created by [akquinet AG](http://www.akquinet.de/en.html).
