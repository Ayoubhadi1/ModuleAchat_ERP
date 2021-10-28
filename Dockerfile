FROM alvistack/openjdk-16
ADD target/achat-1.0.0-SNAPSHOT.jar achat-1.0.0-SNAPSHOT.jar
CMD java -jar achat-1.0.0-SNAPSHOT.jar