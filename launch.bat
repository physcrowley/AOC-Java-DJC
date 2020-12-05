@echo off
javac -d bin ^
days/Day.java ^
days/Day0/Day0.java ^
days/Day1/Day1.java ^
days/Day2/Day2.java ^
days/Day3/Day3.java ^
days/Day4/Day4.java ^
days/Day5/Day5.java ^
days/Day6/Day6.java ^
Main.java

@echo on
java -cp bin Main
