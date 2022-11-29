package com.macys.domain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.macys.dao.RunInfoHistoryDAO;

public class Principal {
		
	public static void sendMail(int threshold) throws FileNotFoundException, IOException {
		
		try {
			
			String fullPathToLogExecution = "MonitorTasksExecution.txt";
			
			try {
				InputStream is = new FileInputStream("list_emails.properties");
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				try (BufferedReader br = new BufferedReader(isr)) {
					String linemail = "";

					System.out.println("");
					while ((linemail = br.readLine()) != null) {
						
						String smail = "`mail -s " + "\"THERE ARE MORE THAN " + threshold + " tasks running a day! Please, check that! THANK YOU! \" " + linemail + " < " +  fullPathToLogExecution + "`";
						System.out.println(smail);
						
						String[] cmdsendmail = { "/bin/csh", "-c", smail };

						Runtime rtsendmail = Runtime.getRuntime();
						Process psend = rtsendmail.exec(cmdsendmail);
						psend.waitFor();
					}
				}
			} catch (FileNotFoundException fne) {
				System.out.println(
						"File list_emails.properties not found or not created! Please, create this one by putting the email address line by line. Let this file on the same directory of this .jar file.");
			}

//		System.out.println(smail);

			System.out.println("");
			System.out.println("--> Mail sent to Control-M groups!");

		} catch (Exception e) {
			System.out.println("##### Install the correct mail software!! ####");
		}
	}
			
	

	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		
		//String od = "20221117";
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

		if (quantityOfArguments > 1 || quantityOfArguments == 0) {
			System.out.println("The program ACCEPTS ONLY 1 argument! Parameter threshold MUST be an INTEGER number, for example: 100000 in which it means 100 thousand of tasks.");
			System.out.println("");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("");
			System.out.println("You MUST create THESE 3 files on the same directory that monitorJobs.jar file is: configs.properties, list_emails.properties and MonitorTasksExecution.txt");
			System.out.println("");
			System.out.println(" - On configs.properties, you must add these lines according to: ");
			System.out.println("");
					
			System.out.println("prop.server.host = <IP of Control-EM Server>");
			System.out.println("prop.server.password = <DB password>");
			System.out.println("prop.server.user = <DB user>");
			System.out.println("prop.server.dbname = <DB name>");
			
			System.out.println("");
			System.out.println(" - On list_emails.properties you MUST the emails line by line");
			System.out.println("");
			System.out.println(" - On MonitorTasksExecution.txt you should ENTER the message you want to alert via email.");
			System.out.println("");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("");
		} else {
			//int total = rdao.showQuantityOfJobsIn24Hours(od);
			int total = rdao.showQuantityOfJobsIn24Hours(resultprt.replaceAll("\"", "")); //replace takes out the double quotes from String value

			int threshold = 0;

			threshold = Integer.parseInt(args[0]);
						
		
			/*System.out.println("");
			System.out.println("Total: " + total);
			System.out.println("Threshold: " + threshold);
			System.out.println("");*/

			if (total <= threshold) {
				System.out.println("There are less than " + threshold + " jobs!");
			} else {
				/*System.out.println("CAUTION!! There are more than "  + threshold + " JOBS running daily! Currently running: " + total
						+ " jobs running in the day!");*/
				sendMail(threshold);
				
			}
		}
		prt.destroy();
	}
	

}
