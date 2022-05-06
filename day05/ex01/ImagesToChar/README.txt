rm -rf target classes
mkdir target
find . -type f -name "*.java" > classes
javac -sourcepath src/java -d target @classes
rm -rf classes
cp -r src/resources target
jar cfm target/images-to-chars-printer.jar src/manifest.txt -C target/ .
java -jar target/images-to-chars-printer.jar . 0
