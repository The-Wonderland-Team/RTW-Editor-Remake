Global nameEdit$ = ""

Global cursorX = 0
Global cursorY = 0
Global textID = 0
Global editSignId = 0

Global lineCount = 1
Global textLines$[4]

Function initTextEdit()
	For i = 0 To 3
		textLines[i] = ""
	Next
	updateText()
End Function

Function updateText()
	loc = 1
	lastLin$ = nameEdit
	;i = 0
	;While Not loc = 0
	
	;Cls
	;renderText("nameEdit: " + nameEdit, 0, 0)
	;renderText("Press any key to continue...", 0, 32)
	;Flip
	;WaitKey
	
	;debugWrite(lineCount - 1)
	
	For i = 0 To lineCount - 1
		
		;If Not i = 0 Then
			
			
			
		;EndIf
		
		If loc = 0 Then Exit
	
		loc = Instr(lastLin, "#")
		
		If loc = 0 Then
			;renderText(lastLin, 0, i*32+35)
			textLines[i] = lastLin
			;debugWrite(lastLin)
		Else
			lin1$ = Left(lastLin, loc-1)
			lin2$ = Mid(lastLin, loc+1)
			
			lastLin = lin2
			
			;debugWrite(lin2, 0)
			
			;renderText(lin1, 0, 35)
			;renderText(lin2, 0, 67)
			
			;renderText(lin1, 0, i*32+35)
			textLines[i] = lin1
			;debugWrite(lin1)
		EndIf
		;i=i+1
		;debugwrite(i, 0)
	;Wend
	Next
End Function

Function processInputTextEdit()
	
	;If KeyHit(1) Then
	;	changeState(0)
	;	Select textID
	;		Case 0
	;			levelTitle = nameEdit
	;	End Select
	;EndIf
	
	If KeyHit(1) Or KeyHit(28) Then
		changeState(0)
		Select textID
			Case 0
				levelTitle = nameEdit
			Case 1
				levelName = nameEdit
				If Len(levelName) > 0 Then
					saveLevel("CustomLevels/" + levelName + ".LV6")
				EndIf
			Case 2
				signText[editSignId] = nameEdit
		End Select
	EndIf
	
	If KeyHit(208) Then
		cursorY = cursorY + 1
	EndIf
	
	If KeyHit(200) Then
		cursorY = cursorY - 1
	EndIf
	
	If KeyDown(205) Then
		cursorX = cursorX + 1
		Delay(50)
	EndIf
	
	If KeyDown(203) Then
		cursorX = cursorX - 1
		Delay(50)
	EndIf
	
	Local l$
	Local offset
	Local r$
	
	;If cursorY = 0 Then
	
		If KeyDown(14) And cursorX > 0 And cursorX <= Len(textLines[cursorY]) Then
			l$ = Left(textLines[cursorY], cursorX - 1)
			offset = Len(textLines[cursorY]) - Len(l)
			r$ = Right(textLines[cursorY], offset - 1)
			
			textLines[cursorY] = l + r
			nameEdit = textLines[0]
			For i = 1 To lineCount - 1
				nameEdit = nameEdit + "#" + textLines[i]
			Next
			
			If Right(nameEdit, 1) = "#" Then
				nameEdit = Left(nameEdit, Len(nameEdit) - 1)
			EndIf
			
			cursorX = cursorX - 1
			
			updateText()
			
			Delay(50)
	
		EndIf
		
		If KeyDown(211) And cursorX < Len(textLines[cursorY]) Then
			l$ = Left(textLines[cursorY], cursorX)
			offset = Len(textLines[cursorY]) - Len(l)
			r$ = Right(textLines[cursorY], offset - 1)
			
			textLines[cursorY] = l + r
			nameEdit = textLines[0]
			For i = 1 To lineCount - 1
				nameEdit = nameEdit + "#" + textLines[i]
			Next
				
			If Right(nameEdit, 1) = "#" Then
				nameEdit = Left(nameEdit, Len(nameEdit) - 1)
			EndIf
			
			updateText()
			
			Delay(50)
	
		EndIf
		
		If key >= 32 And key <= 126 And (Not key = 35) And Len(textLines[cursorY]) < 50 Then
			l$ = Left(textLines[cursorY], cursorX)
			offset = Len(textLines[cursorY])
			r$ = Right(textLines[cursorY], offset - Len(l))
			
			textLines[cursorY] = l + Chr(key) + r
			nameEdit = textLines[0]
			For i = 1 To lineCount - 1
				nameEdit = nameEdit + "#" + textLines[i]
			Next
			
			If Right(nameEdit, 1) = "#" Then
				nameEdit = Left(nameEdit, Len(nameEdit) - 1)
			EndIf
			
			If textID = 1 Then
				nameEdit = Upper(nameEdit)
			EndIf
			
			cursorX = cursorX + 1
			updateText()
		EndIf

		If cursorY < 0 Then cursorY = 0
		If cursorY >= lineCount - 1 Then cursorY = lineCount - 1		
		If cursorX < 0 Then cursorX = 0
		If cursorX > Len(textLines[cursorY]) Then cursorX = Len(textLines[cursorY])
		
	;Else
		
	;	cursorX = 0
		
	;EndIf
	
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
	
	;======Working for 2 Lines======
	;
	;loc = Instr(nameEdit, "#")
	;
	;If loc = 0 Then
	;	renderText(nameEdit, 0, 35)
	;Else
	;	lin1$ = Left(nameEdit, loc-1)
	;	lin2$ = Mid(nameEdit, loc+1)
	;	
	;	renderText(lin1, 0, 35)
	;	renderText(lin2, 0, 67)
	;EndIf
	
	;loc = 1
	
	;i = 0
	
	;lastLin$ = nameEdit
	
	;While Not loc = 0
	;	
	;	loc = Instr(lastLin, "#")
	;	
	;	If loc = 0 Then
	;		renderText(lastLin, 0, i*32+35)
	;	Else
	;		lin1$ = Left(lastLin, loc-1)
	;		lin2$ = Mid(lastLin, loc+1)
	;		
	;		lastLin = lin2
	;		
	;		;renderText(lin1, 0, 35)
	;		;renderText(lin2, 0, 67)
	;		
	;		renderText(lin1, 0, i*32+35)
	;	EndIf
	;	i=i+1
	;	
	;Wend
	
	For i = 0 To lineCount - 1
		renderText(textLines[i], 0, i*32+35)
	Next
	
	;renderText(lastLin, 0, (i*32)+35)
	
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