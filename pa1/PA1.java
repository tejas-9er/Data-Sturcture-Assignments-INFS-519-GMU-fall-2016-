/**
 * Tejas Gandre
 * G01034008
 * INFS 519
 * Fall 2016
 */  
  
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
 
/**
 * Program to inspect the CS honor code.
 */ 

// Source for CS Honor Code : https://cs.gmu.edu/wiki/pmwiki.php/HonorCode/CSHonorCodePolicies

  public class PA1 
{
public static void main (String[]args) throws IOException 
  {
int n;
String text =
"All CS students must adhere to the GMU Honor Code. In addition to this honor code, the computer science department has further honor code policies regarding programming projects, which is detailed below. Your instructor may state further policies for his or her class as well.\n\nUnless otherwise stated, at the time that an assignment or project is given, all work handed in for credit is to be the result of individual effort. (In some classes group work is encouraged; if so, that will be made explicit when the assignment is given.)\nYou (or your group, if a group assignment) may:\n\n    seek assistance in learning to use the computing facilities;\n    seek assistance in learning to use special features of a programming language's implementation;\n    seek assistance in determining the syntactic correctness of a particular programming language statement or construct;\n    seek an explanation of a particular syntactic error;\n    seek explanations of compilation or run-time error messages \n\nYou (or your group, if a group assignment) may not seek assistance from anyone else, other than your instructor or teaching assistant:\n\n    in designing the data structures used in your solution to a problem;\n    in designing the algorithm to solve a problem;\n    in modifying the design of an algorithm determined to be faulty;\n    in implementing your algorithm in a programming language;\n    in correcting a faulty implementation of your algorithm\n    in determining the semantic correctness of your algorithm. \n\nUnless permission to do so is granted by the instructor, you (or your group, if a group assignment) may not\n\n    give a copy of your work in any form to another student;\n    receive a copy of someone else's work in any form;\n    attempt to gain access to any files other than your own or those authorized by the instructor or computer center;\n    inspect or retain in your possession another student's work, whether it was given to you by another student, it was found after other student has discarded his/her work, or it accidently came into your possession;\n    in any way collaborate with someone else in the design or implementation or logical revision of an algorithm;\n    present as your own, any algorithmic procedure which is not of your own or of the instructor's design, or which is not part of the course's required reading (if you modify any procedure which is presented in the course's texts but which is not specifically mentioned in class or covered in reading assignments, then a citation with page number must be given);\n    incorporate code written by others (such as can be found on the Internet); \n\nYou must:\n\n    report any violations of II and III that you become aware of;\n    if part of a group assignment, be an equal \"partner\" in your group's activities and productions, and represent accurately the level of your participation in your group's activities and productions. \n";
     
      //A do-while loop to repeat the program until exit 
      do
      {
System.out.println 
	  ("--------------------------------------------------------\nSelect an option from the following menu:\n1) View contents: You (or your group, if a group assignment) may...\n2) View contents: You (or your group, if a group assignment) may not seek assistance from anyone else, other than your instructor or teaching assistant...\n3) View contents: Unless permission to do so is granted by the instructor, you (or your group, if a group assignment) may not...\n4) View contents: You must...\n5) Print the honor code to output.txt\n6) Quit\n--------------------------------------------------------");
	
	  // reading the users choice
	Scanner reader = new Scanner (System.in);
	System.out.println ("PLease enter your choice");
	n = reader.nextInt ();
	
	  // Using switch case for providing output according to the choice entered
	  switch (n)
	  {
case 1:System.out.println 
	      ("You (or your group, if a group assignment) may:\n\nseek assistance in learning to use the computing facilities;\nseek assistance in learning to use special features of a programming language's implementation;\nseek assistance in determining the syntactic correctness of a particular programming language statement or construct;\nseek an explanation of a particular syntactic error;\nseek explanations of compilation or run-time error messages ");
break;
	    
case 2:System.out.println 
	      ("You (or your group, if a group assignment) may not seek assistance from anyone else, other than your instructor or teaching assistant:\n\n    in designing the data structures used in your solution to a problem;\n    in designing the algorithm to solve a problem;\n    in modifying the design of an algorithm determined to be faulty;\n    in implementing your algorithm in a programming language;\n    in correcting a faulty implementation of your algorithm\n    in determining the semantic correctness of your algorithm. ");
break;
	    

case 3:System.out.println 
	      ("Unless permission to do so is granted by the instructor, you (or your group, if a group assignment) may not\n\n    give a copy of your work in any form to another student;\n    receive a copy of someone else's work in any form;\n    attempt to gain access to any files other than your own or those authorized by the instructor or computer center;\n    inspect or retain in your possession another student's work, whether it was given to you by another student, it was found after other student has discarded his/her work, or it accidently came into your possession;\n    in any way collaborate with someone else in the design or implementation or logical revision of an algorithm;\n    present as your own, any algorithmic procedure which is not of your own or of the instructor's design, or which is not part of the course's required reading (if you modify any procedure which is presented in the course's texts but which is not specifically mentioned in class or covered in reading assignments, then a citation with page number must be given);\n    incorporate code written by others (such as can be found on the Internet);");
break;

case 4:System.out.println 
	      ("You must:\n\n    report any violations of II and III that you become aware of;\n    if part of a group assignment, be an equal \"partner\" in your group's activities and productions, and represent accurately the level of your participation in your group's activities and productions.");
break;
	    
//printing honor code in the file output.txt
case 5:System.out.println 
		("Honor code has been printed in the output.txt file");
	    try 
	    {
	     PrintWriter out = new PrintWriter ("output.txt");
	     out.println (text);
	     out.close ();
	    }
	    catch (IOException e) 
	    {
	     e.printStackTrace ();
	    }
break;
	  
//exiting from the program     
case 6:System.out.println
		("Thank You");
break;
 
//If wrong choice entered           
default:System.out.println
		("The coice you have entered is invalid.");
break;
}
}while (n != 6);
}
}


 
 
 
 
