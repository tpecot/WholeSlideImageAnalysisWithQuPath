import qupath.ext.stardist.StarDist2D
import qupath.lib.scripting.QP

min_nuclei_area = 0

// Specify the model directory (you will need to change this!)
def inputPath = buildFilePath(PROJECT_BASE_DIR)
def modelPath = inputPath + "/script/stardist_model_1_channel.pb"

def stardist_segmentation = StarDist2D
        .builder(modelPath)
        .preprocess(                 // Apply normalization, calculating values across the whole image
            StarDist2D.imageNormalizationBuilder()
                .maxDimension(4096)    // Figure out how much to downsample large images to make sure the width & height are <= this value
                .percentiles(1, 99.8)  // Calculate image percentiles to use for normalization
                .build()
        	)
        .threshold(0.5)              // Prediction threshold
        .includeProbability(true)    // Include prediction probability as measurement
        .pixelSize(0.5)              // Resolution for detection
        .channels(
            ColorTransforms.createColorDeconvolvedChannel(getCurrentImageData().getColorDeconvolutionStains(), 1),
            ColorTransforms.createColorDeconvolvedChannel(getCurrentImageData().getColorDeconvolutionStains(), 2)
            )
        .measureShape()              // Add shape measurements
        .measureIntensity()          // Add cell measurements (in all compartments)
        .build()

        
// Get annotations
def annotations = QP.getAnnotationObjects()
// Get current image 
var imageData = getCurrentImageData()

// Run detection for annotations
if (annotations.isEmpty()) {
    QP.getLogger().error("No parent objects are selected!")
    return
}

// Run detection for the selected objects
stardist_segmentation.detectObjects(imageData, annotations)

def toDelete = getDetectionObjects().findAll {measurement(it, 'Area µm^2') < min_nuclei_area}
removeObjects(toDelete, true)


println 'Done!'
