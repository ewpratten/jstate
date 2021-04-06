# JState 

[![Documentation](https://img.shields.io/badge/-documentation-blue)](https://ewpratten.retrylife.ca/jstate) ![Build library](https://github.com/Ewpratten/jstate/workflows/Build%20library/badge.svg)

JState is a Java library for executing stateful tasks in the style of a Finite State Machine. I originally wrote this library under the name [libKontrol](https://github.com/frc5024/lib5k/tree/master/libKontrol) for [@frc5024](https://github.com/frc5024). This new library is part of an effort to save my work at Raider Robotics as a collection of Java libraries that do not depend on any of the team's internal infrastructure.

## Using in your project

**Step 1.** Add the RetryLife maven server to your `build.gradle` file:

```groovy
repositories {
    maven { 
        name 'retrylife-release'
        url 'https://release.maven.retrylife.ca/' 
    }
}
```

**Step 2.** Add this library as a dependency:

```groovy
dependencies {
    implementation 'ca.retrylife:jstate:2.+'
    implementation 'ca.retrylife:jstate:2.+:sources'
    implementation 'ca.retrylife:jstate:2.+:javadoc'
}
```

## How to push a release

Pushing a release is simple. Clone this repo, go to master, and run:

```sh
git tag -a <version> -m "<message>"
git push origin <version>
```
