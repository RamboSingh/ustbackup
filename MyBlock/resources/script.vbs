Set wshShell = CreateObject( "WScript.Shell" )
WScript.Echo wshShell.ExpandEnvironmentStrings( "TEMP=%TEMP%" )
wshShell     = Nothing




///Set WshShell = WScript.CreateObject("WScript.Shell")
WshShell.SendKeys """C:\Users\u49391\Desktop\test.docx"""
WshShell.SendKeys """C:\Users\u49391\Desktop\test.txt"""
WshShell.SendKeys "{ENTER}"  