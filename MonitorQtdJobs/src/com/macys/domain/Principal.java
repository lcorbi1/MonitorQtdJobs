package com.macys.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.macys.dao.RunInfoHistoryDAO;

public class Principal {

	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		
		//String od = "20221118";
		String od = "date +\"%Y%m%d\""; //Here It is gonna run a OS command to get the date
		//String od = "ssh root@192.168.182.165 -t 'uptime'";
				
		Runtime rt = Runtime.getRuntime();
		Process prt = rt.exec(od);
		prt.waitFor();
		
		BufferedReader brrt = new BufferedReader(new InputStreamReader(prt.getInputStream()));
		
		String resultprt = brrt.readLine();
		System.out.println("Current Day: " + resultprt);

		RunInfoHistoryDAO rdao = new RunInfoHistoryDAO();

		int quantityOfArguments = args.length;

		if (quantityOfArguments > 0) {
			System.out.println("The program NOT accepts arguments!");
		} else {
			int total = rdao.showQuantityOfJobsIn24Hours(od);

			int threshold = 0;

			threshold = 300;

			if (total <= threshold) {
				System.out.println("There are less than 300 jobs!");
			} else {
				System.out.println("CAUTION!! There are more than 300 JOBS running daily! Currently running: " + total
						+ " jobs running in the day!");
			}
		}
		prt.destroy();
	}
	

}
