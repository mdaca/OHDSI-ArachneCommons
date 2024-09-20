# ArachneCommons

The `ArachneCommons` module contains common code re-used between all Arachne components. It serves as a foundational library for various Arachne projects.

## Building and Installing

### To build and install the artifact to your local Maven repository:
```sh
mvn clean install
```
### To build and install the artifact to your remote Maven repository:
```sh
mvn clean deploy -s .m2/settings.xml -DaltDeploymentRepository=codeartifact::default::https://mdaca-201959883603.d.codeartifact.us-east-2.amazonaws.com/maven/OHDSI/
```
