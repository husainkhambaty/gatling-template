## Gatling Template

A quick template for using Gatling and embedding in any CT (Continuous Test) pipeline. This uses the plugin
[Gatling plugin for Gradle](https://github.com/gatling/gatling-gradle-plugin  "Gatling plugin for Gradle")

It includes:

* a starter `build.gradle` Gradle wrapper
* a quick template simulation and chains
* some handy common functionality

### Pre-requisites
`git` must be installed and available in `$PATH` and a compatible JDK must be installed alongwith $JAVA_HOME configured.

## Quick Start

#### Clone this repo

```bash
git clone https://github.com/husainkhambaty/gatling-template.git
cd ./gatling-template
```
Use [wiremockstub](https://github.com/husainkhambaty/wiremockstub "wiremockstub") to stub a service or hit an actual endpoint. You can use application.conf to configure the endpoint.

#### Run the Test

```bash
./gradlew runLoadTest
```

#### Test configuration

You can provide the following configuration on the command line or extend and invoke it via any pipeline job.

- users : Number of users/threads
- duration : Test duration (in seconds)
- ramp : Ramp up and Ramp down (in seconds)

In addition to the above, the following configuration can be configured in the default `application.conf` file or in a custom configuration file and stored in the resources folder of the project.
- thinkTime : Any required ThinkTime
- paceFrom : Pacing minimum
- paceTo : Pacing max
- pacing : Constant pacing
- endpoint : Configure the top-level endpoint

To provide the custom configuration file you will need to provide it as a java parameter

``` bash
-Dconfiguration="test.conf"
```
