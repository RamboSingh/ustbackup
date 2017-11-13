Set objOutlook = CreateObject("Outlook.Application")
Set objMail = objOutlook.CreateItem(0)
objMail.Recipients.Add ("thangabalan.kundrumalaiyan@hrblock.com")
'objMail.Recipients.Add ("prasoon.bhaskaran@hrblock.com")
'objMail.Recipients.Add ("recipient3@example.com")
objMail.Subject = "Execution Summary Report"
objMail.Body = "PFA the Execution Summary Report" & vbNewLine & "**Note: This is an automatic email" & vbNewLine & "--" & vbNewLine & "Thanks" & vbNewLine & "Thangabalan K"
objMail.Attachments.Add("D:\Dailyupdate\myblockTeam\MyBlock\test-report\SummaryReport\Execution_Summary.pdf")
objMail.Send 
'objOutlook.Quit
Set objMail = Nothing
Set objOutlook = Nothing
