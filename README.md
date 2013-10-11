# android-archetypes
Provides Maven archetypes for Android to quickly bootstrap a Maven project and start developing an Android application (for instance juste one).

All artifacts are based on the android-maven-plugin [http://code.google.com/p/maven-android-plugin/](http://code.google.com/p/maven-android-plugin/). It currently uses the 3.7.0 version.

## Before starting
Download the latest Android SDK from
[http://developer.android.com/sdk/index.html](http://developer.android.com/sdk/index.html) and follow the instructions there.

From Android SDK, install at least the following packages :

* Tools (all packages)
* Latest Android API (currently : 18)
* Android 4.0.3 (API 15)
* Android 2.3.3 (API 10)
* Extras (all packages according to your platform)

Install [maven-android-sdk-deployer](https://github.com/mosabua/maven-android-sdk-deployer) :

	git clone https://github.com/mosabua/maven-android-sdk-deployer.git
	cd maven-android-sdk-deployer/
	mvn install -P 2.3.3
	mvn install -P 4.0.3
	mvn install -P 4.3

## Installation
Use Maven to install these archetypes in your local repository :

	mvn clean install

## android-quickstart archetype
This archetype creates a simple Android application ready to be deployed on an Android device. To initiate this Android project :

	mvn archetype:generate \
		-DarchetypeArtifactId=android-quickstart \
		-DarchetypeGroupId=com.makina.android.archetypes \
		-DarchetypeVersion=0.0.2 \
		-DarchetypeCatalog=local \
		-DgroupId=your.company \
		-DartifactId=my-android-application \
		-Dversion=0.1 \
		-Dplatform=15 \
		-DinteractiveMode=false

where properties :

* `-DgroupId` : your Maven project groupId
* `-DartifactId` : the name of your Maven project
* `-Dversion` : the first version number of your Maven project
* `-Dplatform` : the targeted Android platform version to use (default : 15, Android 4.0.3)

The Android application package use by default the given `groupId`.

Once generated, your application is ready to be built. Plug an Android dev phone ([USB debugging must be enabled in Developer options settings](http://developer.android.com/tools/device.html#setting-up)) and execute the following commands :

	cd my-android-application
	mvn clean install

or with Gradle :

	cd my-android-application
	gradle clean build

To deploy and launch the application with Maven :

	mvn clean install android:deploy android:run

or with Gradle (only deploy) :

	gradle installDebug
