package Practice;

import org.junit.Assert;
import org.testng.annotations.Test;

public class RetryAnalyzerDemo {
@Test(retryAnalyzer=com.crm.autodesk.genericUtility.RetryAnalyzerImplementation.class)
	public void retryAnalyserDemoTest() 
	{
		System.out.println("Retry Analyzer");
		Assert.fail();
	}

}
