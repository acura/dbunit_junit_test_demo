Guide line for how to run dbunit and Junit test:

--URL to access project:https://github.com/acura/dbunit_junit_test_demo.git

-- Import the project as a maven in IDE of your choice.

-- Update the project if users IDE is STS(Right click on project folder then 'maven>update project').

--InvoiceDaoImplTest.java is a class contains tests.Run as Junit test case.


*How @DataSetup and @ExpectedDatabase works:
a)	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/data/createInvoiceData.xml")

-- This annotation is used for database setup.We have to provide some data in createInvoiceData.xml file for database setup in the following format.

	<T_INVOICE id="3" invoice_date="2017-09-24" invoice_no="INV000001"
		currency="USD" vat="10" vat_amount="200" net_amount="400"
		total_amount="500" discount="1" discount_amount="10" />
		
	T_INVOICE - is a table name in database.
	
--This will setup database before the test is run.

b)		@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, table = "T_INVOICE", value = "/data/createInvoiceExpectedData.xml")

--This will run after test method.

--It will compare the data of database table and data in createInvoiceExpectedData.xml file.

--If it is not same then assertion error will be thrown.