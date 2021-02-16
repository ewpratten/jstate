# JState 

[![Documentation](https://img.shields.io/badge/-documentation-blue)](https://ewpratten.retrylife.ca/jstate) ![Build library](https://github.com/Ewpratten/jstate/workflows/Build%20library/badge.svg)

JState is a Java library for simple Finite State Machines. I originally wrote this library under the name [libKontrol](https://github.com/frc5024/lib5k/tree/master/libKontrol) for [@frc5024](https://github.com/frc5024). This new library is part of an effort to save my work at Raider Robotics as a collection of Java libraries that do not depend on any of the team's internal infrastructure.

The goal of JState is to provide a very simple way to quickly implement Finite State Machines in Java code without any inner classes. The library was designed and implemented in 2019 as a way to speed up development times. JState has been used on-robot at Raider Robotics to power many mission-critical systems autonomously and precisely.

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
    implementation 'ca.retrylife:jstate:1.+'
    implementation 'ca.retrylife:jstate:1.+:sources'
    implementation 'ca.retrylife:jstate:1.+:javadoc'
}
```

## Usage

An example program that toggles a boolean every two iterations can be found [here](https://github.com/Ewpratten/jstate/blob/master/src/test/java/ca/retrylife/jstate/UsageTest.java).

## How to push a release

Pushing a release is simple. Clone this repo, go to master, and run:

```sh
git tag -a <version> -m "<message>"
git push origin <version>
```
