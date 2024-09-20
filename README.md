# ArachneCommons
Commons module contains common code re-used between all Arachne components

Build and install artifact to local Maven repository:
```:shell 
mvn clean install

Build and install and deploy artifact to remote AWS CodeArtifact Maven repository:
```:shell
mvn clean deploy -s .m2/settings.xml -DaltDeploymentRepository=codeartifact::default::https://mdaca-201959883603.d.codeartifact.us-east-2.amazonaws.com/maven/OHDSI/
