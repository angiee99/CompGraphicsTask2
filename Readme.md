# Polygon filling and cropping
This lab includes rasterizing lines and polygones with interactive GUI
that enables filling the polygons and cropping objects. 

## Functionality 

- Draw a line with mouse drag 
- Preview the dashed line with mouse drag
- Draw a strict (horizobtal, vertical, or diagonal) line with mouse drag and pressed key SHIFT
- Increase/decrease the dashed line step by pressing key UP/DOWN
- Add new vertex to polygon with a mouse click
- Delete the polygon vertex by clicking on it and pressing the D key
- Interactively add new vertex to polygon with mouse drag and 
Alt/Option key pressed. Release the mouse with Alt/Option key pressed to
finilize adding the vertex
---
- Fill the polygon with SeedFill by clicking on right mouse button 
(if with Alt/Option key pressed, it will use the border condition, 
else will evaluate only based on background color)
- Fill the main polygon with ScanLine by pressing F key 
- Fill the main polygon with pattern with SeedFill by pressing Control key 
and clicking on right mouse button
---
- Draw the rectangle by pressing Control key and mouse clicking on 2 points 
(these points can be upper left and lower right)
- Draw the ellipse limited by rectangle by pressing Control key and Shift key and 
drawing a limiting rectangle (mouse clicking on 2 points)
- Cut the main polygon with last drawn rectangle by pressing Enter 
- Add the vertex to last drawn rectangle by pressing Shift and clicking with right mouse button
- Clear the canvas by pressing key C

### Line rasterization algorithm
The main algorithm used for line rasterization is DDA 2, which was adapted for all the quadrants. 
The same algorithm was edited and used for dashed and strict line rasterization.

### Polygon filling algorithms 
- SeedFill algorithm with areas of 4 pixels (border and background color condition)
- ScanLine algorithm
- SeedFill4 is also used for filling with pattern 

### Cutting algorithm
- Sutherland-Hodgman algorithm
 