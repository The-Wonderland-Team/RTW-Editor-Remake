Global selectedConfirm = -1

Global confirmMessage$ = ""
Global confirmed = False
Global didConfirmRes = False

Function initConfirm()

End Function

Function processInputConfirm()
	If MouseY() > 200 And MouseY() < 264 Then
		i = MouseY() - 200
		
		selectedConfirm = i / 32
	Else
		selectedConfirm = -1
	EndIf
	
	If MouseHit(1) Then
		selectConfirm(selectedConfirm)
	EndIf
End Function

Function selectConfirm(id)
	Select id
		Case 0
			useRenderState = True
			selectedConfirm = -1
			changeState(prevState)
			confirmed = True
			Delay(100)
			;useRenderState = False
		Case 1
			useRenderState = False
			selectedConfirm = -1
			changeState(prevState)
			confirmed = False
			Delay(100)
			;useRenderState = False
	End Select
	didConfirmRes = True
End Function

Function renderConfirm()
	DrawImage(menuBkg, 0, 0)
	
	renderTextCenter("Return To Wonderland Platinum", 400, 10)
	renderTextCenter("-----------------------------", 400, 32)
	
	renderTextCenter("Level Editor v3.00", 400, 64)
	renderTextCenter("===========================", 400, 96)
	
	renderTextCenter(confirmMessage, 400, 150)
	
	;Buttons
	
	renderTextCenter("Yes, Please", 400, 200)
	renderTextCenter("No, Thanks!", 400, 232)
	
	If Not selectedConfirm = -1 Then
		Color 255, 255, 0
		Rect(200, selectedConfirm * 32 + 196, 400, 32, False)
	EndIf
	
	;End of Buttons
	
	renderTextCenter("Use The Mouse To Select Your Menu Option", 400, 500)
	;renderTextCenter("Copyright (C) 2006 Midnight Synergy", 400, 568)
End Function

Function renderDebugConfirm()
	Text(0, 32, "Selected: " + selectedConfirm)
End Function

Function confirm(msg$)
	
	prevState = state
	
	changeState(2)
	
	confirmMessage = msg
	
	didConfirmRes = False
	
End Function

Function didConfirmResponse()
	Return didConfirmRes
End Function

Function resetConfirmResponse()
	didConfirmRes = False
End Function

Function didConfirm()
	Return confirmed
End Function