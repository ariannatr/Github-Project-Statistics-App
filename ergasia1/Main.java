import java.io.*;
import java.util.*;
import operations_pack.*;

public class Main {

   public static void main(String []args) throws IOException{
     
      	if(args.length <2)
      	{	
      		System.err.println("Not enough arguments");
      		System.exit(1);
      	}
	int lines=0;
      	String filename=new String(args[0]);
      	File directory=new File(filename);
      	Operations op=new Operations(filename);
      	System.out.println("Files:"+op.get_files_num(directory));
      	op.get_lines(directory,filename,lines);
	System.out.println("Lines: "+lines);
      	System.out.println("Branches:"+op.get_branches(directory));
      	System.out.println("Tags:"+op.get_tags(directory));
      	System.out.println("Commiters:"+op.get_commiters(directory));
	System.out.println("Branches analytical :");	
	op.get_full_branches(directory);
	System.out.println("Commits statistics  :");
	op.get_commits(directory);
	op.get_percentage_changes(directory);
   }
}