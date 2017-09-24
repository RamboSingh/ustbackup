VERSION 1.0 CLASS
BEGIN
  MultiUse = -1  'True
END
Attribute VB_Name = "ThisOutlookSession"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = True
Private WithEvents Items As Outlook.Items
Attribute Items.VB_VarHelpID = -1
Const PATH_QA_ACCELERATORS = "D:\My Eclipse Workspace\PRJ000_QA_Accelerators"
Const strEmailDisclaimer = "[This is an automated response to your email]"

Private Sub Application_Startup()
  Dim olApp As Outlook.Application ''declaration
  Dim objNS As Outlook.NameSpace ''declaration
  Set olApp = Outlook.Application
  Set objNS = olApp.GetNamespace("MAPI")
  Set Items = objNS.GetDefaultFolder(olFolderInbox).Items
  
End Sub

Private Sub Items_ItemAdd(ByVal objItem As Object)
  
  If TypeName(objItem) = "MailItem" Then
    Dim objMsg As Outlook.MailItem 'You can use objMsg.Subject objMsg.Sender.Name objMsg.Sender.Address objMsg.Body
    Set objMsg = objItem
    If (InStr(UCase(Replace(objMsg.Subject, " ", "")), "MTA?") > 0) Then
        strBody = objMsg.Body
        boolAll = True
        Dim strProjectID
        For Each strLine In Split(strBody, vbCrLf)
            If Trim(strLine) <> "" Then
                If UCase(Trim(strLine)) = "ALL" Then
                    boolAll = True
                Else
                    strProjectID = Trim(strLine)
                    boolAll = False
                End If
                Exit For
            Else
                ''Try next non blank line
            End If
        Next
        If (strProjectID <> strEmailDisclaimer) Then
            If (InStr(UCase(Replace(objMsg.Subject, " ", "")), "MTA?QUERY") > 0) Then
                If boolAll Then
                    Set objFSO = CreateObject("Scripting.FileSystemObject")
                    strSubFolderList = ""
                    strQAAcceleratorFolderName = objFSO.GetFolder(PATH_QA_ACCELERATORS).Name
                    For Each objSubFolder In objFSO.GetFolder(PATH_QA_ACCELERATORS).ParentFolder.SubFolders
                        If (objFSO.FolderExists(objSubFolder.Path) And objSubFolder.Name <> strQAAcceleratorFolderName And objSubFolder.Name <> ".metadata" And objSubFolder.Name <> ".recommenders") Then
                            strSubFolderList = strSubFolderList & vbCrLf & objSubFolder.Name
                        End If
                    Next
                    ReplyToEmail objMsg, "For the list of available test scripts and devices, select a project from below & reply back (expecting only an one word reply)" & vbCrLf & strSubFolderList
                Else
                    Set objFSO = CreateObject("Scripting.FileSystemObject")
                    strSubFolderList = ";"
                    strQAAcceleratorFolderName = objFSO.GetFolder(PATH_QA_ACCELERATORS).Name
                    strPathRootWorkspace = objFSO.GetFolder(PATH_QA_ACCELERATORS).ParentFolder.Path
                    For Each objSubFolder In objFSO.GetFolder(PATH_QA_ACCELERATORS).ParentFolder.SubFolders
                        If (objFSO.FolderExists(objSubFolder.Path) And objSubFolder.Name <> strQAAcceleratorFolderName And objSubFolder.Name <> ".metadata" And objSubFolder.Name <> ".recommenders") Then
                            strSubFolderList = strSubFolderList & objSubFolder.Name & ";"
                        End If
                    Next
                    If InStr(strSubFolderList, ";" & strProjectID) > 0 Then
                        arrPartsToParse = Split(strSubFolderList, ";" & strProjectID)
                        If UBound(arrPartsToParse) > 1 Then
                            ReplyToEmail objMsg, "Sorry, your instruction is ignored: The specified project id matches more than one available projects (" & strProjectID & ")"
                        Else
                            strProjectID = strProjectID & Split(arrPartsToParse(1), ";")(0)
                            strProjectPath = strPathRootWorkspace & "\" & strProjectID
                            strListTestScripts = BatchRunner(strProjectPath, "Query")
                            strListTestScripts = Replace(strListTestScripts, vbCrLf, vbCrLf & "Yes|")
                            objMsg.Subject = "MTA?Execute"
                            ReplyToEmail objMsg, "Make your selection and reply for execution." & vbCrLf & vbCrLf & "ProjectID:" & strProjectID & vbCrLf & strListTestScripts & vbCrLf & "END_OF_LIST"
                        End If
                    Else
                        ReplyToEmail objMsg, "Sorry, your instruction is ignored: Not a valid project id (" & strProjectID & ")"
                    End If
                End If
                Set objMsg = Nothing
            ElseIf (InStr(UCase(Replace(objMsg.Subject, " ", "")), "MTA?EXECUTE") > 0) Then
                Set objFSO = CreateObject("Scripting.FileSystemObject")
                strBody = objMsg.Body
                strValidPortion = Split(Split(strBody, "ProjectID:")(1), "END_OF_LIST")(0)
                strProjectID = Split(strValidPortion, vbCrLf)(0)
                strPathRootWorkspace = objFSO.GetFolder(PATH_QA_ACCELERATORS).ParentFolder.Path
                strProjectPath = strPathRootWorkspace & "\" & strProjectID
                strListTestScripts = ";"
                For Each strLine In Split(strValidPortion, vbCrLf)
                    If (InStr(UCase(Replace(strLine, " ", "")), "YES|") > 0) Then
                        strListTestScripts = strListTestScripts & Trim(Split(strLine, "|")(1)) & ";"
                    End If
                Next
                ReplyToEmail objMsg, "Started executing:" & Replace(strListTestScripts, ";", vbCrLf) & vbCrLf & vbCrLf & "We will send a status update once the execution is complete"
                strResult = BatchRunner(strProjectPath, "Execute " & strListTestScripts)
                objMsg.Subject = "MTA - Execution Completed"
                ReplyToEmail objMsg, "Completed execution. Please find below the status of execution" & vbCrLf & strResult
            Else
                ReplyToEmail objMsg, "Sorry, your inquiry did not qualify any response. Please check the subject line (" & objMsg.Subject & ")"
            End If
        Else
            'This is possibly our own automated email so no response needed in this case.
        End If
    Else
        'These are normal emails and not possibly an inquiry to automated system. No response needed.
    End If
  Else
    'This is not an outlook email. Possibly a meeting invite or similar. No response needed.
  End If
  
End Sub

'-----------------------------
'Utility functions
'-----------------------------

Private Function testingArea()
    displayTextInFile BatchRunner("D:\\My Eclipse Workspace\\PRJ001_MTA_HRBOWeb", "Execute ;testcases.TC002_SecondTestCase;testcases.TC001_FirstTestCase;")
End Function

Private Function BatchRunner(strProjectPath, strParams)
    
    strListJavaLibraryFolders = "Appium;itext-5.5.9;JGoodies;poi-3.14;poi-3.14\lib;poi-3.14\ooxml-lib;SeeTest;selenium-java-2.53.0;selenium-java-2.53.0\libs"
    strCommand = ""
    strCommand = strCommand & "cmd /c D:" & " & "
    strCommand = strCommand & "cd " & strProjectPath & " & "
    strCommand = strCommand & "java -classpath """
    For Each strLibDirectory In Split(strListJavaLibraryFolders, ";")
        strCommand = strCommand & PATH_QA_ACCELERATORS & "\lib\" & strLibDirectory & "\*;"
    Next
    strCommand = strCommand & PATH_QA_ACCELERATORS & "\bin;"
    strCommand = strCommand & strProjectPath & "\bin"" com.ustglobal.common.Packaging$BatchRunner " & strParams
    Set oShell = CreateObject("WScript.Shell")
    'oShell.Run strCommand,
    Set oShellExecute = oShell.exec(strCommand)
    strRetValue = ""
    While Not (oShellExecute.StdOut.AtEndOfStream)
        strRetValue = strRetValue & vbCrLf & oShellExecute.StdOut.ReadLine()
    Wend
    Set oShell = Nothing
    BatchRunner = strRetValue
    
End Function

Private Sub ReplyToEmail(objMsg As Outlook.MailItem, strMessage)
    strPreviousMessage = "-------------------Previous message------------------" & vbCrLf & "from: " + objMsg.Sender.Name & vbCrLf & "to: " & objMsg.ReceivedByName & vbCrLf & "message:" & vbCrLf & objMsg.Body
    With objMsg.Reply
        .Body = strEmailDisclaimer & vbCrLf & vbCrLf & strMessage & vbCrLf & vbCrLf & strPreviousMessage
        '.Display
        .Send
    End With
    objMsg.UnRead = False
End Sub

Sub displayTextInFile(sContent)
    Const sFileTempOutput = "d:\temp_displaytextinfile.txt"
    Set oFSO = CreateObject("Scripting.FileSystemObject")
    Set oFile = oFSO.OpenTextFile(sFileTempOutput, 2, True, -1)
    oFile.Write sContent
    oFile.Close
    Set oFSO = Nothing
    Set oFile = Nothing
    Set oWSH = CreateObject("WScript.Shell")
    oWSH.Run "notepad """ & sFileTempOutput & """"
    Set oFSO = Nothing
    MsgBox "Paused." & vbLf & "File output: " & sFileTempOutput, vbSystemModal
End Sub

Function readFromInputFile()
    rootPath = Left(Wscript.ScriptFullName, (Len(Wscript.ScriptFullName) - Len(Wscript.ScriptName)))
    sInputFile = rootPath & "input.txt"
    Set oFSO = CreateObject("Scripting.FileSystemObject")
    Set oFile = oFSO.OpenTextFile(sInputFile, 1, False, -1)
    readFromInputFile = oFile.ReadAll
    oFile.Close
    Set oFSO = Nothing
    Set oFile = Nothing
End Function
