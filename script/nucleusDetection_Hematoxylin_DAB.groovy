import qupath.ext.stardist.StarDist2D
import qupath.lib.images.servers.ColorTransforms
import qupath.imagej.gui.ImageJMacroRunner

min_nuclei_area = 15

// Specify the model directory (you will need to change this!)
def pathInput = buildFilePath(PROJECT_BASE_DIR)
def pathModel = pathInput + "/script/stardist_model_1_channel.pb"

def stardist_segmentation = StarDist2D.builder(pathModel)
        .threshold(0.5)              // Prediction threshold
        .normalizePercentiles(1, 99.8) // Percentile normalization
        .pixelSize(0.5)              // Resolution for detection
        .channels(
            ColorTransforms.createColorDeconvolvedChannel(getCurrentImageData().getColorDeconvolutionStains(), 1),
            ColorTransforms.createColorDeconvolvedChannel(getCurrentImageData().getColorDeconvolutionStains(), 2)
            )
        .cellExpansion(5.0)          // Approximate cells based upon nucleus expansion
        .cellConstrainScale(1.5)     // Constrain cell expansion using nucleus size
        .measureShape()              // Add shape measurements
        .measureIntensity()          // Add cell measurements (in all compartments)
        .build()

        
def imageData = getCurrentImageData()
def hierarchy = imageData.getHierarchy()
def annotations = hierarchy.getAnnotationObjects()

// Run detection for the selected objects
stardist_segmentation.detectObjects(imageData, annotations)

def toDelete = getDetectionObjects().findAll {measurement(it, 'Nucleus: Area µm^2') < min_nuclei_area}
removeObjects(toDelete, true)


println 'Done!'
