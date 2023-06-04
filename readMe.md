## What project is it?

This program includes our personal implementation of the following classes
	1.- BSTree.java
	2.- BSTreeNode.java

Four interfaces Iterator.java, BSTreeADT are also included. BSTree implements 
the BSTreeADT interface and the Iterator interface is used to override the repective 
methods in the BSTree class.

The proper JUnit testing for BSTree is performed in the following file
	1.- BSTreeTest.java

## Languages

<p align="left"> <a href="https://www.java.com" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> <a href="https://git-scm.com/" target="_blank">

## How to run this application?

For run this application:
	java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f <output.txt>] 

1.  <input.txt> is the path and filename of the text file to be processed by the WordTracker program. 
2. 3 mutually exclusive options at command line:  
	-pf to print in alphabetic order all words along with the corresponding list of files in which the 
	  words occur. 
	-pl to print in alphabetic order all words along with the corresponding list of files and numbers 
	  of the lines in which the word occur. 
	-po to print in alphabetic order all words along with the corresponding list of files, numbers of 
	  the lines in which the word occur and the frequency of occurrence of the words. 
3. Optional argument to redirect of the report in the previous step to the path and filename specified
     in <output.txt>.

	
	
