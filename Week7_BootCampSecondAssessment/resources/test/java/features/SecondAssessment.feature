Feature: Reports Validation

Scenario: Second Assessment 

When Launch Salesforce application login.salesforce.com
And the user is able to enter username as bowyakarthikeyan@testleaf.com
And the user is able to enter password as India@123
And the user is able to click login
And Click on App Launcher
And Click on Service 
And Click on Reports
And Click on New Report-SalesForce Classic
And Click on Leads
And Download the Lead Report Image on the Right side
And Click on Create
And Select Range as All Time
And Select From date as todays date
And Select To as +5 days From Today
Then Verify Whether the Preview is in Tabular Format
And Get the List of Billing State/Province
And Get the Grand Total of Records Available
And Click on Save before Report
And Enter Report name as <Your name>
And Enter Report Unique name as<yourName_anyNumber>
And Enter Report Discussion as Report Updated by <yourName>
And Select Report Folder as Unfiled Public Reports
And Click on Save from Report
Then Verify Report has been created successfully
And Click on Run Report
And Get the total Number of Records
And Click on Edit
And Click on Close 
And Get the text of Report Name 
Then Verify the Report Name
And Get the Date and Time When the Report is Created On