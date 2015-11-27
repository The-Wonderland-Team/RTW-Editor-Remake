Global levelList$[1000000]
Global levelListTitle$[1000000]
Global levelCount = 0

Global loadOffset = 0

Function initLoad()

End Function

Function processInputLoad()

	;If loadOffset + 10 > levelCount Then loadOffset = levelCount - 10
	;If loadOffset < 0 Then loadOffset = 0
	
	If MouseHit(1) Then
		If MouseX() > 672 And MouseY() < 32 Then
			changeState(0)
		EndIf
		
		If MouseY() > 64 And MouseY() < 544 Then
			i = MouseY() - 64
			index = (i / 32) + loadOffset
			
			file$ = levelList[index]
			
			loadLevel("CustomLevels/" + file)
			changeState(0)
			Delay(100)
		EndIf
	EndIf
	
	If MouseDown(1) Then
		If MouseY() > 32 And MouseY() < 64 Then
			If loadOffset > 0 Then
				loadOffset = loadOffset - 1
			EndIf
			Delay(100)
		EndIf
		
		If MouseY() > 544 And MouseY() < 576 Then
			If loadOffset + 15 < levelCount Then
				loadOffset = loadOffset + 1
			EndIf
			Delay(100)
		EndIf
	EndIf
	
End Function

Function renderLoad()
	renderText("Select Level File To Load:", 0, 0)
	
	renderText("[CANCEL]", 672, 0)
	
	If loadOffset > 0 Then
		For i = 0 To 25
			DrawImage(arrows, i*32, 32, 0)
		Next
	Else
		For i = 0 To 25
			DrawImage(arrows, i*32, 32, 4)
		Next
	EndIf
	
	renderCount = levelCount - 1
	
	If renderCount > 14 Then renderCount = 14
	
	For i = loadOffset To renderCount + loadOffset
		file$ = levelList[i]
		offset = Len(file) - 4
		name$ = Upper(Left(file, offset))
		renderText(name, 0, (i-loadOffset)*32+64)
	Next
	
	For i = loadOffset To renderCount + loadOffset
		renderText(levelListTitle[i], 240, (i-loadOffset)*32+64)
	Next
	
	If loadOffset + 15 < levelCount Then
		For i = 0 To 25
			DrawImage(arrows, i*32, 544, 2)
		Next
	Else
		For i = 0 To 25
			DrawImage(arrows, i*32, 544, 6)
		Next
	EndIf
End Function

Function renderDebugLoad()
	Text(0, 32, "Level Count: " + levelCount)
End Function

Function loadLevelList()
	changeState(3)
	
	Local numLevels
	Local i = 0
	
	levelsDir = ReadDir("CustomLevels")
	
	Repeat
		
		file$ = NextFile(levelsDir)
		If file = "" Then Exit
		
		If FileType("CustomLevels/" + file) = 1 And Len(file) > 4 Then
			ext$ = Lower(Right(file, 4))
			If (ext = ".lev" Or ext = ".lv6" Or ext = ".lv5") Then
				levelList[i] = file
				levelListTitle[i] = loadLevelTitle("CustomLevels/" + file)
				i=i+1
			EndIf
		EndIf
		
	Forever
	
	CloseDir(levelsDir)
	
	levelCount = i
	
	loadOffset = 0
	
End Function

Function loadLevelTitle$(path$)
	
	file = ReadFile(path)
	
	Local header$ = ReadString(file)
	
	Local listLevelTitle$
	
	ReadString(file)
	
	If header = "Stinky & Loof Level File v3" Then
		ReadFloat(file)
	
		listLevelTitle = ReadString(file)
	ElseIf header = "Stinky & Loof Level File v5" Or header = "Stinky & Loof Level File v6" Then
			
		ReadInt(file)
			
		listLevelTitle = ReadString(file)
	EndIf
	
	CloseFile(file)
	
	Return listLevelTitle
	
End Function