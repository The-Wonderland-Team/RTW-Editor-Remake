Global levelName$ = ""
Global timer = 300
Global levelTitle$ = "Untitled Level"
Global customHouse$ = ""
Global customModel$ = ""
Global customTexture$ = ""
Global customBackground$ = ""
Global levelTexture = 2
Global levelBackground = 0
Global width = 14
Global height = 14
Global levelID = Rnd(100000000, 999999999)

Global tiles = CreateBank(14 * 14 * 4)
Global objects = CreateBank(14 * 14 * 4)

Global signText$[20]

Global levelMusic = 1

Function loadLevel(path$)
	If Not FileType(path) = 1 Then
		Return False
	EndIf
	
	file = ReadFile(path)
	
	Local header$ = ReadString(file)
	
	Local signCount = 5
	
	Local dummy = 0
	
	levelName = ReadString(file)
	
	levelID = ReadInt(file)
	
	levelTitle = ReadString(file)
	
	If header = "Stinky & Loof Level File v3" Then
	
		timer = ReadInt(file)
	ElseIf header = "Stinky & Loof Level File v5" Then
		
		signCount = 20
	
		dummy = ReadInt(file)
		
		customHouse = ReadString(file)
		
		dummy = ReadInt(file)
		
		customModel = ReadString(file)
		
		timer = ReadInt(file)
		
	ElseIf header = "Stinky & Loof Level File v6" Then
		signCount = 20
	
		dummy = ReadInt(file)
		
		customHouse = ReadString(file)
		
		dummy = ReadInt(file)
		
		customModel = ReadString(file)
		
		dummy = ReadInt(file)
		
		customTexture = ReadString(file)
		
		dummy = ReadInt(file)
		
		customBackground = ReadString(file)

		timer = ReadInt(file)
	
	EndIf
	
	levelTexture = ReadInt(file)
	levelBackground = ReadInt(file)
	
	width = ReadInt(file)
	height = ReadInt(file)
	
	tiles = CreateBank(width * height * 4)
	objects = CreateBank(width * height * 4)
	
	Local i = 0
	
	For x = 0 To width - 1
		For y = 0 To height - 1
			PokeInt(tiles, i*4, ReadInt(file))
			i=i+1
		Next
	Next
	
	i = 0
	
	For x = 0 To width - 1
		For y = 0 To height - 1 
			PokeInt(objects, i*4, ReadInt(file))
			i=i+1
		Next
	Next
	
	If (Not header = "Stinky & Loof Level File v6") And (Not header = "Stinky & Loof Level File v5") Then
		For i = 0 To (width - 1) * (height - 1)
			Local obj = PeekInt(objects, i*4)
			If obj > 2 Then
				PokeInt(objects, i*4, obj+2)
			EndIf
		Next
	EndIf
	
	For i = 0 To signCount - 1
		signText[i] = ReadString(file)
	Next
	
	levelMusic = ReadInt(file)
	
	CloseFile(file)
	
	Return True
End Function

Function saveLevel(path$)
	file = WriteFile(path)
	
	WriteString(file, "Stinky & Loof Level File v6")
	
	WriteString(file, levelName)
	
	WriteInt(file, levelID)
	
	WriteString(file, levelTitle)
	
	WriteInt(file, 0)
	
	WriteString(file, customHouse)
	
	WriteInt(file, 0)
	
	WriteString(file, customModel)
	
	WriteInt(file, 0)
	
	WriteString(file, customTexture)
	
	WriteInt(file, 0)
	
	WriteString(file, customBackground)
	
	WriteInt(file, timer)
	
	WriteInt(file, levelTexture)
	
	WriteInt(file, levelBackground)
	
	WriteInt(file, width)
	
	WriteInt(file, height)
	
	Local i = 0
	
	For x = 0 To width - 1
		For y = 0 To height - 1
			WriteInt(file, PeekInt(tiles, i*4))
			i=i+1
		Next
	Next
	
	foundPlayer = False
	
	i = 0
	
	For x = 0 To width - 1
		For y = 0 To height - 1
			theObj = PeekInt(objects, i*4)
			WriteInt(file, theObj)
			
			If theObj = 1 Or theObj = 2 Or theObj = 3 Or theObj = 4 Then
				foundPlayer = True
			EndIf
			
			i=i+1
		Next
	Next
	
	If Not foundPlayer Then
		PokeInt(objects, (width+1) * 4, 1)
		WriteInt(file, 1)
	EndIf
	
	For i = 0 To 19
		WriteString(file, signText[i])
	Next
	
	WriteInt(file, levelMusic)
	
	CloseFile(file)
	
	Return True
End Function

Function resetLevel()
	
	levelName = ""
	timer = 300
	levelTitle = "Untitled Level"
	customHouse = ""
	customModel = ""
	customTexture = ""
	customBackground = ""
	levelTexture = 2
	levelBackground = 0
	width = 14
	height = 14
	levelID = Rnd(100000000, 999999999)
	
	FreeBank(tiles)
	tiles = CreateBank(14 * 14 * 4)
	FreeBank(objects)
	objects = CreateBank(14 * 14 * 4)
	
	For i = 0 To 19	
		signText[i] = ""
	Next
	
	levelMusic = 1
	
	setupDefaultTiles()
	setupDefaultObjects()
	
	cameraX = 0
	cameraY = 0
	
End Function

Function setupDefaultTiles()
	For x = 0 To 13
		For y = 0 To 13
			setTile(x, y, 1, 0)
		Next
	Next
	
	For x = 0 To 13
		For y = 0 To 13
			If x = 0 Or y = 0 Or x = 13 Or y = 13 Then
				setTile(x, y, 2, 0)
			EndIf
		Next
	Next
End Function

Function setupDefaultObjects()
	For x = 0 To 13
		For y = 0 To 13
			setObject(x, y, 0)
		Next
	Next
End Function

Function setTile(x, y, cat, id)
	
	If x >= 0 And x <= width And y >= 0 And y <= height Then
		;tiles[x + y * 14] = cat * 100 + id
		PokeInt(tiles, (x + y * width) * 4, cat * 100 + id)
	EndIf
	
End Function

Function setObject(x, y, id)
	If x >= 0 And x <= width And y >= 0 And y <= height Then
		;objects[x + y * 14] = id
		PokeInt(objects, (x + y * width) * 4, id)
	EndIf
	
End Function

Function getTile(x, y)
	If x >= 0 And x <= width And y >= 0 And y <= height Then
		;Return tiles[x + y * 14]
	
		Return PeekInt(tiles, (x + y * width) * 4)
	EndIf
	
End Function

Function getObject(x, y)
	If x >= 0 And x <= width And y >= 0 And y <= height Then
		;Return objects[x + y * 14]
		Return PeekInt(objects, (x + y * width) * 4)
	EndIf
	
End Function