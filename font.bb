Global fontpiece1 = LoadAnimImage("graphicseditor/fontpiece1.bmp", 16, 32, 0, 16)
Global fontpiece2 = LoadAnimImage("graphicseditor/fontpiece2.bmp", 16, 32, 0, 16)
Global fontpiece3 = LoadAnimImage("graphicseditor/fontpiece3.bmp", 16, 32, 0, 16)
Global fontpiece4 = LoadAnimImage("graphicseditor/fontpiece4.bmp", 16, 32, 0, 16)
Global fontpiece5 = LoadAnimImage("graphicseditor/fontpiece5.bmp", 16, 32, 0, 16)
Global fontpiece6 = LoadAnimImage("graphicseditor/fontpiece6.bmp", 16, 32, 0, 16)

Function renderText(msg$, x, y)
	For i = 0 To Len(msg)
		Local char$ = Mid(msg, i+1, 1)
		renderCharacter(char, i*16+x, y)
	Next
End Function

Function renderTextCenter(msg$, x, y)
	
	x = x - getStringWidth(msg) / 2
	
	renderText(msg, x, y)
	
End Function

Function renderCharacter(char$, x, y)
	
	Local ascii = Asc(char)
	If (ascii < 0) Then
		Return
	EndIf
	Local charId = ascii - 32
	
	If charId >= 80 And charId < 96 Then
		DrawImage(fontpiece6, x, y, charId - 80)
	ElseIf charId >= 64 Then
		DrawImage(fontpiece5, x, y, charId - 64)
	ElseIf charId >= 48 Then
		DrawImage(fontpiece4, x, y, charId - 48)
	ElseIf charId >= 32 Then
		DrawImage(fontpiece3, x, y, charId - 32)
	ElseIf charId >= 16 Then
		DrawImage(fontpiece2, x, y, charId - 16)
	ElseIf charId < 16 Then
		DrawImage(fontpiece1, x, y, charId)
	EndIf
End Function

Function getStringWidth(msg$)
	Return Len(msg)*16
End Function