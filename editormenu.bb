Global selectedMenu = -1

Function processInputMenu()
	If didConfirmResponse() Then
		If didConfirm() Then
			Select selectedMenu
				Case 2
					openManual()
				Case 3
					quitEditor()
				Case 4
					returnToGame()
			End Select
		EndIf
		selectedMenu = -1
	EndIf
	
	If MouseY() > 200 And MouseY() < 360 Then
		i = MouseY() - 200
		
		selectedMenu = i / 32
	Else
		selectedMenu = -1
	EndIf
	
	If MouseHit(1) Then
		selectMenu(selectedMenu)
	EndIf
End Function

Function selectMenu(id)
	Select id
		Case 0
			selectedMenu = -1
			state = 0
			Delay(100)
		Case 1
			selectedMenu = -1
			loadLevelList()
			Delay(100)
		Case 2
			confirm("CONNECT TO INTERNET FOR ONLINE MANUAL?")
			Delay(100)
		Case 3
			confirm("EXIT TO WINDOWS?")
			Delay(100)
		Case 4
			confirm("RETURN TO GAME?")
			Delay(100)
	End Select
End Function

Function openManual()
	ExecFile("http://www.midnightsynergy.com/returntowonderland/editormanual")
	Delay(100)
	running = False
End Function

Function quitEditor()
	Delay(100)
	running = False
End Function

Function returnToGame()
	ExecFile("wdlgamep.exe")
	Delay(100)
	running = False
End Function

Function renderMenu()
	DrawImage(menuBkg, 0, 0)
	
	renderTextCenter("Return To Wonderland Platinum", 400, 10)
	renderTextCenter("-----------------------------", 400, 32)
	
	renderTextCenter("Level Editor v3.00", 400, 64)
	renderTextCenter("===========================", 400, 96)
	
	renderTextCenter("MAIN MENU", 400, 150)
	
	;Buttons
	
	renderTextCenter("New Level", 400, 200)
	renderTextCenter("Load Level", 400, 232)
	renderTextCenter("Online Manual", 400, 264)
	renderTextCenter("Exit to Windows", 400, 296)
	renderTextCenter("Return To Game", 400, 328)
	
	If Not selectedMenu = -1 Then
		Color 255, 255, 0
		Rect(200, selectedMenu * 32 + 196, 400, 32, False)
	EndIf
	
	;End of Buttons
	
	renderTextCenter("Use The Mouse To Select Your Menu Option", 400, 500)
	renderTextCenter("Copyright (C) 2006 Midnight Synergy", 400, 568)
End Function

Function renderDebugMenu()
	Text(0, 32, "Selected: " + selectedMenu)
End Function