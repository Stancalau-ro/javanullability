# Java Nullability Generation Using Gradle #

A short project that exemplifies how you can generate java package-level nullability contracts
using a Gradle task.

### Package Level Nullability Contract ###

Following this StackOverflow thread: http://stackoverflow.com/questions/4963300/which-notnull-java-annotation-should-i-use
we see that it is possible to set a default nullability value for java element types.
The problem is that you get those nasty _package-info.java_ files everywhere in the project.

### Solution ###

To solve this, let's create a Gradle task that generates a separate folder tree to hold the
package-info files outside the main source folders. The added benefit is that Gradle updates
the files automatically when you add or remove a package to your project.

### How to Configure ###

* The nullability annotation you can find here: _\src\main\java\ro\stancalau\NotNullByDefault.java_
* In the project root, find the _package-info.template_ file
* In _gradle.build_ there is a task named _generateNullability_ that is linked to the _clean_ task
* In _gradle.build_, you need to set a variable named _generatedNullabilityDir_ which is the name of the generated folder. I suggest adding that folder to the VCS ignore file.
* To let the project take into account the generated sources, add the folder to _sourceSets.main.java_

### Prerequisites ###

* [Download and install Gradle](https://gradle.org/)
* Install JRE
* Ensure JAVA_HOME environment variable is set
* Ensure PATH environment variable contains path to Gradle
* Run `gradle clean build` in the project root to build the entire project


More information about this project can be found [here](http://stancalau.ro/java-package-nullability-contract/)