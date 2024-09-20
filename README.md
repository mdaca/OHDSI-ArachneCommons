# ArachneCommons

The `ArachneCommons` module contains common code re-used between all Arachne components. It serves as a foundational library for various Arachne projects.

## Building and Installing

### To build and install the artifact to your local Maven repository:

```shell
mvn clean install

#### To build, install, and deploy the artifact to the remote AWS CodeArtifact Maven repository:

mvn clean deploy -s .m2/settings.xml -DaltDeploymentRepository=codeartifact::default::https://mdaca-201959883603.d.codeartifact.us-east-2.amazonaws.com/maven/OHDSI/
