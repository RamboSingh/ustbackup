
On error resume next

Dim sCaption, sProcessId, objShell, strInstallPath
With GetObject("winmgmts:\\.\root\CIMV2:Win32_Process.Handle='" & CreateObject("WScript.Shell").Exec("rundll32 kernel32,Sleep").ProcessId & "'")
	With GetObject("winmgmts:\\.\root\CIMV2:Win32_Process.Handle='" & .ParentProcessId & "'")
		With GetObject("winmgmts:\\.\root\CIMV2:Win32_Process.Handle='" & .ParentProcessId & "'")
			With GetObject("winmgmts:\\.\root\CIMV2:Win32_Process.Handle='" & .ParentProcessId & "'")
				sCaption = .Caption
				sProcessId = .ProcessId
			End With
		End With
	End With
	.Terminate
End With

'' msgbox sCaption & ":" & sProcessId

Dim strMonitorScript:strMonitorScript = "MoniorAndLaunchOnClose.vbs"
Set objShell = CreateObject("WScript.Shell")
strInstallPath = objShell.ExpandEnvironmentStrings(Wscript.Arguments(0))
Set oFSO = CreateObject("Scripting.FileSystemObject")
Set oFile = oFSO.CreateTextFile(strMonitorScript)
oFile.writeLine"Do While GetObject(""Winmgmts:"").ExecQuery(""Select * from Win32_Process where ProcessId='" & sProcessId & "'"").count <> 0"
oFile.writeLine "	WScript.sleep(500)"
oFile.writeLine "Loop"
oFile.writeLine "CreateObject(""Shell.Application"").Explore """ & strInstallPath & """"
oFile.close
Set oFSO = nothing
objShell.Exec("wscript " & strMonitorScript) ''Asynchronous firing of monitor script