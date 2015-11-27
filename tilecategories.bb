Global numTileCategories = 25
Global currentTileCategory = 2

Function getCurrentTileCategory$()
	Return getTileCategory(currentTileCategory)
End Function

Function getTileCategory$(id)
	Select id
		Case 0
			Return "Empty"
		Case 1
			Return "Floors"
		Case 2
			Return "Walls"
		Case 3
			Return "Water"
		Case 4
			Return "Ice"
		Case 5
			Return "Conveyor"
		Case 6
			Return "Bridges"
		Case 7
			Return "Electro"
		Case 8
			Return "Cannons"
		Case 9
			Return "Lava"
		Case 10
			Return "Gates"
		Case 11
			Return "Buttons"
		Case 12
			Return "Teleporters"
		Case 13
			Return "Signs"
		Case 14
			Return "Spikes"
		Case 15
			Return "Fake Walls"
		Case 16
			Return "Box Factory"
		Case 17
			Return "Transporters"
		Case 18
			Return "Trampolines"
		Case 19
			Return "Push Cannons"
		Case 20
			Return "Sticky Cubes"
		Case 21
			Return "Link Spheres"
		Case 22
			Return "Warp Gates"
		Case 23
			Return "3D View"
		Case 24
			Return "ShadowStinks"
	End Select
End Function

Function getCurrentCategoryTileCount()
	Return getCategoryTileCount(currentTileCategory)
End Function

Function getCategoryTileCount(id)
	Select id
		Case 0
			Return 1
		Case 1
			Return 5
		Case 2
			Return 4
		Case 3
			Return 1
		Case 4
			Return 5
		Case 5
			Return 16
		Case 6
			Return 9
		Case 7
			Return 4
		Case 8
			Return 16
		Case 9
			Return 1
		Case 10
			Return 16
		Case 11
			Return 27
		Case 12
			Return 8
		Case 13
			Return 20
		Case 14
			Return 4
		Case 15
			Return 3
		Case 16
			Return 13
		Case 17
			Return 3
		Case 18
			Return 1
		Case 19
			Return 16
		Case 20
			Return 1
		Case 21
			Return 4
		Case 22
			Return 13
		Case 23
			Return 1
		Case 24
			Return 8
	End Select
End Function