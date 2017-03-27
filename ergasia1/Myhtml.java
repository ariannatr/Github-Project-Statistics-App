package operations_pack;

import java.io.*;
import java.util.*;


public class Myhtml{
	File fhtml;
	BufferedWriter bw;
	public Myhtml(String name)
	{
		fhtml=new File(name);
		String towrite="<html><head><title>Software Technology</title>\n<style>table,th,td{border: 0.2px solid black;}</style></head>\n<body bgcolor=#ffbf80><h1><center>GitHub Statistics</center></h1>"+
		"\n<h5><font color=#2e2e1f >Developed by:<br>Sofianopoulou Stavroula &ensp; (1115201300165)<br>Triantafyllou Andriani &ensp;(1115201300179)</font></h5>\n";
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

	public void print_files(Integer files_num)
	{
		String towrite="<table style=\"width:20%\">\n<tr><th>Number of files</th></tr>\n<tr><td>"+Integer.toString(files_num)+"</td></tr></table><br>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void print_lines(Integer lines_num)
	{
		String towrite="<table style=\"width:20%\">\n<tr><th>Number of lines</th></tr>\n<tr><td>"+Integer.toString(lines_num)+"</td></tr></table><br>\n";
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
		String towrite="<table style=\"width:60%\">\n<tr><th>Branches</th><th>Tags</th><th>Commiters</th></tr>\n<tr><td>"+Integer.toString(branches_num)+"</td>"+
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
		String towrite="<table style=\"width:60%\">\n<tr><th>Branch name</th><th>Created</th><th>Last modified</th></tr>\n";
		try
		{	
			bw.write(towrite);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void add_full_branches(String bname,String created,String lastmodified)
	{
		String towrite="<tr><td>"+bname+"</td>"+"<td>"+created+"</td><td>"+lastmodified+"</td></tr>\n";
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
		String towrite="<b>Total commits are: "+commits+"</b>\n";
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
		String towrite="\n</body> </html>";
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
		String towrite="<table style=\"width:40%\">\n<tr><th>Commiter</th><th>Commits(%)</th></tr>\n";
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
		String towrite="<table style=\"width:40%\">\n<tr><th>Branch</th><th>Commits(%)</th></tr>\n";
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
		String towrite="<table style=\"width:40%\">\n<tr><th>Commiter</th><th>Commits(%)</th></tr>\n";
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
		String towrite="<table style=\"width:40%\">\n<tr><th>Commiter</th><th>Average Lines Modified</th></tr>\n";
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
		String towrite="<table style=\"width:80%\">\n<tr><th>Commiter</th><th>Average per day</th><th>Average per week</th><th>Average per month</th></tr>\n";
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