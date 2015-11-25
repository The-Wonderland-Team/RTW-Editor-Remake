Global nameEdit$ = ""

Global cursorX = 0
Global cursorY = 0
Global textID = 0

Function processInputTextEdit()
	
	;If KeyHit(1) Then
	;	state = 0
	;	Select textID
	;		Case 0
	;			levelTitle = nameEdit
	;	End Select
	;EndIf
	
	If KeyHit(1) Or KeyHit(28) Then
		state = 0
		Select textID
			Case 0
				levelTitle = nameEdit
			Case 1
				levelName = nameEdit
				If Len(levelName) > 0 Then
					saveLevel("CustomLevels/" + levelName + ".LV6")
				EndIf
		End Select
	EndIf
	
	;If KeyHit(208) Then
	;	cursorY = cursorY + 1
	;EndIf
	
	;If KeyHit(200) Then
	;	cursorY = cursorY - 1
	;EndIf
	
	If KeyHit(205) Then
		cursorX = cursorX + 1
	EndIf
	
	If KeyHit(203) Then
		cursorX = cursorX - 1
	EndIf
	
	Local l$
	Local offset
	Local r$
	
	If KeyHit(14) And cursorX > 0 And cursorX <= Len(nameEdit) Then
		l$ = Left(nameEdit, cursorX - 1)
		offset = Len(nameEdit) - Len(l)
		r$ = Right(nameEdit, offset - 1)
		
		nameEdit = l + r
		
		cursorX = cursorX - 1

	EndIf
	
	Local key = GetKey()
	
	If key >= 32 And key <= 126 And Len(nameEdit) < 50 Then
		l$ = Left(nameEdit, cursorX)
		offset = Len(nameEdit)
		r$ = Right(nameEdit, offset - Len(l))
		
		nameEdit = l + Chr(key) + r
		
		If textID = 1 Then
			nameEdit = Upper(nameEdit)
		EndIf
		
		cursorX = cursorX + 1
	EndIf
	
	If cursorX < 0 Then cursorX = 0
	If cursorX > 49 Then cursorX = 49
	If cursorY < 0 Then cursorY = 0
	If cursorY > 1 Then cursorY = 1
	
End Function

Function renderTextEdit()
	
	Select textID
		Case 0
			renderText("Enter Level Name (Maximum Two Lines):", 0, 5)
		Case 1
			renderText("Enter Level Filename (do not include '.LV6')", 0, 5)
	End Select
	
	Select textID
		Case 0
			Color 100, 100, 100
			Rect(0, 32, 800, 64, True)
			
			Color 255, 255, 255
			Rect(-1, 31, 802, 66, False)
		Case 1
			Color 100, 100, 100
			Rect(0, 32, 800, 32, True)
			
			Color 255, 255, 255
			Rect(-1, 31, 802, 34, False)
	End Select
	
	renderText(nameEdit, 0, 35)
	
	renderText("_", cursorX * 16, cursorY * 32 + 35)
	
	Select textID
		Case 0
			renderText("Use Cursor Keys, Delete, and BackSpace to Edit.", 0, 105)
			renderText("Use Cursor Up/Down to move between Lines.", 0, 137)
			renderText("Press 'Enter' or 'Esc' when done.", 0, 169)
	End Select
	
End Function

Function renderDebugTextEdit()

End Function