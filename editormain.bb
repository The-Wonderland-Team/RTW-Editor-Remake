Global tileEmpty = LoadAnimImage("graphicseditor/ed-empty.bmp", 32, 32, 0, 1)
Global tileFloor = LoadAnimImage("graphicseditor/ed-floorv5.bmp", 32, 32, 0, 5)
Global tileWall = LoadAnimImage("graphicseditor/ed-wallv5.bmp", 32, 32, 0, 4)
Global tileWater = LoadAnimImage("graphicseditor/ed-water.bmp", 32, 32, 0, 1)
Global tileIce = LoadAnimImage("graphicseditor/ed-ice.bmp", 32, 32, 0, 5)
Global tileConveyor = LoadAnimImage("graphicseditor/ed-conveyorv5.bmp", 32, 32, 0, 16)
Global tileBridges = LoadAnimImage("graphicseditor/ed-breakawayv5.bmp", 32, 32, 0, 9)
Global tileElectro = LoadAnimImage("graphicseditor/ed-electro.bmp", 32, 32, 0, 4)
Global tileCannons = LoadAnimImage("graphicseditor/ed-cannon.bmp", 32, 32, 0, 16)
Global tileLava = LoadAnimImage("graphicseditor/ed-lava.bmp", 32, 32, 0, 1)
Global tileGates = LoadAnimImage("graphicseditor/ed-gatev5.bmp", 32, 32, 0, 18)
Global tileButtons = LoadAnimImage("graphicseditor/ed-buttonsv5.bmp", 32, 32, 0, 27)
Global tileTeleporters = LoadAnimImage("graphicseditor/ed-teleportv5b.bmp", 32, 32, 0, 8)
Global tileSigns = LoadAnimImage("graphicseditor/ed-signsv5.bmp", 32, 32, 0, 20)
Global tileSpikes = LoadAnimImage("graphicseditor/ed-spikes.bmp", 32, 32, 0, 4)
Global tileFakeWalls = LoadAnimImage("graphicseditor/ed-fakev5.bmp", 32, 32, 0, 3)
Global tileBoxFactory = LoadAnimImage("graphicseditor/ed-boxerv5b.bmp", 32, 32, 0, 13)
Global tileTransporters = LoadAnimImage("graphicseditor/ed-transporter.bmp", 32, 32, 0, 3)
Global tileTrampolines = LoadAnimImage("graphicseditor/ed-trampoline.bmp", 32, 32, 0, 1)
Global tilePushCannons = LoadAnimImage("graphicseditor/ed-pushcannon.bmp", 32, 32, 0, 16)
Global tileStickyCubes = LoadAnimImage("graphicseditor/ed-sticky.bmp", 32, 32, 0, 1)
Global tileLinkSpheres = LoadAnimImage("graphicseditor/ed-linksphere.bmp", 32, 32, 0, 4)
Global tileWarpGates = LoadAnimImage("graphicseditor/ed-warpgates.bmp", 32, 32, 0, 14)
Global tile3DView = LoadAnimImage("graphicseditor/ed-3dview.bmp", 32, 32, 0, 1)
Global tileShadowStinks = LoadAnimImage("graphicseditor/ed-shadowstinks.bmp", 32, 32, 0, 8)

Global objectsPanel = LoadAnimImage("graphicseditor/objectsv5b.bmp", 16, 16, 0, 90)

setupDefaultTiles()
setupDefaultObjects()

Global closeClicked = False

Global tileHighlight = 0
Global objectHighlight = 0

Global specialUnlocked = False

Global selectedID = 0

Global selectObjects = False

Global cameraX = 0
Global cameraY = 0

Global sx = 0
Global sy = 0

Function processInputMain()

Local mx = 0
Local my = 0
;Local sx = 0
;Local sy = 0
Local id = 0
Local limit = 0
Local tilesNew
Local objectsNew

If didConfirmResponse() Then
	If didConfirm() Then
		If closeClicked Then
			doEditorClose()
		EndIf
		closeClicked = False
	EndIf
	selectedMenu = -1
EndIf
	
If MouseDown(1) Then
	If selectObjects Then
		setMouseObject(MouseX(), MouseY(), selectedID)
	Else
		setMouseTile(MouseX(), MouseY(), currentTileCategory, selectedID)
	EndIf
EndIf

If MouseX() > 48 And MouseX() < 496 And MouseY() > 48 And MouseY() < 496 Then
	
	mx = MouseX() - 48
	my = MouseY() - 48
	
	tx = mx / 32
	ty = my / 32
	
	tileHighlight = getTile(tx, ty)
	objectHighlight = getObject(tx, ty)
	
EndIf

If MouseHit(1) Then
	
	If MouseX() > 544 And MouseX() < 791 And MouseY() > 12 And MouseY() < 44 Then
		renameLevel()
	EndIf
	
	;ref:timer
	If MouseX() > 544 And MouseX() < 576 And MouseY() > 88 And MouseY() < 120 Then
		If timer > 10 Then
			timer = timer - 10
		EndIf
	EndIf
	
	If MouseX() > 760 And MouseX() < 792 And MouseY() > 88 And MouseY() < 120 Then
		If timer < 900 Then
			timer = timer + 10
		EndIf
	EndIf

	If MouseX() > 544 And MouseX() < 792 And MouseY() > 502 And MouseY() < 530 Then
		levelMusic = levelMusic + 1
		If levelMusic > 6 Then
			levelMusic = 1
		EndIf
	EndIf
	
	If MouseX() > 753 And MouseX() < 796 And MouseY() > 547 And MouseY() < 588 Then
		handleCloseClick()
	EndIf
	
	If MouseX() > 607 And MouseX() < 721 And MouseY() > 548 And MouseY() < 587 Then
		handleSaveClick()
	EndIf
	
	If MouseX() > 487 And MouseX() < 601 And MouseY() > 548 And MouseY() < 587 Then
		handleLoadClick()
	EndIf
	
	If MouseX() > 544 And MouseX() < 576 And MouseY() > 192 And MouseY() < 224 Then
		currentTileCategory = currentTileCategory - 1
		selectedID = 0
		selectObjects = False
		
		If currentTileCategory < 0 Then
			currentTileCategory = numTileCategories - 1
		EndIf
		
	EndIf
	If MouseX() > 760 And MouseX() < 792 And MouseY() > 192 And MouseY() < 224 Then
		currentTileCategory = currentTileCategory + 1
		selectedID = 0
		selectObjects = False
		
		If currentTileCategory >= numTileCategories Then
			currentTileCategory = 0
		EndIf
		
	EndIf
	

	If MouseX() >= 556 And MouseX() < 780 And MouseY() >= 228 And MouseY() < 356 Then
		mx = MouseX() - 556
		my = MouseY() - 228
		
		sx = mx / 32
		sy = my / 32
		
		id = sx + sy * 7
		
		If id < getCurrentCategoryTileCount() And id >= 0 Then
		
			selectedID = id
			selectObjects = False
		
		EndIf
	EndIf

	If MouseX() >= 548 And MouseX() < 788 And MouseY() >= 392 And MouseY() < 488 Then
		mx = MouseX() - 548
		my = MouseY() - 392
		
		sx = mx / 16
		sy = my / 16
		
		id = sx + sy * 15
		
		limit = 88
		
		If specialUnlocked Then
			limit = 90
		EndIf
		
		If id < limit And id >= 0 Then
		
			selectedID = id
			selectObjects = True
		
		EndIf
	EndIf
	
	If MouseX() > 544 And MouseX() < 791 And MouseY() > 124 And MouseY() < 156 Then
		
		levelTexture=levelTexture+1
		
		If levelTexture > 9 Then
			levelTexture = 0
		ElseIf levelTexture < 0 Then
			levelTexture = 9
		EndIf
		
	EndIf
	
	If MouseX() > 544 And MouseX() < 791 And MouseY() > 156 And MouseY() < 188 Then
		
		levelBackground=levelBackground+1
		
		If levelBackground > 10 Then
			levelBackground = 0
		ElseIf levelBackground < 0 Then
			levelBackground = 10
		EndIf
		
	EndIf
	
	;ref:resize
	
	;left+
	If MouseX() >= 565 And MouseX() < 597 And MouseY() >= 44 And MouseY() < 76 And width < 99 Then
		tilesNew = CreateBank((width+1)*height*4)
		objectsNew = CreateBank((width+1)*height*4)
		
		For x = 0 To width - 1
			For y = 0 To height - 1
				PokeInt(tilesNew, (x + 1 + y * (width+1)) * 4, PeekInt(tiles, (x + y * (width)) * 4))
				If x = 1 Then
					If y > 0 And y < height - 1 Then
						PokeInt(tilesNew, (x + y * (width+1)) * 4, 100)
					Else
						PokeInt(tilesNew, (x + y * (width+1)) * 4, 200)
					EndIf
				ElseIf x = 0 Then
					PokeInt(tilesNew, (x + y * (width+1)) * 4, 200)
				EndIf
			Next
		Next
		
		For x = 0 To width - 1
			For y = 0 To height - 1
				PokeInt(objectsNew, (x + 1 + y * (width+1)) * 4, PeekInt(objects, (x + y * (width)) * 4))
			Next
		Next
		FreeBank(tiles)
		tiles = tilesNew
		FreeBank(objects)
		objects = objectsNew
		width=width+1
	EndIf
	
	;right+
	If MouseX() >= 629 And MouseX() < 661 And MouseY() >= 44 And MouseY() < 76 And width < 99 Then
		tilesNew = CreateBank((width+1)*height*4)
		objectsNew = CreateBank((width+1)*height*4)
		
		For x = 0 To width
			For y = 0 To height - 1
				If x = width - 1 Then
					If y > 0 And y < height - 1 Then
						PokeInt(tilesNew, (x + y * (width+1)) * 4, 100)
					Else
						PokeInt(tilesNew, (x + y * (width+1)) * 4, 200)
					EndIf
				ElseIf x = width Then
					PokeInt(tilesNew, (x + y * (width+1)) * 4, 200)
				Else
					PokeInt(tilesNew, (x + y * (width+1)) * 4, PeekInt(tiles, (x + y * (width)) * 4))
				EndIf
			Next
		Next
		
		For x = 0 To width - 1
			For y = 0 To height - 1
				PokeInt(objectsNew, (x + y * (width+1)) * 4, PeekInt(objects, (x + y * (width)) * 4))
			Next
		Next
		FreeBank(tiles)
		tiles = tilesNew
		FreeBank(objects)
		objects = objectsNew
		width=width+1
	EndIf
	
	;down+
	If MouseX() >= 741 And MouseX() < 773 And MouseY() >= 44 And MouseY() < 76 And width < 99 Then
		tilesNew = CreateBank(width*(height+1)*4)
		objectsNew = CreateBank(width*(height+1)*4)
		
		For x = 0 To width - 1
			For y = 0 To height
				If y = height - 1 Then
					If x > 0 And x < width - 1 Then
						PokeInt(tilesNew, (x + y * (width)) * 4, 100)
					Else
						PokeInt(tilesNew, (x + y * (width)) * 4, 200)
					EndIf
				ElseIf y = height Then
					PokeInt(tilesNew, (x + y * (width)) * 4, 200)
				Else
					PokeInt(tilesNew, (x + y * (width)) * 4, PeekInt(tiles, (x + y * (width)) * 4))
				EndIf
			Next
		Next
		
		For x = 0 To width - 1
			For y = 0 To height - 1
				PokeInt(objectsNew, (x + y * (width)) * 4, PeekInt(objects, (x + y * (width)) * 4))
			Next
		Next
		FreeBank(tiles)
		tiles = tilesNew
		FreeBank(objects)
		objects = objectsNew
		height=height+1
	EndIf
	
	;up+
	If MouseX() >= 677 And MouseX() < 709 And MouseY() >= 44 And MouseY() < 76 And width < 99 Then
		tilesNew = CreateBank(width*(height+1)*4)
		objectsNew = CreateBank(width*(height+1)*4)
		
		For x = 0 To width - 1
			For y = 0 To height - 1
				PokeInt(tilesNew, (x + (y + 1) * (width)) * 4, PeekInt(tiles, (x + y * (width)) * 4))
				If y = 1 Then
					If x > 0 And x < width - 1 Then
						PokeInt(tilesNew, (x + y * (width)) * 4, 100)
					Else
						PokeInt(tilesNew, (x + y * (width)) * 4, 200)
					EndIf
				ElseIf y = 0 Then
					PokeInt(tilesNew, (x + y * (width)) * 4, 200)
				EndIf
			Next
		Next
		
		For x = 0 To width - 1
			For y = 0 To height - 1
				PokeInt(objectsNew, (x + (y + 1) * (width)) * 4, PeekInt(objects, (x + y * (width)) * 4))
			Next
		Next
		FreeBank(tiles)
		tiles = tilesNew
		FreeBank(objects)
		objects = objectsNew
		height=height+1
	EndIf


EndIf

If MouseHit(2) Then
	;left-
	If MouseX() >= 565 And MouseX() < 597 And MouseY() >= 44 And MouseY() < 76 And width > 14 Then
		tilesNew = CreateBank((width-1)*height*4)
		objectsNew = CreateBank((width-1)*height*4)
		
		For x = 0 To width - 1
			For y = 0 To height - 1
				PokeInt(tilesNew, (x - 1 + y * (width-1)) * 4, PeekInt(tiles, (x + y * (width)) * 4))
				;If x = 0 Then
				;	If y > 0 And y < height - 1 Then
				;		PokeInt(tilesNew, (x - 1 + y * (width-1)) * 4, 100)
				;	Else
				;		PokeInt(tilesNew, (x - 1 + y * (width-1)) * 4, 200)
				;	EndIf
				;Else
				If x = 1 Then
					PokeInt(tilesNew, (x - 1 + y * (width-1)) * 4, 200)
				EndIf
			Next
		Next
		
		For x = 0 To width - 1
			For y = 0 To height - 1
				PokeInt(objectsNew, (x - 1 + y * (width-1)) * 4, PeekInt(objects, (x + y * (width)) * 4))
			Next
		Next
		FreeBank(tiles)
		tiles = tilesNew
		FreeBank(objects)
		objects = objectsNew
		width=width-1
		
		If cameraX + 13 > width - 1 Then
			cameraX = width - 14
		EndIf
	EndIf

	;right-
	If MouseX() >= 629 And MouseX() < 661 And MouseY() >= 44 And MouseY() < 76 And width > 14 Then
		tilesNew = CreateBank((width-1)*height*4)
		objectsNew = CreateBank((width-1)*height*4)
		
		For x = 0 To width - 1
			For y = 0 To height - 1
				;If x = width - 2 Then
				;	If y > 0 And y < height - 1 Then
				;		PokeInt(tilesNew, (x + y * (width-1)) * 4, 100)
				;	Else
				;		PokeInt(tilesNew, (x + y * (width-1)) * 4, 200)
				;	EndIf
				;Else
				If x = width - 1 Then
					PokeInt(tilesNew, (x - 1 + y * (width-1)) * 4, 200)
				Else
					PokeInt(tilesNew, (x + y * (width-1)) * 4, PeekInt(tiles, (x + y * (width)) * 4))
				EndIf
			Next
		Next
		
		For x = 0 To width - 2
			For y = 0 To height - 1
				PokeInt(objectsNew, (x + y * (width-1)) * 4, PeekInt(objects, (x + y * (width)) * 4))
			Next
		Next
		FreeBank(tiles)
		tiles = tilesNew
		FreeBank(objects)
		objects = objectsNew
		width=width-1
		
		If cameraX + 13 > width - 1 Then
			cameraX = width - 14
		EndIf
	EndIf
	
	;down-
	;If MouseX() >= 677 And MouseX() < 709 And MouseY() >= 44 And MouseY() < 76 And width > 14 Then
	If MouseX() >= 741 And MouseX() < 773 And MouseY() >= 44 And MouseY() < 76 And height > 14 Then
		tilesNew = CreateBank(width*(height-1)*4)
		objectsNew = CreateBank(width*(height-1)*4)
		
		For x = 0 To width - 1
			For y = 0 To height - 2
				;If y = height - 2 Then
				;	If x > 0 And x < width - 1 Then
				;		PokeInt(tilesNew, (x + y * (width)) * 4, 100)
				;	Else
				;		PokeInt(tilesNew, (x + y * (width)) * 4, 200)
				;	EndIf
				;Else
				If y = height - 2 Then
					PokeInt(tilesNew, (x + y * (width)) * 4, 200)
				Else
					PokeInt(tilesNew, (x + y * (width)) * 4, PeekInt(tiles, (x + y * (width)) * 4))
				EndIf
			Next
		Next
		
		For x = 0 To width - 1
			For y = 0 To height - 2
				PokeInt(objectsNew, (x + y * (width)) * 4, PeekInt(objects, (x + y * (width)) * 4))
			Next
		Next
		FreeBank(tiles)
		tiles = tilesNew
		FreeBank(objects)
		objects = objectsNew
		height=height-1
		
		If cameraY + 13 > height - 1 Then
			cameraY = height - 14
		EndIf
	EndIf
	
	;up-
	If MouseX() >= 677 And MouseX() < 709 And MouseY() >= 44 And MouseY() < 76 And height > 14 Then
		tilesNew = CreateBank(width*(height-1)*4)
		objectsNew = CreateBank(width*(height-1)*4)
		
		For x = 0 To width - 1
			For y = 0 To height - 2
				PokeInt(tilesNew, (x + (y) * (width)) * 4, PeekInt(tiles, (x + (y+1) * (width)) * 4))
				;If x = 0 Then
				;	If y > 0 And y < height - 1 Then
				;		PokeInt(tilesNew, (x - 1 + y * (width-1)) * 4, 100)
				;	Else
				;		PokeInt(tilesNew, (x - 1 + y * (width-1)) * 4, 200)
				;	EndIf
				;Else
				If y = 0 Then
					PokeInt(tilesNew, (x + (y) * (width)) * 4, 200)
				EndIf
			Next
		Next
		
		For x = 0 To width - 1
			For y = 0 To height - 2
				PokeInt(objectsNew, (x + (y) * (width)) * 4, PeekInt(objects, (x + y * (width)) * 4))
			Next
		Next
		FreeBank(tiles)
		tiles = tilesNew
		FreeBank(objects)
		objects = objectsNew
		height = height - 1
		
		If cameraY + 13 > height - 1 Then
			cameraY = height - 14
		EndIf
	EndIf
EndIf

If MouseDown(1) Then
	
	If MouseX() >= 48 And MouseX() < 492 And MouseY() >= 16 And MouseY() < 48 Then
		handleMoveUp()
		Delay(100)
	ElseIf MouseX() >= 48 And MouseX() < 492 And MouseY() >= 496 And MouseY() < 528 Then
		handleMoveDown()
		Delay(100)
	ElseIf MouseX() >= 16 And MouseX() < 48 And MouseY() >= 48 And MouseY() < 496 Then
		handleMoveLeft()
		Delay(100)
	ElseIf MouseX() >= 492 And MouseX() < 524 And MouseY() >= 48 And MouseY() < 496 Then
		handleMoveRight()
		Delay(100)
	EndIf
	
EndIf

If KeyDown(29) And KeyDown(64) Then
	specialUnlocked = True
EndIf

End Function

Function renderMain()
	
	DrawImage(maske, 0, 0)
	
	renderArrows()
	renderTiles()
	renderObjects()
	renderButtons()
	renderMusicTrack()
	renderObjectsPanel()
	renderTilesPanel()
	renderInfoPanel()
	renderStylePanel()
	
End Function

Function renderInfoPanel()
	;ref:info
	renderTextCenter(levelTitle, 670, 12)
	
	DrawImage(arrows, 565, 44, 3)
	
	renderText(width, 597, 48)
	
	DrawImage(arrows, 629, 44, 1)
	
	renderText("X", 661, 48)
	
	DrawImage(arrows, 677, 44, 3)
	
	renderText(height, 709, 48)
	
	DrawImage(arrows, 741, 44, 1)
End Function

Function renderStylePanel()
	If timer > 10 Then
		DrawImage(arrows, 544, 88, 3)
	Else
		DrawImage(arrows, 544, 88, 7)
	EndIf
	
	renderTextCenter("Time: " + timer, 670, 92)
	
	If timer < 900 Then
		DrawImage(arrows, 760, 88, 1)
	Else
		DrawImage(arrows, 760, 88, 5)
	EndIf
	
	renderTextCenter("Style: " + getTextureStyle(levelTexture), 670, 124)
	
	renderTextCenter("Bkg: " + getBackgroundStyle(levelBackground), 670, 156)
End Function

Function getTextureStyle$(id)
	Select id
		Case 0
			Return "Cave"
		Case 1
			Return "Sand"
		Case 2
			Return "Wood"
		Case 3
			Return "Purple"
		Case 4
			Return "Castle"
		Case 5
			Return "Jade"
		Case 6
			Return "Spooky"
		Case 7
			Return "Garden"
		Case 8
			Return "Aztec"
		Case 9
			Return "Custom"
		Default
			Return Str(id)
	End Select
End Function

Function getBackgroundStyle$(id)
	Select id
		Case 0
			Return "Sky"
		Case 1
			Return "Forest"
		Case 2
			Return "Walls"
		Case 3
			Return "Stars"
		Case 4
			Return "Flat"
		Case 5
			Return "Water"
		Case 6
			Return "Lava"
		Case 7
			Return "Warp"
		Case 8
			Return "City"
		Case 9
			Return "Rainbow"
		Case 10
			Return "Custom"
		Default
			Return Str(id)
	End Select
End Function

Function renderDebugMain()
	
	Text(0, 32, "Selected ID(" + sx + ", " + sy + "): " + selectedID)
	If selectObjects Then
		Text(0, 48, "Object Mode")
	Else
		Text(0, 48, "Tile Mode")
	EndIf
	
	If specialUnlocked Then
		Text(0, 64, "Special Unlocked")
	Else
		Text(0, 64, "Special Locked")
	EndIf
	
	catNum$ = (currentTileCategory+1)
	
	If currentTileCategory+1 < 10 Then
		catNum = 0 + catNum
	EndIf
	
	Text(0, 96, "Tile Category: " + catNum + " / " + numTileCategories)
	
	Text(0, 112, "TileID: " + tileHighlight + ", ObjectID:" + objectHighlight) ;160
	
	;renderText("Size: (" + width + ", " + height + ")", 0, 192)
	
	Text(0, 128, "Textures: (" + levelTexture + ", " + backgroundTexture + ")") ; 224
	
	Text(0, 160, "Level ID: " + Str(levelID))
End Function

Function renderArrows()
	
	;Top
	If cameraY > 0 Then
		For i = 0 To 13
			DrawImage(arrows, 32*i+48, 16, 0)
		Next
	Else
		For i = 0 To 13
			DrawImage(arrows, 32*i+48, 16, 4)
		Next
	EndIf
	
	;Left
	If cameraX > 0 Then
		For i = 0 To 13
			DrawImage(arrows, 16, 32*i+48, 3)
		Next
	Else
		For i = 0 To 13
			DrawImage(arrows, 16, 32*i+48, 7)
		Next
	EndIf
	
	;Bottom
	If cameraY + 13 < height - 1 Then
		For i = 0 To 13
			DrawImage(arrows, 32*i+48, 496, 2)
		Next
	Else
		For i = 0 To 13
			DrawImage(arrows, 32*i+48, 496, 6)
		Next
	EndIf
	
	;Right
	
	If cameraX + 13 < width - 1 Then
		For i = 0 To 13
			DrawImage(arrows, 496, 32*i+48, 1)
		Next
	Else
		For i = 0 To 13
			DrawImage(arrows, 496, 32*i+48, 5)
		Next
	EndIf
	
End Function

Function setMouseTile(x, y, cat, id)
	
	If MouseX() > 48 And MouseX() < 496 And MouseY() > 48 And MouseY() < 496 Then
		
		Local mX = MouseX() - 48
		Local mY = MouseY() - 48
		
		Local tX = mX / 32
		Local tY = mY / 32
		
		setTile(tX+cameraX, tY+cameraY, cat, id)
		
		
		
	EndIf
	
End Function

Function setMouseObject(x, y, id)
	
	If MouseX() > 48 And MouseX() < 496 And MouseY() > 48 And MouseY() < 496 Then
		
		Local mX = MouseX() - 48
		Local mY = MouseY() - 48
		
		Local oX = mX / 32
		Local oY = mY / 32
		
		setObject(oX+cameraX, oY+cameraY, id)
		
	EndIf
	
End Function

Function renderObjects()
	For i = cameraX To cameraX + 13
		For j = cameraY To cameraY + 13
			renderObject(i - cameraX, j - cameraY, getObject(i, j))
		Next
	Next
End Function

Function renderObject(x, y, id)

	;If id > 1 Then
	;	id = id + 2
	;EndIf
	;If id < 90 And id >= 0 Then
		DrawImage(objectsPanel, x*32+56, y*32+56, id)
	;Else
	;	DrawImage(objectsPanel, x*32+56, y*32+56, 89)
	;EndIf
End Function

Function renderTiles()
	For i = cameraX To cameraX + 13
		For j = cameraY To cameraY + 13
			renderTile(i - cameraX, j - cameraY, getTile(i, j))
		Next
	Next
End Function

Function renderTile(x, y, id)
	Select id
		Case 0
			DrawImage(tileEmpty, x*32+48, y*32+48, 0)
		Case 100
			DrawImage(tileFloor, x*32+48, y*32+48, 0)
		Case 101
			DrawImage(tileFloor, x*32+48, y*32+48, 1)
		Case 102
			DrawImage(tileFloor, x*32+48, y*32+48, 2)
		Case 103
			DrawImage(tileFloor, x*32+48, y*32+48, 3)
		Case 104
			DrawImage(tileFloor, x*32+48, y*32+48, 4)
		Case 200
			DrawImage(tileWall, x*32+48, y*32+48, 0)
		Case 201
			DrawImage(tileWall, x*32+48, y*32+48, 1)
		Case 202
			DrawImage(tileWall, x*32+48, y*32+48, 2)
		Case 203
			DrawImage(tileWall, x*32+48, y*32+48, 3)
		Case 300
			DrawImage(tileWater, x*32+48, y*32+48, 0)
		Case 400
			DrawImage(tileIce, x*32+48, y*32+48, 0)
		Case 401
			DrawImage(tileIce, x*32+48, y*32+48, 1)
		Case 402
			DrawImage(tileIce, x*32+48, y*32+48, 2)
		Case 403
			DrawImage(tileIce, x*32+48, y*32+48, 3)
		Case 404
			DrawImage(tileIce, x*32+48, y*32+48, 4)
		Case 500
			DrawImage(tileConveyor, x*32+48, y*32+48, 0)
		Case 501
			DrawImage(tileConveyor, x*32+48, y*32+48, 1)
		Case 502
			DrawImage(tileConveyor, x*32+48, y*32+48, 2)
		Case 503
			DrawImage(tileConveyor, x*32+48, y*32+48, 3)
		Case 504
			DrawImage(tileConveyor, x*32+48, y*32+48, 4)
		Case 505
			DrawImage(tileConveyor, x*32+48, y*32+48, 5)
		Case 506
			DrawImage(tileConveyor, x*32+48, y*32+48, 6)
		Case 507
			DrawImage(tileConveyor, x*32+48, y*32+48, 7)
		Case 508
			DrawImage(tileConveyor, x*32+48, y*32+48, 8)
		Case 509
			DrawImage(tileConveyor, x*32+48, y*32+48, 9)
		Case 510
			DrawImage(tileConveyor, x*32+48, y*32+48, 10)
		Case 511
			DrawImage(tileConveyor, x*32+48, y*32+48, 11)
		Case 512
			DrawImage(tileConveyor, x*32+48, y*32+48, 12)
		Case 513
			DrawImage(tileConveyor, x*32+48, y*32+48, 13)
		Case 514
			DrawImage(tileConveyor, x*32+48, y*32+48, 14)
		Case 515
			DrawImage(tileConveyor, x*32+48, y*32+48, 15)
		Case 600
			DrawImage(tileBridges, x*32+48, y*32+48, 0)
		Case 601
			DrawImage(tileBridges, x*32+48, y*32+48, 1)
		Case 602
			DrawImage(tileBridges, x*32+48, y*32+48, 2)
		Case 603
			DrawImage(tileBridges, x*32+48, y*32+48, 3)
		Case 604
			DrawImage(tileBridges, x*32+48, y*32+48, 4)
		Case 605
			DrawImage(tileBridges, x*32+48, y*32+48, 5)
		Case 606
			DrawImage(tileBridges, x*32+48, y*32+48, 6)
		Case 607
			DrawImage(tileBridges, x*32+48, y*32+48, 7)
		Case 608
			DrawImage(tileBridges, x*32+48, y*32+48, 8)
		Case 700
			DrawImage(tileElectro, x*32+48, y*32+48, 0)
		Case 701
			DrawImage(tileElectro, x*32+48, y*32+48, 1)
		Case 702
			DrawImage(tileElectro, x*32+48, y*32+48, 2)
		Case 703
			DrawImage(tileElectro, x*32+48, y*32+48, 3)
		Case 800
			DrawImage(tileCannons, x*32+48, y*32+48, 0)
		Case 801
			DrawImage(tileCannons, x*32+48, y*32+48, 1)
		Case 802
			DrawImage(tileCannons, x*32+48, y*32+48, 2)
		Case 803
			DrawImage(tileCannons, x*32+48, y*32+48, 3)
		Case 804
			DrawImage(tileCannons, x*32+48, y*32+48, 4)
		Case 805
			DrawImage(tileCannons, x*32+48, y*32+48, 5)
		Case 806
			DrawImage(tileCannons, x*32+48, y*32+48, 6)
		Case 807
			DrawImage(tileCannons, x*32+48, y*32+48, 7)
		Case 808
			DrawImage(tileCannons, x*32+48, y*32+48, 8)
		Case 809
			DrawImage(tileCannons, x*32+48, y*32+48, 9)
		Case 810
			DrawImage(tileCannons, x*32+48, y*32+48, 10)
		Case 811
			DrawImage(tileCannons, x*32+48, y*32+48, 11)
		Case 812
			DrawImage(tileCannons, x*32+48, y*32+48, 12)
		Case 813
			DrawImage(tileCannons, x*32+48, y*32+48, 13)
		Case 814
			DrawImage(tileCannons, x*32+48, y*32+48, 14)
		Case 815
			DrawImage(tileCannons, x*32+48, y*32+48, 15)
		Case 900
			DrawImage(tileLava, x*32+48, y*32+48, 0)
		Case 1000
			DrawImage(tileGates, x*32+48, y*32+48, 0)
		Case 1001
			DrawImage(tileGates, x*32+48, y*32+48, 1)
		Case 1002
			DrawImage(tileGates, x*32+48, y*32+48, 2)
		Case 1003
			DrawImage(tileGates, x*32+48, y*32+48, 3)
		Case 1004
			DrawImage(tileGates, x*32+48, y*32+48, 4)
		Case 1005
			DrawImage(tileGates, x*32+48, y*32+48, 5)
		Case 1006
			DrawImage(tileGates, x*32+48, y*32+48, 6)
		Case 1007
			DrawImage(tileGates, x*32+48, y*32+48, 7)
		Case 1008
			DrawImage(tileGates, x*32+48, y*32+48, 8)
		Case 1009
			DrawImage(tileGates, x*32+48, y*32+48, 9)
		Case 1010
			DrawImage(tileGates, x*32+48, y*32+48, 10)
		Case 1011
			DrawImage(tileGates, x*32+48, y*32+48, 11)
		Case 1012
			DrawImage(tileGates, x*32+48, y*32+48, 12)
		Case 1013
			DrawImage(tileGates, x*32+48, y*32+48, 13)
		Case 1014
			DrawImage(tileGates, x*32+48, y*32+48, 14)
		Case 1015
			DrawImage(tileGates, x*32+48, y*32+48, 15)
		Case 1016
			DrawImage(tileGates, x*32+48, y*32+48, 16)
		Case 1017
			DrawImage(tileGates, x*32+48, y*32+48, 17)
		Case 1100
			DrawImage(tileButtons, x*32+48, y*32+48, 0)
		Case 1101
			DrawImage(tileButtons, x*32+48, y*32+48, 1)
		Case 1102
			DrawImage(tileButtons, x*32+48, y*32+48, 2)
		Case 1103
			DrawImage(tileButtons, x*32+48, y*32+48, 3)
		Case 1104
			DrawImage(tileButtons, x*32+48, y*32+48, 4)
		Case 1105
			DrawImage(tileButtons, x*32+48, y*32+48, 5)
		Case 1106
			DrawImage(tileButtons, x*32+48, y*32+48, 6)
		Case 1107
			DrawImage(tileButtons, x*32+48, y*32+48, 7)
		Case 1108
			DrawImage(tileButtons, x*32+48, y*32+48, 8)
		Case 1109
			DrawImage(tileButtons, x*32+48, y*32+48, 9)
		Case 1110
			DrawImage(tileButtons, x*32+48, y*32+48, 10)
		Case 1111
			DrawImage(tileButtons, x*32+48, y*32+48, 11)
		Case 1112
			DrawImage(tileButtons, x*32+48, y*32+48, 12)
		Case 1113
			DrawImage(tileButtons, x*32+48, y*32+48, 13)
		Case 1114
			DrawImage(tileButtons, x*32+48, y*32+48, 14)
		Case 1115
			DrawImage(tileButtons, x*32+48, y*32+48, 15)
		Case 1116
			DrawImage(tileButtons, x*32+48, y*32+48, 16)
		Case 1117
			DrawImage(tileButtons, x*32+48, y*32+48, 17)
		Case 1118
			DrawImage(tileButtons, x*32+48, y*32+48, 18)
		Case 1119
			DrawImage(tileButtons, x*32+48, y*32+48, 19)
		Case 1120
			DrawImage(tileButtons, x*32+48, y*32+48, 20)
		Case 1121
			DrawImage(tileButtons, x*32+48, y*32+48, 21)
		Case 1122
			DrawImage(tileButtons, x*32+48, y*32+48, 22)
		Case 1123
			DrawImage(tileButtons, x*32+48, y*32+48, 23)
		Case 1124
			DrawImage(tileButtons, x*32+48, y*32+48, 24)
		Case 1125
			DrawImage(tileButtons, x*32+48, y*32+48, 25)
		Case 1126
			DrawImage(tileButtons, x*32+48, y*32+48, 26)
		Case 1200
			DrawImage(tileTeleporters, x*32+48, y*32+48, 0)
		Case 1201
			DrawImage(tileTeleporters, x*32+48, y*32+48, 1)
		Case 1202
			DrawImage(tileTeleporters, x*32+48, y*32+48, 2)
		Case 1203
			DrawImage(tileTeleporters, x*32+48, y*32+48, 3)
		Case 1204
			DrawImage(tileTeleporters, x*32+48, y*32+48, 4)
		Case 1205
			DrawImage(tileTeleporters, x*32+48, y*32+48, 5)
		Case 1206
			DrawImage(tileTeleporters, x*32+48, y*32+48, 6)
		Case 1207
			DrawImage(tileTeleporters, x*32+48, y*32+48, 7)
		Case 1300
			DrawImage(tileSigns, x*32+48, y*32+48, 0)
		Case 1301
			DrawImage(tileSigns, x*32+48, y*32+48, 1)
		Case 1302
			DrawImage(tileSigns, x*32+48, y*32+48, 2)
		Case 1303
			DrawImage(tileSigns, x*32+48, y*32+48, 3)
		Case 1304
			DrawImage(tileSigns, x*32+48, y*32+48, 4)
		Case 1305
			DrawImage(tileSigns, x*32+48, y*32+48, 5)
		Case 1306
			DrawImage(tileSigns, x*32+48, y*32+48, 6)
		Case 1307
			DrawImage(tileSigns, x*32+48, y*32+48, 7)
		Case 1308
			DrawImage(tileSigns, x*32+48, y*32+48, 8)
		Case 1309
			DrawImage(tileSigns, x*32+48, y*32+48, 9)
		Case 1310
			DrawImage(tileSigns, x*32+48, y*32+48, 10)
		Case 1311
			DrawImage(tileSigns, x*32+48, y*32+48, 11)
		Case 1312
			DrawImage(tileSigns, x*32+48, y*32+48, 12)
		Case 1313
			DrawImage(tileSigns, x*32+48, y*32+48, 13)
		Case 1314
			DrawImage(tileSigns, x*32+48, y*32+48, 14)
		Case 1315
			DrawImage(tileSigns, x*32+48, y*32+48, 15)
		Case 1316
			DrawImage(tileSigns, x*32+48, y*32+48, 16)
		Case 1317
			DrawImage(tileSigns, x*32+48, y*32+48, 17)
		Case 1318
			DrawImage(tileSigns, x*32+48, y*32+48, 18)
		Case 1319
			DrawImage(tileSigns, x*32+48, y*32+48, 19)
		Case 1400
			DrawImage(tileSpikes, x*32+48, y*32+48, 0)
		Case 1401
			DrawImage(tileSpikes, x*32+48, y*32+48, 1)
		Case 1402
			DrawImage(tileSpikes, x*32+48, y*32+48, 2)
		Case 1403
			DrawImage(tileSpikes, x*32+48, y*32+48, 3)
		Case 1500
			DrawImage(tileFakeWalls, x*32+48, y*32+48, 0)
		Case 1501
			DrawImage(tileFakeWalls, x*32+48, y*32+48, 1)
		Case 1502
			DrawImage(tileFakeWalls, x*32+48, y*32+48, 2)
		Case 1600
			DrawImage(tileBoxFactory, x*32+48, y*32+48, 0)
		Case 1601
			DrawImage(tileBoxFactory, x*32+48, y*32+48, 1)
		Case 1602
			DrawImage(tileBoxFactory, x*32+48, y*32+48, 2)
		Case 1603
			DrawImage(tileBoxFactory, x*32+48, y*32+48, 3)
		Case 1604
			DrawImage(tileBoxFactory, x*32+48, y*32+48, 4)
		Case 1605
			DrawImage(tileBoxFactory, x*32+48, y*32+48, 5)
		Case 1606
			DrawImage(tileBoxFactory, x*32+48, y*32+48, 6)
		Case 1607
			DrawImage(tileBoxFactory, x*32+48, y*32+48, 7)
		Case 1608
			DrawImage(tileBoxFactory, x*32+48, y*32+48, 8)
		Case 1609
			DrawImage(tileBoxFactory, x*32+48, y*32+48, 9)
		Case 1610
			DrawImage(tileBoxFactory, x*32+48, y*32+48, 10)
		Case 1611
			DrawImage(tileBoxFactory, x*32+48, y*32+48, 11)
		Case 1612
			DrawImage(tileBoxFactory, x*32+48, y*32+48, 12)
		Case 1700
			DrawImage(tileTransporters, x*32+48, y*32+48, 0)
		Case 1701
			DrawImage(tileTransporters, x*32+48, y*32+48, 1)
		Case 1702
			DrawImage(tileTransporters, x*32+48, y*32+48, 2)
		Case 1800
			DrawImage(tileTrampolines, x*32+48, y*32+48, 0)
		Case 1900
			DrawImage(tilePushCannons, x*32+48, y*32+48, 0)
		Case 1901
			DrawImage(tilePushCannons, x*32+48, y*32+48, 1)
		Case 1902
			DrawImage(tilePushCannons, x*32+48, y*32+48, 2)
		Case 1903
			DrawImage(tilePushCannons, x*32+48, y*32+48, 3)
		Case 1904
			DrawImage(tilePushCannons, x*32+48, y*32+48, 4)
		Case 1905
			DrawImage(tilePushCannons, x*32+48, y*32+48, 5)
		Case 1906
			DrawImage(tilePushCannons, x*32+48, y*32+48, 6)
		Case 1907
			DrawImage(tilePushCannons, x*32+48, y*32+48, 7)
		Case 1908
			DrawImage(tilePushCannons, x*32+48, y*32+48, 8)
		Case 1909
			DrawImage(tilePushCannons, x*32+48, y*32+48, 9)
		Case 1910
			DrawImage(tilePushCannons, x*32+48, y*32+48, 10)
		Case 1911
			DrawImage(tilePushCannons, x*32+48, y*32+48, 11)
		Case 1912
			DrawImage(tilePushCannons, x*32+48, y*32+48, 12)
		Case 1913
			DrawImage(tilePushCannons, x*32+48, y*32+48, 13)
		Case 1914
			DrawImage(tilePushCannons, x*32+48, y*32+48, 14)
		Case 1915
			DrawImage(tilePushCannons, x*32+48, y*32+48, 15)
		Case 2000
			DrawImage(tileStickyCubes, x*32+48, y*32+48, 0)
		Case 2100
			DrawImage(tileLinkSpheres, x*32+48, y*32+48, 0)
		Case 2101
			DrawImage(tileLinkSpheres, x*32+48, y*32+48, 1)
		Case 2102
			DrawImage(tileLinkSpheres, x*32+48, y*32+48, 2)
		Case 2103
			DrawImage(tileLinkSpheres, x*32+48, y*32+48, 3)
		Case 2200
			DrawImage(tileWarpGates, x*32+48, y*32+48, 0)
		Case 2201
			DrawImage(tileWarpGates, x*32+48, y*32+48, 1)
		Case 2202
			DrawImage(tileWarpGates, x*32+48, y*32+48, 2)
		Case 2203
			DrawImage(tileWarpGates, x*32+48, y*32+48, 3)
		Case 2204
			DrawImage(tileWarpGates, x*32+48, y*32+48, 4)
		Case 2205
			DrawImage(tileWarpGates, x*32+48, y*32+48, 5)
		Case 2206
			DrawImage(tileWarpGates, x*32+48, y*32+48, 6)
		Case 2207
			DrawImage(tileWarpGates, x*32+48, y*32+48, 7)
		Case 2208
			DrawImage(tileWarpGates, x*32+48, y*32+48, 8)
		Case 2209
			DrawImage(tileWarpGates, x*32+48, y*32+48, 9)
		Case 2210
			DrawImage(tileWarpGates, x*32+48, y*32+48, 10)
		Case 2211
			DrawImage(tileWarpGates, x*32+48, y*32+48, 11)
		Case 2212
			DrawImage(tileWarpGates, x*32+48, y*32+48, 12)
		Case 2300
			DrawImage(tile3DView, x*32+48, y*32+48, 0)
		Case 2400
			DrawImage(tileShadowStinks, x*32+48, y*32+48, 0)
		Case 2401
			DrawImage(tileShadowStinks, x*32+48, y*32+48, 1)
		Case 2402
			DrawImage(tileShadowStinks, x*32+48, y*32+48, 2)
		Case 2403
			DrawImage(tileShadowStinks, x*32+48, y*32+48, 3)
		Case 2404
			DrawImage(tileShadowStinks, x*32+48, y*32+48, 4)
		Case 2405
			DrawImage(tileShadowStinks, x*32+48, y*32+48, 5)
		Case 2406
			DrawImage(tileShadowStinks, x*32+48, y*32+48, 6)
		Case 2407
			DrawImage(tileShadowStinks, x*32+48, y*32+48, 7)
	End Select
End Function

Function renderButtons()
	renderText("Load", 512, 558)
	renderText("Save", 632, 558)
	renderText("Brdr", 32, 558)
	renderText("Wipe", 152, 558)
	renderText("Blck", 272, 558)
End Function

Function renderMusicTrack()
	renderText("Music Track: " + Str(levelMusic), 560, 506)
End Function

Function renderObjectsPanel()
	renderText("Objects", 610, 368)
	
	Local i = 0
	
	;548, 392
	
	For y = 0 To 5
		For x = 0 To 14
			If (Not specialUnlocked) And (i >= 88) Then
				Exit
			EndIf
			
			DrawImage(objectsPanel, x*16+548, y*16+392, i)
			i=i+1
		Next
	Next
	
	If selectObjects Then
		Color 255, 255, 0
		Rect(getSelectedObjectX()*16+548,getSelectedObjectY()*16+392, 16, 16, 0)
		
		Color 0, 0, 0
		Rect(getSelectedObjectX()*16+549,getSelectedObjectY()*16+393, 14, 14, 0)
	EndIf
End Function

Function getSelectedObjectX()
	Return selectedID - getSelectedObjectY() * 15
End Function

Function getSelectedObjectY()
	Return selectedID / 15
End Function

Function handleCloseClick()
	renderState = 1
	closeClicked = True
	confirm("EXIT EDITOR  (did you remember to save)?")
End Function

Function doEditorClose()
	resetConfirmResponse()
	resetLevel()
	state = 1
	Delay(100)
	useRenderState = False
End Function

Function handleLoadClick()
	;Load Level
	;loadLevel("CustomLevels/sample.lv6")
	loadLevelList()
End Function

Function handleSaveClick()
	;Save Level
	;saveLevel("CustomLevels/sample.lv6")
	
	nameEdit = levelName
	
	textID = 1
	
	state = 4
End Function

Function handleMoveUp()
	If cameraY > 0 Then
		cameraY = cameraY - 1
	EndIf
End Function

Function handleMoveLeft()
	If cameraX > 0 Then
		cameraX = cameraX - 1
	EndIf
End Function

Function handleMoveDown()
	If cameraY + 13 < height - 1 Then
		cameraY = cameraY + 1
	EndIf
End Function

Function handleMoveRight()
	If cameraX + 13 < width - 1 Then
		cameraX = cameraX + 1
	EndIf
End Function

Function renderTilesPanel()
	
	DrawImage(arrows, 544, 196, 3)
	DrawImage(arrows, 760, 196, 1)
	
	renderTextCenter(getCurrentTileCategory(), 670, 200)
	
	;556, 228
	
	Select currentTileCategory
		Case 0
			DrawImage(tileEmpty, 556, 228, 0)
		Case 1
			For i = 0 To 4
				DrawImage(tileFloor, i*32+556, 228, i)
			Next
		Case 2
			For i = 0 To 3
				DrawImage(tileWall, i*32+556, 228, i)
			Next
		Case 3
			DrawImage(tileWater, 556, 228, 0)
		Case 4
			For i = 0 To 4
				DrawImage(tileIce, i*32+556, 228, i)
			Next
		Case 5
			i = 0
			For y = 0 To 2
				For x = 0 To 6
					If i > 15 Then
						Exit
					EndIf
					DrawImage(tileConveyor, x*32+556, y*32+228, i)
					i=i+1
				Next
			Next
		Case 6
			i = 0
			For y = 0 To 1
				For x = 0 To 6
					If i > 8 Then
						Exit
					EndIf
					DrawImage(tileBridges, x*32+556, y*32+228, i)
					i=i+1
				Next
			Next
		Case 7
			For i = 0 To 3
				DrawImage(tileElectro, i*32+556, 228, i)
			Next
		Case 8
			i = 0
			For y = 0 To 2
				For x = 0 To 6
					If i > 15 Then
						Exit
					EndIf
					DrawImage(tileCannons, x*32+556, y*32+228, i)
					i=i+1
				Next
			Next
		Case 9
			DrawImage(tileLava, 556, 228, 0)
		Case 10
			i = 0
			For y = 0 To 2
				For x = 0 To 6
					If i > 17 Then
						Exit
					EndIf
					DrawImage(tileGates, x*32+556, y*32+228, i)
					i=i+1
				Next
			Next
		Case 11
			i = 0
			For y = 0 To 3
				For x = 0 To 6
					If i > 26 Then
						Exit
					EndIf
					DrawImage(tileButtons, x*32+556, y*32+228, i)
					i=i+1
				Next
			Next
		Case 12
			i = 0
			For y = 0 To 1
				For x = 0 To 6
					If i > 7 Then
						Exit
					EndIf
					DrawImage(tileTeleporters, x*32+556, y*32+228, i)
					i=i+1
				Next
			Next
		Case 13
			i = 0
			For y = 0 To 3
				For x = 0 To 6
					If i > 19 Then
						Exit
					EndIf
					DrawImage(tileSigns, x*32+556, y*32+228, i)
					i=i+1
				Next
			Next
		Case 14
			For i = 0 To 3
				DrawImage(tileSpikes, i*32+556, 228, i)
			Next
		Case 15
			For i = 0 To 2
				DrawImage(tileFakeWalls, i*32+556, 228, i)
			Next
		Case 16
			i = 0
			For y = 0 To 1
				For x = 0 To 6
					If i > 12 Then
						Exit
					EndIf
					DrawImage(tileBoxFactory, x*32+556, y*32+228, i)
					i=i+1
				Next
			Next
		Case 17
			For i = 0 To 2
				DrawImage(tileTransporters, i*32+556, 228, i)
			Next
		Case 18
			DrawImage(tileTrampolines, 556, 228, i)
		Case 19
			i = 0
			For y = 0 To 2
				For x = 0 To 6
					If i > 15 Then
						Exit
					EndIf
					DrawImage(tilePushCannons, x*32+556, y*32+228, i)
					i=i+1
				Next
			Next
		Case 20
			DrawImage(tileStickyCubes, 556, 228, i)
		Case 21
			For i = 0 To 3
				DrawImage(tileLinkSpheres, i*32+556, 228, i)
			Next
		Case 22
			i = 0
			For y = 0 To 1
				For x = 0 To 6
					If i > 12 Then
						Exit
					EndIf
					DrawImage(tileWarpGates, x*32+556, y*32+228, i)
					i=i+1
				Next
			Next
		Case 23
			DrawImage(tile3DView, 556, 228, i)
		Case 24
			i = 0
			For y = 0 To 1
				For x = 0 To 6
					If i > 7 Then
						Exit
					EndIf
					DrawImage(tileShadowStinks, x*32+556, y*32+228, i)
					i=i+1
				Next
			Next
	End Select
	
	If Not selectObjects Then
	
		Color 255, 255, 0
		Rect(getSelectedTileX()*32+556,getSelectedTileY()*32+228, 32, 32, 0)
		
		Color 0, 0, 0
		Rect(getSelectedTileX()*32+557,getSelectedTileY()*32+229, 30, 30, 0)
	
	EndIf
	
End Function

Function getSelectedTileX()
	Return selectedID - getSelectedTileY() * 7
End Function

Function getSelectedTileY()
	Return selectedID / 7
End Function

Function renameLevel()

	nameEdit = levelTitle
	
	textID = 0
	
	state = 4
	
End Function