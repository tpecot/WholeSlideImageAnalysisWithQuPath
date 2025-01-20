import qupath.ext.biop.cellpose.Cellpose2D

min_fiber_area = 0

// Specify the model name (cyto, nuc, cyto2, omni_bact or a path to your custom model)
def pathModel = 'cyto2'

def cellpose = Cellpose2D.builder( pathModel )
        .pixelSize( 0.65 )             // Resolution for detection in um
        .channels( 1 )	      // Select detection channel(s)
        .cellprobThreshold(0.0)        // Threshold for the mask detection, defaults to 0.0
        .flowThreshold(10)            // Threshold for the flows, defaults to 0.4 
        .diameter(100)                    // Median object diameter. Set to 0.0 for the `bact_omni` model or for automatic computation
        //.cellExpansion(5.0)              // Approximate cells based upon nucleus expansion
        //.cellConstrainScale(1.5)       // Constrain cell expansion using nucleus size
        .measureShape()                // Add shape measurements
        .measureIntensity()             // Add cell measurements (in all compartments)  
        .build()
        
// Get annotations
def annotations = QP.getAnnotationObjects()
// Get current image 
var imageData = getCurrentImageData()

// Run detection for the selected objects
if (annotations.isEmpty()) {
    QP.getLogger().error("No parent objects are selected!")
    return
}

cellpose.detectObjects(imageData, annotations)

def toDelete = getDetectionObjects().findAll {measurement(it, 'Area µm^2') < min_fiber_area}
removeObjects(toDelete, true)

println 'Done!'
