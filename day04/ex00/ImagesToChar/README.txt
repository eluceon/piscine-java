rm -rf target classes
mkdir target
find . -type f -name "*.java" > classes
javac -sourcepath src/java -d target @classes
rm -rf classes
java -cp target edu.school21.printer.app.Program - + ./it.bmp