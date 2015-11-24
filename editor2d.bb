Graphics 800,600, 32, 2
AppTitle "Editor"
SetBuffer BackBuffer()

Include "global.bb"
Include "font.bb"
Include "leveldata.bb"
Include "tilecategories.bb"
Include "editormain.bb"
Include "editormenu.bb"
Include "editorconfirm.bb"
Include "editorload.bb"

HidePointer()

Global mseX = 0
Global mseY = 0

Global state = 1

Global running = 1

While running

	Cls()
	
	processInput()
	render()
	
	If debugMode Then
		renderDebug()
	EndIf
	Flip
	
Wend

End

Function processInput()
	
	If KeyHit(1) Then
		running = 0
	EndIf
	
	mseX = MouseX()
	mseY = MouseY()
	
	Select state
		Case 0
			processInputMain()
		Case 1
			processInputMenu()
		Case 2
			processInputConfirm()
		Case 3
			processInputLoad()
	End Select
	
	If KeyDown(29) And KeyHit(65) And allowDebug Then
		debugMode = Not debugMode
	EndIf
	
End Function

Function render()
	
	Select state
		Case 0
			renderMain()
		Case 1
			renderMenu()
		Case 2
			renderConfirm()
		Case 3
			renderLoad()
	End Select
	
	DrawImage(mouse, mseX, mseY)
	
End Function

Function renderDebug()
	
	Color 255, 255, 255
	Text(0, 0, "v1.0 (Debug Mode)")
	
	Select state
		Case 0
			renderDebugMain()
		Case 1
			renderDebugMenu()
		Case 2
			renderDebugConfirm()
		Case 3
			renderDebugLoad()
	End Select
	
End Function