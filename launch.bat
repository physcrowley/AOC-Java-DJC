@echo off
rem use "-Xlint:unchecked" to show additionnal compilation errors
javac -d bin ^ 
days/Day.java ^
days/Day0/Day0.java ^
days/Day1/Day1.java ^
days/Day2/Day2.java ^
days/Day3/Day3.java ^
days/Day4/Day4.java ^
days/Day5/Day5.java ^
days/Day6/Day6.java ^
days/Day7/Day7.java ^
days/Day8/Day8.java ^
days/Day9/Day9.java ^
days/Day11/Day11.java ^
Main.java

@echo on
java -cp bin Main
