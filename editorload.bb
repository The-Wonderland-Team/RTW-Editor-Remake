Global levelList$[1000000]
Global levelListTitle$[1000000]
Global levelCount = 0

Function processInputLoad()
	
	If MouseHit(1) Then
		If MouseX() > 672 And MouseY() < 32 Then
			state = 0
		EndIf
		
		If MouseY() > 64 And MouseY() < 544 Then
			i = MouseY() - 64
			index = i / 32
			
			file$ = levelList[index]
			
			loadLevel("CustomLevels/" + file)
			state = 0
			Delay(100)
		EndIf
	EndIf
	
End Function

Function renderLoad()
	renderText("Select Level File To Load:", 0, 0)
	
	renderText("[CANCEL]", 672, 0)
	
	For i = 0 To 25
		DrawImage(arrows, i*32, 32, 4)
	Next
	
	For i = 0 To levelCount - 1
		file$ = levelList[i]
		offset = Len(file) - 4
		name$ = Upper(Left(file, offset))
		renderText(name, 0, i*32+64)
	Next
	
	For i = 0 To levelCount
		renderText(levelListTitle[i], 240, i*32+64)
	Next
	
	For i = 0 To 25
		DrawImage(arrows, i*32, 544, 6)
	Next
End Function

Function renderDebugLoad()
	Text(0, 32, "Level Count: " + levelCount)
End Function

Function loadLevelList()
	state = 3
	
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