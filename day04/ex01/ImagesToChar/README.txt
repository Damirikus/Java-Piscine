To make a package do:
(from the ImageToChar directory)
1. Make a folder for target
mkdir target

2. Run compiler with target attribute
javac -d target src/java/edu/school21/printer/app/Main.java src/java/edu/school21/printer/logic/Converter.java

3. Copy resources
cp -R ./src/resources ./target/resources

4. Make jar
jar cvmf src/manifest.txt target/Main.jar -C target .

4. Run jar
java -jar target/Main.jar . 0