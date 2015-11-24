Global selectedMenu = -1

Function processInputMenu()
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
			selectedMenu = -1
			ExecFile("http://www.midnightsynergy.com/returntowonderland/editormanual")
			Delay(100)
			running = False
		Case 3
			selectedMenu = -1
			Delay(100)
			running = False
		Case 4
			selectedMenu = -1
			ExecFile("wdlgamep.exe")
			Delay(100)
			running = False
	End Select
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