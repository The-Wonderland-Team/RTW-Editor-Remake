Global levelName$ = "level"
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
	
	
	
	If header = "Stinky & Loof Level File v3" Then
		Local unknown# = ReadFloat(file)
	
		levelTitle = ReadString(file)
	
		timer = ReadInt(file)
	ElseIf header = "Stinky & Loof Level File v5" Then
		
		signCount = 20
			
		dummy = ReadInt(file)
			
		levelTitle = ReadString(file)
	
		dummy = ReadInt(file)
		
		customHouse = ReadString(file)
		
		dummy = ReadInt(file)
		
		customModel = ReadString(file)
		
		timer = ReadInt(file)
		
	ElseIf header = "Stinky & Loof Level File v6" Then
		signCount = 20
		
		dummy = ReadInt(file)
		
		levelTitle = ReadString(file)
	
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
	
	WriteInt(file, 0)
	
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
	
	i = 0
	
	For x = 0 To width - 1
		For y = 0 To height - 1
			WriteInt(file, PeekInt(objects, i*4))
			i=i+1
		Next
	Next
	
	For i = 0 To 19
		WriteString(file, signText[i])
	Next
	
	WriteInt(file, levelMusic)
	
	CloseFile(file)
	
	Return True
End Function