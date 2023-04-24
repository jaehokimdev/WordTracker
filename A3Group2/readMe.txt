********************************************************************************************************
********************************************************************************************************
*******************************************About this program*******************************************
********************************************************************************************************
********************************************************************************************************

- This program includes our personal implementation of the following classes
	1.- BSTree.java
	2.- BSTreeNode.java

- Four interfaces Iterator.java, BSTreeADT are also included. BSTree implements 
the BSTreeADT interface and the Iterator interface is used to override the repective 
methods in the BSTree class.

- The proper JUnit testing for BSTree is performed in the following file
	1.- BSTreeTest.java

- For run this application:
	java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f <output.txt>] 

1.  <input.txt> is the path and file name of input. Ex) res/textfile.txt

2.  Pf, pl and po are exclusive options  
	- pf to print words and file name
	- pl to print words, file name and numbers of the lines
	- po to print words, file name, numbers of the lines and number of occurrance 

3. <output.txt> is the path and file name of the output. Ex) res/output.txt

Ex) java -jar WordTracker.jar res/textfile.txt -po -f res/output.txt 

	