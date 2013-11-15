# android-archetypes
Provides Maven archetypes for Android to quickly bootstrap a Maven project and start developing an Android application.

All artifacts are based on the android-maven-plugin [http://code.google.com/p/maven-android-plugin/](http://code.google.com/p/maven-android-plugin/). It currently uses the 3.7.0 version.

## Before starting
Download the latest Android SDK from
[http://developer.android.com/sdk/index.html](http://developer.android.com/sdk/index.html) and follow the instructions there.

From Android SDK, install at least the following packages :

* Tools (all packages)
* Android 4.3 (API 18)
* Android 2.3.3 (API 10)
* Extras (all packages according to your platform)

Install [maven-android-sdk-deployer](https://github.com/mosabua/maven-android-sdk-deployer) :

	git clone https://github.com/mosabua/maven-android-sdk-deployer.git
	cd maven-android-sdk-deployer/
	mvn install -P 2.3.3
	mvn install -P 4.3

## Installation
Use Maven to install these archetypes in your local Maven repository :

	mvn clean install

## android-quickstart archetype
This archetype creates a simple Android application ready to be deployed on an Android device. To initiate this Android project :

	mvn archetype:generate \
		-DarchetypeArtifactId=android-quickstart \
		-DarchetypeGroupId=com.makina.android.archetypes \
		-DarchetypeVersion=0.0.5 \
		-DarchetypeCatalog=local \
		-DarchetypeRepository=local \
		-DgroupId=your.company \
		-DartifactId=my-android-application \
		-Dversion=0.1 \
		-DinteractiveMode=false

where properties :

* `-DgroupId` : your Maven project groupId
* `-DartifactId` : the name of your Maven project
* `-Dversion` : the first version number of your Maven project

You can define two optional properties :
* `-DminSdkVersion` : the minimum API Level required for the application to run (default : 10, Android 2.3.3)
* `-DtargetSdkVersion` : the targeted Android platform version to use (default : 18, Android 4.3)

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

## android-simple-project
This archetype creates a multi-module project containing the Android application and a project for testing this application (instrumentation tests) :

	mvn archetype:generate \
		-DarchetypeArtifactId=android-simple-project \
		-DarchetypeGroupId=com.makina.android.archetypes \
		-DarchetypeVersion=0.0.5 \
		-DarchetypeCatalog=local \
		-DarchetypeRepository=local \
		-DgroupId=your.company \
		-DartifactId=my-android-application \
		-Dversion=0.1 \
		-DinteractiveMode=false

where properties :

* `-DgroupId` : your Maven project groupId
* `-DartifactId` : the name of your Maven project
* `-Dversion` : the first version number of your Maven project

You can define two optional properties :
* `-DminSdkVersion` : the minimum API Level required for the application to run (default : 10, Android 2.3.3)
* `-DtargetSdkVersion` : the targeted Android platform version to use (default : 18, Android 4.3)

The Android application package use by default the given `groupId`.

Once generated, your application is ready to be built and tested. Plug an Android dev phone ([USB debugging must be enabled in Developer options settings](http://developer.android.com/tools/device.html#setting-up)) and execute the following commands :

	cd my-android-application
	mvn clean install

To deploy and launch the application with Maven :

	cd my-android-application
	mvn clean install android:deploy android:run