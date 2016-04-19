package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *  
Round 1A 2016   https://code.google.com/codejam/contest/4304486/dashboard#s=p1
 *  When Sergeant Argus's army assembles for drilling, they stand in the shape of an N by N square grid, with exactly one soldier in each cell. Each soldier has a certain height.

Argus believes that it is important to keep an eye on all of his soldiers at all times. Since he likes to look at the grid from the upper left, he requires that:

    Within every row of the grid, the soldiers' heights must be in strictly increasing order, from left to right.
    Within every column of the grid, the soldiers' heights must be in strictly increasing order, from top to bottom. 

Although no two soldiers in the same row or column may have the same height, it is possible for multiple soldiers in the grid to have the same height.

Since soldiers sometimes train separately with their row or their column, Argus has asked you to make a report consisting of 2*N lists of the soldiers' heights: one representing each row (in left-to-right order) and column (in top-to-bottom order). As you surveyed the soldiers, you only had small pieces of paper to write on, so you wrote each list on a separate piece of paper. However, on your way back to your office, you were startled by a loud bugle blast and you dropped all of the pieces of paper, and the wind blew one away before you could recover it! The other pieces of paper are now in no particular order, and you can't even remember which lists represent rows and which represent columns, since you didn't write that down.

You know that Argus will make you do hundreds of push-ups if you give him an incomplete report. Can you figure out what the missing list is?
Input

The first line of the input gives the number of test cases, T. T test cases follow. Each consists of one line with an integer N, followed by 2*N-1 lines of N integers each, representing the lists you have, as described in the statement. It is guaranteed that these lists represent all but one of the rows and columns from a valid grid, as described in the statement.
Output

For each test case, output one line containing Case #x: y, where x is the test case number (starting from 1) and y is a list of N integers in strictly increasing order, representing the missing list.
Limits

1 <= T <= 50.
1 <= all heights <= 2500.
The integers on each line will be in strictly increasing order.
It is guaranteed that a unique valid answer exists.
Small dataset

2 <= N <= 10.
Large dataset

2 <= N <= 50.
Sample

Input
  	

 

1
3
1 2 3
2 3 5
3 5 6
2 3 4
1 2 3

	
Output
Case #1: 3 4 6


 * @author tranga
 *
 */

public class RankAndFile {
	public static void main(String[] args) throws IOException {
		if(args.length < 2){
			System.err.println("arguments: infile outfile");
		}
		String inFile = args[0];
		String outFile = args[1];
		int caseNo = 1;
		File fin = new File(inFile);
		FileInputStream fis = new FileInputStream(fin);
	
		FileOutputStream fos = new FileOutputStream(outFile);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	 
		String line = br.readLine();
		int totalCases = Integer.parseInt(line);
		while (totalCases >= caseNo) {
			int size = Integer.parseInt(br.readLine());
			int rowsToRead = size*2 -1;
			String strArrays[] = new String[rowsToRead];
			for(int i=0; i < rowsToRead; i++){
				strArrays[i] = br.readLine();
			}
			bw.write("Case #"+caseNo+": "+getMissingLine(strArrays));
			bw.newLine();
			caseNo++;
		}
	 
		br.close();
		bw.close();
	}

	private static String getMissingLine(String[] strArrays) {
		HashMap<String, Integer> charMap = new HashMap<String, Integer>();
		for(int i=0; i < strArrays.length; i++){
			String str = strArrays[i];
			StringTokenizer strTok = new StringTokenizer(str);
			while(strTok.hasMoreElements()){
				String tok = strTok.nextToken();
				Integer count = charMap.get(tok);
				if(count == null){
					charMap.put(tok, Integer.valueOf(1));
				}else{
					charMap.put(tok, count + 1);
				}
			}
		}
		ArrayList<Integer> oddCountStrs = new ArrayList<Integer>();
		Set<String> keySet = charMap.keySet();
	    Iterator<String> itr = keySet.iterator();
	    while(itr.hasNext()){
	    	String tok = itr.next();
	    	Integer count = charMap.get(tok);
	    	if(count%2 > 0){
	    		oddCountStrs.add(new Integer(tok));
	    	}
	    }
	    
	    Collections.sort(oddCountStrs);
	    StringBuffer strBuff = new StringBuffer();
	    for(Integer str:oddCountStrs){
	    	strBuff.append(str).append(" ");
	    }
		return strBuff.toString();
	}

}
