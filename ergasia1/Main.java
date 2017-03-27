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
      	String filename=new String(args[0]);
      	File directory=new File(filename);
      	Operations op=new Operations(filename);
        Myhtml html=new Myhtml(args[1]);

      	html.print_files(op.get_files_num(directory));
        html.print_lines(op.get_lines(directory,filename));
	    html.print_branches_tags_commiters(op.get_branches(directory),op.get_tags(directory),op.get_commiters(directory));
        html.start_full_branches();
        System.out.println("Branches analytical :");	
	   op.get_full_branches(directory,html);
       html.end_table();
	   System.out.println("Commits statistics  :");
	   op.get_commits(directory,html);
	   op.get_percentage_changes(directory,html);

       System.out.println("Commits statistics per day month year");
        op.get_commit_statistics_per_author(directory,html);

       html.close_html();
   
   }
}
