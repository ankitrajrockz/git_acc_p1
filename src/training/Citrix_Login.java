package training;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty9.server.Authentication.User;

import sun.tools.jar.Main;

public class Citrix_Login {

	private  static WebDriver driver;
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		
			try {
				
			 System.setProperty("webdriver.chrome.driver","C:\\Users\\Ankit.j.Raj\\Desktop\\Selenium\\chromedriver.exe");
			 
			 ChromeOptions opt= new ChromeOptions();
			 opt.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
			 opt.addArguments("--disable-infobars");
			
			 //opt.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
			 opt.addArguments("--disable-extensions"); 
			 
			 opt.setCapability(CapabilityType.SUPPORTS_LOCATION_CONTEXT, true);
			 opt.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			 opt.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			 opt.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			 
			 
			 
			 driver= new ChromeDriver(opt);
			 driver.manage().window().maximize();
			
			 driver.get("https://vdiaas.hcscanyplace.net/vpn/index.html");
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 Robot rb= new Robot();
			
			 
			/* BufferedImage img= new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			 ImageIO.write(img, "PNG", new File(".//img.png"));
			 Thread.sleep(2000);*/
			 
			 By username=By.xpath("//*[@id='Enter user name']");
			 
			 if(elementFound(driver, 10, username)) {
				 driver.findElement(username).sendKeys("U409022");
				 driver.findElement(By.xpath("//*[@name='passwd']")).sendKeys("Infy1791");
				 driver.findElement(By.id("Log_On")).click();
				 Thread.sleep(2000);
			 }
			
			
			
			 
			 
			 By title_citrix_rec=By.xpath("//a[text()='Detect Receiver']");
			 if(elementFound(driver, 10, title_citrix_rec)) {
				 driver.findElement(title_citrix_rec).click();
				 Thread.sleep(5000);
				 rb.keyPress(KeyEvent.VK_TAB);
				 rb.keyRelease(KeyEvent.VK_TAB);
				 Thread.sleep(1000);
				 rb.keyPress(KeyEvent.VK_ENTER);
				 rb.keyRelease(KeyEvent.VK_ENTER);
				 Thread.sleep(2000);
				 
				 By already_Installed= By.xpath("//h1[contains(text(),'Citrix Receiver is already installed')]/following-sibling::div/a[text()='Already installed']");
				 if(elementFound(driver, 10, already_Installed)) {
					 //driver.findElement(already_Installed).click();
					 Actions act= new Actions(driver);
					 act.moveToElement(driver.findElement(already_Installed)).click().build().perform();
				 }
				 
			 }
			 
			By citrix_Desktop=By.xpath("//*[@role='tablist']//*[@id='desktopsBtn']");
			if(elementFound(driver, 10, citrix_Desktop)) {
				driver.findElement(citrix_Desktop).click();
				 
				List<WebElement> dsk_ava= driver.findElements(By.xpath("//*[text()='All Apps']/following-sibling::ul/li/a[1]"));
						
				Iterator<WebElement> ite=dsk_ava.iterator();
				
				while(ite.hasNext()) {
					WebElement el=ite.next();
					String dsk_name=el.getText();
					if(dsk_name.equals("VDI PLUS")) {
						el.click();
						Thread.sleep(6000);
						
						openFile();
					}
				}
				
			}
			}
			finally {
				
				//System.out.println("End");
				teardown();
			}
			
	}
	
	public static boolean elementFound(WebDriver drive, long time, By add) {
		 Boolean wel_header=false;
		 WebDriverWait wait=new WebDriverWait(drive, time);
		 try {
			 wel_header=wait.until(ExpectedConditions.or(
				 
				 ExpectedConditions.elementToBeClickable(add),
				 ExpectedConditions.presenceOfElementLocated(add),
				 ExpectedConditions.visibilityOfElementLocated(add)
				 ));
		 	 return wel_header;
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 return false;
		}
		
	}
	
	public static void teardown() {
		driver.close();
		driver.quit();
	}

	public static void openFile() {
		
		try {
			String path=System.getProperty("user.home").toString()+"\\Downloads";
			File dir = new File(path);   

            File[] fileList = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.endsWith(".ica");
                }
            });
            
            for(File file : fileList) {
                //System.out.println(file.getName());
               // Process process = new ProcessBuilder(path+"\\"+file.getName()).start();
                
            	String cmd1= "cmd /c "+file.getName();
            	String cmd2= path+"\\";
                Runtime run  = Runtime.getRuntime(); 
                Process proc = run.exec(
                		cmd1,null, new File(cmd2)
                		); 
               
            }
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
