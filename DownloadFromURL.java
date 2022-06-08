package com.example.DownloadFromURL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DownloadFromURL {
	@Scheduled(cron = "0 0/1 * * * ?")
	public void downLoad(){
		LocalDate todaysDate = LocalDate.now();
		
		String xml = downloadFromUrl("https://www.good.nat.gov.tw/regcenter/csv/"+todaysDate.minusDays(1L)+"-new-addressbook.csv","D:");
		String csv = downloadFromUrl("https://www.good.nat.gov.tw/regcenter/csv/"+todaysDate.minusDays(1L)+"-new-subrogation.csv","D:");  
		System.out.println(xml+csv);
	}
	
	 public static String downloadFromUrl(String url,String dir) {  
		 final Logger logger = (Logger) LoggerFactory.getLogger(DownloadFileApplication.class);
	     LocalDateTime today=LocalDateTime.now(); 
	     FileOutputStream fos = null;
	      OutputStreamWriter osw = null;
	 //     BufferedWriter bw = null;
	     
		 try {  
	        	
	            URL httpurl = new URL(url);  
	            String fileName = getFileNameFromUrl(url);  
	            System.out.println(fileName);  
	            File f = new File(dir + fileName);
	       //     FileOutputStream c=null;
	            fos = new FileOutputStream(f);
	            fos.write(0xef);
	            fos.write(0xbb);
	            fos.write(0xbf);
	            osw = new OutputStreamWriter(fos, "UTF-8");
	            osw.close();
	            fos.close();
	            FileInputStream(new File(filename))); 
	            BufferedReader br= new BufferedReader(new InputStreamReader(in,"GBK"))
	     //       bw = new BufferedWriter(osw);
	        //    f=new OutputStreamWriter(f,"UTF-8");
	         //  FileInputStream(f); 
	          //  BufferedReader br= new BufferedReader(new InputStreamReader(f,"UTG-8"));
	     //       OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
	            FileUtils.copyURLToFile(httpurl, f);  
	        // OutputStream outputStream = new OutputStream();
				//   outputStream = response.getOutputStream();
	            //outputStream.write(new byte[] {(byte) 0xEF,(byte) 0xBB,(byte) 0xBF});
	            //output
	      //      OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
	        } catch (Exception e) {  
	        	//final Logger logger = (Logger) LoggerFactory.getLogger(DownloadFromURLApplication.class);
	            e.printStackTrace(); 
	            logger.error(today+"失敗");
	          //  return ResponseEntity.status(HttpStatus.OK).body(todoList);
	            return "Fault!";
	        }   
	        logger.info(today+"成功");
	        return "Successful!";  
	    }  
	      
	    public static String getFileNameFromUrl(String url){  
	        String name = new Long(System.currentTimeMillis()).toString() + ".X";  
	        int index = url.lastIndexOf("/");  
	        if(index > 0){  
	            name = url.substring(index + 1);  
	            if(name.trim().length()>0){  
	                return name;  
	            }  
	        }  
	        return name;  
	    }  
}
