@echo off
javac -d bin ^
days/Day.java ^
days/Day0/Day0.java ^
days/Day1/Day1.java ^
Main.java

@echo on
java -cp bin Main
