package operations_pack;

import java.io.*;
import java.util.*;


public class Operations{
	Integer total_files;
	String name;
	public Operations(String filename)
	{
		total_files=0;
		name=new String(filename);	
	}

	public int get_files_num(File directory) throws IOException
	{
		File contents[]=directory.listFiles();
      	Integer total_num=0;
    	total_files=total_num;
    	Process p=Runtime.getRuntime().exec("git ls-files ",null,directory);
      	InputStreamReader isr= new InputStreamReader(p.getInputStream());
      	BufferedReader read=new BufferedReader(isr);
      	String result;
      	while((result=read.readLine())!=null)
      	{  
      		//System.out.println("File: "+result);
      		total_num++;	
      	}	
      	//System.out.println("Files "+total_num);
      	return total_num;
	}

	public int get_lines(File directory,String dirname) throws IOException
	{
		Integer total_num=0;
            Integer templines;
      	Process p=Runtime.getRuntime().exec("git ls-files ",null,directory);
            InputStreamReader isr= new InputStreamReader(p.getInputStream());
            BufferedReader read=new BufferedReader(isr);
            String result;
            while((result=read.readLine())!=null)
            {  
                  String filename=new String(dirname+"/"+result);
                 
                  File fcontnet=new File(filename);
                  LineNumberReader lnr=new LineNumberReader( new FileReader(fcontnet));
                  lnr.skip(Long.MAX_VALUE);
                  templines=lnr.getLineNumber()+1;
                  //System.out.println(filename +" "+templines);
                  total_num+=templines;      
            }
            p.destroy();  
            return total_num;   
	}

	public int get_branches(File directory) throws IOException
	{
		Integer total_num=0;
		Process p=Runtime.getRuntime().exec("git branch -a",null,directory);
      	InputStreamReader isr= new InputStreamReader(p.getInputStream());
      	BufferedReader read=new BufferedReader(isr);
      	String result;
      	while((result=read.readLine())!=null)
      	{
      		//System.out.println("Exei grammes"+res[0]+"!!");
      		if(result.startsWith("*")||result.contains("->"))
				continue;  
      		total_num++;
      		
      	}	
      	p.destroy();
      	return total_num;
	}

	public int get_tags(File directory) throws IOException
	{
		Integer total_num=0;
		Process p=Runtime.getRuntime().exec("git tag ",null,directory);
      	InputStreamReader isr= new InputStreamReader(p.getInputStream());
      	BufferedReader read=new BufferedReader(isr);
      	String result;
      	while((result=read.readLine())!=null)
      	{
      		//System.out.println("Exei grammes"+res[0]+"!!");
      		total_num++;
      		
      	}	
      	p.destroy();
      	return total_num;
	}

	public int get_commiters(File directory) throws IOException
	{
		Integer total_num=0;
		Process p=Runtime.getRuntime().exec("git shortlog -sn HEAD",null,directory);
      	InputStreamReader isr= new InputStreamReader(p.getInputStream());
      	BufferedReader read=new BufferedReader(isr);
      	String result;
      	while((result=read.readLine())!=null)
      	{
      		System.out.println(result);
      		total_num++;
      	}	
      	p.destroy();
      	return total_num;
	}

	public void get_full_branches(File directory) throws IOException
	{
		Process p=Runtime.getRuntime().exec("git branch -a ",null,directory);
		InputStreamReader isr= new InputStreamReader(p.getInputStream());
      	BufferedReader read=new BufferedReader(isr);
		String result;
		String parameter[]=new String[1];
      	while((result=read.readLine())!=null)
      	{
      		if(result.startsWith("*")||result.contains("->"))
				continue;  
      		System.out.println("BRANCH : "+result);

      		Process created=Runtime.getRuntime().exec("git log --date=short --decorate=full --reverse"+result,null,directory);//
			InputStreamReader isr3= new InputStreamReader(created.getInputStream());
      		BufferedReader read3=new BufferedReader(isr3);
      		read3.readLine();
      		read3.readLine();
      		String creation=read3.readLine();
      		String[] parts;
      		parts=creation.split(":");
      		System.out.println("CREATED :"+parts[1].replaceAll(" ", ""));


      		String executecommand="git log --date=short --decorate=full"+result; //--pretty=format:\"Id :%h %n Author :%an %n Date :%ad %n Message : %b %n\" 
			Process p2=Runtime.getRuntime().exec(executecommand,null,directory);//
			InputStreamReader isr2= new InputStreamReader(p2.getInputStream());
      		BufferedReader read2=new BufferedReader(isr2);
			String result2;
			String last_modified;
			
			read2.readLine();
			read2.readLine();
			last_modified=read2.readLine();
			String[] parts2;
      		parts2=last_modified.split(":");
			System.out.println("LAST MODIFIED :"+parts2[1].replaceAll(" ", ""));
      		p2.destroy();
		}
		p.destroy();
      	return ;
	}

	public void get_commits(File directory) throws IOException
	{
		Process p=Runtime.getRuntime().exec("git rev-list HEAD --count ",null,directory);//
		InputStreamReader isr= new InputStreamReader(p.getInputStream());
      	BufferedReader read=new BufferedReader(isr);
		String sumof_commits;
      	sumof_commits=read.readLine();
		System.out.println("Sum of commits :"+sumof_commits);


		Process analytical_commits=Runtime.getRuntime().exec("git rev-list HEAD --all --count ",null,directory);//
		InputStreamReader com= new InputStreamReader(analytical_commits.getInputStream());
      	BufferedReader com_read=new BufferedReader(com);
		String sumof_analytical_commits;
      	sumof_analytical_commits=com_read.readLine();
		System.out.println("Sum of analytical_commits :"+sumof_analytical_commits);



		Process p2=Runtime.getRuntime().exec("git shortlog -sn HEAD ",null,directory);
		InputStreamReader isr2= new InputStreamReader(p2.getInputStream());
      	BufferedReader read2=new BufferedReader(isr2);
		String result2;
		String[] parts;
		System.out.println("Percentage of commits per author :");
      	while((result2=read2.readLine())!=null)
      	{
			parts=result2.split("\t");
			String f=parts[0].replaceAll(" ", "");
			float percentage=Float.parseFloat(f)/Float.parseFloat(sumof_commits);
			System.out.println(Float.toString(percentage)+"\t"+parts[1]);
		}
		p.destroy();
		p2.destroy();

		System.out.println("Percentage of commits per branch :");

		Process p3=Runtime.getRuntime().exec("git branch -a",null,directory);
      	InputStreamReader isr3= new InputStreamReader(p3.getInputStream());
      	BufferedReader read3=new BufferedReader(isr3);
      	String result3;
      	while((result3=read3.readLine())!=null)
      	{
      		if(result3.startsWith("*")||result3.contains("->"))
				continue;
			Process p4=Runtime.getRuntime().exec("git rev-list HEAD --count"+result3,null,directory);//git rev-list --count --no-merged
      		InputStreamReader isr4= new InputStreamReader(p4.getInputStream());
      		BufferedReader read4=new BufferedReader(isr4);
      		String result4=read4.readLine();
      		System.out.println("Returned "+result4+" commits");
      		float percentage=Float.parseFloat(result4)/Float.parseFloat(sumof_analytical_commits);
      		System.out.println(Float.toString(percentage)+"\t"+result3);
      		p4.destroy();
      	}	
      	p3.destroy();
 		System.out.println("Percentage of commits per branch per author:");
 		
 		Process p5=Runtime.getRuntime().exec("git branch -a",null,directory);
      	InputStreamReader isr5= new InputStreamReader(p5.getInputStream());
      	BufferedReader read5=new BufferedReader(isr5);
      	String result5;
      	while((result5=read5.readLine())!=null)
      	{
      		if(result5.startsWith("*")||result5.contains("->"))
				continue;
			System.out.println("Branch :"+result5);
			//get num of commits on specific branch
			Process p6=Runtime.getRuntime().exec("git rev-list HEAD --count "+result5,null,directory);//git rev-list --count --no-merges
      		InputStreamReader isr6= new InputStreamReader(p6.getInputStream());
      		BufferedReader read6=new BufferedReader(isr6);
      		String commits_of_branch=read6.readLine();
      		p6.destroy();
      		//get commits per author on branch
      		Process p7=Runtime.getRuntime().exec("git shortlog -s -n "+result5,null,directory);//git rev-list --count --no-merges
      		InputStreamReader isr7= new InputStreamReader(p7.getInputStream());
      		BufferedReader read7=new BufferedReader(isr7);
      		String result7;
			String[] parts2;
			System.out.println("Percentage of commits per author :");
      		while((result7=read7.readLine())!=null)
      		{
				parts2=result7.split("\t");
				String f=parts2[0].replaceAll(" ", "");
				float percentage=Float.parseFloat(f)/Float.parseFloat(commits_of_branch);
				System.out.println(Float.toString(percentage)+"\t"+parts2[1]);
			}
			p7.destroy();
      	}	
      	p5.destroy();
	}

	public void get_percentage_changes(File directory) throws IOException
	{
		Process p1=Runtime.getRuntime().exec("git shortlog HEAD  -s -n",null,directory);
      	InputStreamReader isr1= new InputStreamReader(p1.getInputStream());
      	BufferedReader read1=new BufferedReader(isr1);
      	String author;
      	while((author=read1.readLine())!=null)
      	{
      		String[] parts2;
      		parts2=author.split("\t");
      		author=parts2[1];
      		System.out.println(author);
      		String executecommand;
      		executecommand="git log --shortstat --oneline --author='"+author+"'";
      		System.out.println("executecommand "+executecommand);
      		Process p=Runtime.getRuntime().exec(executecommand,null,directory);//--shortstat --oneline --pretty=tformat: --numstat
	      	InputStreamReader isr= new InputStreamReader(p.getInputStream());
	      	BufferedReader read=new BufferedReader(isr);
	      	String result;
	      	int added=0;
	      	int deleted=0;
	      	while((result=read.readLine())!=null)
	      	{
	      		//System.out.println("reeturned :"+result);
	      		if(result.startsWith(" "))	
	      		{
	      			String[] parts;
	      			parts=result.split(",");
	      			int count=0;
	      			for(String retval:parts)
	      			{
	      				if(count==0)
	      				{
	      					count++;
	      					continue;
	      				}
	      				if(retval.contains("+"))
	      				{
	      					String changes=retval.replaceAll("[^0-9.]", "");
	      					added+=Integer.parseInt(changes);
	      				}
	      				else
	      				{
	      					String changes=retval.replaceAll("[^0-9.]", "");
	      					deleted+=Integer.parseInt(changes);
	      				}
	      				count++;
	      			}
	      		}
	      	}
	      	System.out.println("Lines added "+added+" Lines Deleted "+deleted);
	      	p.destroy();
      	}	
      	return ;
	}

	public void get_commit_statistics_per_author(File directory) throws IOException
	{
		Process p=Runtime.getRuntime().exec("git shortlog -sn HEAD ",null,directory);
		InputStreamReader isr= new InputStreamReader(p.getInputStream());
      	BufferedReader read=new BufferedReader(isr);
		String result2;
		String[] parts;
		System.out.println("Percentage of commits per author :");
      	while((result2=read.readLine())!=null)
      	{
			parts=result2.split("\t");
			String f=parts[0].replaceAll(" ", "");
			System.out.println(f+"\t"+parts[1]);


			Process created=Runtime.getRuntime().exec("git log --date=short --reverse --author="+parts[1],null,directory);//first commit
			InputStreamReader isr3= new InputStreamReader(created.getInputStream());
      		BufferedReader read3=new BufferedReader(isr3);
      		read3.readLine();
      		read3.readLine();
      		String creation=read3.readLine();
      		if(creation==null)
      			continue;
      		String[] parts_created;
      		parts_created=creation.split(":");
      		System.out.println("CREATED :"+parts_created[1].replaceAll(" ", ""));
      		parts_created[1]=parts_created[1].replaceAll(" ", "");
      		String[] split_first_date=parts_created[1].split("-");
      		created.destroy();

      		String executecommand="git log --date=short --author="+parts[1];
			Process p2=Runtime.getRuntime().exec(executecommand,null,directory);//
			InputStreamReader isr2= new InputStreamReader(p2.getInputStream());
      		BufferedReader read2=new BufferedReader(isr2);
			String last_modified;
			
			read2.readLine();
			read2.readLine();
			last_modified=read2.readLine();
			String[] parts2;
      		parts2=last_modified.split(":");
			System.out.println("LAST MODIFIED :"+parts2[1].replaceAll(" ", ""));
			parts2[1]=parts2[1].replaceAll(" ", "");
			String[] split_last_date=parts2[1].split("-");

      		p2.destroy();
      		int years=Integer.parseInt(split_last_date[0]) -Integer.parseInt(split_first_date[0]);
      		int months=years*12 + (Integer.parseInt(split_last_date[1]) - Integer.parseInt(split_first_date[1]));
      		int days=years*365+ (Integer.parseInt(split_last_date[1])-Integer.parseInt(split_first_date[1]))*30 + Integer.parseInt(split_last_date[2])-Integer.parseInt(split_first_date[2]);

      		System.out.println("Diffrence    years :"+years+" months "+ months+" days "+ days);

      		float commits_per_year;
      		if(years==0)
      			commits_per_year=Float.parseFloat(f);
      		else
      			commits_per_year=Float.parseFloat(f)/(float)years;
      		float commits_per_month;
      		if(months==0)
      			commits_per_month=Float.parseFloat(f);
      		else
      			commits_per_month=Float.parseFloat(f)/(float)months;
      		float commits_per_day;
      		if(days==0)
      			commits_per_day=Float.parseFloat(f);
      		else
      			commits_per_day=Float.parseFloat(f)/(float)days;

      		System.out.println("Average per year :"+commits_per_year+" per month:" + commits_per_month+" per day:"+commits_per_day);

		}
		p.destroy();

	}
}



