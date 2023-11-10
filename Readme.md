# Basic Rasterization
This lab includes rasterizing lines and polygones with interactive GUI

## Functionality 

- Draw a line with mouse drag 
- Preview the dashed line with mouse drag
- Draw a strict (horizobtal, vertical, or diagonal) line with mouse drag and pressed key SHIFT
- Increase/decrease the dashed line step by pressing key UP/DOWN
- Add new vertex to polygon with a mouse click
- Delete the polygon vertex by clicking on it and pressing the D key
- Clear the canvas by pressing key C

### Line rasterization algorithm
The main algorithm used for line rasterization is DDA 2, which was adapted for all the quadrants. 
The same algorithm was edited and used for dashed and strict line rasterization.
