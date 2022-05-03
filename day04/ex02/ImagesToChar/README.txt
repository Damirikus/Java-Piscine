To make a package do:
(from the ImageToChar directory)

1. Make a folder for target
mkdir target

2. Compile the source code into the target folder
javac -cp lib/JColor-5.3.0.jar:lib/jcommander-1.82.jar -d target src/java/edu/school21/printer/app/Main.java src/java/edu/school21/printer/logic/Converter.java

3. Copy resources
cp -R ./src/resources ./target/resources

4. Go to target folder
cd target

5. Unzip jars into the target folder
jar xf ../lib/JColor-5.3.0.jar
jar xf ../lib/jcommander-1.82.jar

6. Return to ImageToChar folder
cd ..

7. Make jar
jar cvmf src/manifest.txt target/Main.jar -C target .

8. Run jar
java -jar target/Main.jar --white=GREEN --black=RED