package operations_pack;

import java.io.*;
import java.util.*;


public class Myhtml{
	File fhtml;
	BufferedWriter bw;
	public Myhtml(String name,String dirname)
	{
		fhtml=new File(new String(name+".html"));
		File parent=fhtml.getParentFile();
		if(!parent.exists() && ! parent.mkdirs())
		{
			throw new IllegalStateException("Couldn't create dir");
		}
		String towrite="<html><head><title>Software Technology</title>\n"+
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"+
			 "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js\"></script>"+
			 "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>"+
			"</head>\n<body bgcolor=#ffbf80> "+"<nav class=\"navbar navbar-inverse\">\n <div class=\"container-fluid\"><div class=\"navbar-header\">"+
      "<a class=\"navbar-brand\" href=\"#\">GitHub Statistics</a></div>\n"+
    "<ul class=\"nav navbar-nav\"> <li class=\"active\"><a href=\"#\">Details</a></li>\n<li><a href=\"#TA\" title=\"Number of files\">Task A</a></li>\n<li><a href=\"#TB\"title=\"Number of lines\">Task B</a></li>"+
      "\n<li><a href=\"#TC\"title=\"Number of branches,tags and commiters\">Task C</a></li><li><a href=\"#TD\" title=\"Branches' info(+commits)\">Task D</a></li><li><a href=\"#TE\" title=\"Commits per author,per branch,per branch per author\">"+
      "Task E</a></li><li><a href=\"#TF\" title=\"Commits average per author per day,per week, per month\">Task F</a></li><li><a href=\"#TG\" title=\"Lines average per author\">Task G</a></li></ul>\n </div>\n</nav>"+
		"\n<div class=\"container\">"+"<h5>Worked on git repository <strong>"+dirname+"</strong></br>\n"+
		"Developed by:<br>Sofianopoulou Stavroula &ensp; (1115201300165)<br>Triantafyllou Andriani &ensp;(1115201300179)</h5>\n";
		try
		{
			
			bw=new BufferedWriter(new FileWriter(fhtml));	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void print_files(Integer files_num, ArrayList<String> allfiles)
	{
		String towrite="</br><table class=\"table table-striped table-bordered table-hover table-condensed\" style=\"width:20%\" id=\"TA\">\n<tr><th>Number of files</th></tr>\n<tr><td>"+
			Integer.toString(files_num)+"</td></tr></table>\n"+"<button type=\"button\" class=\"btn btn-info\" data-toggle=\"collapse\" data-target=\"#listfiles\">View files</button></br>"+
			"<div id=\"listfiles\" class=\"collapse\"><ul>";
		try
		{	
			bw.write(towrite);
			for(Integer i=0 ;i<files_num;i++)
			{
				towrite="<li>"+allfiles.get(i)+"</li>\n";
				bw.write(towrite);	
			}
			towrite="</ul></div>";
			bw.write(towrite);

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void print_lines(Integer lines_num)
	{
		String towrite="</br><table class=\"table table-striped table-bordered table-hover table-condensed\"style=\"width:20%\"id=\"TB\">\n<tr><th>Number of lines</th></tr>\n<tr><td>"+Integer.toString(lines_num)+"</td></tr></table><br>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void print_branches_tags_commiters(Integer branches_num,Integer tags_num,Integer commiters_num)
	{
		String towrite="</br><table class=\"table table-striped table-bordered table-hover table-condensed\" style=\"width:60%\" id=\"TC\">\n<tr><th>Branches</th><th>Tags</th><th>Commiters</th></tr>\n<tr><td>"+Integer.toString(branches_num)+"</td>"+
		"<td>"+Integer.toString(tags_num)+"</td><td>"+Integer.toString(commiters_num)+"</td></tr></table><br>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void start_full_branches()
	{
		String towrite="</br><table class=\"table table-striped table-bordered table-hover table-condensed\" style=\"width:80%\" id=\"TD\">\n<tr><th>Branch name</th><th>Created</th><th>Last modified</th></tr>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void add_full_branches(String bname,String created,String lastmodified,Integer i,ArrayList <Commits_Info> cilist)
	{
		String towrite2="<div id=\"branch_info_"+Integer.toString(i)+"\" class=\"collapse\"><table class=\"table table-hover\">"+
		"<tr><th>Id</th><th>Commiter</th><th>Date</th><th>Message</th><th>Tags</th></tr>";
		
		for(Commits_Info ci:cilist)
		{
			towrite2+="<tr><td>"+ci.id+"</td><td>"+ci.author+"</td><td>"+ci.date+"</td><td>"+ci.message+"</td><td>"+ci.tags+"</td></tr>";
		}

		towrite2+="</table> </div>";

		String towrite="<tr><td>"+bname+"  </br><button type=\"button\" class=\"btn btn-info\" data-toggle=\"collapse\" data-target=\"#branch_info_"+Integer.toString(i)+
		"\"> Show commit history</button>"+towrite2+"</td>"+"<td>"+created+"</td><td>"+lastmodified+"</td></tr>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}


	public void end_table()
	{
		String towrite="</table><br>\n\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void print_sum_commits(String commits)
	{
		String towrite="<p id=\"TE\"><strong>Total commits are: "+commits+"</strong></p>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void close_html()
	{
		String towrite="\n</br></br></br></div></body> </html>";
		try
		{
			bw.write(towrite);
			bw.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return;
	}


	public void start_commits_per_author()
	{
		String towrite="</br><table class=\"table table-striped table-bordered table-hover table-condensed\" style=\"width:40%\">\n<tr><th>Commiter</th><th>Commits(%)</th></tr>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void add_commits_per_author(String cname,String percentage)
	{
		String towrite="<tr><td>"+cname+"</td>"+"<td>"+percentage+"</td></tr>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}


	public void start_commits_per_branch()
	{
		String towrite="<table class=\"table table-striped table-bordered table-hover table-condensed\" style=\"width:40%\">\n<tr><th>Branch</th><th>Commits(%)</th></tr>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void add_commits_per_branch(String bname,String percentage)
	{
		String towrite="<tr><td>"+bname+"</td>"+"<td>"+percentage+"</td></tr>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void start_commits_per_branch_per_author()
	{
		String towrite="<table class=\"table table-striped table-bordered table-hover table-condensed\" style=\"width:40%\">\n<tr><th>Commiter</th><th>Commits(%)</th></tr>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void add_commits_per_branch_per_author_addbranch(String bname)
	{
		String towrite="<tr><th>"+bname+"</th></tr>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void add_commits_per_branch_per_author(String cname,String percentage)
	{
		String towrite="<tr><td>"+cname+"</td>"+"<td>"+percentage+"</td></tr>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void start_lines_average()
	{
		String towrite="</br><table class=\"table table-striped table-bordered table-hover table-condensed\" style=\"width:40%\"id=\"TG\">\n<tr><th>Commiter</th><th>Average Lines Modified</th></tr>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void add_lines_average(String cname,String average)
	{
		String towrite="<tr><td>"+cname+"</td>"+"<td>"+average+"</td></tr>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}


	public  void start_commiter_statistics()
	{
		String towrite="</br><table class=\"table table-striped table-bordered table-hover table-condensed\" style=\"width:80%\"id=\"TF\">\n<tr><th>Commiter</th><th>Average per day</th><th>Average per week</th><th>Average per month</th></tr>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void add_commiter_statistics(String cname,String perday,String perweek,String permonth)
	{
		String towrite="<tr><td>"+cname+"</td>"+"<td>"+perday+"</td><td>"+perweek+"</td><td>"+permonth+"</td></tr>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}