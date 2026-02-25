dir /s /b src\*.java > sources.txt

javac -cp lib\mysql-connector-j-9.5.0.jar -d out @sources.txt

mkdir out\Properties

copy src\Properties\SC.jpg out\Properties\

java -cp out;lib\mysql-connector-j-9.5.0.jar App