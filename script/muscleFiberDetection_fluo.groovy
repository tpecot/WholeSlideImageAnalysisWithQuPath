import qupath.ext.biop.cellpose.Cellpose2D

// Specify the model name (cyto, nuc, cyto2, omni_bact or a path to your custom model)
def pathModel = 'cyto2'

def cellpose = Cellpose2D.builder( pathModel )
        .pixelSize( 0.5 )             // Resolution for detection in um
        .channels( 3 )	      // Select detection channel(s)
        .normalizePercentilesGlobal(0.1, 99.8, 10) // Convenience global percentile normalization. arguments are percentileMin, percentileMax, dowsample.
        .cellprobThreshold(0.0)        // Threshold for the mask detection, defaults to 0.0
        .flowThreshold(0.4)            // Threshold for the flows, defaults to 0.4 
        .diameter(50)                    // Median object diameter. Set to 0.0 for the `bact_omni` model or for automatic computation
        //.cellExpansion(5.0)              // Approximate cells based upon nucleus expansion
        //.cellConstrainScale(1.5)       // Constrain cell expansion using nucleus size
        .measureShape()                // Add shape measurements
        .measureIntensity()             // Add cell measurements (in all compartments)  
        .build()
        
// Run detection for the selected objects
def imageData = getCurrentImageData()
def pathObjects = getSelectedObjects()
if (pathObjects.isEmpty()) {
    Dialogs.showErrorMessage("Cellpose", "Please select a parent object!")
    return
}
cellpose.detectObjects(imageData, pathObjects)
println 'Done!'